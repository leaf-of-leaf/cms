package com.briup.cms.service.imp;

import com.briup.cms.bean.Link;
import com.briup.cms.bean.LinkExample;
import com.briup.cms.mapper.LinkMapper;
import com.briup.cms.service.ILinkService;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作链接的service功能类
 * @author kj
 * */
@Service
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public void saveOrUpdateLink(Link link) throws CustomerException {
        //参数为引用类型，要做判空处理
        if(link == null){
            throw new CustomerException(StatusCodeUtil.ERRO_CODE, "参数为空!");
        }
        //判断Link对象的id是否为空,如果为空，则新增，如果不为空，则修改
        if(link.getId() == null){
            linkMapper.insert(link);
        }
        if(linkMapper.selectByPrimaryKey(link.getId()) != null){
            linkMapper.updateByPrimaryKeySelective(link);
        }
    }

    @Override
    public void deleteLinkById(int id) throws CustomerException {
        linkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Link findLinkById(int id) throws CustomerException {
        return linkMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Link> findAllLinks() throws CustomerException {
//        LinkExample example = new LinkExample();
//        List<Link> links = linkMapper.selectByExample(example);
        return linkMapper.selectByExample(new LinkExample());
    }

    @Override
    public List<Link> findLinksByName(String name) throws CustomerException {
        LinkExample example = new LinkExample();
        name = name == null ? "": name.trim();

        if("".equals(name)){
            //如果搜索条件没写，则返回所有数据
            return linkMapper.selectByExample(example);
        }else{
            //如果搜索条件不为null,则返回满足条件的数据
            LinkExample.Criteria criteria = example.createCriteria();
            criteria.andNameLike("%"+ name + "%");
            List<Link> links = linkMapper.selectByExample(example);
            return links;
        }
    }

    @Override
    public List<Link> findLinksByLimit(Integer page) throws CustomerException{
        //页数为零即为第一页，条数要大于零
        //每页为三条数据
        final Integer elements = 3;
        if (page >= 0) {
            List<Link> links = linkMapper.findLinksByLimit((page - 1) * 3, elements);
            return links;
        }
        else{
            throw new CustomerException(StatusCodeUtil.ERRO_CODE, "分页错误");
        }
    }
}
