package com.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import com.guestbook.vo.GuestbooksVo;

@Repository
public class GuestbooksDao {

	public String insert(String name,String password, String content) {

		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1.jbdc 드라이브 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 3.SQLE 테스트문
			String query = "insert into guestbooks values(seq_guestbooks_no.nextval,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, content);
			

			count = pstmt.executeUpdate();

			// 바인딩을 안줘도 된다.
			// rs.next() =안에 있는 내용을 한칸씩 밀어 주어라
			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패." + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);

			}

		}

		return null;
	}

	public List<GuestbooksVo> getList() {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<GuestbooksVo> list = new ArrayList<GuestbooksVo>();

		try {
			// 1.jbdc 드라이브 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");

			// 3.SQLE 테스트문
			String query = "select no, name, content, reg_date from guestbooks order by no desc";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			// 바인딩을 안줘도 된다.
			// rs.next() =안에 있는 내용을 한칸씩 밀어 주어라
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");// DB에 있는 필드의 이름으로 있어야 계산시 찾아올수있다.
				String content = rs.getString("content");
				String date=rs.getString("reg_date");
				

				GuestbooksVo vo = new GuestbooksVo(no, name,content,date);
				list.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패." + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);

			}

		}

		return list;

}
	public int delete(int no,String password) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			conn=DriverManager.getConnection(url,"webdb","webdb");
			System.out.println("접속성공");
			
			String query="delete from guestbooks "
					+"where password=? "
					+"and no=?";
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(2, no);
			pstmt.setString(1, password);
			
			pstmt.executeQuery();
			
		}catch(ClassNotFoundException e) {
			System.out.println("error:드라이벌딩 실패."+e);
		}catch(SQLException e) {
			System.out.println("error"+e);
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(SQLException e) {
				System.out.println("error:"+e);
			}

	}
		return 0;
		
	}
}