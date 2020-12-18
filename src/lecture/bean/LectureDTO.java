package lecture.bean;

public class LectureDTO {
   private String lecName, lecDate, lecTeacher, lecContent, tCode;
   private int lecMemNum, lecTO, lecCode;

   public String getLecName() {
      return lecName;
   }

   public void setLecName(String lecName) {
      this.lecName = lecName;
   }

   public String getLecDate() {
      return lecDate;
   }

   public void setLecDate(String lecDate) {
      this.lecDate = lecDate;
   }

   public String getLecTeacher() {
      return lecTeacher;
   }
   
   public void settCode(String tCode) {
		this.tCode = tCode;
	}
   
   public String gettCode() {
		return tCode;
	}

   public void setLecTeacher(String lecTeacher) {
      this.lecTeacher = lecTeacher;
   }
   
   public int getLecMemNum() {
	      return lecMemNum;
   }
   
   public void setLecMemNum(int lecMemNum) {
	      this.lecMemNum = lecMemNum;
   }
   
   public int getLecTO() {
      return lecTO;
   }

   public void setLecTO(int lecTO) {
      this.lecTO = lecTO;
   }

   public int getLecCode() {
      return lecCode;
   }

   public void setLecCode(int lecCode) {
      this.lecCode = lecCode;
   }

   public String getLecContent() {
      return lecContent;
   }

   public void setLecContent(String lecContent) {
      this.lecContent = lecContent;
   }
   
   	//ToString
 	@Override
 	public String toString() {
 		return "  "+lecName+"  -  " + lecTeacher+" °­»ç";
 	}
}