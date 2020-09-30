package com.wxh.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wxh.condition.BaseCondition;
import com.wxh.util.MyQueryAnnotation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxh
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Dairy extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @MyQueryAnnotation("like")
    @TableField("TITLE")
    private String title;

    @TableField("CONTENT")
    private String content;

    @TableField("URL")
    private String url;

    @TableField("CREATED")
    private Date created;
    
    @MyQueryAnnotation(value = "gt",field = "created")
	@TableField(exist = false)
	private Date startDate;
	@MyQueryAnnotation(value = "lt",field = "created")
	@TableField(exist = false)
	private Date endDate;
	
	@TableField(exist = false)
    private String urlFirst;

}
