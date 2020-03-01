package com.briup.cms.controller;

import com.briup.cms.bean.Link;
import com.briup.cms.service.ILinkService;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.Message;
import com.briup.cms.utils.MessageUtil;
import com.briup.cms.utils.StatusCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 与连接相关的 和前端交互的web层
 * @author kj
 * */
@RestController
@Api(description = "链接相关接口")
public class LinkController {
    @Autowired
    private ILinkService linkService;

    /**
     * 先进行link判空，再进行添加操作
     * @param link
     * @return
     */
    @PostMapping("/addLink")
    @ApiOperation("新增链接")
    public Message<String> addLink(Link link){
        try {
            linkService.saveOrUpdateLink(link);
            return MessageUtil.success();
        }catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE, "系统错误," + e.getMessage());
        }
    }

    /**
     * 修改链接
     * @param link
     * @return
     */
    @PostMapping("/updateLink")
    @ApiOperation("修改链接")
    public Message<String> updateLink(Link link){
        try {
            linkService.saveOrUpdateLink(link);
            return MessageUtil.success();
        }catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE, "系统错误," + e.getMessage());
        }
    }

    /**
     * 查找链接
     * @param id
     * @return
     */
    @GetMapping("/selectLinkById")
    @ApiOperation("通过id查找链接")
        public Message<Link> findLink(int id){
        return MessageUtil.success(linkService.findLinkById(id));
    }

    @GetMapping("/selectLinks")
    @ApiOperation("查询所有链接")
    public Message<List<Link>> selectLinks(){
        List<Link> links = linkService.findAllLinks();
        return MessageUtil.success(links);
    }

    @GetMapping("/selectLinksByName")
    @ApiOperation("根据链接名查询,支持模糊查询")
    public Message<List<Link>> selectLinksByNam(String name){
        List<Link> links = linkService.findLinksByName(name);
        return MessageUtil.success(links);
    }

    /**
     *
     * @param page
     * @return
     */
    @GetMapping("/selectLinksByLimit")
    @ApiOperation("分页查找链接")
    public Message<Object> findLinksByLimit(Integer page){
        try {
            List<Link> links = linkService.findLinksByLimit(page);
            if(links.size() != 0)
            return MessageUtil.success(links);
            else return MessageUtil.success("查询成功，但没有数据");
        }catch (CustomerException e){
            //0,3
            //
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE, "系统错误," + e.getMessage());
        }
    }

    /**
     * 删除链接
     * @param id
     * @return
     */
    @GetMapping("/deleteLinkById")
    @ApiOperation("通过id删除链接")
    public Message<String> deleteLink(int id){
        linkService.deleteLinkById(id);
        return MessageUtil.success();
    }
}
