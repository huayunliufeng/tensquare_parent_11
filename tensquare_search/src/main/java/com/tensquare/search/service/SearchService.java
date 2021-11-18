package com.tensquare.search.service;

import com.tensquare.search.dao.SearchDao;
import com.tensquare.search.pojo.Article;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author 华韵流风
 * @ClassName SearchService
 * @Date 2021/9/27 19:38
 * @packageName com.tensquare.search.service
 * @Description TODO
 */
@Service
public class SearchService {

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加索引
     *
     * @param article article
     */
    public void add(Article article) {
        article.setId(String.valueOf(idWorker.nextId()));
        searchDao.save(article);
    }

    /**
     * 查询
     *
     * @param title   title
     * @param content content
     * @param page    page
     * @param size    size
     * @return Page<Article>
     */
    public Page<Article> findByTitleOrContent(String title, String content, int page, int size) {
        return searchDao.findByTitleOrContent(title, content, PageRequest.of(page - 1, size));
    }
}
