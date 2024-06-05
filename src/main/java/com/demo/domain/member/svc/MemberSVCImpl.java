package com.demo.domain.member.svc;


import com.demo.Web.form.JoinAddForm;
import com.demo.domain.member.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberSVCImpl implements MemberSVC {

  @Autowired
  MemberDAO memberDAO;

  @Override
  public Long addUser(JoinAddForm joinAddForm) {
    return memberDAO.addUser(joinAddForm);
  }
}
