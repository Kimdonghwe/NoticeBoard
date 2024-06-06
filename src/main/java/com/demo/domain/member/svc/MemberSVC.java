package com.demo.domain.member.svc;

import com.demo.Web.form.member.JoinAddForm;
import com.demo.domain.entity.Member;

import java.util.Optional;

public interface MemberSVC {

  Long addUser(JoinAddForm joinAddForm);

  Optional<Member> getMemberByEmail(String email);
  Boolean existMemberByEmailAndPw(String email,String pw);

  Boolean existMemberByEmail(String email);
}
