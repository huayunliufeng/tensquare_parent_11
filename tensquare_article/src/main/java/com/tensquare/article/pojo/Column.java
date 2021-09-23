package com.tensquare.article.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 华韵流风
 * @ClassName Column
 * @Date 2021/9/18 14:05
 * @packageName com.tensquare.article.pojo
 * @Description TODO
 */
@Data
@Entity
@Table(name = "tb_column")
public class Column implements Serializable {

    @Id
    private String id;
    private String name;
    private String summary;
    private String userid;
    private Date createtime;
    private Date checktime;
    private String state;

}
