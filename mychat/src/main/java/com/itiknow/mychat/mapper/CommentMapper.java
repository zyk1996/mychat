package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Comment;
import com.itiknow.mychat.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    @Select("select * from comment where id=#{id}")
    @Results(id="commentMap",value = {
            @Result(column = "id",property = "id",javaType = Long.class,id=true),
            @Result(column = "space_id",property = "spaceId",javaType = Long.class),
            @Result(column = "account_from",property = "accountFrom",javaType = String.class),
            @Result(column = "account_to",property = "accountTo",javaType = String.class),
            @Result(column = "content",property = "content",javaType = String.class),
            @Result(column = "createtime",property = "createtime",javaType = Long.class),
            @Result(column = "type",property = "type",javaType = String.class),
            @Result(column = "account_from",property = "from",one = @One(select = "com.itiknow.mychat.mapper.UserMapper.getUserByAccount"),javaType = User.class),
            @Result(column = "account_to",property = "to",one = @One(select = "com.itiknow.mychat.mapper.UserMapper.getUserByAccount"),javaType = User.class)
    })
    Comment selectById(@Param("id")String id);
    @Select("select * from comment where space_id=#{spaceId} order by createtime")
    @ResultMap("commentMap")
    List<Comment> selectCommitsBySpace(@Param("spaceId")Long spaceId);
    @Insert("insert into comment(space_id,account_from,account_to,content,createtime,type) values(" +
            "#{entity.spaceId},#{entity.accountFrom},#{entity.accountTo}" +
            ",#{entity.content},#{entity.createtime},#{entity.type})")
    Integer insert(@Param("entity") Comment comment);
}
