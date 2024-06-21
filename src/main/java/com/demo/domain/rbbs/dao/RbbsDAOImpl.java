package com.demo.domain.rbbs.dao;


import com.demo.Web.form.board.CommentForm;
import com.demo.domain.entity.Rbbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RbbsDAOImpl implements RbbsDAO {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  public List<Rbbs> getCommentsBynoticeboardId(Long noticeboardId) {
    String sql = "SELECT * FROM RBBS WHERE NOTICEBOARD_ID = :noticeboardId ORDER BY cdate ASC";

    SqlParameterSource params = new MapSqlParameterSource()
            .addValue("noticeboardId", noticeboardId);

    return jdbcTemplate.query(sql,params,(rs, rowNum) -> {
      Rbbs rbbs = new Rbbs();
      rbbs.setCommentId(rs.getLong("comment_id"));
      rbbs.setNoticeboardId(rs.getLong("noticeboard_id"));
      rbbs.setManagementId(rs.getLong("management_id"));
      rbbs.setNickname(rs.getString("nickname"));
      rbbs.setContent(rs.getString("content"));
      rbbs.setCdate(rs.getTimestamp("cdate").toLocalDateTime());
      rbbs.setUdate(rs.getTimestamp("udate").toLocalDateTime());
      return rbbs;
    });
  }

  public int insertComment(CommentForm commentForm) {
    String sql = "INSERT INTO RBBS (COMMENT_ID, noticeboard_id, management_id, nickname, content) " +
            "VALUES (RBBSTB_COMMENT_ID.NEXTVAL,:noticeboardId, :managementId, :nickname, :content)";

    // 현재 시간 설정


    SqlParameterSource params = new MapSqlParameterSource()
            .addValue("noticeboardId", commentForm.getNoticeboardId())
            .addValue("managementId", commentForm.getManagementId())
            .addValue("nickname", commentForm.getNickname())
            .addValue("content", commentForm.getContent());




    // 삽입 후 생성된 comment_id를 가져오는 로직이 필요할 수 있음 (Auto-generated Key를 사용하는 경우)

    return jdbcTemplate.update(sql, params);
  }

  @Override
  public int updateComment(CommentForm commentForm) {
    StringBuffer sql = new StringBuffer();

    sql.append(" UPDATE RBBS ");
    sql.append(" SET CONTENT = :content, UDATE = SYSDATE ");
    sql.append(" WHERE COMMENT_ID = :commentId ");

    SqlParameterSource params = new MapSqlParameterSource()
            .addValue("commentId", commentForm.getCommentId())
            .addValue("content", commentForm.getContent());




    // 삽입 후 생성된 comment_id를 가져오는 로직이 필요할 수 있음 (Auto-generated Key를 사용하는 경우)

    return jdbcTemplate.update(sql.toString(), params);
  }

  @Override
  public int deleteComment(Long commentId) {
    StringBuffer sql = new StringBuffer();

    sql.append(" DELETE FROM RBBS              ");
    sql.append(" WHERE COMMENT_ID = :commentId ");

    SqlParameterSource params = new MapSqlParameterSource()
            .addValue("commentId", commentId);
    return jdbcTemplate.update(sql.toString(), params);
  }

  @Override
  public int getCommentCntBynoticeboardId(Long noticeboardId) {
    StringBuffer sql = new StringBuffer();

    sql.append(" SELECT COUNT(*) FROM RBBS     ");
    sql.append(" WHERE NOTICEBOARD_ID = :noticeboardId ");

    SqlParameterSource params = new MapSqlParameterSource()
            .addValue("noticeboardId", noticeboardId);

    return jdbcTemplate.queryForObject(sql.toString(),params,Integer.class);
  }
}
