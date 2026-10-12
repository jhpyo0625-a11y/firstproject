package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {
    private String title;
    private String content;

    public ArticleForm(String title, String content){
        this.title=title;
        this.content=content;
    }

    //직접 추가
    public Article toEntity() {
        return new Article(null, title, content);
    }
    //직접 추가

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


}
