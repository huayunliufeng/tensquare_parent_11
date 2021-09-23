package com.tensquare.article.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 华韵流风
 * @ClassName Article
 * @Date 2021/9/18 13:54
 * @packageName com.tensquare.article.pojo
 * @Description TODO
 */

@Data
@Entity
@Table(name = "tb_article")
public class Article implements Serializable {
        @Id
        private String id;
        private String columnid;
        private String userid;
        private String title;
        private String content;
        private String image;
        private Date createtime;
        private Date updatetime;
        private String ispublic;
        private String istop;
        private Integer visits;
        private Integer thumbup;
        private Integer comment;
        private String state;
        private String channelid;
        private String url;
        private String type;
}
