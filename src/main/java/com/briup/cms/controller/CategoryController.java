package com.briup.cms.controller;

import com.briup.cms.bean.Category;
import com.briup.cms.bean.ex.CategoryEx;
import com.briup.cms.service.imp.CategoryServiceImpl;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.Message;
import com.briup.cms.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kj
 * @Date 2020/2/27 14:19
 * @Version 1.0
 */

@Api(description = "栏目操作")
@RestController
public class CategoryController {

    @Autowired(required = false)
    private CategoryServiceImpl categoryService;

    @GetMapping("/deleteCategory")
    @ApiOperation("通过id删除栏目")
    public Message<String> deleteCategory(int id){
        categoryService.deleteCategoryById(id);
        return MessageUtil.success("删除成功");
    }

    @GetMapping("/findCategoryById")
    @ApiOperation("通过id查找栏目")
    public Message<Object> findCategoryById(int id){
        Category category = categoryService.findCategoryById(id);
        return category != null ?
                MessageUtil.success(category):
                MessageUtil.success("查找为空");
    }

    @GetMapping("/findAllCategorys")
    @ApiOperation("查找所有栏目")
    public Message<Object> findAllCategorys(){
        List<Category> categories = categoryService.findAllCategorys();
        return categories.size() != 0 ?
                MessageUtil.success(categories):
                MessageUtil.success("查找为空");
    }

    @PostMapping("/addCategory")
    @ApiOperation("添加栏目")
    public Message<String> addCategory(Category category){
        try{
            categoryService.addOrUpdateCategory(category);
            return MessageUtil.success("添加成功");
        }catch (CustomerException e){
            return MessageUtil.error(500, "添加失败:" + e.getMessage());
        }
    }

    @PostMapping("/updateCategory")
    @ApiOperation("修改栏目")
    public Message<String> updateCategory(Category category){
        try{
            categoryService.addOrUpdateCategory(category);
            return MessageUtil.success("修改成功");
        }catch (CustomerException e){
            return MessageUtil.error(500, "修改失败:" + e.getMessage());
        }
    }

    @GetMapping("/findCategoryAndArticles")
    @ApiOperation("找到相应栏目以及对应的文章")
    public Message<Object> findCategoryEx(String name){
        CategoryEx categoryEx = null;
        try{
            return (categoryEx = categoryService.findCategoryEx(name)) == null?
                    MessageUtil.success("查询成功，但不存在对应栏目"):
                    //查询栏目下文章的属性 有 id,author,title,clicktimes,publishdate
                    MessageUtil.success(categoryEx);
        } catch (CustomerException e){
            return MessageUtil.error(500,"查询错误：" + e.getMessage());
        }
    }
}
