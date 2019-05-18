package com.zjx.conf;

import com.zjx.entity.Film;
import com.zjx.service.FilmService;
import com.zjx.service.LinkService;
import com.zjx.service.WebSiteInfoService;
import com.zjx.service.WebSiteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-16 21:01
 */
@Component("startupRunner")
public class StartupRunner implements CommandLineRunner, ServletContextListener {

    private ServletContext application = null;

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Resource
    private LinkService linkService;

    @Resource
    private WebSiteService webSiteService;

    @Override
    public void run(String... args) throws Exception {
        this.loadData();
    }

    /**
     * 加载数据到applicaton缓存中
     */
    public void loadData() {
        application.setAttribute("newestInfoList", webSiteInfoService.list(null, 0, 10)); // 最新10条电影动态
        Film film = new Film();
        film.setHot(1);
        application.setAttribute("newestHotFilmList", filmService.list(film, 0, 10)); // 获取最新10条热门电影
        application.setAttribute("newestIndexHotFilmList", filmService.list(film, 0, 32)); // 获取最新32条热门电影 首页显示用到
        application.setAttribute("newestWebSiteList", webSiteService.newestList(0, 10)); // 获取最新10条电影网站收录
        application.setAttribute("newestFilmList", filmService.list(null, 0, 10)); // 获取最新10条电影信息
        application.setAttribute("linkList", linkService.listAll()); // 查询所有友情链接
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        application = servletContextEvent.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
