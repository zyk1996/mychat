package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Store;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StoreMapper {
    @Insert("insert into store(space_id,account) values(#{entity.spaceId},#{entity.account})")
    Integer insert(@Param("entity") Store store);
}
