package com.demo.Web.api.prefer;

import com.demo.Web.form.member.LoginForm;
import com.demo.Web.form.member.SessionConst;
import com.demo.domain.prefer.svc.PreferSVC;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/prefer")
public class ApiPreferController {

  @Autowired
  PreferSVC preferSVC;

  @PostMapping("/like")
  public String toggleLike(HttpServletRequest request, @RequestParam("noticeboardId") Long noticeboardId) {

    HttpSession session = request.getSession(false);  // Get existing session, do not create a new one
    // Retrieve the LoginMember object from the session
    LoginForm loginMember = (LoginForm) session.getAttribute(SessionConst.LOGIN_MEMBER);

    preferSVC.toggleLike(loginMember.getManagementId(), noticeboardId);
    return "Success";
  }

  @GetMapping("/isliked")
  public boolean isLiked(HttpServletRequest request, @RequestParam("noticeboardId") Long noticeboardId) {
    HttpSession session = request.getSession(false);  // Get existing session, do not create a new one
    LoginForm loginMember = (LoginForm) session.getAttribute(SessionConst.LOGIN_MEMBER);

    return preferSVC.isLiked(loginMember.getManagementId(), noticeboardId);
  }



}
