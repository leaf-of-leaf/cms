package com.briup.cms.service.imp;

import com.briup.cms.bean.Article;
import com.briup.cms.bean.ArticleExample;
import com.briup.cms.bean.Category;
import com.briup.cms.bean.CategoryExample;
import com.briup.cms.mapper.ArticleMapper;
import com.briup.cms.mapper.CategoryMapper;
import com.briup.cms.service.IArticleService;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author kj
 * @Date 2020/2/28 10:42
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Override
    public void saveOrUpdateArticle(Article article) throws CustomerException {
        if(article == null){
            throw new CustomerException(StatusCodeUtil.ERRO_CODE,"参数为空");
        }

        if(article.getId() == null){
            article.setPublishdate(new Date());
            article.setClicktimes(0);
            articleMapper.insert(article);
        }else {
            article.setPublishdate(new Date());
            articleMapper.updateByPrimaryKey(article);
        }

    }

    @Override
    public void deleteArticleById(int id) throws CustomerException {
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Article> findArticleByCondition(String keyStr, String condition) throws CustomerException {
        ArticleExample example = new ArticleExample();
        /*
        分三种情况
        1.没有添加任何条件则查询所有文章
        2.没有指定栏目，但指定了查询的关键字，则根据关键字查询所有文章
        3.指定栏目，同时没有指定查询的关键字，则根据栏目查询满足条件的文章
        4.指定栏目，同时指定查询的关键字，则根据栏目和关键字查询满足条件的文章
         */
        keyStr = keyStr == null? "" : keyStr.trim();
        condition = condition == null?"" : condition.trim();

        if("".equals(keyStr) && "".equals(condition)){
            //情况1
            return articleMapper.selectByExample(example);
        }else if(!"".equals(keyStr) && "".equals(condition)){
            //情况2
            example.createCriteria().andTitleLike("%" + keyStr + "%");
            return articleMapper.selectByExample(example);
        }else if(!"".equals(condition) && "".equals(keyStr)){
            //情况3
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andNameEqualTo(condition);
            List<Category> categories = categoryMapper.selectByExample(categoryExample);

            if (categories.size() > 0){
                example.createCriteria().andCategoryIdEqualTo(categories.get(0).getId());
            }else {
                throw new CustomerException(StatusCodeUtil.ERRO_CODE,"没有指定的搜索栏目");
            }
            return articleMapper.selectByExample(example);
        }else {
            //情况4
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andNameEqualTo(keyStr);
            List<Category> categories = categoryMapper.selectByExample(categoryExample);

            //and方式拼接条件
            example.createCriteria().andCategoryIdEqualTo(categories.get(0).getId())
                    .andTitleLike("%" + keyStr + "%");
            //or方式拼接条件        example.or()
            return articleMapper.selectByExample(example);
        }
    }

    @Override
    public Article findArticleById(int id) throws CustomerException {
        Article article = articleMapper.selectByPrimaryKey(id);
        article.setClicktimes(article.getClicktimes() + 1);
        articleMapper.updateByPrimaryKeySelective(article);
        return article;
    }
}
