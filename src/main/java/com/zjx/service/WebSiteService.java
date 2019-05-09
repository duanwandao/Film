package com.zjx.service;

import com.zjx.entity.WebSite;

import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-09 12:17
 */
public interface WebSiteService {

    /**
     * 分页查询收录电影网址
     * @param page
     * @param pageSize
     * @return
     */
    List<WebSite> list(WebSite webSite, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     * @param webSite
     * @return
     */
    Long getCount(WebSite webSite);

    /**
     * 添加或者修改收录电影网址
     * @param webSite
     */
    void save(WebSite webSite);

    /**
     * 根据id删除收录电影网址
     * @param id
     */
    void delete(Integer id);
}
