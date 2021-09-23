package com.tensquare.qa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
@Entity
@Table(name = "tb_problem")
public class Problem implements Serializable {
    @Id
    private String id;
    private String title;
    private String content;
    private Date createtime;
    private Date updatetime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer reply;
    private String solve;
    private String replyname;
    private Date replytime;


    //`id` varchar(20) NOT NULL COMMENT 'ID',
    //  `title` varchar(100) DEFAULT NULL COMMENT '标题',
    //  `content` text COMMENT '内容',
    //  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
    //  `updatetime` datetime DEFAULT NULL COMMENT '修改日期',
    //  `userid` varchar(20) DEFAULT NULL COMMENT '用户ID',
    //  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
    //  `visits` bigint(20) DEFAULT NULL COMMENT '浏览量',
    //  `thumbup` bigint(20) DEFAULT NULL COMMENT '点赞数',
    //  `reply` bigint(20) DEFAULT NULL COMMENT '回复数',
    //  `solve` varchar(1) DEFAULT NULL COMMENT '是否解决',
    //  `replyname` varchar(100) DEFAULT NULL COMMENT '回复人昵称',
    //  `replytime` datetime DEFAULT NULL COMMENT '回复日期',
}
