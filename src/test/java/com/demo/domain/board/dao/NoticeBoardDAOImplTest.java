package com.demo.domain.board.dao;

import com.demo.Web.form.board.AddBoardForm;
import com.demo.Web.form.board.BoardListForm;
import com.demo.Web.form.member.LoginForm;
import com.demo.domain.entity.NoticeBoard;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class NoticeBoardDAOImplTest {

  @Autowired
  NoticeBoardDAO noticeBoardDAO;

  @Test
  @DisplayName("게시글리스트")
  void getBoardLst() {

    BoardListForm boardListForm = new BoardListForm();
    List<NoticeBoard> list = noticeBoardDAO.getBoardLst(boardListForm);
    
    list.forEach(ele -> log.info("ele = {} ", ele));

  }

  @Test
  @DisplayName("게시글저장")
  void addBoard() {

    for(int i=0 ; i< 100; i++) {
      LoginForm loginForm = new LoginForm(22L, "hoki", "kdh1@naver.com");

      // AddBoardForm 객체 생성 및 값 할당
      AddBoardForm addBoardForm = new AddBoardForm();
      addBoardForm.setSelectGubun("B0102");
      addBoardForm.setTitle("Solution"+" "+i);
      addBoardForm.setContent("공략"+" "+i);

      log.info("noticeBoardId = {} ", noticeBoardDAO.addBoard(addBoardForm, loginForm));

    }
  }

  @Test
  @DisplayName("게시글입력")
  void getBoardBynoticeboardId() {


    log.info("noticeboard = {} ", noticeBoardDAO.getBoardBynoticeboardId(22l));

  }

  @Test
  @DisplayName("게시글수정")
  void updateBoardBynoticeboardId() {
    AddBoardForm addBoardForm = new AddBoardForm();
    addBoardForm.setContent("helohelohelo");
    addBoardForm.setSelectGubun("B0102");
    addBoardForm.setTitle("테스트용");
    Long noticeboardId = 141l;
    Long managementId = 1l;

    log.info("cnt = {} ", noticeBoardDAO.updateBoardBynoticeboardId(addBoardForm,managementId,noticeboardId));
    
  }
}