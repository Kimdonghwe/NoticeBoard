package com.demo.domain.board.dao;

import com.demo.Web.form.board.AddBoardForm;
import com.demo.Web.form.board.BoardListForm;
import com.demo.Web.form.member.LoginForm;
import com.demo.domain.entity.NoticeBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Repository
public class NoticeBoardDAOImpl implements NoticeBoardDAO {

  private final NamedParameterJdbcTemplate template;

  public NoticeBoardDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

//  게시글 조회
  @Override
  public List<NoticeBoard> getBoardLst(BoardListForm boardListForm) {

    String keyword = boardListForm.getKeyWord();
    String codeId = boardListForm.getCodeId();
    Long reqPage = boardListForm.getReqPage();
    Long recPage = boardListForm.getRecCnt();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT NOTICEBOARD_ID, MANAGEMENT_ID, TITLE, CODE_ID, HIT, NICKNAME, PREFERENCE_ID, CDATE, UDATE ");
    sql.append(" FROM NOTICEBOARD ");
    sql.append(" WHERE CODE_ID = :codeId ");

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("codeId", codeId);

    if (keyword != null && !keyword.trim().isEmpty()) {
      sql.append("AND TITLE LIKE :keyword ");
      parameters.addValue("keyword", "%" + keyword + "%");
    }
    sql.append("offset (:reqPage-1) * :recCnt rows ");
    sql.append("fetch first :recCnt rows only ");

    parameters.addValue("reqPage",reqPage);
    parameters.addValue("recCnt",recPage);


    return template.query(sql.toString(), parameters,  (rs, rowNum) -> {
              NoticeBoard noticeBoard = new NoticeBoard();
              noticeBoard.setNoticeboardId(rs.getLong("NOTICEBOARD_ID"));
              noticeBoard.setManagementId(rs.getLong("MANAGEMENT_ID"));
              noticeBoard.setTitle(rs.getString("TITLE"));
              noticeBoard.setNickname(rs.getString("NICKNAME"));
              noticeBoard.setCodeId(rs.getString("CODE_ID"));
              noticeBoard.setHit(rs.getLong("HIT"));
              noticeBoard.setPreferenceId(rs.getLong("PREFERENCE_ID"));
              noticeBoard.setCdate(rs.getTimestamp("CDATE").toLocalDateTime());
              noticeBoard.setUdate(rs.getTimestamp("UDATE").toLocalDateTime());
              return noticeBoard;
            });


  }

  @Override
  public int getBoardLstCnt(BoardListForm boardListForm) {
    String keyword = boardListForm.getKeyWord();
    String codeId = boardListForm.getCodeId();

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT COUNT(*)");
    sql.append(" FROM NOTICEBOARD ");
    sql.append(" WHERE CODE_ID = :codeId ");

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("codeId", codeId);

    if (keyword != null && !keyword.trim().isEmpty()) {
      sql.append("AND TITLE LIKE :keyword ");
      parameters.addValue("keyword", "%" + keyword + "%");
    }

    int cnt = template.queryForObject(sql.toString(),parameters,Integer.class);

    return cnt;
  }


  //  게시글 저장(insert)

  @Override
  public Long addBoard(AddBoardForm addBoardForm, LoginForm loginForm) {
    StringBuffer sql = new StringBuffer();

    sql.append(" INSERT INTO NOTICEBOARD (NOTICEBOARD_ID, MANAGEMENT_ID, TITLE, CODE_ID, NICKNAME, BCONTENT, HIT, PREFERENCE_ID)  ");
    sql.append(" VALUES (NOTICEBOARDTB_NOTICEBOARD_ID.NEXTVAL, :managementId, :title, :codeId, :nickname, :bcontent, 0 ,0 ) ");

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("managementId", loginForm.getManagementId());
    parameters.addValue("title", addBoardForm.getTitle());
    parameters.addValue("codeId", addBoardForm.getSelectGubun());
    parameters.addValue("nickname", loginForm.getNickname());
    parameters.addValue("bcontent", addBoardForm.getContent());

    KeyHolder keyHolder = new GeneratedKeyHolder();

    // SQL 실행
    template.update(sql.toString(), parameters, keyHolder, new String[]{"NOTICEBOARD_ID"});

    // Insert된 레코드에서 회원 번호 추출
    Long noticeboardId = ((BigDecimal) keyHolder.getKeys().get("NOTICEBOARD_ID")).longValue();

    return noticeboardId;
  }

  // 게시글 조회

  @Override
  public NoticeBoard getBoardBynoticeboardId(Long noticeboardId) {
    StringBuffer sql = new StringBuffer();

    sql.append(" SELECT * FROM NOTICEBOARD  ");
    sql.append(" WHERE NOTICEBOARD_ID = :noticeboardId ");

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("noticeboardId", noticeboardId);

    NoticeBoard noticeBoard = template.queryForObject(sql.toString(),parameters, BeanPropertyRowMapper.newInstance(NoticeBoard.class));
    return noticeBoard;

  }
}
