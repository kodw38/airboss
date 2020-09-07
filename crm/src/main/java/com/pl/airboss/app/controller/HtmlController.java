package com.pl.airboss.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by admin on 2020/4/15.
 */
@Controller
public class HtmlController {
    @RequestMapping("/")
    public String login(Map<String,Object> map){
        //map.put("hello","<h2>你好<h2>");
        //map.put("users", Arrays.asList("zhangsan","lishi","wangwu"));
        return "index";
    }
}
