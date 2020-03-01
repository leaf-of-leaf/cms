package com.briup.cms.service;

import com.briup.cms.bean.Category;
import com.briup.cms.bean.ex.CategoryEx;
import com.briup.cms.utils.CustomerException;

public interface ICategoryExService {
    /**
     *
     * @param id
     */
    CategoryEx findCategoryAndArticles(Integer id) throws CustomerException;
}
