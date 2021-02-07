package board.mysql;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class BoardDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	//싱글톤 객체생성
	private static BoardDAO dao = new BoardDAO();
	
	//jsp에서 dao객체 얻기
	public static BoardDAO getDao(){
		return dao;
	}//getDao()
	
	private BoardDAO(){}
	
	//커넥션
	private Connection getConn() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getConn()
	
	//글쓰기 , 답글쓰기
	public void insertBoard(BoardDTO dto) throws Exception{
		int num = dto.getNum();
		int ref = dto.getRef();
		int re_step = dto.getRe_step();
		int re_level = dto.getRe_level();
		
		int number = 0;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select max(num) from board01");
			rs = pstmt.executeQuery();
			if(rs.next()){//글이 있을때
				number = rs.getInt(1)+1;
			}else{//글이 없을때
				number = 1;
			}//else
			
			if(num!=0){
				sql = "update board01 set re_step=re_step+2 where ref=? and re_step>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				
				re_step = re_step+1;
				re_level = re_level+1;
			}else{
				ref = number;
				re_step = 0;
				re_level = 0;
			}//else
			
			sql = "insert into board01(writer,title,pw,regdate,ref,re_step,re_level,content,ip)"
					+" values(?,?,?,now(),?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getPw());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, re_step);
			pstmt.setInt(6, re_level);
			pstmt.setString(7, dto.getContent());
			pstmt.setString(8, dto.getIp());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
	}//insertBoard()
	
	
	
	
	
	
	
	
	
	
}//class
