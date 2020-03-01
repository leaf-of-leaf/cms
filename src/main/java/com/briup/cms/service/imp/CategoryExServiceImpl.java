package com.briup.cms.service.imp;

import com.briup.cms.bean.ex.CategoryEx;
import com.briup.cms.mapper.CategoryExMapper;
import com.briup.cms.service.ICategoryExService;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kj
 * @Date 2020/2/29 11:56
 * @Version 1.0
 */
@Service
public class CategoryExServiceImpl implements ICategoryExService {

    @Autowired(required = false)
    private CategoryExMapper categoryExMapper;

    @Override
    public CategoryEx findCategoryAndArticles(Integer id) throws CustomerException {
        if(id == null || id < 0) throw new CustomerException(StatusCodeUtil.ERRO_CODE, "id为空或小于零");
        return categoryExMapper.findCategoryAndArticles(id);
    }
}
