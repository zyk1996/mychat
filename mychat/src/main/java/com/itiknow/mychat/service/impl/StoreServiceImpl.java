package com.itiknow.mychat.service.impl;

import com.itiknow.mychat.entity.Store;
import com.itiknow.mychat.mapper.StoreMapper;
import com.itiknow.mychat.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements IStoreService {
    @Autowired
    StoreMapper storeMapper;
    @Override
    public Integer insert(Store store) {
        return storeMapper.insert(store);
    }
}
