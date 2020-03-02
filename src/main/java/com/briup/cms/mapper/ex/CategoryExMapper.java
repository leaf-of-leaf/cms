package com.briup.cms.mapper.ex;

import com.briup.cms.bean.ex.CategoryEx;

import java.util.List;

/**
 * 处理 查询栏目及其包含的文章信息
 */
public interface CategoryExMapper {

    /**
     * 实现查询所有栏目及其包含的文章信息
     * @return
     */
    List<CategoryEx> findAllCategoryExs();

    CategoryEx findCategoryEx(String name);

}
