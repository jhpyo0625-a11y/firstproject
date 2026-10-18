package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Slf4j//simple Logging Facade for java
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
    public String createArticle(ArticleForm form){//form태그 데이터를 DTO로 받음
        //System.out.println(form.toString());//DTO에 폼 데이터가 잘 담겼는지 체크하는 함수 호출
        log.info(form.toString());
        //log.debug(form.toString());

        //1. DTO->Entity 변한
        Article article=form.toEntity();
        log.trace(article.toString());

        //2. Repo -> Entity -> DB 저장
        Article saved = articleRepository.save(article);//DB에 저장하는 코드 1줄
        log.info(saved.toString());

        // 사용자가 작성한 게시글 상세페이지
        // 제목, 내용, id
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        //GetMapping 으로 들어온 숫자를 DB에서 꺼내서 사용자에게 보여줘야함
        // EX) 1 -> 1번 게시글을 DB에서 조회 -> view 던져줘야함 (addAttribute함수 활용)
        log.info("id = " + id);
        // 1. id를 조회
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 화면 전달
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 모든 데이터 가져오기
        List<Article> articleEntityList = (List<Article>) articleRepository.findAll();

        // 모델에 등록
        model.addAttribute("articleList",articleEntityList);

        // 화면 전달
        return "articles/index";
    }
}