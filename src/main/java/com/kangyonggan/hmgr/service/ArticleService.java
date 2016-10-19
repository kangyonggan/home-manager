package com.kangyonggan.hmgr.service;

import com.kangyonggan.hmgr.model.Article;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/13
 */
public interface ArticleService {

    /**
     * 保存文章
     *
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 更新文章
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 删除文章
     *
     * @param article
     */
    void deleteArticle(Article article);

    /**
     * 恢复文章
     *
     * @param article
     */
    void recoverArticle(Article article);

    /**
     * 根据ID查找文章
     *
     * @param id
     * @return
     */
    Article findArticleById(Long id);

    /**
     * 搜索文章
     *
     * @param pageNum
     * @param code
     * @param title
     * @return
     */
    List<Article> searchArticles(int pageNum, String code, String title);
}
