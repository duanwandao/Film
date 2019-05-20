package com.zjx.respository;

import com.zjx.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-02 15:38
 */
public interface FilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {

    /**
     * 获取上一个电影
     *
     * @param id
     * @return
     */
    @Query(value = "select * from t_film where id<?1 order by id desc limit 1", nativeQuery = true)
    Film getLast(Integer id);

    /**
     * 获取下一个电影
     *
     * @param id
     * @return
     */
    @Query(value = "select * from t_film where id >?1 order by id asc limit 1", nativeQuery = true)
    Film getNext(Integer id);

    @Query(value = "select * from t_film order by rand() limit ?1", nativeQuery = true)
    List<Film> randomList(Integer id);
}
