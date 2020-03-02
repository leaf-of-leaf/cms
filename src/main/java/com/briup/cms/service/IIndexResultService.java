package com.briup.cms.service;

import com.briup.cms.bean.ex.IndexResult;
import com.briup.cms.utils.CustomerException;

/**
 * 首页数据管理的service接口
 * @author kj
 */
public interface IIndexResultService {
    /**
     * 查询首页需要的所有数据
     * @return
     * @throws CustomerException
     */
   IndexResult findIndexAllResult() throws CustomerException;


}
