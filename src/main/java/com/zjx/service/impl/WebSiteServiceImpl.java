package com.zjx.service.impl;

import com.zjx.entity.WebSite;
import com.zjx.respository.WebSiteRepository;
import com.zjx.service.WebSiteService;
import com.zjx.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-09 12:20
 */
@Service("webSiteService")
public class WebSiteServiceImpl implements WebSiteService {

    @Autowired
    private WebSiteRepository webSiteRepository;

    @Override
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");
        Page<WebSite> pageWebSite = webSiteRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (webSite != null) {
                if (StringUtil.isNotEmpty(webSite.getName())) {
                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + webSite.getName().trim() + "%"));
                }
                if (StringUtil.isNotEmpty(webSite.getUrl())) {
                    predicate.getExpressions().add(cb.like(root.get("url"), "%" + webSite.getUrl().trim() + "%"));
                }
            }
            return predicate;
        }, pageable);
        return pageWebSite.getContent();
    }

    @Override
    public Long getCount(WebSite webSite) {
        Long count = webSiteRepository.count(new Specification<WebSite>() {
            @Override
            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSite != null) {
                    if (StringUtil.isNotEmpty(webSite.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + webSite.getName().trim() + "%"));
                    }
                    if (StringUtil.isNotEmpty(webSite.getUrl())) {
                        predicate.getExpressions().add(cb.like(root.get("url"), "%" + webSite.getUrl().trim() + "%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public void save(WebSite webSite) {
        webSiteRepository.save(webSite);
    }

    @Override
    public void delete(Integer id) {
        webSiteRepository.delete(id);
    }

    @Override
    public WebSite findById(Integer id) {
        return webSiteRepository.findOne(id);
    }

}
