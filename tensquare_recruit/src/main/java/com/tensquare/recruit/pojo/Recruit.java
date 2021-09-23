package com.tensquare.recruit.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="tb_recruit")
public class Recruit implements Serializable {
    @Id
    private String id;
    private String jobname;
    private String salary;
    @Column(name = "`condition`")
    private String condition;
    private String education;
    private String type;
    private String address;
    private String eid;
    private Date createtime;
    private String state;
    private String label;
    private String content1;
    private String content2;
}
