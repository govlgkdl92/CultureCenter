package lecture.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lecture.bean.LectureDTO;

@SuppressWarnings("all")
public class LectureDAO {
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;

   private static LectureDAO instance;

   private String driver = "oracle.jdbc.driver.OracleDriver";
   //private String url = "jdbc:oracle:thin:@58.123.1.248:1521:xe";	//�̽� ��
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//�п�
   private String username = "c##java";
   private String password = "bit";

   // ������
   public LectureDAO() {
      // DB �μ�Ʈ
      try {
         Class.forName(driver);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }// ������ end

   // ����
   public void getConnection() {
      try {
         conn = DriverManager.getConnection(url, username, password);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }// getConnection() end

   // �̱��� - DB�� �ݵ�� �̱������� �ؾ� �Ѵ�. �ѹ��� �����ؾ� �ϱ� ������.
   public static LectureDAO getInstance() {
      if (instance == null) {
         synchronized (LectureDAO.class) {
            instance = new LectureDAO();// ��ó�� ������ �� �� �� ���� ����,�����ȴ�.
         }
      }
      return instance; // �� �ܿ��� �������� �ҷ���.
   }

   //������ ����
   public int getLecCode() {
      int lecCode = 0;
      String sql = "select LECCODE.nextval from dual;";
      getConnection();

      try {
         pstmt = conn.prepareStatement(sql);// ����
         rs = pstmt.executeQuery();// ����

         rs.next();
         lecCode = rs.getInt(1);

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return lecCode;
   }//������ ���� end
   

   // list ��� ����
   public List<LectureDTO> getLectureList() {
      List<LectureDTO> dtoList = new ArrayList<LectureDTO>();

      try {
         String sql = "select * from lecture order by LecCode";
         getConnection();

         pstmt = conn.prepareStatement(sql);// ����
         rs = pstmt.executeQuery(); // ����

         while (rs.next()) {
            LectureDTO dto = new LectureDTO();
            dto.setLecName(rs.getString("lecName"));
            dto.setLecCode(rs.getInt("lecCode"));
            dto.setLecDate(rs.getString("lecDate"));
            dto.setLecMemNum(rs.getInt("lecMemNum"));
            dto.setLecTO(rs.getInt("lecTO"));
            dto.setLecTeacher(rs.getString("lecTeacher"));
            dto.setLecContent(rs.getString("lecContent"));
            dto.settCode(rs.getString("tCode"));
            dtoList.add(dto);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return dtoList;
   } // list ��� end
   
   
// ���� ���
   public void insertLecture(LectureDTO dto) {
	      String sql = "insert into lecture(lecCode, lecName, lecDate, lecMemNum, lecTo, lecTeacher, lecContent, tCode)"
	      						 + " values(LECCODE.nextval, ?, ?, 0 , ?, ?,?,?)";
	      getConnection();

	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, dto.getLecName());
	         pstmt.setString(2, dto.getLecDate());
	         pstmt.setInt(3, dto.getLecTO());
	         pstmt.setString(4, dto.getLecTeacher());
	         pstmt.setString(5, dto.getLecContent());
	         pstmt.setString(6, dto.gettCode());
	         
	         
	         pstmt.executeUpdate();// ����

	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null)
	               pstmt.close();
	            if (conn != null)
	               conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
   }// ����end
   
   // ���� �޼ҵ�
   public void deleteLecture(int lecCode) {
      String sql = "delete lecture where lecCode=?";
      getConnection();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, lecCode);
         pstmt.executeUpdate();// ����

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

   // ���� �޼ҵ�
   public void updateLecture(LectureDTO dto) {
      String sql = "update lecture set lecName=?" + ",lecDate=?" + ",lecTO=?" + ",lecTeacher=?" + "where lecCode=?";
      getConnection();
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getLecName());
         pstmt.setString(2, dto.getLecDate());
         pstmt.setInt(3, dto.getLecTO());
         pstmt.setString(4, dto.getLecTeacher());
         pstmt.setInt(5, dto.getLecCode());

         pstmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

   public List<LectureDTO> getMyLectureList(List<Integer> listC) {
	 List<LectureDTO> dtoList = new ArrayList<LectureDTO>();
	 
	 getConnection();
	
		 
	     try {
	    	for(int i=0; i<listC.size(); i++) {
		        String sql = "select * from lecture where LecCode=?";
	
		        pstmt = conn.prepareStatement(sql);// ����
		        pstmt.setInt(1, listC.get(i));

		        rs = pstmt.executeQuery(); // ����
		
		        while (rs.next()) {
		           LectureDTO dto = new LectureDTO();
		           dto.setLecName(rs.getString("lecName"));
		           dto.setLecCode(rs.getInt("lecCode"));
		           dto.setLecDate(rs.getString("lecDate"));
		           dto.setLecMemNum(rs.getInt("lecMemNum"));
		           dto.setLecTO(rs.getInt("lecTO"));
		           dto.setLecTeacher(rs.getString("lecTeacher"));
		           dto.setLecContent(rs.getString("lecContent"));
		           dto.settCode(rs.getString("tCode"));
		           dtoList.add(dto);
		        }
	    	}
	     } catch (SQLException e) {
	        e.printStackTrace();
	     
     } finally {
        try {
           if (rs != null)
              rs.close();
           if (pstmt != null)
              pstmt.close();
           if (conn != null)
              conn.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
     }
     return dtoList;
  } // list ��� end

   
   
   // ���� Ȩ list ��� ����
   public String getHomeLectureList() {
     String name = new String();
     
      try {
         String sql = "select * from lecture order by LecDate desc";
         getConnection();

         pstmt = conn.prepareStatement(sql);// ����
         rs = pstmt.executeQuery(); // ����

         while (rs.next()) {
        	 name = rs.getString("lecName");
         }      
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return name;
   } // ���� Ȩ  list ��� end

}