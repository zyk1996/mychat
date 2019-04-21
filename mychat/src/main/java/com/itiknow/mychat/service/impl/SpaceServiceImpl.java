package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.Space;
import com.itiknow.mychat.mapper.SpaceMapper;
import com.itiknow.mychat.service.ISpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpaceServiceImpl implements ISpaceService {
    @Autowired
    SpaceMapper spaceMapper;
    @Override
    public List<Space> selectByAccountMySelf(String account) {
        return spaceMapper.selectByAccountMySelf(account);
    }

    @Override
    public Integer insert(Space space) {
        return spaceMapper.insert(space);
    }

    @Override
    public Space selectById(Long id) {
        return spaceMapper.selectById(id);
    }

    @Override
    public List<Space> selectSpaceByAccountAll(String account) {
        return spaceMapper.selectSpaceByAccountAll(account);
    }

    @Override
    public Integer deleteById(Long id) {
        return spaceMapper.deleteById(id);
    }

    @Override
    public Integer updateById(Space space) {
        return spaceMapper.updateById(space);
    }

    @Override
    public Space selectByIdWithOutOther(Long id) {
        return spaceMapper.selectByIdWithOutOther(id);
    }

    @Override
    public List<Space> selectSpacesByStore(String account) {
        return spaceMapper.selectSpacesByStore(account);
    }
}
