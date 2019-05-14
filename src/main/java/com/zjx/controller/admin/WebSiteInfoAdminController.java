package com.zjx.controller.admin;

import com.zjx.entity.WebSiteInfo;
import com.zjx.service.WebSiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-13 9:10
 */
@Controller
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {

    @Autowired
    private WebSiteInfoService webSiteInfoService;

    @RequestMapping("/toWebSiteInfoManage")
    public String toWebSiteInfoManage() {
        return "admin/webSiteInfoManage";
    }

    /**
     * 分页查询电影动态信息
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(WebSiteInfo webSiteInfo, @RequestParam(value="page",required=false)Integer page, @RequestParam(value="rows",required=false)Integer rows)throws Exception{
        List<WebSiteInfo> webSiteInfoList=webSiteInfoService.list(webSiteInfo,page-1, rows);
        Long total=webSiteInfoService.getCount(webSiteInfo);
        Map<String,Object> resultMap= new HashMap<>();
        resultMap.put("rows", webSiteInfoList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 删除电影动态信息
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam(value="ids")String ids)throws Exception{
        String []idsStr=ids.split(",");
        Map<String,Object> resultMap= new HashMap<>();
        for(int i=0;i<idsStr.length;i++){
            webSiteInfoService.delete(Integer.parseInt(idsStr[i]));
        }
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 添加电影动态信息
     * @param webSiteInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String,Object> save(WebSiteInfo webSiteInfo)throws Exception{
        webSiteInfo.setPublishDate(new Date());
        Map<String,Object> resultMap= new HashMap<>();
        webSiteInfoService.save(webSiteInfo);
        resultMap.put("success", true);
        return resultMap;
    }

}
