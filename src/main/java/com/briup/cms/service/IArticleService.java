package com.briup.cms.service;

import com.briup.cms.bean.Article;
import com.briup.cms.utils.CustomerException;

import java.util.List;

/**
 * 文章相关内容的接口
 */
public interface IArticleService {

    /**
     * 增加或修改文章
     */
    void saveOrUpdateArticle(Article article) throws CustomerException;

    /**
     * 删除文章
     */
    void deleteArticleById(int id) throws CustomerException;

    /**
     * 查询文章
     * @param keyStr 表示搜索框
     * @param condition 表示栏目框
     * @return
     * @throws CustomerException
     */
    List<Article> findArticleByCondition(String keyStr, String condition)
            throws CustomerException;

    /**
     * 根据id查询文章
     */
    Article findArticleById(int id) throws CustomerException;
}
