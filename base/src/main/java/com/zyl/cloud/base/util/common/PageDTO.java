package com.zyl.cloud.base.util.common;

import lombok.Data;

import java.util.List;

/**
 * 自定义分页实体
 *
 * @author 张友谅
 * @since 0:37 2020/12/18
 **/
@Data
public class PageDTO<T> {

	// 当前页
	private Integer current;
	// 页大小
	private Integer size;
	// 总页数
	private Integer pages;
	// 总个数
	private Integer total;
	// 数据集合
	private List<T> records;

}
