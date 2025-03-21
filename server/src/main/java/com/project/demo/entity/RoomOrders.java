package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 房间订单：(RoomOrders)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RoomOrders")
public class RoomOrders implements Serializable {

    // RoomOrders编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_orders_id")
    private Integer room_orders_id;

    // 订单编号
    @Basic
    private String order_number;
    // 房间名称
    @Basic
    private String room_name;
    // 房间房型
    @Basic
    private String room_type;
    // 房间价格
    @Basic
    private String room_price;
    // 顾客用户
    @Basic
    private Integer customer_users;
    // 用户姓名
    @Basic
    private String user_name;
    // 联系电话
    @Basic
    private String contact_number;
    // 入住时间
    @Basic
    private Timestamp check_in_time;
    // 预订天数
    @Basic
    private Integer booking_days;
    // 订单总价
    @Basic
    private String total_order_price;
    // 预订备注
    @Basic
    private String booking_remarks;
    // 预订状态
    @Basic
    private String reservation_status;
    // 预订回复
    @Basic
    private String reservation_response;




    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;










    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
