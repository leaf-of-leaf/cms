package com.briup.cms.service.imp;

import com.briup.cms.bean.Link;
import com.briup.cms.bean.ex.CategoryEx;
import com.briup.cms.bean.ex.IndexResult;
import com.briup.cms.service.ICategoryService;
import com.briup.cms.service.IIndexResultService;
import com.briup.cms.service.ILinkService;
import com.briup.cms.utils.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询首页所有数据的实现类
 * @author kj
 * @Date 2020/3/2 9:50
 * @Version 1.0
 */
@Service
public class IndexResultServiceImpl implements IIndexResultService {

    //同级调用service，而不用dao
    @Autowired(required = false)
    private ILinkService linkService;

    @Autowired(required = false)
    private ICategoryService categoryService;

    @Override
    public IndexResult findIndexAllResult() throws CustomerException {

        IndexResult indexResult = new IndexResult();
        //少变量，少对象,少创建,java最小单位为类
        List<Link> links = linkService.findAllLinks();
        List<CategoryEx> categories = categoryService.findAllCategoryEx();
        //设置所有的超链接
        indexResult.setLinks(links);
        //设置所有的栏目机器包含的所有文章信息
        indexResult.setCategoryExes(categories);
        
        return indexResult;
    }
}
