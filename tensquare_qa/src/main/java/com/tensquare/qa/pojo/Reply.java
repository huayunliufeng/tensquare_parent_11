package com.tensquare.qa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_reply")
public class Reply implements Serializable {

    @Id
    private String id;
    private String problemid;
    private String content;
    private Date createtime;
    private Date updatetime;
    private String userid;
    private String nickname;
}
