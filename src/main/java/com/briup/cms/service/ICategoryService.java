package com.briup.cms.service;

import com.briup.cms.bean.Category;
import com.briup.cms.bean.ex.CategoryEx;
import com.briup.cms.utils.CustomerException;

import java.util.List;

public interface ICategoryService {

    /**
     * 添加或修改栏目信息
     * @param category
     */
    void addOrUpdateCategory(Category category) throws CustomerException;

    /**
     * 根据id删除栏目
     * @param id
     */
    void deleteCategoryById(int id) throws CustomerException;

    /**
     * 根据id查找指定的栏目信息
     */
    Category findCategoryById(int id) throws CustomerException;

    /**
     * 查询所有栏目
     * @return
     */
    List<Category> findAllCategorys() throws CustomerException;

    /**
     * 查询栏目信息并且级联查询包含的文章信息
     * @return
     * @throws CustomerException
     */
    List<CategoryEx> findAllCategoryEx() throws CustomerException;

    CategoryEx findCategoryEx(String name) throws CustomerException;
}
