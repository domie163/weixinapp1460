package com.project.demo.entity;


import java.sql.Timestamp;
import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import javax.persistence.*;


/**
 * 收货地址：(Address)表实体类
 *
 * @author xxx
 *@since 202X-XX-XX
 */
@Setter
@Getter
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = -43117653992759254L;

    /**
     * 收货地址：
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    /**
     * 姓名：
     */

    @Basic
    @Column(name = "name")
    private String name;

    /**
     * 手机：
     */

    @Basic
    @Column(name = "phone")
    private String phone;

    /**
     * 邮编：
     */

    @Basic
    @Column(name = "postcode")
    private String postcode;

    /**
     * 地址：
     */

    @Basic
    @Column(name = "address")
    private String address;

    /**
     * 用户ID：[0,8388607]用户获取其他与用户相关的数据
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
     * 默认判断
     */

    @Basic
    @Column(name = "default")
    @JSONField(name = "default")
    private Boolean defaults;

}

