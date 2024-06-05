package com.demo.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {

  private Long managementId; //MANAGEMENT_ID    NUMBER(10) NOT NULL,
  private  String email; //EMAIL    VARCHAR2(30) NOT NULL,
  private  String pw; //PW    VARCHAR2(20) NOT NULL,
  private  String tel; //TEL    VARCHAR2(11),
  private  String nickname; //NICKNAME    VARCHAR2(36) NOT NULL,
  private  String gender; //GENDER    VARCHAR2(3),
  private  String address; //ADDRESS    VARCHAR2(120),
  private LocalDateTime udate; //UDATE    TIMESTAMP DEFAULT SYSDATE NOT NULL,
  private  LocalDateTime cdate; //CDATE    TIMESTAMP DEFAULT SYSDATE NOT NULL

}
