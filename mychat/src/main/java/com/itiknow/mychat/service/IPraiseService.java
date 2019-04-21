package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.Praise;

import java.util.List;

public interface IPraiseService {
    Integer insert(Praise praise);
    List<Praise> selectPraisesBySpace(Long spaceid);
}
