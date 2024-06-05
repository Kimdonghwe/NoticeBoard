package com.demo.Web.form;

import lombok.Data;

@Data
public class JoinAddForm {

  private  String email; //EMAIL    VARCHAR2(30) NOT NULL,
  private  String pw; //PW    VARCHAR2(20) NOT NULL,
  private  String tel; //TEL    VARCHAR2(11),
  private  String nickname; //NICKNAME    VARCHAR2(36) NOT NULL,
  private  String gender; //GENDER    VARCHAR2(3),
  private  String address; //ADDRESS    VARCHAR2(120),
  private String birthYear;
  private String birthMonth;
  private String birthDay;

}
