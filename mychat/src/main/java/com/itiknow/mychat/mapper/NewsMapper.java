package com.itiknow.mychat.mapper;

import com.itiknow.mychat.entity.News;
import com.itiknow.mychat.entity.ShortNews;
import com.itiknow.mychat.sql.DynamicSql;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsMapper {
    @InsertProvider(type = DynamicSql.class,method = "insertNews")
    Integer insert(@Param("news") News news);
    @Select("select id,title,date,company from news order by createtime desc")
    @Results(id="shortNewsMap",value = {
            @Result(id = true,column = "id",property = "id",javaType = Long.class),
            @Result(column = "title",property = "title",javaType = String.class),
            @Result(column = "date",property = "date",javaType = String.class),
            @Result(column = "company",property = "company",javaType = String.class)
    })
    List<ShortNews> selectAllShortNews();
    @Select("select * from news where id=#{id}")
    @Results(id="newsMap",value = {
            @Result(id = true,column = "id",property = "id",javaType = Long.class),
            @Result(column = "title",property = "title",javaType = String.class),
            @Result(column = "date",property = "date",javaType = String.class),
            @Result(column = "company",property = "company",javaType = String.class),
            @Result(column = "content",property = "content",javaType = String.class),
            @Result(column = "flag_attr",property = "flagAttr",javaType = String.class),
            @Result(column = "attr_src",property = "attrSrc",javaType = String.class),
            @Result(column = "createtime",property = "createtime",javaType = Long.class)

    })
    News selectById(@Param("id")Long id);
    @Delete("delete from news where id=#{id}")
    Integer deleteById(@Param("id")Long id);
}
