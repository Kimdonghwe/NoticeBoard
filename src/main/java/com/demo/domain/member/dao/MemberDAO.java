package com.demo.domain.member.dao;

import com.demo.Web.form.member.JoinAddForm;
import com.demo.domain.entity.Member;

import java.util.Optional;

public interface MemberDAO {

  Long addUser(JoinAddForm joinAddForm);

  Optional<Member> getMemberByEmail(String email);
  Boolean existMemberByEmailAndPw(String email,String pw);
}
