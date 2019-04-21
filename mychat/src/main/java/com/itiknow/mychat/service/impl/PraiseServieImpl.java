package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.Praise;
import com.itiknow.mychat.mapper.PraiseMapper;
import com.itiknow.mychat.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PraiseServieImpl implements IPraiseService {
    @Autowired
    PraiseMapper praiseMapper;
    @Override
    public Integer insert(Praise praise) {
        return praiseMapper.insert(praise);
    }

    @Override
    public List<Praise> selectPraisesBySpace(Long spaceid) {
        return praiseMapper.selectPraisesBySpace(spaceid);
    }
}
