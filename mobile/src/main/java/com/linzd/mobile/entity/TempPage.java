package com.linzd.mobile.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 * @author 林哲达
 * @param <T>
 * Created by linzd on 2016/11/20.
 */
@Data
@ApiModel(value="TempPage",description="分页对象TempPage")
public class TempPage<T> implements Serializable {
	
	private static final long serialVersionUID = 8397451586805905035L;
	/**
	 * 总数
	 */
	private int total;
	/**
	 * 要显示的数据
	 */
	private List<T> rows;
	/**
	 * 当前页数
	 */
	private int pageNo;
	/**
	 * 一页多少条数据
	 */
	private int pageSize;


	
	
}
