package teacher.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lecture.bean.LectureDTO;
import teacher.bean.TeacherDTO;
import teacher.dao.TeacherDAO;


public class TeacherDAO {
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;

   private static TeacherDAO instance;

   private String driver = "oracle.jdbc.driver.OracleDriver";
   //private String url = "jdbc:oracle:thin:@58.123.1.248:1521:xe";	//이슬 집
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//학원
   private String username = "c##java";
   private String password = "bit";

   // 생성자
   public TeacherDAO() {
      // DB 인서트
      try {
         Class.forName(driver);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }// 생성자 end

   // 접속
   public void getConnection() {
      try {
         conn = DriverManager.getConnection(url, username, password);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }// getConnection() end

   // 싱글톤 - DB는 반드시 싱글톤으로 해야 한다. 한번만 생성해야 하기 때문에.
   public static TeacherDAO getInstance() {
      if (instance == null) {
         synchronized (TeacherDAO.class) {
            instance = new TeacherDAO();// 맨처음 실행할 때 딱 한 번만 실행,생성된다.
         }
      }
      return instance; // 그 외에는 기존값을 불러옴.
   }

   // list 목록 생성
   public List<TeacherDTO> getTeacherList() {
      List<TeacherDTO> dtoList = new ArrayList<TeacherDTO>();

      try {
         String sql = "select * from teacher order by tcode";
         getConnection();

         pstmt = conn.prepareStatement(sql);// 생성
         rs = pstmt.executeQuery(); // 실행

         while (rs.next()) {
            TeacherDTO dto = new TeacherDTO();
            dto.settCode(rs.getString("tcode"));
            dto.settName(rs.getString("tname"));
            dto.settGender(rs.getInt("tgender"));
            dto.settPhone(rs.getString("tphone"));
            dto.settEmail(rs.getString("temail"));
            dto.setType(rs.getString("type"));        
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
   }// list 목록 생성 end



   // 선생님 확인 부분
	public TeacherDTO myInfoArticle(LectureDTO dto) {
		TeacherDTO myDto = new TeacherDTO();

		getConnection();

		try {
			String sql = "select * from teacher where tcode=?";
			pstmt = conn.prepareStatement(sql);// 생성

			pstmt.setString(1, dto.gettCode());

			rs = pstmt.executeQuery();// 실행

			while (rs.next()) {
				myDto.settName(rs.getString("tname"));
				myDto.settGender(rs.getInt("tgender"));
				myDto.settPhone(rs.getString("tphone"));
				myDto.settEmail(rs.getString("temail"));				
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
		return myDto;
	}// 교사 정보 end
   
   
	//수정 
	public void updateArticle(TeacherDTO dto) { //
      getConnection();

      try {
         String sql = "update teacher set tname=?, tphone=?, tgender=?, temail=?, type=? where tcode= ?";
         pstmt = conn.prepareStatement(sql);// 생성

         pstmt.setString(1, dto.gettName());
         pstmt.setString(2, dto.gettPhone());
         pstmt.setInt(3, dto.gettGender());
         pstmt.setString(4, dto.gettEmail());
         pstmt.setString(5, dto.getType());
         pstmt.setString(6, dto.gettCode());
         pstmt.executeUpdate();// 실행

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
   }//수정end

	
	//등록
   public void insertArticle(TeacherDTO dto) {
      getConnection();
      String sql = "insert into teacher(tcode, tname, tgender, tphone, temail, type ) values(?, ?, ?, ?, ?, ?)";
      try {
         pstmt = conn.prepareStatement(sql);// 생성
         pstmt.setString(1, dto.gettCode());
         pstmt.setString(2, dto.gettName());
         pstmt.setInt(3, dto.gettGender());
         pstmt.setString(4, dto.gettPhone());
         pstmt.setString(5, dto.gettEmail());
         pstmt.setString(6, dto.getType());
         pstmt.executeUpdate();// 실행
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
   }//등록 end

   
   //삭제
   public void dropArticle(String tcode) {
      // DB
      getConnection(); // 접속

      try {
         String sql = "delete teacher where tcode = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, tcode);
         pstmt.executeUpdate();// 실행

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
   }//삭제 end

   
 //수강 신청 시 Tcode로 검색 
public int overlapArticle(TeacherDTO dto) {
	int sw = 0;
	getConnection();

	try {
		String sql = "select * from teacher where tCode=?";
		pstmt = conn.prepareStatement(sql);// 생성

		pstmt.setString(1, dto.gettCode());

		sw = pstmt.executeUpdate();// 실행

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
	return sw;
}


//선생 정보 불러오기(수정부분)
public TeacherDTO teacherInfoArticle(TeacherDTO dto) {
	TeacherDTO tdto = new TeacherDTO();
	
	getConnection();
	try {
		String sql = "select * from teacher where tCode=?";
		pstmt = conn.prepareStatement(sql);// 생성

		pstmt.setString(1, dto.gettCode());

		rs = pstmt.executeQuery();// 실행

		while (rs.next()) {
			tdto.settName(rs.getString("tName"));
			tdto.settCode(rs.getString("tCode"));
			tdto.settPhone(rs.getString("tPhone"));
			tdto.settEmail(rs.getString("tEmail"));
			tdto.settGender(rs.getInt("tGender"));
			tdto.setType(rs.getString("type"));

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

	return tdto;
}//선생 정보 불러오기end


//선생 정보 수정
public void changeArticle(TeacherDTO dto) {
	// DB
	getConnection();

	try {
		String sql = "update teacher set tname= ?, tgender=?, "
									  + "tphone=?, temail=?, type=? where tcode= ?";
		pstmt = conn.prepareStatement(sql);// 생성
		pstmt.setString(1, dto.gettName());
		pstmt.setInt(2, dto.gettGender());
		pstmt.setString(3, dto.gettPhone());
		pstmt.setString(4, dto.gettEmail());
		pstmt.setString(5, dto.getType());
		pstmt.setString(6, dto.gettCode());
		
		pstmt.executeUpdate();// 실행

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
	
}//수정 end


   
   
   
	}