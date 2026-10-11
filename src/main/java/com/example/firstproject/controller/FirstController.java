package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","홍길동");
        return "greeting";
    }

    @GetMapping("/bye~")
    public String seeYouAgain(Model model){
        model.addAttribute("username","홍길동");
        return "goodbye";
    }

    public String randomQuote(Model model) {
        String[] quotes = {
                "살아 있는 한 희망은 있다 -키케로",
                "산다는 것 그것은 치열한 전투이다 -로망로랑",
                "하루에 3시간을 걸으면 7년 후에 지구를 한바퀴 돌 수 있다. -사무엘존슨",
                "언제나 현재에 집중할 수 있다면 행복할 것이다. -파울로 코엘료",
                "진정으로 웃으려면 고통을 참아야하며, 나아가 고통을 즐길 줄 알아야 해 -찰리 채플린",
                "직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다. -엘버트 하버드",
                "신은 용기있는자를 결코 버리지 않는다 -켄러",
                "행복의 문이 하나 닫히면 다른 문이 열린다 그러나 우리는 종종 닫힌 문을 멍하니 바라보다가 우리를 향해 열린 문을 보지 못하게 된다 -헬렌켈러",
                "피할 수 없으면 즐겨라 -로버트 엘리엇"};
        int randInt= (int)(Math.random() * quotes.length);
        model.addAttribute("quotes",quotes[randInt]);
        return "quote";
    }
}
