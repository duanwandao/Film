package com.zjx.respository;

import com.zjx.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-09 12:15
 */
public interface WebSiteRepository extends JpaRepository<WebSite,Integer>, JpaSpecificationExecutor<WebSite> {
}
