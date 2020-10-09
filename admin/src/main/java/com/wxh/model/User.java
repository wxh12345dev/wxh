package com.wxh.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wxh.condition.BaseCondition;
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
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseCondition {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("USERNAME")
    private String username;

    @TableField("PASSWORD")
    private String password;

    @TableField("CREATED")
    private Date created;

    @TableField("CODE")
    private String code;


}
