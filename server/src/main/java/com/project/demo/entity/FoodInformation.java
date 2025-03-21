package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 食物信息：(FoodInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "FoodInformation")
public class FoodInformation implements Serializable {

    // FoodInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_information_id")
    private Integer food_information_id;

    // 备注信息
    @Basic
    private String remarks;













    /**
     * 标题：[0,125]用于产品和html的<title>标签中
     */

    @Basic
    @Column(name = "cart_title")
    private String cart_title;

    /**
     * 封面图：用于显示于产品列表页
     */

    @Basic
    @Column(name = "cart_img")
    private String cart_img;

    /**
     * 描述：[0,255]用于产品规格描述
     */

    @Basic
    @Column(name = "cart_description")
    private String cart_description;

    /**
     * 原价：[1]
     */

    @Basic
    @Column(name = "cart_price_ago")
    private Double cart_price_ago;

    /**
     * 卖价：[1]
     */

    @Basic
    @Column(name = "cart_price")
    private Double cart_price;

    /**
     * 商品库存
     */

    @Basic
    @Column(name = "cart_inventory")
    private Double cart_inventory;

    /**
     * 商品分类：
     */

    @Basic
    @Column(name = "cart_type")
    private String cart_type;

    /**
     * 正文：产品的主体内容
     */

    @Basic
    @Column(name = "cart_content")
    private String cart_content;

    /**
     * 主图1：
     */

    @Basic
    @Column(name = "cart_img_1")
    private String cart_img_1;

    /**
     * 主图2：
     */

    @Basic
    @Column(name = "cart_img_2")
    private String cart_img_2;

    /**
     * 主图3：
     */

    @Basic
    @Column(name = "cart_img_3")
    private String cart_img_3;

    /**
     * 主图4：
     */

    @Basic
    @Column(name = "cart_img_4")
    private String cart_img_4;

    /**
     * 主图5：
     */

    @Basic
    @Column(name = "cart_img_5")
    private String cart_img_5;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
