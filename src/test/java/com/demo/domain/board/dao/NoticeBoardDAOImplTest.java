package com.demo.domain.board.dao;

import com.demo.Web.form.board.BoardListForm;
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
}