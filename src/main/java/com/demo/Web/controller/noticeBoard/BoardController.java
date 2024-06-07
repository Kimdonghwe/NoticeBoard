package com.demo.Web.controller.noticeBoard;


import com.demo.Web.form.board.BoardListForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

  @GetMapping
  public String boardList(
                          @ModelAttribute BoardListForm boardListForm,
                          Model model){


    return "board/boardList";
  }

}
