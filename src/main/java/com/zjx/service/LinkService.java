package com.zjx.service;

import com.zjx.entity.Link;

import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-04 15:17
 */
public interface LinkService {

    /**
     * 分页查询友情链接
     * @param page
     * @param pageSize
     * @return
     */
    List<Link> list(Integer page, Integer pageSize);

    /**
     * 查询所有友情链接
     * @return
     */
    List<Link> listAll();

    /**
     * 获取总记录数
     * @return
     */
    Long getCount();

    /**
     * 添加或者修改友情链接
     * @param link
     */
    void save(Link link);

    /**
     * 根据id删除友情链接
     * @param id
     */
    void delete(Integer id);
}
