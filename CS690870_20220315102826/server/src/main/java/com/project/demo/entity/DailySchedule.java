package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *每日安排：(DailySchedule)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DailySchedule")
public class DailySchedule implements Serializable {

    //DailySchedule编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_schedule_id")
    private Integer daily_schedule_id;
   // 学号
   @Basic
    private Integer student_number;
   // 学生姓名
   @Basic
    private String student_name;
   // 教师工号
   @Basic
    private Integer teacher_job_number;
   // 教师姓名
   @Basic
    private String teacher_name;
   // 班级名称
   @Basic
    private String class_name;
   // 记录日期
   @Basic
    private Timestamp record_date;
   // 时间安排
   @Basic
    private String schedule;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
