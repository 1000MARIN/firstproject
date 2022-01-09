package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j  // 로깅을 위한 골뱅이(어노테이션)
public class ArticleController {

    @Autowired  // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;

    @GetMapping ("/articles/new")
    public String newArticleForm() {

        return "articles/new";  // articles/new.mustache
    }

    @PostMapping("/articles/create")
    public String createArticles(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form.toString());  -> 로깅기능으로 대체

        // 1. Dto를 변환 ! Entity!
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article);  -> 로깅기능으로 대체

        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString()); -> 로깅기능으로 대체

        return "redirect:/articles/" + saved.getId();

    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";     // articles/show.mustache
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 Article을 가져온다!
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 Article 묶음으로 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지를 설정
        return "articles/index";    // articles/index.mustache
    }
}