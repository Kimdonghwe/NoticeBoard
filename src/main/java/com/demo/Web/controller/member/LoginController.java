package com.demo.Web.controller.member;


import com.demo.Web.form.member.LoginCheckForm;
import com.demo.Web.form.member.LoginForm;
import com.demo.Web.form.member.SessionConst;
import com.demo.domain.entity.Member;
import com.demo.domain.member.svc.MemberSVC;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@RequestMapping
@Controller // Controller 역할을 하는 클래스
public class LoginController {

  @Autowired
  MemberSVC memberSVC;

  @GetMapping("/")
  public String home() {
    return "home/home"; // home.html을 반환
  }

  @PostMapping("/")
  public String loginOffer(@ModelAttribute LoginCheckForm loginCheckForm, HttpServletRequest request,
                           @RequestParam(value = "redirectUrl", required = false) String redirectUrl,
                           RedirectAttributes redirectAttributes) {
    log.info("loginForm={}", loginCheckForm);

//    // 회원 아이디 존재 유무 체크
    if (memberSVC.existMemberByEmailAndPw(loginCheckForm.getEmail(),loginCheckForm.getPw() ) ){
      Optional<Member> optionalMember = memberSVC.getMemberByEmail(loginCheckForm.getEmail());
//
      // 회원 정보가 존재하면 세션에 저장
      if (optionalMember.isPresent()) {
        HttpSession session = request.getSession(true);  // 세션 생성
        Member member = optionalMember.get();
        LoginForm loginMember = new LoginForm(member.getManagementId(), member.getEmail(), member.getNickname());
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

      } else {
        // 비밀번호 불일치
        redirectAttributes.addFlashAttribute("message", "비밀번호가 일치하지 않습니다.");
        return "redirect:";
      }
    } else {
      // 아이디가 존재하지 않음
      redirectAttributes.addFlashAttribute("message", "존재하지 않는 사용자 아이디입니다.");
      return "redirect:";
    }

    return "redirect:/board";
  }




}
