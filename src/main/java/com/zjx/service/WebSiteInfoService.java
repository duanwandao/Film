package com.zjx.service;

import com.zjx.entity.WebSiteInfo;

import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-13 7:47
 */
public interface WebSiteInfoService {

    /**
     * 分页查询电影动态信息
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @return
     */
    Long getCount(WebSiteInfo webSiteInfo);

    /**
     * 根据电影id查询动态信息
     *
     * @param filmId
     * @return
     */
    List<WebSiteInfo> getByFilmId(Integer filmId);

    /**
     * 根据电影网址id查询电影动态信息
     *
     * @param webSiteId
     * @return
     */
    List<WebSiteInfo> getByWebSiteId(Integer webSiteId);

    /**
     * 添加或者修改电影动态信息
     * @param webSiteInfo
     */
    void save(WebSiteInfo webSiteInfo);

    /**
     * 根据id删除电影动态信息
     * @param id
     */
    void delete(Integer id);
}
