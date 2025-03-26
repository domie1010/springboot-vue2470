package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *教师：(Teacher)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "Teacher")
public class Teacher implements Serializable {

    //Teacher编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer teacher_id;
   // 教师工号
   @Basic
    private String teacher_job_number;
   // 教师姓名
   @Basic
    private String teacher_name;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
