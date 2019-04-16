package com.itiknow.mychat.sql;

import com.itiknow.mychat.entity.News;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DynamicSql {
    public String insertNews(News news){
        return new SQL(){
            {
                INSERT_INTO("news");
                VALUES("company","#{news.company}");
                VALUES("date","#{news.date}");
                VALUES("title","#{news.title}");
                VALUES("content","#{news.content}");
                if(news.getFlagAttr()!=null){
                    VALUES("flag_attr","#{news.flagAttr}");
                }
                if(news.getAttrSrc()!=null){
                    VALUES("attr_src","#{news.attrSrc}");
                }
                VALUES("createtime","#{news.createtime}");
            }
        }.toString();
        /*News news= (News) map.get("bean");
        SQL sql=new SQL();
        sql.INSERT_INTO("news");
        if(news.getId()!=null){
            sql.VALUES("id","#{bean.id}");
        }
        if(news.getCompany()!=null){
            sql.VALUES("company",news.getCompany());
        }
        if(news.getDate()!=null){
            sql.VALUES("date",news.getDate());
        }
        if(news.getTitle()!=null){
            sql.VALUES("title",news.getTitle());
        }
        if(news.getContent()!=null){
            sql.VALUES("content",news.getContent());
        }
        if(news.getFlagAttr()!=null){
            sql.VALUES("flag_attr",news.getFlagAttr());
        }
        if(news.getAttrSrc()!=null){
            sql.VALUES("attr_src",news.getAttrSrc());
        }
        if(news.getCreatetime()!=null){
            sql.VALUES("createtime",String.valueOf(news.getCreatetime()));
        }
        System.err.println(sql.toString());
        return sql.toString();*/

    }
}
