package com.demo.Web.controller.noticeBoard;


import com.demo.Web.form.board.AddBoardForm;
import com.demo.Web.form.board.BoardListForm;
import com.demo.Web.form.member.LoginForm;
import com.demo.Web.form.member.SessionConst;
import com.demo.domain.board.svc.NoticeBoardSVC;
import com.demo.domain.entity.NoticeBoard;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

  @Autowired
  NoticeBoardSVC noticeBoardSVC;

  @GetMapping
  public String boardList( HttpServletRequest request,
                          @ModelAttribute BoardListForm boardListForm,
                          Model model){
    HttpSession session = request.getSession(false);  // Get existing session, do not create a new one
    // Retrieve the LoginMember object from the session
    LoginForm loginMember = (LoginForm) session.getAttribute(SessionConst.LOGIN_MEMBER);

    List<NoticeBoard> noticeBoards = noticeBoardSVC.getBoardLst(boardListForm);
    int totalCnt = noticeBoardSVC.getBoardLstCnt(boardListForm);

    model.addAttribute("session",session);
    model.addAttribute("keyword", boardListForm.getKeyword());
    model.addAttribute("codeId", boardListForm.getCodeId());
    model.addAttribute("totalCnt", totalCnt);
    model.addAttribute("list", noticeBoards);
    model.addAttribute("cpgs", boardListForm.getCpgs());
    model.addAttribute("cp", boardListForm.getCp());


    return "board/boardList";
  }

  @GetMapping("add")
  public String addBoard( HttpServletRequest request,
          Model model){

    HttpSession session = request.getSession(false);  // Get existing session, do not create a new one
    // Retrieve the LoginMember object from the session
    LoginForm loginMember = (LoginForm) session.getAttribute(SessionConst.LOGIN_MEMBER);

    model.addAttribute("nicknmae", loginMember.getNickname());
    model.addAttribute("managementId", loginMember.getManagementId());
    model.addAttribute("email", loginMember.getEmail());

    return "board/addBoard";
  }

  @PostMapping("/add")
  public String submitForm(HttpServletRequest request, @ModelAttribute AddBoardForm addBoardForm, Model model
  , RedirectAttributes redirectAttributes) {

    HttpSession session = request.getSession(false);  // Get existing session, do not create a new one
    // Retrieve the LoginMember object from the session
    LoginForm loginMember = (LoginForm) session.getAttribute(SessionConst.LOGIN_MEMBER);

    Long noticeboardId = noticeBoardSVC.addBoard(addBoardForm,loginMember);

    noticeBoardSVC.getBoardBynoticeboardId(noticeboardId);
    
    // 리다이렉트 시 noticeboardId를 파라미터로 넘겨주기 위해 RedirectAttributes 사용
    redirectAttributes.addAttribute("noticeboardId", noticeboardId);

    // Redirect or return a view name
    return "redirect:/board/detail/{noticeboardId}";
  }

  @GetMapping("/detail/{noticeboardId}")
  public String getDeatilBoard(@PathVariable("noticeboardId") Long noticeboardId, Model model) {
    log.info("noticeboardId = {} ", noticeboardId);

    NoticeBoard noticeBoard = noticeBoardSVC.getBoardBynoticeboardId(noticeboardId);

    model.addAttribute("noticeBoard", noticeBoard);

    return "board/boardDetail";
  }

}
