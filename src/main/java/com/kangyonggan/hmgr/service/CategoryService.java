package com.kangyonggan.hmgr.service;

import com.kangyonggan.hmgr.model.Category;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/13
 */
public interface CategoryService {

    /**
     * 保存栏目
     *
     * @param category
     */
    void saveCategory(Category category);

    /**
     * 更新栏目
     *
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 删除栏目
     *
     * @param category
     */
    void deleteCategory(Category category);

    /**
     * 恢复栏目
     *
     * @param category
     */
    void recoverCategory(Category category);

    /**
     * 根据ID查找栏目
     *
     * @param id
     * @return
     */
    Category findCategoryById(Long id);

    /**
     * 根据栏目代码查找栏目
     *
     * @param categoryCode
     * @return
     */
    Category findCategoryByCode(String categoryCode);

    /**
     * 搜索栏目
     *
     * @param pageNum
     * @param name
     * @return
     */
    List<Category> searchCategories(int pageNum, String name);

    /**
     * 查找所有栏目
     *
     * @return
     */
    List<Category> findAllCategories();
}
