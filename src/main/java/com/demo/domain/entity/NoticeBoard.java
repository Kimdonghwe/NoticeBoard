package com.demo.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class NoticeBoard {

  private Long noticeboardId;
  private Long managementId;
  private String title;
  private String codeId;
  private String nickname;
  private String bcontent;
  private Long hit;
  private Long preferenceId;
  private LocalDateTime cdate;
  private LocalDateTime udate;

}
