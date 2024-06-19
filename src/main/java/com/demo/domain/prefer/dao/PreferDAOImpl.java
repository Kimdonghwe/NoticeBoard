package com.demo.domain.prefer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PreferDAOImpl implements PreferDAO {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public boolean isLikeAlreadyExists(Long managementId, Long noticeboardId) {
    String query = "SELECT COUNT(*) FROM MEMBER_PREFER_RECORD WHERE MANAGEMENT_ID = :managementId AND NOTICEBOARD_ID = :noticeboardId";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("managementId", managementId);
    params.addValue("noticeboardId", noticeboardId);

    int count = namedParameterJdbcTemplate.queryForObject(query, params, Integer.class);
    return count > 0;
  }

  public void addLike(Long managementId, Long noticeboardId) {
    String insertLikeHistory = "INSERT INTO MEMBER_PREFER_RECORD (RECORD_ID,MANAGEMENT_ID, NOTICEBOARD_ID) VALUES (MEMBER_PREFER_RECORD_RECORD_ID.NEXTVAL,:managementId, :noticeboardId)";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("managementId", managementId);
    params.addValue("noticeboardId", noticeboardId);

    namedParameterJdbcTemplate.update(insertLikeHistory, params);

    updatePreferenceCount(noticeboardId, 1);
  }

  public void removeLike(Long managementId, Long noticeboardId) {
    String deleteLikeHistory = "DELETE FROM MEMBER_PREFER_RECORD WHERE MANAGEMENT_ID = :managementId AND NOTICEBOARD_ID = :noticeboardId";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("managementId", managementId);
    params.addValue("noticeboardId", noticeboardId);

    namedParameterJdbcTemplate.update(deleteLikeHistory, params);

    updatePreferenceCount(noticeboardId, -1);
  }

  private void updatePreferenceCount(Long noticeboardId, int count) {
    String updatePreference = "UPDATE PREFER SET GOOD = GOOD + :count WHERE NOTICEBOARD_ID = :noticeboardId";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("count", count);
    params.addValue("noticeboardId", noticeboardId);

    namedParameterJdbcTemplate.update(updatePreference, params);
  }

  public int getGood(Long noticeboardId){
    String sql = "select good from prefer where noticeboard_id = :noticeboardId";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("noticeboardId", noticeboardId);

    return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
  }

}
