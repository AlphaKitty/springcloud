package com.zyl.cloud.base.util.common;

import java.util.List;

/**
 * 分页工具类
 *
 * @author 张友谅
 * @since 0:36 2020/12/18
 **/
public class PageUtil {

	public static <T> PageDTO<T> page(Integer pageNum, Integer pageSize, Integer total, List<T> data) {
		Integer unboxTotal = total == null ? pageSize : total;
		PageDTO<T> pageDTO = new PageDTO<>();
		pageDTO.setCurrent(pageNum);
		pageDTO.setSize(pageSize);
		pageDTO.setTotal(unboxTotal);
		pageDTO.setPages(unboxTotal % pageSize == 0 ? unboxTotal / pageSize : unboxTotal / pageSize + 1);
		pageDTO.setRecords(data);
		return pageDTO;
	}

}
