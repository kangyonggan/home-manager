package com.kangyonggan.hmgr.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.hmgr.model.Article;
import com.kangyonggan.hmgr.model.Category;
import com.kangyonggan.hmgr.service.ArticleService;
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
@RequestMapping("article")
@Log4j2
public class ArticleController {

    private static final String PATH_ROOT = "article/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_FORM = PATH_ROOT + "form";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 文章管理
     *
     * @param pageNum
     * @param code
     * @param title
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "code", required = false, defaultValue = "") String code,
                       @RequestParam(value = "title", required = false, defaultValue = "") String title,
                       Model model) {
        List<Article> articles = articleService.searchArticles(pageNum, code, title);
        PageInfo<Article> page = new PageInfo(articles);
        List<Category> categories = categoryService.findAllCategories();

        model.addAttribute("page", page);
        model.addAttribute("categories", categories);
        return PATH_LIST;
    }

    /**
     * 添加文章
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        List<Category> categories = categoryService.findAllCategories();

        model.addAttribute("article", new Article());
        model.addAttribute("categories", categories);
        return PATH_FORM;
    }

    /**
     * 保存文章
     *
     * @param article
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("article") @Valid Article article,
                       BindingResult result) {
        if (!result.hasErrors()) {
            articleService.saveArticle(article);
            return "redirect:/" + PATH_ROOT;
        } else {
            return PATH_FORM;
        }
    }

    /**
     * 修改文章
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findArticleById(id);
        List<Category> categories = categoryService.findAllCategories();

        model.addAttribute("article", article);
        model.addAttribute("categories", categories);
        return PATH_FORM;
    }

    /**
     * 更新文章
     *
     * @param article
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("article") @Valid Article article,
                       BindingResult result) {
        if (!result.hasErrors()) {
            articleService.updateArticle(article);
            return "redirect:/" + PATH_ROOT;
        } else {
            return PATH_FORM;
        }
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Article article = articleService.findArticleById(id);
        articleService.deleteArticle(article);
        return "redirect:/" + PATH_ROOT;
    }

    /**
     * 恢复文章
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/recover", method = RequestMethod.GET)
    public String recover(@PathVariable("id") Long id) {
        Article article = articleService.findArticleById(id);
        articleService.recoverArticle(article);
        return "redirect:/" + PATH_ROOT;
    }

}
