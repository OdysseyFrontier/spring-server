//package com.enjoyTrip.OdysseyFrontiers.board.model.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ssafy.board.model.BoardDto;
//import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
//import com.ssafy.search.model.Gugun;
//import com.ssafy.util.DBUtil;
//
//public class BoardDaoImpl implements BoardDao {
//
//	private static BoardDao boardDao;
//	private DBUtil dbUtil;
//
//	private BoardDaoImpl() {
//		dbUtil = DBUtil.getInstance();
//	}
//
//	public static BoardDao getBoardDao() {
//		if(boardDao == null)
//			boardDao = new BoardDaoImpl();
//		return boardDao;
//	}
//
//	@Override
//	public void writeArticle(BoardDto boardDto) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("insert into board (user_id, subject, content) \n");
//			sql.append("values (?, ?, ?)");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, boardDto.getUserId());
//			pstmt.setString(2, boardDto.getSubject());
//			pstmt.setString(3, boardDto.getContent());
//			pstmt.executeUpdate();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//	}
//
//	@Override
//	public List<BoardDto> listArticle() throws SQLException {
//		List<BoardDto> list = new ArrayList<>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select article_no, user_id, subject, content, hit, register_time \n");
//			sql.append("from board \n");
//			sql.append("order by article_no desc");
//			pstmt = conn.prepareStatement(sql.toString());
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				BoardDto boardDto = new BoardDto();
//				boardDto.setArticleNo(rs.getInt("article_no"));
//				boardDto.setUserId(rs.getString("user_id"));
//				boardDto.setSubject(rs.getString("subject"));
//				boardDto.setContent(rs.getString("content"));
//				boardDto.setHit(rs.getInt("hit"));
//				boardDto.setRegisterTime(rs.getString("register_time"));
//				list.add(boardDto);
//			}
//		} finally {
//			dbUtil.close(rs, pstmt, conn);
//		}
//		return list;
//	}
//
//	@Override
//	public BoardDto getArticle(int articleNo) throws SQLException {
//		BoardDto boardDto = new BoardDto();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select article_no, user_id, subject, content, hit, register_time \n");
//			sql.append("from board \n");
//			sql.append("where article_no = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, articleNo);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				boardDto.setArticleNo(rs.getInt("article_no"));
//				boardDto.setUserId(rs.getString("user_id"));
//				boardDto.setSubject(rs.getString("subject"));
//				boardDto.setContent(rs.getString("content"));
//				boardDto.setHit(rs.getInt("hit"));
//				boardDto.setRegisterTime(rs.getString("register_time"));
//			}
//
//		} finally {
//			dbUtil.close(rs, pstmt, conn);
//		}
//
//		return boardDto;
//	}
//
//	@Override
//	public void updateHit(int articleNo) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = dbUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("update board \n");
//			sql.append("set hit = hit + 1 \n");
//			sql.append("where article_no = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, articleNo);
//			pstmt.executeUpdate();
//		} finally {
//			dbUtil.close(pstmt, conn);
//		}
//	}
//
//	@Override
//	public void modifyArticle(BoardDto boardDto) throws SQLException {
//
//	}
//
//	@Override
//	public void modifyArticle(BoardDto boardDto) throws SQLException {
//		String sql = "update board set subject = ?, content = ?,register_time = CURRENT_TIMESTAMP where article_no = ?; ";
//
//		Connection conn = null;
//		PreparedStatement stmp = null;
//		try {
//			conn = dbUtil.getConnection();
//			stmp = conn.prepareStatement(sql);
//			stmp.setString(1, boardDto.getSubject());
//			stmp.setString(2, boardDto.getContent());
//			stmp.setInt(3, boardDto.getArticleNo());
//
//			stmp.executeUpdate();
//		} finally {
//			dbUtil.close(stmp, conn);
//		}
//
//	}
//
//	@Override
//	public void deleteArticle(int articleNO) throws SQLException {
//		String sql = "delete from board where article_no = ?;";
//
//		Connection conn = null;
//		PreparedStatement stmp = null;
//		try {
//			conn = dbUtil.getConnection();
//			stmp = conn.prepareStatement(sql);
//			stmp.setInt(1, articleNO);
//			stmp.executeUpdate();
//
//		} finally {
//			dbUtil.close(stmp, conn);
//		}
//
//	}
//
//}
