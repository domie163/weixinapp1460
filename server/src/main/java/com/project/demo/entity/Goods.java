package com.project.demo.entity;


import java.sql.Timestamp;
import java.io.Serializable;

import lombok.*;

import javax.persistence.*;


/**
 * 商品信息：(Goods)表实体类
 *
 * @author xxx
 *@since 202X-XX-XX
 */
@Setter
@Getter
@Entity
public class Goods implements Serializable {

    private static final long serialVersionUID = 352802334662900437L;

    /**
     * 产品id：[0,8388607]
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 标题：[0,125]用于产品和html的<title>标签中
     */

    @Basic
    @Column(name = "title")
    private String title;

    /**
     * 封面图：用于显示于产品列表页
     */

    @Basic
    @Column(name = "img")
    private String img;

    /**
     * 描述：[0,255]用于产品规格描述
     */

    @Basic
    @Column(name = "description")
    private String description;

    /**
     * 原价：[1]
     */

    @Basic
    @Column(name = "price_ago")
    private Double priceAgo;

    /**
     * 卖价：[1]
     */

    @Basic
    @Column(name = "price")
    private Double price;

    /**
     * 销量：[0,1000000000]
     */

    @Basic
    @Column(name = "sales")
    private Integer sales;

    /**
     * 商品库存
     */

    @Basic
    @Column(name = "inventory")
    private Double inventory;

    /**
     * 商品分类：
     */

    @Basic
    @Column(name = "type")
    private String type;

    /**
     * 点击量：[0,1000000000]访问这篇产品的人次
     */

    @Basic
    @Column(name = "hits")
    private Integer hits;

    /**
     * 正文：产品的主体内容
     */

    @Basic
    @Column(name = "content")
    private String content;

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

    /**
     * 主图1：
     */

    @Basic
    @Column(name = "img_1")
    private String img_1;

    /**
     * 主图2：
     */

    @Basic
    @Column(name = "img_2")
    private String img_2;

    /**
     * 主图3：
     */

    @Basic
    @Column(name = "img_3")
    private String img_3;

    /**
     * 主图4：
     */

    @Basic
    @Column(name = "img_4")
    private String img_4;

    /**
     * 主图5：
     */

    @Basic
    @Column(name = "img_5")
    private String img_5;

    /**
     * 自定义字段
     */

    @Basic
    @Column(name = "customize_field")
    private String customize_field;

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
     * 来源ID：
     */

    @Basic
    @Column(name = "source_id")
    private Integer sourceId;

    /**
     * 用户
     */

    @Basic
    @Column(name = "user_id")
    private Integer user_id;

}

