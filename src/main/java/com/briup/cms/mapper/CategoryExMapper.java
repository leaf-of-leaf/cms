package com.briup.cms.mapper;

import com.briup.cms.bean.Category;
import com.briup.cms.bean.ex.CategoryEx;

public interface CategoryExMapper {

    CategoryEx findCategoryAndArticles(Integer id);

}
