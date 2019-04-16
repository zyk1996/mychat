package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Room;
import com.itiknow.mychat.entity.User;
import com.itiknow.mychat.entity.UserRoomRel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRoomRelMapper {
    @Select("select * from userroomrel where room=#{userRoomRel.room}" +
            " and account=#{userRoomRel.account}")
    @Results(id = "userRoomRelMap",value = {
            @Result(column = "room",property = "room",javaType = String.class),
            @Result(column = "account",property = "account",javaType = String.class),
            @Result(column = "acc_flag",property = "accFlag",javaType = String.class),
            @Result(column = "starttime",property = "starttime",javaType = Long.class)
    })
    UserRoomRel getByUserAndRoom(@Param("userRoomRel")UserRoomRel userRoomRel);
    @Select("select user.* from userroomrel,user where userroomrel.account=user.account and userroomrel.room=#{id}")
    @ResultMap("com.itiknow.mychat.mapper.UserMapper.userMap")
    List<User> selectUsersByRoom(@Param("id")String id);

    @Insert("insert into userroomrel(room,account) values(#{entity.room},#{entity.account})")
    Integer insert(@Param("entity") UserRoomRel userRoomRel);
    @Select("select t.* from userroomrel,room t where account=#{account} and t.room=userroomrel.room")
    @ResultMap("com.itiknow.mychat.mapper.RoomMapper.roomMap")
    List<Room> selectRooms(@Param("account")String account);
}
