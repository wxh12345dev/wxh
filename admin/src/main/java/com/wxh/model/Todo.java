package com.wxh.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wxh.condition.BaseCondition;
import com.wxh.util.MyQueryAnnotation;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Todo extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    
    @MyQueryAnnotation("like")
    @TableField("CONTENT")
    private String content;

    @TableField("FINISH")
    @MyQueryAnnotation("eq")
    private Integer finish;

    @TableField("CREATED")
    private Date created;


}
