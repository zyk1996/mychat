package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Praise;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PraiseMapper {

    @Insert("insert into praise(space_id,account) values(#{entity.spaceId},#{entity.account})")
    Integer insert(@Param("entity") Praise praise);
    @Select("select * from praise where space_id=#{space_id}")
    @Results(id = "praiseMap",value = {
            @Result(column = "space_id",property = "spaceId",javaType = Long.class),
            @Result(column = "account",property = "account",javaType = String.class),
            @Result(column = "account",property = "user",one = @One(select = "com.itiknow.mychat.mapper.UserMapper.getUserByAccount"))
    })
    List<Praise> selectPraisesBySpace(@Param("spaceid")Long spaceid);
}
