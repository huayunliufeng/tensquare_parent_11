package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 华韵流风
 * @ClassName ArticleDao
 * @Date 2021/9/18 13:51
 * @packageName com.tensquare.article.dao
 * @Description TODO
 */
public interface ArticleDao extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {

    /**
     * 根据频道ID获取文章列表
     *
     * @param channelId channelId
     * @param pageable pageable
     * @return Page<Article>
     */
    Page<Article> findByChannelid(String channelId, Pageable pageable);

    /**
     * 根据专栏ID获取文章列表
     *
     * @param columnId columnId
     * @param pageable pageable
     * @return Page<Article>
     */
    Page<Article> findByColumnid(String columnId, Pageable pageable);

    /**
     *
     * @param istop istop
     * @return List<Article>
     */
    List<Article> findByIstop(String istop);

}
