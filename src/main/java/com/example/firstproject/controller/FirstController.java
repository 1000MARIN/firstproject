package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "ChulJin");  // (key, value) -> 보여줄 페이지 에서 사용 할 변수
        return "greetings"; // templates 안에 greetings.mustache 파일을 찾아 -> 브라우저로 전송!
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");  // (key, value) -> 보여줄 페이지 에서 사용 할 변수
        return "goodbye";   // templates 안에 goodbye.mustache 파일을 찾아 -> 브라우저로 전송!
    }
}
