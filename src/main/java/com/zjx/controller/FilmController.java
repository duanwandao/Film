package com.zjx.controller;

import com.zjx.entity.Film;
import com.zjx.service.FilmService;
import com.zjx.service.WebSiteInfoService;
import com.zjx.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-18 11:07
 */
@Controller
@RequestMapping("/film")
public class FilmController {

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 搜索电影 简单模糊查询
     * @param s_film
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    public ModelAndView search(@Valid Film s_film, BindingResult bindingResult)throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error", bindingResult.getFieldError().getDefaultMessage());
            modelAndView.addObject("title", "首页");
            modelAndView.addObject("mainPage", "film/indexFilm");
            modelAndView.addObject("mainPageKey", "indexFilm");
            modelAndView.setViewName("index");
        }else{
            List<Film> filmList=filmService.list(s_film, 0, 32);
            modelAndView.addObject("filmList", filmList);
            modelAndView.addObject("title", s_film.getName());
            modelAndView.addObject("mainPage", "film/result");
            modelAndView.addObject("mainPageKey", "result");
            modelAndView.addObject("s_film", s_film);
            modelAndView.addObject("total", filmList.size());
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @RequestMapping("/list/{id}")
    public ModelAndView list(@PathVariable(value = "id")Integer page){
        ModelAndView modelAndView = new ModelAndView();
        List<Film> filmList=filmService.list(null, page-1, 20);
        Long total=filmService.getCount(null);
        modelAndView.addObject("filmList", filmList);
        modelAndView.addObject("pageCode", PageUtil.genPagination("/film/list", total, page, 20));
        modelAndView.addObject("title", "电影列表");
        modelAndView.addObject("mainPage", "film/list");
        modelAndView.addObject("mainPageKey", "#f");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 根据id获取电影详细信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/{id}")
    public ModelAndView view(@PathVariable(value="id")Integer id)throws Exception{
        ModelAndView mav=new ModelAndView();
        Film film=filmService.findById(id);
        mav.addObject("film", film);
        mav.addObject("title", film.getTitle());
        mav.addObject("randomFilmList", filmService.randomList(8));
        mav.addObject("webSiteInfoList", webSiteInfoService.getByFilmId(id));
        mav.addObject("pageCode", this.getUpAndDownPageCode(filmService.getLast(id), filmService.getNext(id)));
        mav.addObject("mainPage", "film/view");
        mav.addObject("mainPageKey", "#f");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 获取下一个电影你和上一个电影
     * @param lastFilm
     * @param nextFilm
     * @return
     */
    private String getUpAndDownPageCode(Film lastFilm,Film nextFilm){
        StringBuffer pageCode=new StringBuffer();
        if(lastFilm==null || lastFilm.getId()==null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else{
            pageCode.append("<p>上一篇：<a href='/film/"+lastFilm.getId()+"'>"+lastFilm.getTitle()+"</a></p>");
        }
        if(nextFilm==null || nextFilm.getId()==null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else{
            pageCode.append("<p>下一篇：<a href='/film/"+nextFilm.getId()+"'>"+nextFilm.getTitle()+"</a></p>");
        }
        return pageCode.toString();
    }
}
