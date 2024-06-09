package com.demo.Web.controller.noticeBoard;


import com.demo.Web.form.board.BoardListForm;
import com.demo.domain.board.svc.NoticeBoardSVC;
import com.demo.domain.entity.NoticeBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

  @Autowired
  NoticeBoardSVC noticeBoardSVC;

  @GetMapping
  public String boardList(
                          @ModelAttribute BoardListForm boardListForm,
                          Model model){

    log.info("boardListForm = {}", boardListForm);
    List<NoticeBoard> noticeBoards = noticeBoardSVC.getBoardLst(boardListForm);
    int totalCnt = noticeBoardSVC.getBoardLstCnt(boardListForm);

    log.info("noticeBoards = {}", noticeBoards);

    model.addAttribute("totalCnt", totalCnt);
    model.addAttribute("list", noticeBoards);
    model.addAttribute("cpgs", boardListForm.getCpgs());
    model.addAttribute("cp", boardListForm.getCp());


    return "board/boardList";
  }

}
