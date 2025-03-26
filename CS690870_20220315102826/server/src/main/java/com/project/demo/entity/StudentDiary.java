package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学生日记：(StudentDiary)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudentDiary")
public class StudentDiary implements Serializable {

    //StudentDiary编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_diary_id")
    private Integer student_diary_id;
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
   // 心情
   @Basic
    private String mood;
   // 日期
   @Basic
    private Timestamp date;
   // 内容
   @Basic
    private String content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
