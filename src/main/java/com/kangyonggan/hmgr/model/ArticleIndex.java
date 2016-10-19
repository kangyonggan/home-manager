package com.kangyonggan.hmgr.model;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Table(name = "article_index")
@Data
public class ArticleIndex implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    private Long id;

    /**
     * 文章ID
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 标题
     */
    private String title;

    /**
     * 栏目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 内容
     */
    private String body;
}