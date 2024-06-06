package com.demo.domain.member.svc;


import com.demo.Web.form.member.JoinAddForm;
import com.demo.domain.entity.Member;
import com.demo.domain.member.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberSVCImpl implements MemberSVC {

  @Autowired
  MemberDAO memberDAO;

  @Override
  public Long addUser(JoinAddForm joinAddForm) {
    return memberDAO.addUser(joinAddForm);
  }

  @Override
  public Optional<Member> getMemberByEmail(String email) {
    return memberDAO.getMemberByEmail(email);
  }

  @Override
  public Boolean existMemberByEmailAndPw(String email, String pw) {
    return memberDAO.existMemberByEmailAndPw(email,pw);
  }
  @Override
  public Boolean existMemberByEmail(String email) {
    return memberDAO.existMemberByEmail(email);
  }
}
