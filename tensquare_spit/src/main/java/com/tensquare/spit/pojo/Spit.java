package com.tensquare.spit.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author 华韵流风
 * @ClassName Spit
 * @Date 2021/9/22 20:13
 * @packageName com.tensquare.spit.pojo
 * @Description TODO
 */
@Data
@Entity
public class Spit implements Serializable {

    @Id
    private String id;
    private String content;
    private String publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;

}
