package com.zjx.controller.admin;

import com.zjx.entity.Film;
import com.zjx.service.FilmService;
import com.zjx.service.WebSiteInfoService;
import com.zjx.util.DateUtil;
import com.zjx.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.*;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-01 10:27
 */
@Controller
@RequestMapping("/admin/film")
public class FilmAdminController {

    @Value("${imageFilePath}")
    private String imageFilePath;

    @Autowired
    private FilmService filmService;

    @Autowired
    private WebSiteInfoService webSiteInfoService;

    @RequestMapping("/toAddFilm")
    public String toAddFilm() {
        return "admin/addFilm";
    }

    @RequestMapping("/toFilmManage")
    public String toFilmManage() {
        return "admin/filmManage";
    }

    /**
     * 添加或者修改电影信息
     *
     * @param film
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(Film film, @RequestParam("imageFile") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName));
            film.setImageName(newFileName);
        }
        film.setPublishDate(new Date());
        filmService.save(film);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }


    @RequestMapping("/ckeditorUpload")
    @ResponseBody
    public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws Exception {
        String fileName = file.getOriginalFilename();  // 获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 获取文件的后缀
        String newFileName = DateUtil.getCurrentDateStr() + suffixName;

        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName));
        //file.transferTo(new File(imageFilePath+newFileName));  以前用的是这种，springMVC封装的方法

        //注意图片上传后可能需要停几秒才会显示，如果操作太快会发现没有图片显示，这并不是bug。
        //最好上传后直接地址栏进图片地址查看是否404，太快了会404，停一会就好了
        //还有一点是注意路径可能被拦截，要配置好路径
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/filmImages/" + newFileName + "','')");
        sb.append("</script>");

        return sb.toString();
    }

    /**
     * 分页查询电影信息
     *
     * @param film
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(Film film, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        List<Film> filmList = filmService.list(film, page - 1, rows);
        Long total = filmService.getCount(film);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", filmList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 删除电影信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) {
        String[] idsStr = ids.split(",");
        StringBuilder filmNames = new StringBuilder();  //未能删除的电影名字
        int notDeteleNumber = 0;
        for (String id : idsStr) {
            int filmId = Integer.parseInt(id);
            if (webSiteInfoService.getByFilmId(filmId).size() > 0) {
                String filmName = filmService.findById(filmId).getName();
                filmNames.append("《" + filmName + "》,");
                notDeteleNumber++;
            } else {
                filmService.delete(filmId);
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        if (filmNames.length() > 0) {
            resultMap.put("filmNames", filmNames.substring(0, (filmNames.length() - 1)));
            resultMap.put("notDeleteAll", true);
            resultMap.put("notDeteleNumber", notDeteleNumber);
        }
        return resultMap;
    }

    @RequestMapping("/toModifyFilm")
    public ModelAndView toModifyFilm(@RequestParam(value = "id", required = false) Integer id, ModelAndView modelAndView) {
        Film film = filmService.findById(id);
        modelAndView.addObject("film", film);
        modelAndView.setViewName("admin/modifyFilm");
        return modelAndView;
    }

    /**
     * 下拉框模糊查询用到
     * @param q
     * @return
     */
    @RequestMapping("/comboList")
    @ResponseBody
    public List<Film> comboList(String q) {
        if (StringUtil.isEmpty(q)) {
            return null;
        }
        Film film = new Film();
        film.setName(q);
        List<Film> filmList = filmService.list(film, 0, 30);  // 最多查询30条记录
        return filmList;
    }

}
