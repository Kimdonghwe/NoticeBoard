package com.demo.domain.board.dao;

import com.demo.Web.form.board.AddBoardForm;
import com.demo.Web.form.board.BoardListForm;
import com.demo.Web.form.member.LoginForm;
import com.demo.domain.entity.NoticeBoard;

import java.util.List;

public interface NoticeBoardDAO {


  List<NoticeBoard> getBoardLst(BoardListForm boardListForm);

  int getBoardLstCnt(BoardListForm boardListForm);

  Long addBoard(AddBoardForm addBoardForm, LoginForm loginForm);

  NoticeBoard getBoardBynoticeboardId(Long noticeboardId);

  int updateBoardBynoticeboardId(AddBoardForm addBoardForm, Long managementId, Long noticeboardId);

  int deleteBoardBynoticeboardId(Long managementId, Long noticeboardId);

  void calucateHit(Long noticeboardId);
}
