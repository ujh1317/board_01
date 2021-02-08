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
	
	//�̱��� ��ü����
	private static BoardDAO dao = new BoardDAO();
	
	//jsp���� dao��ü ���
	public static BoardDAO getDao(){
		return dao;
	}//getDao()
	
	private BoardDAO(){}
	
	//Ŀ�ؼ�
	private Connection getConn() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getConn()
	
	//�۾��� , ��۾���
	public void getInsert(BoardDTO dto) throws Exception{
		int num = dto.getNum();
		int ref = dto.getRef();
		int re_step = dto.getRe_step();
		int re_level = dto.getRe_level();
		
		int number = 0;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
			if(rs.next()){//���� ������
				number = rs.getInt(1)+1;
			}else{//���� ������
				number = 1;
			}//else
			
			if(num!=0){
				sql = "update board set re_step=re_step+2 where ref=? and re_step>?";
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
			
			sql = "insert into board(writer,title,category,regdate,modifydate,ref,re_step,re_level,content,ip,rank)"
					+" values(?,?,?,now(),now(),?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getCategory());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, re_step);
			pstmt.setInt(6, re_level);
			pstmt.setString(7, dto.getContent());
			pstmt.setString(8, dto.getIp());
			pstmt.setInt(9, dto.getRank());
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
	}//getInsert()
	
	//�۰���
	public int getCount() throws Exception{
		int x = 0;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if(rs.next()){
				x = rs.getInt(1); //�� �۰���
			}//if
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//getCount()
	
	//����Ʈ
	public List getList(int start, int cnt, String list_category) throws Exception{
		List<BoardDTO> list = null;
		try{
			conn = getConn();
			if(list_category==null || list_category==""||list_category=="��ü��"){
				
				sql = "select * from board order by ref desc, re_step asc limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start); //������ġ
				pstmt.setInt(2, cnt); //����
				rs = pstmt.executeQuery();
			}else{
				sql = "select * from board where category=? order by ref desc, re_step asc limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, list_category);
				pstmt.setInt(2, start);
				pstmt.setInt(3, cnt);
				rs = pstmt.executeQuery();
			}//else
			if(rs.next()){
				list = new ArrayList<BoardDTO>();
				do{
					BoardDTO dto = new BoardDTO();
					dto.setNum(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setTitle(rs.getString("title"));
					dto.setCategory(rs.getString("category"));
					dto.setContent(rs.getString("content"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setModifydate(rs.getString("modifydate"));
					dto.setReadcount(rs.getInt("readcount"));
					dto.setRef(rs.getInt("ref"));
					dto.setRe_step(rs.getInt("re_step"));
					dto.setRe_level(rs.getInt("re_level"));
					dto.setIp(rs.getString("ip"));
					dto.setRank(rs.getInt("rank"));
					list.add(dto);
				}while(rs.next());
			}//if
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return list;
	}//getList()
	
	//�۳��뺸��
	public BoardDTO getContent(int num) throws Exception{
		BoardDTO dto = null;
		try{
			conn = getConn();
			sql = "update board set readecount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setModifydate(rs.getString("modifydate"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setIp(rs.getString("ip"));
				dto.setRank(rs.getInt("rank"));
			}//if
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return dto;
	}//getContent()
	
	//�ۼ���
	public BoardDTO getUpdate(int num) throws Exception{
		BoardDTO dto = null;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setModifydate(rs.getString("modifydate"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setIp(rs.getString("ip"));
				dto.setRank(rs.getInt("rank"));
			}//while
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return dto;
	}//getUpdate()
	
	//DB����
	public int getUpdateDb(BoardDTO dto) throws Exception{
		int x = -10;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, dto.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()){
				sql = "update board set title=?, category=?, content=?, modifydate=now() where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getCategory());
				pstmt.setString(3, dto.getContent());
				pstmt.setInt(4, dto.getNum());
				pstmt.executeUpdate();
				x = 1;
			}else{
				x = -1;
			}//else
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){rs.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//getUpdateDb()
	
	//�ۻ���
	public int getDelete(int num) throws Exception{
		int x = -10;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pstmt = conn.prepareStatement("delete * from board where num=?");
				pstmt.setInt(1, num);
				pstmt.executeQuery();
				x = 1;
			}else{
				x = -1;
			}//else
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
		return x;
	}//getDelete()
}//class
