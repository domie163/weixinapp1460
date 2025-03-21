package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 食物订单：(FoodOrders)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "FoodOrders")
public class FoodOrders implements Serializable {

    // FoodOrders编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_orders_id")
    private Integer food_orders_id;

    // 订单编号
    @Basic
    private String order_number;
    // 顾客用户
    @Basic
    private Integer customer_users;
    // 食物名称
    @Basic
    private String food_name;
    // 购买数量
    @Basic
    private Integer purchase_quantity;
    // 订单总价
    @Basic
    private String total_order_price;
    // 制作状态
    @Basic
    private String production_status;
    // 订单备注
    @Basic
    private String order_remarks;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
