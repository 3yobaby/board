package net.board.db;

import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BoardDAO() {
		Context init;
		try {
			init = new InitialContext();
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			// con =
			// DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	private void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception ex) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (Exception ex) {
			}
		if (con != null)
			try {
				con.close();
			} catch (Exception ex) {
			}
	}

	public List<BoardBean> getBoardList(int page, int limit) {
		List<BoardBean> list = new ArrayList<BoardBean>();
		String sql = "select * from ("
				+ "	select rownum rnum, b.* from board b ORDER BY board_re_ref desc, board_re_lev asc) "
				+ " where rnum >= ? and rnum <= ?";
		// 1페이지 Limit 10개. 1번~ 10번
		// 1번 = (page-1)*limit+1
		// 10번 = page*limit
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*limit+1);
			pstmt.setInt(2, page*limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardBean bean = new BoardBean();
				/*
				 * BOARD_NUM NUMBER BOARD_NAME VARCHAR2(20 BYTE) BOARD_PASS
				 * VARCHAR2(15 BYTE) BOARD_SUBJECT VARCHAR2(50 BYTE)
				 * BOARD_CONTENT VARCHAR2(2000 BYTE) BOARD_FILE VARCHAR2(50
				 * BYTE) BOARD_RE_REF NUMBER BOARD_RE_LEV NUMBER BOARD_RE_SEQ
				 * NUMBER BOARD_READCOUNT NUMBER BOARD_DATE DATE
				 */
				bean.setBoard_num(rs.getInt("board_num"));
				bean.setBoard_name(rs.getString("board_name"));
				bean.setBoard_pass(rs.getString("board_pass"));
				bean.setBoard_subject(rs.getString("board_subject"));
				bean.setBoard_content(rs.getString("board_content"));
				bean.setBoard_file(rs.getString("board_file"));
				bean.setBoard_re_ref(rs.getInt("board_re_ref"));
				bean.setBoard_re_lev(rs.getInt("board_re_lev"));
				bean.setBoard_re_seq(rs.getInt("board_re_seq"));
				bean.setBoard_readcount(rs.getInt("board_readcount"));
				bean.setBoard_date(rs.getDate("board_date"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int getListCount() {
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			close();
		}
		return x;
	}

	public boolean boardInsert(BoardBean board) {
		// board_seq
		int result;
		try {
			con = ds.getConnection();
			String sql = "INSERT INTO board "
					+ " 	(board_num, board_name, board_pass, "
					+ " 	board_subject, board_content, board_file, "
					+ " 	board_re_ref, board_re_lev, board_re_seq, "
					+ " 	board_readcount, board_date) "
					+ " VALUES (BOARD_SEQ.NEXTVAL, "
					+ " ?,?,?,?,?,BOARD_SEQ.NEXTVAL,?,?,?, SYSDATE)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_pass());
			pstmt.setString(3, board.getBoard_subject());
			pstmt.setString(4, board.getBoard_content());
			pstmt.setString(5, board.getBoard_file());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);

			result = pstmt.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	public BoardBean getDetail(int num) {
		BoardBean board = new BoardBean();
		try {
			String sql = "select * from board where board_num=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setBoard_num(rs.getInt("BOARD_NUM"));
				board.setBoard_name(rs.getString("BOARD_NAME"));
				board.setBoard_pass(rs.getString("BOARD_PASS"));
				board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
				board.setBoard_content(rs.getString("BOARD_CONTENT"));
				board.setBoard_file(rs.getString("BOARD_FILE"));
				board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
				board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
				board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
				board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				board.setBoard_date(rs.getDate("BOARD_DATE"));
				return board;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally{
			close();
		}
		return null;
	}
	
	public void setReadCount(int board_num){
		try{
			String sql = "update board "
					+ " set board_readcount = board_readcount+1 "
					+ " where board_num = ?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			close();
		}
	}
	
	public boolean isBoardWriter(int boardNum, String boardPass){
		boolean result = false;
		try{
			String sql = "select * from board where board_num=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			rs.next();
			String pass = rs.getString("board_pass");
			if(boardPass.equals(pass)){
				result = true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			close();
		}
		return result;
	}
	
	public boolean boardModify(BoardBean board){
		try{
			String sql = "UPDATE board set board_name=?, board_subject=?, board_content=? "
					+ " where board_num=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_name());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setInt(4, board.getBoard_num());
			if(pstmt.executeUpdate() > 0){
				return true;
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			close();
		}
		return false;
	}

	public boolean deleteBoard(int board_num) {
		try{
			String sql = "Delete board where board_num=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			if(pstmt.execute()){
				return true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			close();
		}
		return false;
	}

	public void boardReply(BoardBean board) {
		// 글 그룹 번호 re_ref
		int re_ref = board.getBoard_re_ref();
		int re_lev = board.getBoard_re_lev();
		int re_seq = board.getBoard_re_seq();
		try{
			String sql = "select board_seq.nextval from dual";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			int nextval = rs.getInt(1);
			
			sql = "UPDATE board "
					+ " SET board_re_seq=board_re_seq+1 "
					+ " WHERE board_re_ref=? and "
					+ " board_re_seq > ?";
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			pstmt.executeUpdate();
			// ref : 원글
			// lev : 답글 레벨
			// seq : 답글간 레벨
			sql = "INSERT INTO board "
					+ "(board_num,"
					+ " board_name,board_pass,board_subject,board_content,board_file,"
					+ " board_re_ref,board_re_lev,board_re_seq,board_readcount,board_date) "
					+ " VALUES (?, "
					+ " ?,?,?,?,?,?,?,?,0,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nextval);
			pstmt.setString(2, board.getBoard_name());
			pstmt.setString(3, board.getBoard_pass());
			pstmt.setString(4, board.getBoard_subject());
			pstmt.setString(5, board.getBoard_content());
			pstmt.setString(6, "");
			pstmt.setInt(7, board.getBoard_re_ref());
			pstmt.setInt(8, board.getBoard_re_lev() + 1);
			pstmt.setInt(9, board.getBoard_re_seq() + 1);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			close();
		}
	}
}
