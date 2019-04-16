package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Admin;
import com.itiknow.mychat.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    @Select("select * from admin where account=#{admin.account} " +
            "and password=#{admin.password}")
    @Results(id = "adminMap",value = {
            @Result(column = "account", property = "account", javaType = String.class, id = true),
            @Result(column = "password", property = "password", javaType = String.class)
    })
    Admin adminLogin(@Param("admin")Admin admin);

}
