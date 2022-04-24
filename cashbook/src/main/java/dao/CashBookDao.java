package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.CashBook;
public class CashBookDao {
	//달별 cashbookList select 메서드
	public List<Map<String,Object>> selectcashBookListbyMonth(int year, int month) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//쿼리 작성
		String sql = "SELECT "
				+ "			cashbook_no cashBookNo"
				+ "			,DAY(cash_date) day"
				+ "			,kind"
				+ "			,cash"
				+ "			,LEFT(memo,5) memo "
				+ "		FROM cashbook"
				+ "		WHERE YEAR(cash_date) = ? AND MONTH(cash_date)=?"
				+ "		ORDER BY DAY(cash_date) ASC, kind ASC";
		//DB에 값 요청
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, year);
			stmt.setInt(2, month);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap <String,Object>();
				map.put("cashBookNo",rs.getInt("cashBookNo"));
				map.put("day",rs.getInt("day"));
				map.put("kind",rs.getString("kind"));
				map.put("cash",rs.getInt("cash"));
				map.put("memo",rs.getString("memo"));
				list.add(map);
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
		return list;
	}
	//태그에 따른 cashbookList select 메서드
	public List<Map<String,Object>> selectcashBookListbyTag(String tag) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//쿼리 작성
		String sql = "SELECT c.cashbook_no cashbookNo "
				+ "		,t.tag tag "
				+ "		,c.cash_date cashDate "
				+ "		,c.kind kind "
				+ "		,c.cash cash "
				+ "		,LEFT(c.memo,5) memo "
				+ "FROM cashbook c INNER JOIN hashtag t "
				+ "ON c.cashbook_no = t.cashbook_no "
				+ "WHERE tag = ? "
				+ "ORDER BY c.cash_date DESC";
		//DB에 값 요청
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap <String,Object>();
				map.put("cashBookNo",rs.getInt("cashBookNo"));
				map.put("tag",rs.getString("tag"));
				map.put("cashDate",rs.getString("cashDate"));
				map.put("kind",rs.getString("kind"));
				map.put("cash",rs.getInt("cash"));
				map.put("memo",rs.getString("memo"));
				list.add(map);
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
		return list;
	}
	
	//입력폼-insertCashBookControoler에서 요청
	public void insertCashBook(CashBook cashBook,List<String> hashtag) {
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//DB에 요청
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false);//자동커밋을 해제
			//insert cashBook 쿼리
			String insertCashBookSql = "INSERT INTO cashbook("
					+ "													cash_date"
					+ "													, kind"
					+ "													, cash"
					+ "													, memo"
					+ "													,member_id "
					+ "													, update_date"
					+ "													, create_date)"
					+ "										 VALUES (?, ?, ?,?,?, NOW(), NOW())";
			//PreparedStatement.RETURN_GENERATED_KEYS는  insert+select 방금생성된 행의 키 값 ex) 방금 입력한 cashBook_no를 select
			stmt = conn.prepareStatement(insertCashBookSql,PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashBook.getCashDate());
			stmt.setString(2, cashBook.getKind());
			stmt.setInt(3, cashBook.getCash());
			stmt.setString(4, cashBook.getMemo());
			stmt.setString(5,cashBook.getMemeberId());
			stmt.executeUpdate();//insert와 select 실행
			rs = stmt.getGeneratedKeys(); //Key를 가져와서 rs에 서장
			int cashBookNo = 0; //방금 생성된 cashBookNo을 넣을 변수
			if(rs.next()) {
				cashBookNo = rs.getInt(1);
			}
			//hashtag테이블에 데이터를 저장하는 코드
			PreparedStatement stmt2 = null;
			//insertHashtag 쿼리
			for(String h : hashtag) {// hashtag만큼 반복해서 insert
				String insertHashtagSql = "INSERT INTO hashtag(cashBook_no,tag) VALUES (?,?)";
				stmt2= conn.prepareStatement(insertHashtagSql);
				stmt2.setInt(1, cashBookNo);
				stmt2.setString(2, h);
				stmt2.executeUpdate();
			}
			conn.commit(); //최종 커밋
		} catch (Exception e) {
			try {
				conn.rollback(); //예외가 발생하면 롤백
			} catch(SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				//DB자원반납 제일 중요한 conn 반납
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//상세보기-/CashBookOneController에서 요청
	public CashBook selectCashBookOne(int cashBookNo) {
		CashBook c = new CashBook(); //DB 도출값을 vo.CashBook에 넣어 리턴
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리 작성
		String sql = "SELECT "
				+ "			cashbook_no cashBookNo"
				+ "			,cash_date cashDate"
				+ "			,kind"
				+ "			,cash"
				+ "			,memo"
				+ "			,create_date createDate"
				+ "			,update_date updateDate "
				+ "		FROM cashbook"
				+ "		WHERE cashbook_no = ? ";
		//DB에 값 요청
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashBookNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				c.setCashBookNo(rs.getInt("cashBookNo"));
				c.setCashDate(rs.getString("cashDate"));
				c.setKind(rs.getString("kind"));
				c.setCash(rs.getInt("cash"));
				c.setMemo(rs.getString("memo"));
				c.setCreateDate(rs.getString("createDate"));
				c.setUpdateDate(rs.getString("updateDate"));
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
		return c;
	}
	
	//cashbook 삭제하기 DeleteCashBookController에서 호출
	public void deleteCashBook(int cashBookNo) {
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		//쿼리 작성
		//hashtag 테이블의 cashbook_no는 cashbook 테이블에 외래키를 가지고 있으므로, hashtag 테이블 -> cashbook 테이블 순으로 지워야 한다.
		//hashtag 테이블에서 삭제 쿼리
		String sql1 = "DELETE FROM hashtag WHERE cashbook_no = ?"	;
		//cashbook 테이블에서 삭제 쿼리
		String sql2 = "DELETE FROM cashbook WHERE cashbook_no = ?";
		//DB에 값 요청
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 오토커밋해제
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, cashBookNo);
			stmt.executeUpdate();// hashtag 테이블에서 delect
			stmt.close(); // stmt를 닫아준 후 다음 요청 실행
			stmt = conn.prepareStatement(sql2);
			stmt.setInt(1, cashBookNo);
			stmt.executeUpdate();// cashbook 테이블에서 delect
			conn.commit(); //최종 커밋
			
		} catch (Exception e) {
			try {
				conn.rollback();//실패시 롤백
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
	}

	public void updateCashBook(CashBook cashBook,List<String>hashtag) {
		//DB 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//DB에 요청
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); // 오토커밋해제
			//update cashBook 쿼리
			String updateCashBookSql = "UPDATE cashbook SET"
					+ "													cash_date=?"
					+ "													, kind=?"
					+ "													, cash=?"
					+ "													, memo=?"
					+ "													, update_date=NOW()"
					+ "								WHERE cashbook_no=?";
			stmt = conn.prepareStatement(updateCashBookSql);
			stmt.setString(1, cashBook.getCashDate());
			stmt.setString(2, cashBook.getKind());
			stmt.setInt(3, cashBook.getCash());
			stmt.setString(4, cashBook.getMemo());
			stmt.setInt(5, cashBook.getCashBookNo());
			stmt.executeUpdate();//cashBook update 실행
			//hashtag테이블 태그를 변경하는 코드
			//1.hashtag table의 해당 태그 모두 삭제
			PreparedStatement stmt2 = null;
			String deleteHashtagSql = "DELETE FROM hashtag WHERE cashbook_no=? ";
			stmt2=conn.prepareStatement(deleteHashtagSql);
			stmt2.setInt(1, cashBook.getCashBookNo());
			stmt2.executeUpdate(); //hashtag delect 요청
			//2.hashtag table에 새로 태그 저장
			PreparedStatement stmt3 = null;
			for(String h : hashtag) {// hashtag만큼 반복해서 insert
				String insertHashtagSql = "INSERT INTO hashtag(cashBook_no,tag,create_date) VALUES (?,?,NOW())";
				stmt3= conn.prepareStatement(insertHashtagSql);
				stmt3.setInt(1, cashBook.getCashBookNo());
				stmt3.setString(2, h);
				stmt3.executeUpdate();
			}
			conn.commit(); //최종 커밋
		} catch (Exception e) {
			try {
				conn.rollback(); //예외가 발생하면 롤백
			} catch(SQLException e1) {
			e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				//DB자원반납 제일 중요한 conn 반납
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}		
	

}
