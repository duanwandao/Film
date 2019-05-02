package com.zjx.service;

import com.zjx.entity.Film;

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
    public void save(Film film);
}
