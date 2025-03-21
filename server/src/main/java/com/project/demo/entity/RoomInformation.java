package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 房间信息：(RoomInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RoomInformation")
public class RoomInformation implements Serializable {

    // RoomInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_information_id")
    private Integer room_information_id;

    // 房间名称
    @Basic
    private String room_name;
    // 房间房型
    @Basic
    private String room_type;
    // 房间价格
    @Basic
    private Integer room_price;
    // 房间位置
    @Basic
    private String room_location;
    // 房间图片
    @Basic
    private String room_image;
    // 房间介绍
    @Basic
    private String room_introduction;

    // 点击数
    @Basic
    private Integer hits;

    // 点赞数
    @Basic
    private Integer praise_len;












    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
