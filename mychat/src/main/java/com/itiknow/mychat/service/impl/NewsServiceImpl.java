package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.News;
import com.itiknow.mychat.entity.ShortNews;
import com.itiknow.mychat.mapper.NewsMapper;
import com.itiknow.mychat.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    NewsMapper newsMapper;
    @Override
    public Integer insert(News news) {
        return newsMapper.insert(news);
    }

    @Override
    public List<ShortNews> selectAllShortNews() {
        return newsMapper.selectAllShortNews();
    }

    @Override
    public News selectById(Long id) {
        return newsMapper.selectById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return newsMapper.deleteById(id);
    }

    @Override
    public List<ShortNews> selectAllShortNewsCount() {
        return newsMapper.selectAllShortNewsCount();
    }

    @Override
    public Integer addCount(Long id) {
        return newsMapper.addCount(id);
    }
}
