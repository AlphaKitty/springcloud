package com.zyl.cloud.base.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表
 *
 * @author 张代富
 * @since 2022-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Privilege implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 父权限id
     */
    private Long parentId;

    /**
     * 权限名
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 描述
     */
    private String describe;

    /**
     * 类型 0目录 1菜单 -1按钮
     */
    private Integer type;

    /**
     * 创建人id
     */
    private Long createUserId;

    /**
     * 更新人id
     */
    private Long updateUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 组件名
     */
    private String component;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 关联权限id
     */
    private Long relate;

}
