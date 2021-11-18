package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 华韵流风
 * @ClassName ArticleService
 * @Date 2021/9/18 14:09
 * @packageName com.tensquare.article.service
 * @Description TODO
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 注入redis模板
     */
    @Resource
    private RedisTemplate<String, Article> redisTemplate;


    /**
     * 添加article
     *
     * @param article article
     */
    public void add(Article article) {
        article.setId(String.valueOf(idWorker.nextId()));
        article.setCreatetime(new Date());
        articleDao.save(article);
    }

    /**
     * 根据id删除article
     *
     * @param id id
     */
    public void remove(String id) {
        articleDao.deleteById(id);
        redisTemplate.delete("article_" + id);
    }

    /**
     * 根据id修改article
     *
     * @param id      id
     * @param article article
     */
    public void update(String id, Article article) {
        article.setId(id);
        articleDao.save(article);
        article.setUpdatetime(new Date());
        redisTemplate.delete("article_" + id);
        redisTemplate.opsForValue().set("article_" + id, findById(id), 24 * 60 * 60, TimeUnit.SECONDS);
    }

    /**
     * 根据id查询article
     *
     * @param id id
     * @return Article
     */
    public Article findById(String id) {
        //从缓存中查找
        Article article = redisTemplate.opsForValue().get("article_" + id);
        if (article == null) {
            article = articleDao.findById(id).get();
            redisTemplate.opsForValue().set("article_" + id, article, 24 * 60 * 60, TimeUnit.SECONDS);
        }
        return article;
    }

    /**
     * 查询所有article
     *
     * @return List<Article>
     */
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    /**
     * 根据条件查询文章列表
     *
     * @param article article
     * @return List<Article>
     */
    public List<Article> findByArticle(Article article) {
        return articleDao.findAll(getSpecification(article));
    }

    /**
     * 文章分页
     *
     * @param article article
     * @param page    page
     * @param size    size
     * @return Page<Article>
     */
    public Page<Article> findByArticlePage(Article article, int page, int size) {
        return articleDao.findAll(getSpecification(article), PageRequest.of(page - 1, size));
    }

    /**
     * 点赞
     *
     * @param articleId articleId
     */
    public void thumbUp(String articleId) {
        Article article = articleDao.findById(articleId).get();
        article.setThumbup(article.getThumbup() + 1);
        articleDao.save(article);
    }

    /**
     * 文章审核
     *
     * @param articleId articleId
     */
    public void examine(String articleId) {
        Article article = articleDao.findById(articleId).get();
        article.setState("1");
        articleDao.save(article);
    }

    /**
     * 头条文章
     *
     * @return List<Article>
     */
    public List<Article> articleTop(){
        return articleDao.findByIstop("1");
    }

    /**
     * 根据频道ID获取文章列表
     *
     * @param channelId channelId
     * @param page      page
     * @param size      size
     * @return Page<Article>
     */
    public Page<Article> findByChannelid(String channelId, int page, int size) {
        return articleDao.findByChannelid(channelId, PageRequest.of(page - 1, size));
    }

    /**
     * 根据专栏ID获取文章列表
     *
     * @param columnId columnId
     * @param page     page
     * @param size     size
     * @return Page<Article>
     */
    public Page<Article> findByColumnid(String columnId, int page, int size) {
        return articleDao.findByColumnid(columnId, PageRequest.of(page - 1, size));
    }




    private Specification<Article> getSpecification(Article article) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        List<Predicate> list = new ArrayList<>();
        return (Specification<Article>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(article.getId())) {
                Predicate predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + article.getId() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getColumnid())) {
                Predicate predicate = criteriaBuilder.like(root.get("columnid").as(String.class), "%" + article.getColumnid() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getState())) {
                Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), article.getState());
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getTitle())) {
                Predicate predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + article.getTitle() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getUserid())) {
                Predicate predicate = criteriaBuilder.like(root.get("userid").as(String.class), "%" + article.getState() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getContent())) {
                Predicate predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + article.getContent() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getImage())) {
                Predicate predicate = criteriaBuilder.like(root.get("image").as(String.class), "%" + article.getImage() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getIspublic())) {
                Predicate predicate = criteriaBuilder.like(root.get("ispublic").as(String.class), article.getIspublic());
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getIstop())) {
                Predicate predicate = criteriaBuilder.like(root.get("istop").as(String.class), article.getIstop());
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getChannelid())) {
                Predicate predicate = criteriaBuilder.like(root.get("channelid").as(String.class), "%" + article.getChannelid() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getUrl())) {
                Predicate predicate = criteriaBuilder.like(root.get("url").as(String.class), "%" + article.getUrl() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(article.getType())) {
                Predicate predicate = criteriaBuilder.like(root.get("type").as(String.class), "%" + article.getType() + "%");
                list.add(predicate);
            }
            if (article.getCreatetime() != null) {

                list.add(criteriaBuilder.equal(root.get("createtime").as(Date.class), article.getCreatetime()));
            }
            if (article.getUpdatetime() != null) {
                list.add(criteriaBuilder.equal(root.get("updatetime").as(Date.class), article.getUpdatetime()));
            }
            if (article.getVisits() != null) {
                list.add(criteriaBuilder.equal(root.get("visits").as(Integer.class), article.getVisits()));
            }
            if (article.getThumbup() != null) {
                list.add(criteriaBuilder.equal(root.get("thumbup").as(Integer.class), article.getThumbup()));
            }
            if (article.getComment() != null) {
                list.add(criteriaBuilder.equal(root.get("comment").as(Integer.class), article.getComment()));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };

    }


}
