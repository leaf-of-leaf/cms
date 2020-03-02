package com.briup.cms.controller;

import com.briup.cms.bean.ex.IndexResult;
import com.briup.cms.service.IIndexResultService;
import com.briup.cms.utils.Message;
import com.briup.cms.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kj
 * @Date 2020/3/2 11:29
 * @Version 1.0
 */
@RestController
@Api(description = "首页需要的所有数据")
public class IndexResultController {

    @Autowired
    private IIndexResultService iIndexResultService;

    @GetMapping("/index")
    @ApiOperation("获取首页所有数据")
    public Message<IndexResult> getIndexResult(){
        return MessageUtil.success(iIndexResultService.findIndexAllResult());
    }

}
