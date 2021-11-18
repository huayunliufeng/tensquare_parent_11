package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author 华韵流风
 * @ClassName ArticleDao
 * @Date 2021/9/27 19:36
 * @packageName com.tensquare.search.dao
 * @Description TODO
 */
public interface SearchDao extends ElasticsearchRepository<Article,String> {

    /**
     * 查询
     *
     * @param title title
     * @param content content
     * @param pageable pageable
     * @return Page<Article>
     */
    Page<Article> findByTitleOrContent(String title, String content, Pageable pageable);
}
