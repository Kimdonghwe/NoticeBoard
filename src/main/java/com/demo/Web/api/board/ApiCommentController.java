package com.demo.Web.api.board;

import com.demo.Web.form.board.CommentForm;
import com.demo.Web.form.member.LoginForm;
import com.demo.Web.form.member.SessionConst;
import com.demo.Web.res.ApiResponse;
import com.demo.Web.res.ResCode;
import com.demo.domain.rbbs.svc.RbbsSVC;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {

  @Autowired
  RbbsSVC rbbsSVC;

  @PostMapping("/add")
  public ApiResponse<?> saveComment(HttpServletRequest request, @RequestBody CommentForm commentForm ) {
    HttpSession session = request.getSession(false);  // Get existing session, do not create a new one
    LoginForm loginMember = (LoginForm) session.getAttribute(SessionConst.LOGIN_MEMBER);
    commentForm.setNickname(loginMember.getNickname());
    commentForm.setManagementId(loginMember.getManagementId());

    log.info("insert-commentForm = {} ", commentForm);
    ApiResponse res = null;

      if (rbbsSVC.insertComment(commentForm) == 1) {
        res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), null);
      }else{
        res = ApiResponse.createApiResponse(ResCode.NONE.getCode(), ResCode.NONE.name(), null);
      }

      return res;
  }

  @PostMapping("/update")
  public ApiResponse<?> updateComment(HttpServletRequest request, @RequestBody CommentForm commentForm ) {
    HttpSession session = request.getSession(false);  // Get existing session, do not create a new one
    LoginForm loginMember = (LoginForm) session.getAttribute(SessionConst.LOGIN_MEMBER);
    commentForm.setNickname(loginMember.getNickname());
    commentForm.setManagementId(loginMember.getManagementId());

    log.info("update-commentForm = {} ", commentForm);
    ApiResponse res = null;

    if (rbbsSVC.updateComment(commentForm) == 1) {
      res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), null);
    }else{
      res = ApiResponse.createApiResponse(ResCode.NONE.getCode(), ResCode.NONE.name(), null);
    }

    return res;
  }

  @GetMapping("all")
  public ApiResponse<?> getCommentLst(){

    ApiResponse res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), rbbsSVC.getAllComments());
    return res;
  }

  @DeleteMapping("/delete/{commentId}")
  public ApiResponse<?> deleteComment(@PathVariable("commentId") Long commentId){

    ApiResponse res = null;
    log.info("commentId = {} ", commentId);

    if ( rbbsSVC.deleteComment(commentId) == 1)  res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), rbbsSVC.getAllComments());
    else res = ApiResponse.createApiResponse(ResCode.NONE.getCode(), ResCode.NONE.name(), null);

    return res;
  }
}
