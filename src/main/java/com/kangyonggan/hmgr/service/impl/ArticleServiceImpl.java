package com.kangyonggan.hmgr.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.hmgr.constants.AppConstants;
import com.kangyonggan.hmgr.mapper.ArticleIndexMapper;
import com.kangyonggan.hmgr.model.Article;
import com.kangyonggan.hmgr.model.ArticleIndex;
import com.kangyonggan.hmgr.model.Category;
import com.kangyonggan.hmgr.service.ArticleService;
import com.kangyonggan.hmgr.service.CategoryService;
import com.kangyonggan.hmgr.util.FenCi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/13
 */
@Service
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

    @Autowired
    private ArticleIndexMapper articleIndexMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    @CacheEvict(value = "article", allEntries = true)
    public void saveArticle(Article article) {
        Category category = categoryService.findCategoryByCode(article.getCategoryCode());

        article.setCategoryName(category.getName());
        article.setCreatedTime(new Date());

        super.insertSelective(article);

        saveArticleIndex(article);
    }

    @Override
    @CacheEvict(value = "article", allEntries = true)
    public void updateArticle(Article article) {
        Category category = categoryService.findCategoryByCode(article.getCategoryCode());

        article.setCategoryName(category.getName());
        article.setUpdatedTime(new Date());

        super.updateByPrimaryKeySelective(article);

        updateArticleIndex(article);
    }

    @Override
    @CacheEvict(value = "article", allEntries = true)
    public void deleteArticle(Article article) {
        article.setUpdatedTime(new Date());
        article.setIsDeleted(AppConstants.IS_DELETED_YES);

        super.updateByPrimaryKeySelective(article);
    }

    @Override
    @CacheEvict(value = "article", allEntries = true)
    public void recoverArticle(Article article) {
        article.setUpdatedTime(new Date());
        article.setIsDeleted(AppConstants.IS_DELETED_NO);

        super.updateByPrimaryKeySelective(article);
    }

    @Override
    public Article findArticleById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public List<Article> searchArticles(int pageNum, String code, String title) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(code)) {
            criteria.andEqualTo("categoryCode", code);
        }
        if (StringUtils.isNotEmpty(title)) {
            criteria.andLike("title", "%" + title + "%");
        }

        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return super.selectByExample(example);
    }

    /**
     * 保存文章索引
     *
     * @param article
     */
    private void saveArticleIndex(Article article) {
        ArticleIndex articleIndex = new ArticleIndex();

        articleIndex.setArticleId(article.getId());
        articleIndex.setBody(FenCi.process(article.getBody()));
        articleIndex.setCategoryName(FenCi.process(article.getCategoryName()));
        articleIndex.setTitle(FenCi.process(article.getTitle()));

        articleIndexMapper.insertSelective(articleIndex);
    }

    /**
     * 更新文章索引
     *
     * @param article
     */
    private void updateArticleIndex(Article article) {
        ArticleIndex articleIndex = new ArticleIndex();
        articleIndex.setArticleId(article.getId());
        articleIndex = articleIndexMapper.selectOne(articleIndex);

        articleIndex.setBody(FenCi.process(article.getBody()));
        articleIndex.setCategoryName(FenCi.process(article.getCategoryName()));
        articleIndex.setTitle(FenCi.process(article.getTitle()));

        articleIndexMapper.updateByPrimaryKeySelective(articleIndex);
    }
}
