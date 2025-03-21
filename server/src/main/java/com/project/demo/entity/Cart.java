package com.project.demo.entity;


import java.sql.Timestamp;
import java.io.Serializable;

import lombok.*;

import javax.persistence.*;


/**
 * 购物车：(Cart)表实体类
 *
 * @author xxx
 *@since 202X-XX-XX 
 */
@Setter
@Getter
@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = 978169757869945391L;

    /**
     * 购物车ID：
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer cartId;

    /**
     * 标题：
     */

    @Basic
    @Column(name = "title")
    private String title;

    /**
     * 图片：
     */

    @Basic
    @Column(name = "img")
    private String img;

    /**
     * 用户ID：
     */

    @Basic
    @Column(name = "user_id")
    private Integer userId;

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
     * 状态：使用中，已失效
     */

    @Basic
    @Column(name = "state")
    private Integer state;

    /**
     * 单价：
     */

    @Basic
    @Column(name = "price")
    private Double price;

    /**
     * 原价：
     */

    @Basic
    @Column(name = "price_ago")
    private Double priceAgo;

    /**
     * 总价：
     */

    @Basic
    @Column(name = "price_count")
    private Double priceCount;

    /**
     * 数量：
     */

    @Basic
    @Column(name = "num")
    private Integer num;

    /**
     * 商品id：[0,8388607]
     */

    @Basic
    @Column(name = "goods_id")
    private Double goodsId;

    /**
     * 商品分类：
     */

    @Basic
    @Column(name = "type")
    private String type;

    /**
     * 描述：[0,255]用于产品规格描述
     */

    @Basic
    @Column(name = "description")
    private String description;

}

