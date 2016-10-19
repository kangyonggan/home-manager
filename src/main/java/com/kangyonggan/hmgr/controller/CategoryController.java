package com.kangyonggan.hmgr.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.hmgr.model.Category;
import com.kangyonggan.hmgr.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/13
 */
@Controller
@RequestMapping("category")
@Log4j2
public class CategoryController {

    private static final String PATH_ROOT = "category/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_FORM = PATH_ROOT + "form";

    @Autowired
    private CategoryService categoryService;

    /**
     * 栏目管理
     *
     * @param pageNum
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                       Model model) {
        List<Category> categories = categoryService.searchCategories(pageNum, name);
        PageInfo<Category> page = new PageInfo(categories);

        model.addAttribute("page", page);
        return PATH_LIST;
    }

    /**
     * 添加栏目
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return PATH_FORM;
    }

    /**
     * 保存栏目
     *
     * @param category
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("category") @Valid Category category,
                       BindingResult result) {
        if (!result.hasErrors()) {
            categoryService.saveCategory(category);
            return "redirect:/" + PATH_ROOT;
        } else {
            return PATH_FORM;
        }
    }

    /**
     * 修改栏目
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findCategoryById(id);

        model.addAttribute("category", category);
        return PATH_FORM;
    }

    /**
     * 更新栏目
     *
     * @param category
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("category") @Valid Category category,
                       BindingResult result) {
        if (!result.hasErrors()) {
            categoryService.updateCategory(category);
            return "redirect:/" + PATH_ROOT;
        } else {
            return PATH_FORM;
        }
    }

    /**
     * 删除栏目
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Category category = categoryService.findCategoryById(id);
        categoryService.deleteCategory(category);
        return "redirect:/" + PATH_ROOT;
    }

    /**
     * 恢复栏目
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/recover", method = RequestMethod.GET)
    public String recover(@PathVariable("id") Long id) {
        Category category = categoryService.findCategoryById(id);
        categoryService.recoverCategory(category);
        return "redirect:/" + PATH_ROOT;
    }

}
