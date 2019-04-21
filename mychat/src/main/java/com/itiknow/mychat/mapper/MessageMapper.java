package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Message;
import com.itiknow.mychat.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {
    @Insert("insert into message(account_from,account_to,createtime,content,type,flag_room,room)" +
            " values(#{message.accountFrom},#{message.accountTo},#{message.createtime},#{message.content},#{message.type},#{message.flagRoom},#{message.room})")
    @Options(keyProperty = "id",keyColumn = "id",useGeneratedKeys = true)
    Integer insert(@Param("message") Message message);
    @Select("select * from message where ( (account_from=#{from} and account_to=#{to}) or (account_from=#{to} and account_to=#{from}) ) and flag_room='0' order by createtime ")
    @Results(id = "messageMap",value = {
            @Result(column = "account_from",property = "accountFrom",javaType = String.class),
            @Result(column = "account_to",property = "accountTo",javaType = String.class),
            @Result(column = "createtime",property = "createtime",javaType = Long.class),
            @Result(column = "content",property = "content",javaType = String.class),
            @Result(column = "type",property = "type",javaType = String.class),
            @Result(column = "flag_room",property = "flagRoom",javaType = String.class),
            @Result(column = "room",property = "room",javaType = String.class),
            @Result(column = "account_from",property = "fromObject",javaType = User.class,one = @One(select = "com.itiknow.mychat.mapper.UserMapper.getUserByAccount")),
    })
    List<Message> selectUserMessage(@Param("from")String accountFrom,@Param("to")String accountTo);
    @Select("select * from message where flag_room='1' and room=#{id} order by createtime")
    @ResultMap("messageMap")
    List<Message> selectRoomMessage(@Param("id")String room);
}
