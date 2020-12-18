package member.bean;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private int gender;
	private String phone;
	private String email;
	private int health;
	private int song;
	private int book;
	private int carpent;
	private int cook;
	private int art;

	// 게터
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getBirth() {
		return birth;
	}

	public int getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public int getHealth() {
		return health;
	}

	public int getSong() {
		return song;
	}

	public int getBook() {
		return book;
	}

	public int getCarpent() {
		return carpent;
	}

	public int getCook() {
		return cook;
	}

	public int getArt() {
		return art;
	}

	// 세터
	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setGender(int gender2) {
		this.gender = gender2;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setSong(int song) {
		this.song = song;
	}

	public void setBook(int book) {
		this.book = book;
	}

	public void setCarpent(int carpent) {
		this.carpent = carpent;
	}

	public void setCook(int cook) {
		this.cook = cook;
	}

	public void setArt(int art) {
		this.art = art;
	}
	
	//ToString
	@Override
	public String toString() {
		return "  "+name+"  /  " + id;
	}
		
}
