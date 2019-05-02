package com.zjx.service.impl;

import com.zjx.entity.Film;
import com.zjx.respository.FilmRepository;
import com.zjx.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-02 15:56
 */
@Service("filmService")
public class FilmServiceImpl implements FilmService {

    //@Resource
    @Autowired
    private FilmRepository filmRepository;

    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }

}
