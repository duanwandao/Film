package com.zjx.respository;

import com.zjx.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-02 15:38
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
