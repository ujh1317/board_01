package member;
import java.sql.*;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String ju1;
	private String ju2;
	private String email;
	private String zipcode;
	private String addr;
	private Timestamp regdate;
	
	public MemberDTO(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJu1() {
		return ju1;
	}

	public void setJu1(String ju1) {
		this.ju1 = ju1;
	}

	public String getJu2() {
		return ju2;
	}

	public void setJu2(String ju2) {
		this.ju2 = ju2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
}//class
