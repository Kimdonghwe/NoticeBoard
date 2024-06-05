package com.demo.Web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller // Controller 역할을 하는 클래스
public class LoginController {

  @GetMapping("/")
  public String home() {
    return "home/home"; // home.html을 반환
  }




}
