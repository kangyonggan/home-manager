package com.kangyonggan.hmgr.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.hmgr.constants.AppConstants;
import com.kangyonggan.hmgr.model.Category;
import com.kangyonggan.hmgr.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
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
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Override
    @CacheEvict(value = "category", allEntries = true)
    public void saveCategory(Category category) {
        category.setCreatedTime(new Date());

        super.insertSelective(category);
    }

    @Override
    @CacheEvict(value = "category", allEntries = true)
    public void updateCategory(Category category) {
        category.setUpdatedTime(new Date());

        super.updateByPrimaryKeySelective(category);
    }

    @Override
    @CacheEvict(value = "category", allEntries = true)
    public void deleteCategory(Category category) {
        category.setUpdatedTime(new Date());
        category.setIsDeleted(AppConstants.IS_DELETED_YES);

        super.updateByPrimaryKeySelective(category);
    }

    @Override
    @CacheEvict(value = "category", allEntries = true)
    public void recoverCategory(Category category) {
        category.setUpdatedTime(new Date());
        category.setIsDeleted(AppConstants.IS_DELETED_NO);

        super.updateByPrimaryKeySelective(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public Category findCategoryByCode(String categoryCode) {
        Category category = new Category();
        category.setIsDeleted(AppConstants.IS_DELETED_NO);
        category.setCode(categoryCode);

        return super.selectOne(category);
    }

    @Override
    public List<Category> searchCategories(int pageNum, String name) {
        Example example = new Example(Category.class);

        if (StringUtils.isNotEmpty(name)) {
            example.createCriteria().andLike("name", "%" + name + "%");
        }

        example.setOrderByClause("sort asc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return super.selectByExample(example);
    }

    @Override
    public List<Category> findAllCategories() {
        Category category = new Category();
        category.setIsDeleted(AppConstants.IS_DELETED_NO);

        return super.select(category);
    }
}
