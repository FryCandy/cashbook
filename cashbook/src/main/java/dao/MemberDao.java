package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Member;
public class MemberDao {
	
	//회원정보
	public Member selectMemberOne(String memberId) {
		Member m = new Member();
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리 작성
		String sql ="SELECT member_id memberId"
				+ "					,name"
				+ "					,gender"
				+ "					,age"
				+ "					,create_date createDate"
				+ "					,update_date updateDate "
				+ "		 FROM member "
				+ "		WHERE member_id=? ";
		//DB에 값 요청
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs =stmt.executeQuery();
			if(rs.next()) {
				m.setMemberId(rs.getString("MemberID"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setCreateDate(rs.getString("createDate"));
				m.setUpdateDate(rs.getString("updateDate"));
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
		return m;
	}
	
	//로그인
	public Member selectMemberByIdPw(Member member) {
		Member m = new Member(); //로그인 실패시 null이 리턴
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
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs =stmt.executeQuery();
			if(rs.next()) {//로그인 성공시 id와 level을 DB에서 가져와서 Member에 저장
				m.setMemberId(rs.getString("member_id"));
				m.setLevel(rs.getInt("level"));
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
		
		return m;
	}
	//Member 테이블 입력
	public int insertMember(Member member) {
		int row = -1; // 결과 리턴할 변수 선언,예외 발생시 -1이 출력
		String memberId =null; //로그인 실패시 null이 리턴
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		//쿼리 작성
		String sql ="INSERT INTO member (member_id "
				+ "									,member_pw "
				+ " 								,name "
				+ "									, gender "
				+ "									,age "
				+ "									,level"
				+ "									,create_date"
				+ "									,update_date) "
				+ "			VALUES (?,PASSWORD(?),?,?,?,1,NOW(),NOW()) ";
		//DB에 값 요청
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getGender());
			stmt.setInt(5, member.getAge());
			row =stmt.executeUpdate();
			
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
		return row;
	}
	
	//Member 테이블 수정
	public int updateMemberByIdPw(Member member,String newMemberPw) {
		int row = -1; // 결과 리턴할 변수 선언,예외 발생시 -1이 출력
		String memberId =null; //로그인 실패시 null이 리턴
		if(newMemberPw.equals("")) {//새로운 비밀번호 현수가 "" 이라면 현재비밀번호로 채움
			newMemberPw=member.getMemberPw();
		}
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		//쿼리 작성
		String sql ="UPDATE member SET name = ? "
				+ "									, gender=? "
				+ "									,age=? "
				+ "									,member_pw = PASSWORD(?) "
				+ "									,update_date = NOW() "
				+ "			WHERE member_id = ? "
				+ "			AND member_pw =PASSWORD(?)";
		//DB에 값 요청
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getGender());
			stmt.setInt(3, member.getAge());
			stmt.setString(4, newMemberPw);
			stmt.setString(5, member.getMemberId());
			stmt.setString(6, member.getMemberPw());
			row =stmt.executeUpdate();
					
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
		return row;
	}
	//Member 테이블 삭제
	public int deleteMember(Member member) {
		int row = -1; // 결과 리턴할 변수 선언,예외 발생시 -1이 출력
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//이슈 : hashtag는 cashbook에 외래키 cashbook은 meber에 외래키
		//삭제 순서 : hashtag -> cashbook -> member
		//쿼리 작성
		//0. select cashbook_no
		String selectCashBookNoSql = "SELECT cashbook_no cashbookNo FROM cashbook WHERE member_id=?";
		//1. hashtag 테이블 데이터 삭제
		String deletehashtagSql = "DELETE FROM hashtag WHERE cashbook_no=?"; 
		//2.cashbook 테이블 데이터 삭제
		String deleteCashbookSql = "DELETE FROM cashbook WHERE member_id=?"; 
		//3.member 테이블 데이터 삭제
		String deleteMemberSql = "DELETE FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); // 자동 커밋 해제
			//0. select cashbook_no
			stmt = conn.prepareStatement(selectCashBookNoSql);
			stmt.setString(1, member.getMemberId());
			rs = stmt.executeQuery();
			List<Integer> list = new ArrayList<>(); //cashBookNo을 담을 리스트 
			while(rs.next()) {//memberId에 따른 모든 cashbookNo 정보 저장
				list.add(rs.getInt("cashbookNo"));
				
			}
			//1. hashtag 테이블 데이터 삭제
			stmt.close(); // stmt를 반납후 재사용
			stmt = conn.prepareStatement(deletehashtagSql);
			for(Integer i : list) {
				stmt.setInt(1, i);
				stmt.executeUpdate();
			}
			//stmt 해제 후 다음 쿼리 요청
			stmt.close();
			//2.cashbook 테이블 데이터 삭제
			stmt = conn.prepareStatement(deleteCashbookSql);
			stmt.setString(1, member.getMemberId());
			stmt.executeUpdate();
			//stmt 해제 후 다음 쿼리 요청
			stmt.close();
			//3. member 테이블 데이터 삭제
			stmt = conn.prepareStatement(deleteMemberSql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate();
			if (row == 1) { // 삭제 성공시 commit
				conn.commit();
			} else { // 성공이외의 결과시 rollback
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
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
		return row;
	}
	
	
}
