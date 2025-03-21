package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 房型管理：(RoomTypeManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RoomTypeManagement")
public class RoomTypeManagement implements Serializable {

    // RoomTypeManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_management_id")
    private Integer room_type_management_id;

    // 房间房型
    @Basic
    private String room_type;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
