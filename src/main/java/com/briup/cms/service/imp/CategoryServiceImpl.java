package com.briup.cms.service.imp;

import com.briup.cms.bean.ArticleExample;
import com.briup.cms.bean.Category;
import com.briup.cms.bean.CategoryExample;
import com.briup.cms.bean.ex.CategoryEx;
import com.briup.cms.mapper.ArticleMapper;
import com.briup.cms.mapper.CategoryMapper;
import com.briup.cms.mapper.ex.CategoryExMapper;
import com.briup.cms.service.ICategoryService;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/2/27 14:18
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements ICategoryService {


    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired(required = false)
    private CategoryExMapper categoryExMapper;

    @Override
    public CategoryEx findCategoryEx(String name) throws CustomerException {
        if(name == null) throw new CustomerException(StatusCodeUtil.ERRO_CODE,"name为空");
        else return categoryExMapper.findCategoryEx(name);
    }

    @Override
    public List<CategoryEx> findAllCategoryEx() throws CustomerException {

        return categoryExMapper.findAllCategoryExs();
    }

    @Override
    public void addOrUpdateCategory(Category category) throws CustomerException {
        if(category == null) throw new CustomerException(StatusCodeUtil.ERRO_CODE, "category为null");
        /*
        id为null时为插入
        id大于0且数据存在时可以修改
        修改时,mybatis为动态sql
         */
        if(category.getId() == null){

            //插入判断，数据库中一样的数据

            categoryMapper.insert(category);
        }else if(category.getId() > 0 && categoryMapper.selectByPrimaryKey(category.getId()) != null){
            categoryMapper.updateByPrimaryKeySelective(category);
        }else
            throw new CustomerException(500, category.getId() > 0 ?
                    "此条数据不存在" : "id <= 0");
    }

    @Override
    public void deleteCategoryById(int id) throws CustomerException {
        //删除栏目先要删除栏目下的文章
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        articleMapper.deleteByExample(articleExample);

        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category findCategoryById(int id) throws CustomerException {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> findAllCategorys() throws CustomerException {
        return categoryMapper.selectByExample(new CategoryExample());
    }


}
