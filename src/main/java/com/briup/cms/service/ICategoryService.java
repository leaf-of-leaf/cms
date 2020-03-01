package com.briup.cms.service;

import com.briup.cms.bean.Category;
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

}
