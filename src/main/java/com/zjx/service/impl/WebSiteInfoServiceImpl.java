package com.zjx.service.impl;

import com.zjx.entity.WebSiteInfo;
import com.zjx.respository.WebSiteInfoRepository;
import com.zjx.service.WebSiteInfoService;
import com.zjx.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-13 8:57
 */
@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    @Autowired
    private WebSiteInfoRepository webSiteInfoRepository;

    @Override
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "publishDate");
        Page<WebSiteInfo> webSiteInfoPage = webSiteInfoRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (webSiteInfo != null) {
                if (StringUtil.isNotEmpty(webSiteInfo.getInfo())) {
                    predicate.getExpressions().add(cb.like(root.get("info"), "%" + webSiteInfo.getInfo().trim() + "%"));
                }
            }
            return predicate;
        }, pageable);
        return webSiteInfoPage.getContent();
    }

    @Override
    public Long getCount(WebSiteInfo webSiteInfo) {
        Long count = webSiteInfoRepository.count((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (webSiteInfo != null) {
                if (StringUtil.isNotEmpty(webSiteInfo.getInfo())) {
                    predicate.getExpressions().add(cb.like(root.get("info"), "%" + webSiteInfo.getInfo().trim() + "%"));
                }
            }
            return predicate;
        });
        return count;
    }

    @Override
    public List<WebSiteInfo> getByFilmId(Integer filmId) {
        return webSiteInfoRepository.getByFilmId(filmId);
    }

    @Override
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId) {
        return webSiteInfoRepository.getByWebSiteId(webSiteId);
    }

    @Override
    public void save(WebSiteInfo webSiteInfo) {
        webSiteInfoRepository.save(webSiteInfo);
    }

    @Override
    public void delete(Integer id) {
        webSiteInfoRepository.delete(id);
    }
}
