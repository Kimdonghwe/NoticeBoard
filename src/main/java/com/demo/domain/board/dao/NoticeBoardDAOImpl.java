package com.demo.domain.board.dao;

import com.demo.domain.entity.NoticeBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class NoticeBoardDAOImpl implements NoticeBoardDAO {

  private final NamedParameterJdbcTemplate template;

  public NoticeBoardDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public List<NoticeBoard> getBoardLst(String codeId, String keyword) {
    log.info("codeId = {}, keyword = {}", codeId, keyword);

    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT NOTICEBOARD_ID, MANAGEMENT_ID, TITLE, CODE_ID, HIT, NICKNAME, BCONTENT, PREFERENCE_ID, CDATE, UDATE ");
    sql.append(" FROM NOTICEBOARD ");
    sql.append(" WHERE CODE_ID = :codeId ");

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("codeId", codeId);

    if (keyword != null && !keyword.trim().isEmpty()) {
      sql.append("AND TITLE LIKE :keyword ");
      parameters.addValue("keyword", "%" + keyword + "%");
    }

    log.info("Executing query: {}", sql.toString());
    log.info("With parameters: {}", parameters.getValues());

    List<NoticeBoard> list = new ArrayList<>();

    log.info("list = {}", list);
    return template.query(sql.toString(), parameters,  (rs, rowNum) -> {
              NoticeBoard noticeBoard = new NoticeBoard();
              noticeBoard.setNoticeboardId(rs.getLong("NOTICEBOARD_ID"));
              noticeBoard.setManagementId(rs.getLong("MANAGEMENT_ID"));
              noticeBoard.setTitle(rs.getString("TITLE"));
              noticeBoard.setNickname(rs.getString("NICKNAME"));
              noticeBoard.setCodeId(rs.getString("CODE_ID"));
              noticeBoard.setBcontent(rs.getString("BCONTENT"));
              noticeBoard.setHit(rs.getLong("HIT"));
              noticeBoard.setPreferenceId(rs.getLong("PREFERENCE_ID"));
              noticeBoard.setCdate(rs.getTimestamp("CDATE").toLocalDateTime());
              noticeBoard.setUdate(rs.getTimestamp("UDATE").toLocalDateTime());
              return noticeBoard;
            });


  }

}
