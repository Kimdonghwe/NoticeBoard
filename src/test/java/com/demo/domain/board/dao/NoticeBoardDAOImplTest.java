package com.demo.domain.board.dao;

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
  void getBoardLst() {
    List<NoticeBoard> list = noticeBoardDAO.getBoardLst("B0101","SE");

    log.info("size = {}", list.size());
    list.forEach(ele -> log.info("ele = {}", ele));
  }
}