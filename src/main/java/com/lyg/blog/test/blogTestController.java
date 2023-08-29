package com.lyg.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller // 스프링이 com.lyg.blog 패키지 이하를 스캔해서 모든 파일은 스캔하여 특정 어노테이션이 붙어있는 클래스 파일들을 스프링 컨테이너에 관리해줌
@RestController
public class blogTestController {

    @GetMapping("/test")
    public String hello() {
        return "<h1>hi</h1>";
    }
}
