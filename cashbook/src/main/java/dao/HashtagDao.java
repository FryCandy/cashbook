package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;
public class HashtagDao {
	//전체 데이터에서 태그 순위 요청 메서드
	public List<Map<String,Object>> selectTagRankList(){
		List<Map<String,Object>> list = new ArrayList<>();
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//DB에 요청
		try {
			conn = DBUtil.getConnection();
			//전체 데이터에서 태그 순위 정하는 쿼리 작성
			String sql = "SELECT t.tag"
					+ "					,t.cnt \"count\""
					+ "					,RANK() over(ORDER BY t.cnt DESC) \"rank\" "
					+ "			FROM "
					+ "				(SELECT tag, COUNT(*) cnt "
					+ "					FROM hashtag "
					+ "					GROUP BY tag) t";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("tag", rs.getString("tag"));
				map.put("count", rs.getInt("count"));
				map.put("rank", rs.getInt("rank"));
				list.add(map);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}
	//조건에 따라 검색된 태그 순위 요청 메서드
	public List<Map<String,Object>> selectTagRankListByOption(String kind,String beginDate,String endDate){
		List<Map<String,Object>> list = new ArrayList<>();
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//DB에 요청
		try {
			conn = DBUtil.getConnection();
			//쿼리 작성
			String sql = "SELECT t.tag"
					+ "					,t.cnt \"count\""
					+ "					,RANK() over(ORDER BY t.cnt DESC) \"rank\" "
					+ "		FROM "
					+ "				(SELECT c.kind kind, h.tag, COUNT(*) cnt "
					+ "				FROM hashtag h "
					+ "				INNER JOIN cashbook c "
					+ "				ON h.cashbook_no = c.cashbook_no "
					+ "				WHERE c.kind LIKE ? "
					+ "				AND c.cash_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') "
					+ "				GROUP BY tag) t";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+kind+"%");
			stmt.setString(2, beginDate);
			stmt.setString(3, endDate);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("tag", rs.getString("tag"));
				map.put("count", rs.getInt("count"));
				map.put("rank", rs.getInt("rank"));
				list.add(map);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}






}
