package com.briup.cms.controller;

import com.briup.cms.bean.Article;
import com.briup.cms.service.imp.ArticleServiceImpl;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.Message;
import com.briup.cms.utils.MessageUtil;
import com.briup.cms.utils.StatusCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章相关信息的controller
 * @author kj
 * @Date 2020/2/28 14:25
 * @Version 1.0
 */
@RestController
@Api(description = "文章相关接口")
public class ArticleController {

    @Autowired(required = false)
    private ArticleServiceImpl articleService;

    @PostMapping("/saveArticle")
    @ApiOperation("添加文章信息")
    public Message<String> saveArticle(Article article){
        try {
            articleService.saveOrUpdateArticle(article);
            return MessageUtil.success("保存成功");
        } catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE, "保存失败:" + e.getMessage());
        }
    }

    @PostMapping("/updateArticleById")
    @ApiOperation("修改文章信息")
    public Message<String> updateArticleById(Article article){
        try {
            articleService.saveOrUpdateArticle(article);
            return MessageUtil.success("修改成功");
        } catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE, "修改失败:" + e.getMessage());
        }
    }

    @GetMapping("/deleteArticleById")
    @ApiOperation("删除文章信息")
    public Message<String> deleteArticleById(int i){
        articleService.deleteArticleById(i);
        return MessageUtil.success("删除成功");
    }

    @GetMapping("/findArticleByCondition")
    @ApiOperation("根据条件查询文章信息")
    public Message<List<Article>> getArticleByCondition(String keyStr, String condition){
        try{
            List<Article> articles = articleService.findArticleByCondition(keyStr, condition);
            return MessageUtil.success(articles);
        }catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE, "系统错误:" + e.getMessage());
        }
    }

    //
    @GetMapping("/findArticleById")
    @ApiOperation("通过id查找文章信息")
    public Message<Article> findArticleById(int i){
        return MessageUtil.success(articleService.findArticleById(i));
    }

}
