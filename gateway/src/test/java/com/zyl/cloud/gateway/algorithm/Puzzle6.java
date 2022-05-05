package com.zyl.cloud.gateway.algorithm;

/**
 * Z 字形变换
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * <p>
 * PAYPALISHIRING
 * =>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * =>PAHNAPLSIIGYIR
 */
public class Puzzle6 {

	public static void main(String[] args) {
		Puzzle6 puzzle6 = new Puzzle6();
		System.out.println(puzzle6.myVersion("1234567890", 3));
	}

	/**
	 * 执行用时： 1 ms, 在所有 Java 提交中击败了100.00%的用户
	 * 内存消耗： 41.2 MB, 在所有 Java 提交中击败了93.64%的用户
	 * 通过测试用例： 1157 / 1157
	 */
	public String myVersion(String s, int numRows) {

		// TODO: 2022/4/27 zylTodo 错误1: 忘了考虑长度小于行数的情况
		// TODO: 2022/4/27 zylTodo 错误2: 忘了考虑行数为1的情况 导致后面除数0异常
		if (s.length() <= numRows || numRows == 1) {
			return s;
		}

		char[] chars = s.toCharArray();
		// 在长度已知的前提下字符数组比StringBuilder内存小时间快
		char[] resultChars = new char[chars.length];
		// StringBuilder result = new StringBuilder();

		// 当前字符下标
		int index;
		// 结果字符数组递增下标
		int p = 0;

		// 行遍历
		for (int i = 0; i < numRows; i++) {
			// result.append(chars[i]);
			resultChars[p++] = chars[i];
			index = i;
			// 不换行
			while (true) {
				// 关键就是这句 下标/(总行数-1)的结果如果为偶数则下行递增(可以看成Z的横) 如果为奇数则上行递增(可以看成Z的斜) 也可以理解为每次遇到Z的拐点切换下标的生成公式
				index = index / (numRows - 1) % 2 == 0 ? 2 * (numRows - i - 1) + index : 2 * i + index;
				if (index < chars.length) {
					// result.append(chars[index]);
					resultChars[p++] = chars[index];
				} else {
					break;
				}
			}
		}
		// return result.toString();
		return new String(resultChars);
	}

}
