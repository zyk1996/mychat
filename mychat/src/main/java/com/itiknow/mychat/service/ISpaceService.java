package com.itiknow.mychat.service;

import com.itiknow.mychat.entity.Space;

import java.util.List;

public interface ISpaceService {
    List<Space> selectByAccountMySelf(String account);
    Integer insert(Space space);
    Space selectById(Long id);
    List<Space> selectSpaceByAccountAll(String account);
    Integer deleteById(Long id);

    Integer updateById(Space space);
    Space selectByIdWithOutOther(Long id);
    List<Space> selectSpacesByStore(String account);
}
