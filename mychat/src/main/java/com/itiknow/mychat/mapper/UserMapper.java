package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user " +
            "where account=#{user.account} and password=#{user.password}")
    @Results(id="userMap",value={
            @Result(column = "account",property = "account",javaType = String.class,id=true),
            @Result(column = "createtime",property = "createtime",javaType = Long.class),
            @Result(column = "flag_vip",property = "flagVip",javaType = String.class),
            @Result(column = "time_start_vip",property = "timeStartVip",javaType = Long.class),
            @Result(column = "time_vip",property = "timeVip",javaType = Long.class),
            @Result(column = "name",property = "name",javaType = String.class),
            @Result(column = "birthday",property = "birthday",javaType = String.class),
            @Result(column = "sex",property = "sex",javaType = String.class),
            @Result(column = "image",property = "image",javaType = String.class),
            @Result(column = "phone",property = "phone",javaType = String.class)
    })
    User userLogin(@Param("user") User user);
    @Insert("insert into user(account,password,phone) " +
            "values(#{user.account},#{user.password},#{user.phone})")
    Integer userRegister(@Param("user")User user);
    @Select("select * from user " +
            "where account=#{user.account} and phone=#{user.phone}")
    @ResultMap(value="userMap")
    User getUserByAccountAndPhone(@Param("user") User user);
    @Select("select * from user " +
            "where account=#{user.account}")
    @ResultMap(value="userMap")
    User getUserByAccount(@Param("user") User user);
    @Update("update user set password=#{user.password} where account=#{user.account}")
    Integer updateUserPassword(@Param("user") User user);
    @Update("update user set flag_vip=#{user.flagVip},time_start_vip=#{user.timeStartVip} where account=#{user.account}")
    Integer updateUserFlagVip(@Param("user") User user);
    @Update("update user set image=#{user.image} where account=#{user.account}")
    Integer updateUserImage(@Param("user") User user);
    @Update("update user set name=#{user.name},phone=#{user.phone}," +
            "birthday=#{user.birthday},sex=#{user.sex} where account=#{user.account}")
    Integer updateUserInfo(@Param("user")User user);

}
