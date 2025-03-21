package com.project.demo.entity;


import java.sql.Timestamp;
import java.io.Serializable;

import lombok.*;

import javax.persistence.*;


/**
 * (GoodsType)表实体类
 *
 * @author xxx
 *@since 202X-XX-XX
 */
@Setter
@Getter
@Entity
public class GoodsType implements Serializable {

    private static final long serialVersionUID = 154798044277226279L;

    /**
     * 商品分类ID：
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 上级分类ID：[0,32767]
     */

    @Basic
    @Column(name = "father_id")
    private Integer fatherId;

    /**
     * 商品名称：
     */

    @Basic
    @Column(name = "name")
    private String name;

    /**
     * 描述：
     */

    @Basic
    @Column(name = "desc")
    private String desc;

    /**
     * 图标：
     */

    @Basic
    @Column(name = "icon")
    private String icon;

    /**
     * 来源表：
     */

    @Basic
    @Column(name = "source_table")
    private String sourceTable;

    /**
     * 来源字段：
     */

    @Basic
    @Column(name = "source_field")
    private String sourceField;

    /**
     * 创建时间：
     */

    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 更新时间：
     */

    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;

}

