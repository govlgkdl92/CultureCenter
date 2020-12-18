package mapping.bean;

public class MappingDTO {
	private String id, name, lecName, tCode, eval; 
	private int lecCode, sw;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	public void settCode(String tCode) {
		this.tCode = tCode;
	}
	public void setEval(String eval) {
		this.eval = eval;
	}
	public void setLecCode(int lecCode) {
		this.lecCode = lecCode;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLecName() {
		return lecName;
	}
	public String gettCode() {
		return tCode;
	}
	public String getEval() {
		return eval;
	}
	public int getLecCode() {
		return lecCode;
	}
	public int getSw() {
		return sw;
	}
	
	//ToString
 	@Override
 	public String toString() {
 		
 			return "  "+eval;
	
 	}
}
