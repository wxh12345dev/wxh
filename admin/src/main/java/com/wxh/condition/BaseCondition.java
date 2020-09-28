package com.wxh.condition;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxh
 * @since 2019-09-18
 */
@Setter
@Getter
public class BaseCondition implements Serializable {

	private static final long serialVersionUID = 1L;
	@TableField(exist = false)
	private Long currentPage;
	@TableField(exist = false)
	private Long pageSize;
	
}
