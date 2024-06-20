package com.demo.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Rbbs {
  private Long commentId;
  private Long noticeboardId;
  private Long managementId;
  private String nickname;
  private String content;
  private LocalDateTime cdate;
  private LocalDateTime udate;
}
