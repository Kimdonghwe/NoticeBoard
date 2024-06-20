package com.demo.Web.form.board;

import lombok.Data;

@Data
public class CommentForm {
  private Long commentId;
  private Long noticeboardId;
  private Long managementId;
  private String nickname;
  private String content;

}
