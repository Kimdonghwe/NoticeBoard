package com.demo.domain.rbbs.svc;

import com.demo.Web.form.board.CommentForm;
import com.demo.domain.entity.Rbbs;
import com.demo.domain.rbbs.dao.RbbsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbbsSVCImpl implements  RbbsSVC{
  @Autowired
  RbbsDAO rbbsDAO;

  @Override
  public List<Rbbs> getCommentsBynoticeboardId(Long noticeboardId) {
    return rbbsDAO.getCommentsBynoticeboardId(noticeboardId);
  }

  @Override
  public int insertComment(CommentForm commentForm) {
    return rbbsDAO.insertComment(commentForm);
  }

  @Override
  public int updateComment(CommentForm commentForm) {
    return rbbsDAO.updateComment(commentForm);
  }

  @Override
  public int deleteComment(Long commentId) {
    return rbbsDAO.deleteComment(commentId);
  }

  @Override
  public int getCommentCntBynoticeboardId(Long noticeboardId) {
    return rbbsDAO.getCommentCntBynoticeboardId(noticeboardId);
  }
}
