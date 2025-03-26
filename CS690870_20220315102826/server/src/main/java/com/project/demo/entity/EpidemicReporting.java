package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *疫情上报：(EpidemicReporting)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "EpidemicReporting")
public class EpidemicReporting implements Serializable {

    //EpidemicReporting编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "epidemic_reporting_id")
    private Integer epidemic_reporting_id;
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
   // 上报类型
   @Basic
    private String report_type;
   // 核酸结果
   @Basic
    private String nucleic_acid_results;
   // 检测时间
   @Basic
    private Timestamp detection_time;
   // 健康码
   @Basic
    private String health_code;
   // 备注
   @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
