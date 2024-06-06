package com.demo.Web.controller.member;

import com.demo.Web.form.member.JoinAddForm;
import com.demo.domain.member.svc.MemberSVC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/join")// Controller 역할을 하는 클래스
public class JoinController {

  @Autowired
  MemberSVC memberSVC;

  @GetMapping
  public String join() {
    return "join/join"; // home.html을 반환
  }


  @PostMapping
  public String joinAdd(@ModelAttribute JoinAddForm joinAddForm) {
    log.info("joinAddForm = {} ", joinAddForm);
    Long managementId = memberSVC.addUser(joinAddForm);

    return (managementId != null) ? "redirect:/" : "redirect:/join";
  }

}
