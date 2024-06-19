package com.demo.domain.prefer.svc;

public interface PreferSVC {

  void toggleLike(Long managementId, Long noticeboardId);

  boolean isLiked(Long managementId, Long noticeboardId);

  int getGood(Long noticeboardId);
}
