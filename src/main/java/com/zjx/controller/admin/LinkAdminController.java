package com.zjx.controller.admin;

import com.zjx.entity.Link;
import com.zjx.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-04 15:28
 */
@RequestMapping("/admin/link")
@Controller
public class LinkAdminController {

    @Autowired
    private LinkService linkService;

    @RequestMapping("/toLinkManage")
    public String toAddFilm() {
        return "admin/linkManage";
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        List<Link> linkList = linkService.list(page - 1, rows);    //bootstrap 分页页数从1开始
        Long total = linkService.getCount();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", linkList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加或者修改友情链接
     *
     * @param link
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.PUT})
    @ResponseBody
    public Map<String, Object> save(Link link) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        linkService.save(link);
        resultMap.put("success", true);
        return resultMap;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        Map<String, Object> resultMap = new HashMap<>();
        for (int i = 0; i < idsStr.length; i++) {
            linkService.delete(Integer.parseInt(idsStr[i]));
        }
        resultMap.put("success", true);
        return resultMap;
    }
}
