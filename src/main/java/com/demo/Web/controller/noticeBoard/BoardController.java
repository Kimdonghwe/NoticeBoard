package com.demo.Web.controller.noticeBoard;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

  @GetMapping
  public String boardList(){

    return "board/boardList";
  }

}
