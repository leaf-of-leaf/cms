package com.briup.cms.bean.ex;

import com.briup.cms.bean.Link;

import java.io.Serializable;
import java.util.List;

/**
 * 保存首页的所有数据
 * @author kj
 * @Date 2020/2/28 17:18
 * @Version 1.0
 */
public class IndexResult implements Serializable {

    private List<CategoryEx> categoryExes;
    private List<Link> links;

    public IndexResult(List<CategoryEx> categoryExes, List<Link> links) {
        this.categoryExes = categoryExes;
        this.links = links;
    }

    public IndexResult() {
    }

    public List<CategoryEx> getCategoryExes() {
        return categoryExes;
    }

    public void setCategoryExes(List<CategoryEx> categoryExes) {
        this.categoryExes = categoryExes;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
