package com.zjx.service.impl;

import com.zjx.entity.Link;
import com.zjx.respository.LinkRepository;
import com.zjx.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-04 15:20
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Override
    public List<Link> list(Integer page, Integer pageSize) {
        return linkRepository.findAll(new PageRequest(page, pageSize)).getContent();
    }

    @Override
    public Long getCount() {
        return linkRepository.count();
    }

    @Override
    public void save(Link link) {
        linkRepository.save(link);
    }

    @Override
    public void delete(Integer id) {
        linkRepository.delete(id);
    }
}
