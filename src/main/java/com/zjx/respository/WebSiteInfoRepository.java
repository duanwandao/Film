package com.zjx.respository;

import com.zjx.entity.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-13 7:44
 */
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Integer>, JpaSpecificationExecutor<WebSiteInfo> {

    /**
     * 根据电影id查询动态信息
     *
     * @param filmId
     * @return
     */
    @Query(value = "select * from t_info where film_id=?1", nativeQuery = true)
    List<WebSiteInfo> getByFilmId(Integer filmId);

    /**
     * 根据电影网址id查询电影动态信息
     * @param webSiteId
     * @return
     */
    @Query(value = "select * from t_info where web_site_id=?1", nativeQuery = true)
    List<WebSiteInfo> getByWebSiteId(Integer webSiteId);
}
