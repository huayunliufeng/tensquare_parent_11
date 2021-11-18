package com.tensquare.spit.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 华韵流风
 * @ClassName Spit
 * @Date 2021/9/22 20:13
 * @packageName com.tensquare.spit.pojo
 * @Description TODO
 */
@Data
@Document(collection = "spit")
public class Spit implements Serializable {

    @Id
    private String _id;
    private String content;
    private Date publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;

}
