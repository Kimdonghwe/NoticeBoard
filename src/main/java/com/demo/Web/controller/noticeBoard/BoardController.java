package com.demo.Web.controller.noticeBoard;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

  @GetMapping
  public String boardList(
                          @RequestParam(value = "keyword", defaultValue = "") String keyWord,   // 게시글 키워드
                          @RequestParam(value = "codeId",defaultValue = "B0101") String codeId, //게시글 분류
                          @RequestParam(value = "reqPage", defaultValue = "1") Long reqPage, // 요청 페이지
                          @RequestParam(value = "recCnt", defaultValue = "16") Long recCnt,  // 레코드 수
                          @RequestParam(value = "cpgs", defaultValue = "1") Long cpgs, // 페이지 그룹 시작번호
                          @RequestParam(value = "cp", defaultValue = "1") Long cp,
                          Model model){

    return "board/boardList";
  }

}
