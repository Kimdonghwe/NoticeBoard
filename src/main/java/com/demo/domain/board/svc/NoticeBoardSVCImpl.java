package com.demo.domain.board.svc;

import com.demo.Web.form.board.BoardListForm;
import com.demo.domain.board.dao.NoticeBoardDAO;
import com.demo.domain.entity.NoticeBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeBoardSVCImpl implements NoticeBoardSVC{
  @Autowired
  NoticeBoardDAO noticeBoardDAO;

  //  게시글 리스트 추출
  @Override
  public List<NoticeBoard> getBoardLst(BoardListForm boardListForm) {
    return noticeBoardDAO.getBoardLst(boardListForm);
  }
  //  추출한 게시글 개수 반환
  @Override
  public int getBoardLstCnt(BoardListForm boardListForm) {
    return noticeBoardDAO.getBoardLstCnt(boardListForm);
  }
}
