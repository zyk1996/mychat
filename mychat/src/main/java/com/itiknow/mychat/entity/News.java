package com.itiknow.mychat.entity;

import lombok.Data;

@Data
public class News {
    private Long id;
    private String title;
    private String date;
    private String company;
    private String content;
    private String flagAttr;
    private String attrSrc;
    private Long createtime;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", company='" + company + '\'' +
                ", content='" + content + '\'' +
                ", flagAttr='" + flagAttr + '\'' +
                ", attrSrc='" + attrSrc + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
