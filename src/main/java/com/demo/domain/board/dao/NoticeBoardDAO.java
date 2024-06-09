package com.demo.domain.board.dao;

import com.demo.Web.form.board.BoardListForm;
import com.demo.domain.entity.NoticeBoard;

import java.util.List;

public interface NoticeBoardDAO {


  List<NoticeBoard> getBoardLst(BoardListForm boardListForm);

  int getBoardLstCnt(BoardListForm boardListForm);
}
