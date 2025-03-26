package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *每日打卡：(PunchInEveryDay)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PunchInEveryDay")
public class PunchInEveryDay implements Serializable {

    //PunchInEveryDay编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "punch_in_every_day_id")
    private Integer punch_in_every_day_id;
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
   // 今日体温
   @Basic
    private String today_temperature;
   // 身体状态
   @Basic
    private String physical_state;
   // 打卡地址
   @Basic
    private String punch_in_address;
   // 健康码
   @Basic
    private String health_code;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
