package com.demo.domain.member.dao;

import com.demo.Web.form.JoinAddForm;

public interface MemberDAO {

  Long addUser(JoinAddForm joinAddForm);
}
