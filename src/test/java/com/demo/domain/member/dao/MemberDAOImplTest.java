package com.demo.domain.member.dao;

import com.demo.Web.form.member.JoinAddForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MemberDAOImplTest {

  @Autowired
  MemberDAO memberDAO;
  
  @Test
  @DisplayName("지역으로검색")
  void addUser() {
    JoinAddForm joinAddForm = new JoinAddForm();
    joinAddForm.setEmail("1");
    joinAddForm.setPw("1");
    joinAddForm.setNickname("가나다");
    joinAddForm.setTel("01012345678");
    joinAddForm.setGender("남");
    joinAddForm.setAddress("울산광역시 북구");
    joinAddForm.setBirthYear("1999");
    joinAddForm.setBirthMonth("06");
    joinAddForm.setBirthDay("22");

    log.info("managementId = {}", memberDAO.addUser(joinAddForm));
  }
}