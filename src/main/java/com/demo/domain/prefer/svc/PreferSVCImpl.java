package com.demo.domain.prefer.svc;

import com.demo.domain.prefer.dao.PreferDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PreferSVCImpl implements PreferSVC{

  @Autowired
  PreferDAO preferDAO;


  @Transactional
  public void toggleLike(Long managementId, Long noticeboardId) {
    if (preferDAO.isLikeAlreadyExists(managementId, noticeboardId)) {
      preferDAO.removeLike(managementId, noticeboardId);
    } else {
      preferDAO.addLike(managementId, noticeboardId);
    }
  }

  public boolean isLiked(Long managementId, Long noticeboardId) {
    return preferDAO.isLikeAlreadyExists(managementId, noticeboardId);
  }

  @Override
  public int getGood(Long noticeboardId) {
    return preferDAO.getGood(noticeboardId);
  }
}
