package com.tensquare.article.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 华韵流风
 * @ClassName Channel
 * @Date 2021/9/18 14:03
 * @packageName com.tensquare.article.pojo
 * @Description TODO
 */
@Data
@Entity
@Table(name = "tb_channel")
public class Channel implements Serializable {

    @Id
    private String id;
    private String name;
    private String state;

}
