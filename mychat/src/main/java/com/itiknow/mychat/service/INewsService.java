package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.News;
import com.itiknow.mychat.entity.ShortNews;

import java.util.List;

public interface INewsService {
    Integer insert(News news);
    List<ShortNews> selectAllShortNews();
    News selectById(Long id);
    Integer deleteById(Long id);
}
