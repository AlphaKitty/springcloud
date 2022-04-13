package com.zyl.cloud.base.util.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListBinder<A, B, C> {

	private final List<A> aList;
	private final List<B> bList;
	private final String aRelevance;
	private final String bRelevance;
	private final Class<C> cClass;

	/**
	 * 按公共字段值相同组合两个集合
	 *
	 * @param aList      集合A
	 * @param bList      集合B
	 * @param aRelevance 实体A关联字段名
	 * @param bRelevance 实体B关联字段名
	 * @param cClass     返回集合元素类型
	 */
	public ListBinder(List<A> aList, List<B> bList, String aRelevance, String bRelevance, Class<C> cClass) {
		this.aList = aList;
		this.bList = bList;
		this.aRelevance = aRelevance;
		this.bRelevance = bRelevance;
		this.cClass = cClass;
	}

	/**
	 * 按公共字段值相同组合两个集合(字段同名)
	 *
	 * @param aList     集合A
	 * @param bList     集合B
	 * @param relevance 实体AB同名关联字段名
	 * @param cClass    返回集合元素类型
	 */
	public ListBinder(List<A> aList, List<B> bList, String relevance, Class<C> cClass) {
		this(aList, bList, relevance, relevance, cClass);
	}

	/**
	 * List相同字段赋值 代替遍历set
	 *
	 * @param originList  原始集合
	 * @param targetClass 目标集合类型
	 */
	public ListBinder(List<A> originList, Class<C> targetClass) {
		this(originList, null, null, null, targetClass);
	}

	// 使用示例
	public static void main(String[] args) {
		AC ac = new AC();
		AC ac1 = new AC();
		BC bc = new BC();

		// 模拟ac和bc的id相同 两个实体会组合
		ac.setId("aaa");
		ac.setName("aaa");
		ac.setSex("aaa");
		ac1.setId("aaaa");
		ac1.setName("aaa");
		ac1.setSex("aaa");

		bc.setId("aaa");
		bc.setSchool("bbb");
		bc.setPic("bbb");

		List<AC> acs = new ArrayList<>();
		List<BC> bcs = new ArrayList<>();
		// acs.add(ac);
		// acs.add(ac1);
		// bcs.add(bc);

		List<CC> ccs = new ListBinder<>(acs, bcs, "id", CC.class).bind();
		// System.out.println(ccs);
	}

	/**
	 * 遍历两个List 按关联匹配组合
	 * 注意:
	 * 1.效果类似于数据库中的内连接 只取交集
	 * 2.如果aList和bList中有同名不同值的字段 优先取aList的值 应尽量避免同名不同义的字段
	 *
	 * @return 整合后的集合
	 */
	public List<C> bind() {
		// 空List判断
		if (this.aList == null || this.bList == null) {
			return new ArrayList<>();
		}
		return aList.stream()
		            // 遍历A集合 对每个a元素组合对应字段相等的b元素
		            .map(aEle -> bList.stream()
		                              // 过滤出B集合中对应字段和a相等的b元素
		                              .filter(bEle -> {
			                              try {
				                              Field aDeclaredField = aEle.getClass().getDeclaredField(aRelevance);
				                              Field bDeclaredField = bEle.getClass().getDeclaredField(bRelevance);
				                              aDeclaredField.setAccessible(true);
				                              bDeclaredField.setAccessible(true);
				                              return Objects.equals(aDeclaredField.get(aEle), bDeclaredField.get(bEle));
			                              } catch (NoSuchFieldException | IllegalAccessException e) {
				                              // System.out.println(e.toString());
				                              return false;
			                              }
		                              })
		                              .findFirst()
		                              // a和b组合到c 并返回c
		                              .map(bEle -> {
			                              try {
				                              C c = cClass.getDeclaredConstructor().newInstance();
				                              for (Field field : cClass.getDeclaredFields()) {
					                              String fieldName = field.getName();
					                              field.setAccessible(true);
					                              // 从a或b实体中取出同名字段值并赋值 如果a和b中有同名不同值的字段 优先取a
					                              try {
						                              Field aDeclaredField = aEle.getClass().getDeclaredField(fieldName);
						                              aDeclaredField.setAccessible(true);
						                              Object value = aDeclaredField.get(aEle);
						                              if (null != value) {
							                              field.set(c, value);
						                              } else {
							                              Field bDeclaredField = bEle.getClass().getDeclaredField(fieldName);
							                              bDeclaredField.setAccessible(true);
							                              field.set(c, bDeclaredField.get(bEle));
						                              }
					                              } catch (NoSuchFieldException e) {
						                              try {
							                              Field declaredField = bEle.getClass().getDeclaredField(fieldName);
							                              declaredField.setAccessible(true);
							                              field.set(c, declaredField.get(bEle));
						                              } catch (NoSuchFieldException noSuchFieldException) {
							                              // noSuchFieldException.printStackTrace();
							                              // System.out.println(noSuchFieldException.toString());
						                              }
					                              }
				                              }
				                              return c;
			                              } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				                              // System.out.println(e.toString());
			                              }
			                              return null;
		                              })
		                              .orElse(null))
		            .filter(Objects::nonNull)
		            .collect(Collectors.toList());
	}

	/**
	 * List相同字段赋值 代替遍历set
	 *
	 * @return cList
	 */
	public List<C> assign() {
		try {
			List<C> cs = new ArrayList<>();
			for (A a : aList) {
				C c = cClass.getDeclaredConstructor().newInstance();
				for (Field cField : cClass.getDeclaredFields()) {
					cField.setAccessible(true);
					if (Arrays.stream(a.getClass().getDeclaredFields()).filter(field -> field.getName().equals(cField.getName())).count() == 0) {
						continue;
					}
					Field aDeclaredField = a.getClass().getDeclaredField(cField.getName());
					aDeclaredField.setAccessible(true);
					Object value = aDeclaredField.get(a);
					cField.set(c, value);
				}
				cs.add(c);
			}
			return cs;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
			// System.out.println(e.toString());
			return null;
		}
	}

	private static class AC {

		private String id;
		private String name;
		private String sex;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

	}

	private static class BC {

		private String id;
		private String school;
		private String pic;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSchool() {
			return school;
		}

		public void setSchool(String school) {
			this.school = school;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

	}

	private static class CC {

		private String id;
		private String name;
		private String school;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSchool() {
			return school;
		}

		public void setSchool(String school) {
			this.school = school;
		}

	}

}
