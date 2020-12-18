package teacher.bean;

public class TeacherDTO {
	private String tCode, tName, tPhone, tEmail, type;
	private int tGender;
	
	public String gettCode() {
		return tCode;
	}
	public void settCode(String tCode) {
		this.tCode = tCode;
	}
	
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	
	public int gettGender() {
		return tGender;
	}
	public void settGender(int tGender) {
		this.tGender = tGender;
	}
	
	public String gettPhone() {
		return tPhone;
	}
	public void settPhone(String tPhone) {
		this.tPhone = tPhone;
	}
	
	public String gettEmail() {
		return tEmail;
	}
	public void settEmail(String tEmail) {
		this.tEmail = tEmail;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//ToString
 	@Override
 	public String toString() {
 		return "  "+tName+" (" + tCode+")";
 	}

}
