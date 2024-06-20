package com.demo.domain.rbbs.dao;

import com.demo.Web.form.board.CommentForm;
import com.demo.domain.entity.Rbbs;

import java.util.List;

public interface RbbsDAO {

  List<Rbbs> getAllComments();
  public int insertComment(CommentForm commentForm);

  public int updateComment(CommentForm commentForm);

  public int deleteComment(Long commentId);

  public int getCommentCntBynoticeboardId(Long noticeboardId);
}
