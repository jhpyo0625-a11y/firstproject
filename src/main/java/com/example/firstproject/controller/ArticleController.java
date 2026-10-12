package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    //의존성 주입 = DI (dependency injection)
    @Autowired//스프링부트가 만들어놓은 레포지토리 객체 주입(DI)
    private ArticleRepository articleRepository;//객체 선언

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());

        //1. DTO->Entity 변한
        Article article=form.toEntity();
        System.out.println(article.toString());

        //2. Repo -> Entity -> DB 저장
        Article saved = articleRepository.save(article);//DB에 저장하는 코드 1줄
        System.out.println(saved.toString());

        return "";
    }
}
