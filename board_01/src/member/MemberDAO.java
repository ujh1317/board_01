package member;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();//�̱��水ü
	public static MemberDAO getInstance(){
		return instance;
	}//getInstance()
	
	//Ŀ�ؼ�Ǯ
	public Connection getConn() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getConn()
	
	//id�ߺ�üũ
	public int confirmId(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select id from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				x = 1; //������� id
			}else{
				x = -1; //��� ������ id
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
	}//confirmId()
	
	//ȸ������
	public void getMember(MemberDTO dto){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConn();
			String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNick());
			pstmt.setString(5, dto.getJumin1());
			pstmt.setString(6, dto.getJumin2());
			pstmt.setString(7, dto.getEmail());
			pstmt.setString(8, dto.getZipcode());
			pstmt.setString(9, dto.getAddr());
			pstmt.setTimestamp(10, dto.getRegdate());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(conn!=null){conn.close();}
			}catch(Exception e){}
		}//finally
	}//getMember()
	
	//�α���(����)
	public int userCheck(String id, String pw){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try{
			conn = getConn();
			pstmt = conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String dbPw = rs.getString("pw");
				if(pw.equals(dbPw)){
					x = 1; //�α��� ����
				}else{
					x = 0; //��ȣ Ʋ��
				}//else
			}else{
				x = -1; //���� id
			}
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
	}//userCheck()
	
}//class
