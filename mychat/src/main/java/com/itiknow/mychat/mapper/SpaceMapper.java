package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.Space;
import com.itiknow.mychat.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpaceMapper {
    @Select("select * from space where account_from=#{account} order by createtime desc")
    @ResultMap("spaceMap")
    List<Space> selectByAccountMySelf(@Param("account")String account);
    @Insert("insert into space(account_from,content,createtime,datetime,flag_addr,addr,flag_attr,attr_src" +
            ") values(#{entity.accountFrom},#{entity.content},#{entity.createtime}," +
            "#{entity.datetime},#{entity.flagAddr},#{entity.addr},#{entity.flagAttr},#{entity.attrSrc})")
    @Options(keyProperty = "id",useGeneratedKeys = true)
    Integer insert(@Param("entity")Space space);
    @Select("select * from space where id=#{id}")
    @Results(id = "spaceMap",value = {
            @Result(column = "id",property = "id",javaType = Long.class),
            @Result(column = "account_from",property = "accountFrom",javaType = String.class),
            @Result(column = "content",property = "content",javaType = String.class),
            @Result(column = "createtime",property = "createtime",javaType = Long.class),
            @Result(column = "datetime",property = "datetime",javaType = String.class),
            @Result(column = "flag_addr",property = "flagAddr",javaType = String.class),
            @Result(column = "addr",property = "addr",javaType = String.class),
            @Result(column = "flag_attr",property = "flagAttr",javaType = String.class),
            @Result(column = "attr_src",property = "attrSrc",javaType = String.class),
            @Result(column = "account_from",property = "user",one = @One(select = "com.itiknow.mychat.mapper.UserMapper.getUserByAccount"),javaType = User.class),
            @Result(column = "id",property = "comments",many = @Many(select = "com.itiknow.mychat.mapper.CommentMapper.selectCommitsBySpace"),javaType = List.class),
            @Result(column = "id",property = "praises",many = @Many(select = "com.itiknow.mychat.mapper.PraiseMapper.selectPraisesBySpace"),javaType = List.class)
    })
    Space selectById(@Param("id")Long id);
    @Select("select * from space where id=#{id}")
    @Results(id = "singleSpaceMap",value = {
            @Result(column = "id",property = "id",javaType = Long.class),
            @Result(column = "account_from",property = "accountFrom",javaType = String.class),
            @Result(column = "content",property = "content",javaType = String.class),
            @Result(column = "createtime",property = "createtime",javaType = Long.class),
            @Result(column = "datetime",property = "datetime",javaType = String.class),
            @Result(column = "flag_addr",property = "flagAddr",javaType = String.class),
            @Result(column = "addr",property = "addr",javaType = String.class),
            @Result(column = "flag_attr",property = "flagAttr",javaType = String.class),
            @Result(column = "attr_src",property = "attrSrc",javaType = String.class),
    })
    Space selectByIdWithOutOther(@Param("id")Long id);
    @Select("select distinct space.* from space,userrel where (account_from=#{account}) or (userrel.primary_account=#{account} and account_from=userrel.secondary_account) order by space.createtime desc")
    @ResultMap("spaceMap")
    List<Space> selectSpaceByAccountAll(@Param("account")String account);
    @Delete("delete from space where id=#{id}")
    Integer deleteById(@Param("id")Long id);

    @Update("update space set content=#{entity.content},flag_addr=#{entity.flagAddr},addr=#{entity.addr}" +
            " where id=#{entity.id}")
    Integer updateById(@Param("entity") Space space);
    @Select("select space.* from space,store where store.account=#{account} and store.space_id=space.id")
    @ResultMap("spaceMap")
    List<Space> selectSpacesByStore(@Param("account")String account);
}
