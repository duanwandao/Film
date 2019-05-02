package com.zjx.controller.admin;

import com.zjx.entity.Film;
import com.zjx.service.FilmService;
import com.zjx.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/toAddFilm")
    public String toAddFilm(){
        return "admin/addFilm";
    }

    /**
     * 添加或者修改电影信息
     * @param film
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String,Object> save(Film film, @RequestParam("imageFile")MultipartFile file)throws  Exception {
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
    public String ckeditorUpload(@RequestParam("upload")MultipartFile file, String CKEditorFuncNum)throws Exception{
        String fileName = file.getOriginalFilename();  // 获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 获取文件的后缀
        String newFileName= DateUtil.getCurrentDateStr()+suffixName;

        FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imageFilePath+newFileName));
        //file.transferTo(new File(imageFilePath+newFileName));  以前用的是这种，springMVC封装的方法

        //注意图片上传后可能需要停几秒才会显示，如果操作太快会发现没有图片显示，这并不是bug。
        //最好上传后直接地址栏进图片地址查看是否404，太快了会404，停一会就好了
        //还有一点是注意路径可能被拦截，要配置好路径
        StringBuffer sb=new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/filmImages/"+ newFileName + "','')");
        sb.append("</script>");

        return sb.toString();
    }
}
