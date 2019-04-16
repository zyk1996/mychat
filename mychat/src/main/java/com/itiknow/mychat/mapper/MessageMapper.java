package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MessageMapper {
    @Insert("insert into message(account_from,account_to,createtime,content,type,flag_room,room)" +
            " values(#{message.accountFrom},#{message.accountTo},#{message.createtime},#{message.content},#{message.type},#{message.flagRoom},#{message.room})")
    @Options(keyProperty = "id",keyColumn = "id",useGeneratedKeys = true)
    Integer insert(@Param("message") Message message);
}
