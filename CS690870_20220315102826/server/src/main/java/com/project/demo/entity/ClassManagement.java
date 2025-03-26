package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *班级管理：(ClassManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ClassManagement")
public class ClassManagement implements Serializable {

    //ClassManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_management_id")
    private Integer class_management_id;
   // 班级名称
   @Basic
    private String class_name;
   // 班主任
   @Basic
    private String headmaster;
   // 联系电话
   @Basic
    private String contact_number;
   // 班级人数
   @Basic
    private Integer class_size;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
