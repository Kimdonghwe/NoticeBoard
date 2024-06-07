package com.demo.Web.form.board;

import lombok.Data;

@Data
public class BoardListForm {
  private  String keyWord="";
  private   String codeId ="B0101" ;//게시글 분류
  private Long reqPage =1L; // ;요청 페이지
  private  Long recCnt =16L;  //; 레코드 수
  private Long cpgs = 1L; // 페이지; 그룹 시작번호
  private Long cp =1L;
}
