package com.zjx.service;

import com.zjx.entity.Film;

import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-02 15:55
 */
public interface FilmService {

    /**
     * 添加或者修改电影
     * @param film
     */
    void save(Film film);

    /**
     * 分页查询电影信息
     * @param film
     * @param page
     * @param pageSize
     * @return
     */
    List<Film> list(Film film,Integer page,Integer pageSize);

    /**
     * 获取总记录数
     * @param film
     * @return
     */
    Long getCount(Film film);

    /**
     * 根据id查找实体
     * @param id
     * @return
     */
    Film findById(Integer id);

    /**
     * 根据id删除电影
     * @param id
     */
    void delete(Integer id);
}
