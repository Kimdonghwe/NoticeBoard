package com.demo.domain.member.dao;

import com.demo.Web.form.member.JoinAddForm;
import com.demo.domain.entity.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberDAOImpl implements MemberDAO{

  private final NamedParameterJdbcTemplate template;

  public MemberDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }


  @Override
  public Long addUser(JoinAddForm joinAddForm) {
    StringBuffer sql = new StringBuffer();

    sql.append(" INSERT INTO member (MANAGEMENT_ID, EMAIL, PW, TEL, NICKNAME,GENDER, ADDRESS, BIRTHDAY) ");
    sql.append(" VALUES(MEMBERTB_MANAGEMENT_ID.NEXTVAL, :email, :pw, :tel, :nickname,:gender, :address, :birthday) ");

    SqlParameterSource param = new MapSqlParameterSource()
            .addValue("email", joinAddForm.getEmail())
            .addValue("pw", joinAddForm.getPw())
            .addValue("tel", joinAddForm.getTel())
            .addValue("nickname", joinAddForm.getNickname())
            .addValue("gender", joinAddForm.getGender())
            .addValue("address", joinAddForm.getAddress())
            .addValue("birthday",joinAddForm.getBirthYear() + "/" + joinAddForm.getBirthMonth() + "/" + joinAddForm.getBirthDay());
    // 변경된 레코드 정보를 읽어오는 용도
    KeyHolder keyHolder = new GeneratedKeyHolder();

    // SQL 실행
    template.update(sql.toString(), param, keyHolder, new String[]{"MANAGEMENT_ID"});

    // Insert된 레코드에서 회원 번호 추출
    Long managementId = ((BigDecimal) keyHolder.getKeys().get("MANAGEMENT_ID")).longValue();


    return managementId;
  }

  @Override
  public Boolean existMemberByEmailAndPw(String email,String pw) {
    String sql = "select count(email) from member where email = :email and pw = :pw ";

    Map param = Map.of("email", email,"pw",pw);
    Integer cnt = template.queryForObject(sql, param, Integer.class);

    return cnt == 1 ? true : false;
  }
  @Override
  public Optional<Member> getMemberByEmail(String email) {

    String sql = "select * from member where email = :email ";
    Map param = Map.of("email", email);
    try {
      Member member = template.queryForObject(sql, param, new BeanPropertyRowMapper<>(Member.class));
      return Optional.of(member);
    }catch(EmptyResultDataAccessException e){
      return Optional.empty();
    }
  }
}
