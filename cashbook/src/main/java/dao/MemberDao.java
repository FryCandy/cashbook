package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao {
	//회원가입
	//회원수정
	//회원탈퇴
	//회원정보
	
	//로그인
	public String selectMemberByIdPw(Member member) {
		String memberId =null; //로그인 실패시 null이 리턴
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리 작성
		String sql ="SELECT * FROM member "
				+ "		WHERE member_id=? "
				+ "		AND member_pw=PASSWORD(?)";
		//DB에 값 요청
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs =stmt.executeQuery();
			if(rs.next()) {
				memberId=rs.getString("member_id");
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
		
		return memberId;
	}
}
