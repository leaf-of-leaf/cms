package com.briup.cms.controller;

import com.briup.cms.service.ICategoryExService;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.Message;
import com.briup.cms.utils.MessageUtil;
import com.briup.cms.utils.StatusCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kj
 * @Date 2020/2/29 12:12
 * @Version 1.0
 */
@RestController
@Api(description = "首页数据")
public class IndexResultController {

    @Autowired(required = false)
    private ICategoryExService categoryExService;

    @GetMapping("/index")
    @ApiOperation("查询所有栏目")
    public Message<Object> findIndexData(Integer id){
        try{
            return MessageUtil.success(categoryExService.findCategoryAndArticles(id));
        }catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE,"获取数据错误");

        }
    }

}
