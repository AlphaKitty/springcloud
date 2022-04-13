package com.zyl.cloud.base.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 *
 * @author 张代富
 * @since 2022-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role implements Serializable {

    /**
     * ID
     */
    // @TableId(value = "role_id")
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String describe;

    /**
     * 角色状态 1启用 0禁用
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date createTime;

}
