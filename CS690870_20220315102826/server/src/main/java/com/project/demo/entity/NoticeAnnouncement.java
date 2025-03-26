package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *通知公告：(NoticeAnnouncement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "NoticeAnnouncement")
public class NoticeAnnouncement implements Serializable {

    //NoticeAnnouncement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_announcement_id")
    private Integer notice_announcement_id;
   // 标题
   @Basic
    private String title;
   // 相关附件
   @Basic
    private String relevant_attachments;
   // 发布人
   @Basic
    private String publisher;
   // 发布日期
   @Basic
    private Timestamp release_date;
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
