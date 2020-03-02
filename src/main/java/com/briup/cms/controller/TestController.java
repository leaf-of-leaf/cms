package com.briup.cms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "测试接口")
public class TestController {

    @RequestMapping("/")
    @ApiOperation("HelloWorld接口")
    public String test(){
        return "Hello World";
    }



}
