package com.project.demo.entity;


import java.sql.Timestamp;
import java.io.Serializable;

import lombok.*;

import javax.persistence.*;


/**
 * 订单：(Order)表实体类
 *
 * @author xxx
 *@since 202X-XX-XX
 */
@Setter
@Getter
@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = -79265503181582181L;

    /**
     * 订单ID：
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单号：
     */

    @Basic
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 商品id：[0,8388607]
     */

    @Basic
    @Column(name = "goods_id")
    private Double goodsId;

    /**
     * 商品标题：
     */

    @Basic
    @Column(name = "title")
    private String title;

    /**
     * 商品图片：
     */

    @Basic
    @Column(name = "img")
    private String img;

    /**
     * 价格：
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
     * 数量：
     */

    @Basic
    @Column(name = "num")
    private Integer num;

    /**
     * 总价：
     */

    @Basic
    @Column(name = "price_count")
    private Double priceCount;

    /**
     * 规格：
     */

    @Basic
    @Column(name = "norms")
    private String norms;

    /**
     * 商品分类：
     */

    @Basic
    @Column(name = "type")
    private String type;

    /**
     * 联系人姓名：
     */

    @Basic
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 联系人邮箱：
     */

    @Basic
    @Column(name = "contact_email")
    private String contactEmail;

    /**
     * 联系人手机：
     */

    @Basic
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 收件地址：
     */

    @Basic
    @Column(name = "contact_address")
    private String contactAddress;

    /**
     * 邮政编码：
     */

    @Basic
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * 买家ID：
     */

    @Basic
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 商家ID：
     */

    @Basic
    @Column(name = "merchant_id")
    private Integer merchantId;

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
     * 描述：[0,255]用于产品规格描述
     */

    @Basic
    @Column(name = "description")
    private String description;

    /**
     * 订单状态：待付款，待发货，待签收，已签收，待退款，已退款，已拒绝，已完成
     */

    @Basic
    @Column(name = "state")
    private String state;

}

