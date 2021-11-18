package com.tensquare.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author 华韵流风
 * @ClassName Article
 * @Date 2021/9/27 19:31
 * @packageName com.tensquare.search.pojo
 * @Description TODO
 */
@Data
@Document(indexName = "tensquare_search", type = "article")
public class Article implements Serializable {
    @Id
    private String id;
    /**
     * index表示该列是索引key，它会与关键字进行比对
     */
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Auto)
    private String title;
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.Auto)
    private String content;
    private String state;

}
