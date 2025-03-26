package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学生请假：(StudentsAskForLeave)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudentsAskForLeave")
public class StudentsAskForLeave implements Serializable {

    //StudentsAskForLeave编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "students_ask_for_leave_id")
    private Integer students_ask_for_leave_id;
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
   // 请假类型
   @Basic
    private String leave_type;
   // 请假天数
   @Basic
    private Integer leave_days;
   // 开始时间
   @Basic
    private Timestamp start_time;
   // 结束时间
   @Basic
    private Timestamp end_time;
   // 相关凭证
   @Basic
    private String relevant_vouchers;
   // 请假原因
   @Basic
    private String reason_for_leave;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
