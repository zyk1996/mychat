package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Room;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomMapper {
    @Select("select * from room where room=#{id}")
    @Results(id = "roomMap",value = {
            @Result(column = "room",property = "room",javaType = String.class,id = true),
            @Result(column = "create_user",property = "createUser",javaType = String.class),
            @Result(column = "createtime",property = "createtime",javaType = Long.class),
            @Result(column = "image",property = "image",javaType = String.class),
            @Result(column = "name",property = "name",javaType = String.class),
            @Result(column = "room",property = "users",many = @Many(select="com.itiknow.mychat.mapper.UserRoomRelMapper.selectUsersByRoom"),javaType = List.class)
    })
    Room getById(@Param("id")String room);

    @Insert("insert into room(room,createtime,create_user) values(#{room.room},#{room.createtime},#{room.createUser})")
    Integer insertRoom(@Param("room") Room room);
}
