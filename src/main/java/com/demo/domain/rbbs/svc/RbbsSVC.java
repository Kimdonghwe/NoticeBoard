package com.demo.domain.rbbs.svc;

import com.demo.Web.form.board.CommentForm;
import com.demo.domain.entity.Rbbs;

import java.util.List;

public interface RbbsSVC {

  List<Rbbs> getCommentsBynoticeboardId(Long noticeboardId);
  public int insertComment(CommentForm commentForm);

  public int updateComment(CommentForm commentForm);
  public int deleteComment(Long commentId);

  public int getCommentCntBynoticeboardId(Long noticeboardId);

}
