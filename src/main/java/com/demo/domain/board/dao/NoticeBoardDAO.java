package com.demo.domain.board.dao;

import com.demo.domain.entity.NoticeBoard;

import java.util.List;

public interface NoticeBoardDAO {


  List<NoticeBoard> getBoardLst(String codeId, String keyword);
}
