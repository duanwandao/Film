package com.zjx.service.impl;

import com.zjx.entity.Film;
import com.zjx.respository.FilmRepository;
import com.zjx.service.FilmService;
import com.zjx.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;

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

    @Override
    public List<Film> list(Film film, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "publishDate");
        Page<Film> filmPage = filmRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (film != null) {
                if (StringUtil.isNotEmpty(film.getName())) {
                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + film.getName().trim() + "%"));
                }
            }
            return predicate;
        }, pageable);
        return filmPage.getContent();
    }

    @Override
    public Long getCount(Film film) {
        Long count = filmRepository.count((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (film != null) {
                if (StringUtil.isNotEmpty(film.getName())) {
                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + film.getName().trim() + "%"));
                }
            }
            return predicate;
        });
        return count;
    }

    @Override
    public Film findById(Integer id) {
        return filmRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        filmRepository.delete(id);
    }

}
