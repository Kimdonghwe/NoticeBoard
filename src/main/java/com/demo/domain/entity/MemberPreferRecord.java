package com.demo.domain.entity;

import lombok.Data;

@Data
public class MemberPreferRecord {

  private Long preferId;
  private Long managementId;
  private Long noticeboardId;
  private int pfCheck;
}
