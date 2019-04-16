package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.UserRel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRelMapper {
    @Select("select * from userrel where primary_account=#{userRel.primaryAccount}" +
            " and secondary_account=#{userRel.secondaryAccount}")
    @Results(id = "userRelMap",value = {
            @Result(column = "primary_account",property = "primaryAccount",javaType = String.class),
            @Result(column = "secondary_account",property = "secondaryAccount",javaType = String.class),
            @Result(column = "createtime",property = "createtime",javaType = Long.class),
            @Result(column = "mark",property = "mark",javaType = String.class),
            @Result(column = "flag_black",property = "flagBlack",javaType = String.class)
    })
    UserRel get(@Param("userRel")UserRel userRel);
    @Insert("insert into userrel(primary_account,secondary_account,createtime) values(" +
            "#{entity.primaryAccount},#{entity.secondaryAccount},#{entity.createtime})")
    Integer insert(@Param("entity")UserRel userRel);
    @Select("select user.* from userrel,user where primary_account=#{account} and secondary_account=account")
    @ResultMap("com.itiknow.mychat.mapper.UserMapper.userMap")
    List<User> selectUsers(@Param("account")String account);
}
