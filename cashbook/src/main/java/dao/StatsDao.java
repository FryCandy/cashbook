package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;
import vo.Stats;

public class StatsDao {
	
	public void insertStats() { //<-Listener
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql ="INSERT INTO stats(day,cnt) VALUES(CURDATE(),1)";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//DB자원 반납
				stmt.close();
				conn.close();
			}catch(SQLException e) {
			e.printStackTrace();
			}
		}
	}
	//오늘 날짜 값 불러오기
	public Stats selectStatsOneByNow() {//<-- Listener, Controller
		Stats stats = new Stats();
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT day,cnt FROM stats WHERE day = CURDATE()";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				stats.setcnt(rs.getInt("cnt"));
				stats.setDay(rs.getString("day"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//DB자원 반납
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return stats;
	}
	public void updateStatsByNow() { //<--Listener
		//DB 자원 준비
		Connection conn =null;
		PreparedStatement stmt = null;
		String sql ="UPDATE stats SET cnt = cnt+1 WHERE day = CURDATE() ";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//DB자원 반납
				stmt.close();
				conn.close();
			}catch(SQLException e) {
			}
		}
	}
	//전체 접속자 보기
	public int selectStatsTotalCount() { // 
		int totalCount=0;
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="SELECT SUM(cnt) cnt FROM stats";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalCount =rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//DB자원 반납
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return totalCount;
	}
}
