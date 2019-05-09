package com.zjx.controller.admin;

import com.zjx.entity.WebSite;
import com.zjx.service.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-09 15:04
 */
@Controller
@RequestMapping("/admin/webSite")
public class WebSiteAdminController {

    @Autowired
    private WebSiteService webSiteService;

    @RequestMapping("/toWebSiteManage")
    public String toAddFilm() {
        return "admin/webSiteManage";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(WebSite webSite, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        List<WebSite> webSiteList = webSiteService.list(webSite, page - 1, rows);
        Long total = webSiteService.getCount(webSite);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", webSiteList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加或者修改收录电影网址
     *
     * @param webSite
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(WebSite webSite) throws Exception {
        webSiteService.save(webSite);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 删除收录电影网址
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            webSiteService.delete(Integer.parseInt(id));
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }
}
