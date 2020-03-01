package com.briup.cms.service;

import com.briup.cms.bean.Link;
import com.briup.cms.utils.CustomerException;

import java.util.List;

/**
 * 关于链接的相关操作
 * @author kj
 * */
public interface ILinkService {
    /**
     * 保存链接信息
     * @param link
     * */
    void saveOrUpdateLink(Link link) throws CustomerException;

    /**
     * 通过id删除链接
     * @param id
     * @throws CustomerException
     */
    void deleteLinkById(int id) throws CustomerException;

    /**
     * 查询所有链接信息
     */
    List<Link> findAllLinks() throws CustomerException;

    /**
     * 通过id查询链接
     * @throws CustomerException
     */
    Link findLinkById(int id) throws CustomerException;

    /**
     * 根据 链接名 查询链接
     */
    List<Link> findLinksByName(String name) throws CustomerException;

    /**
     * 分页查询,条数为3条
     * @param page
     * @return
     */
    List<Link> findLinksByLimit(Integer page) throws CustomerException;
}
