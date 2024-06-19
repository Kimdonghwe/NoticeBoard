package com.demo.domain.prefer.dao;

public interface PreferDAO {

  boolean isLikeAlreadyExists(Long managementId, Long noticeboardId);

  void addLike(Long managementId, Long noticeboardId);

  void removeLike(Long managementId, Long noticeboardId);

  public int getGood(Long noticeboardId);
}
