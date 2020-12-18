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
   //private String url = "jdbc:oracle:thin:@58.123.1.248:1521:xe";	//�̽� ��
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//�п�
   private String username = "c##java";
   private String password = "bit";

   // ������
   public TeacherDAO() {
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
   public static TeacherDAO getInstance() {
      if (instance == null) {
         synchronized (TeacherDAO.class) {
            instance = new TeacherDAO();// ��ó�� ������ �� �� �� ���� ����,�����ȴ�.
         }
      }
      return instance; // �� �ܿ��� �������� �ҷ���.
   }

   // list ��� ����
   public List<TeacherDTO> getTeacherList() {
      List<TeacherDTO> dtoList = new ArrayList<TeacherDTO>();

      try {
         String sql = "select * from teacher order by tcode";
         getConnection();

         pstmt = conn.prepareStatement(sql);// ����
         rs = pstmt.executeQuery(); // ����

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
   }// list ��� ���� end



   // ������ Ȯ�� �κ�
	public TeacherDTO myInfoArticle(LectureDTO dto) {
		TeacherDTO myDto = new TeacherDTO();

		getConnection();

		try {
			String sql = "select * from teacher where tcode=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.gettCode());

			rs = pstmt.executeQuery();// ����

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
	}// ���� ���� end
   
   
	//���� 
	public void updateArticle(TeacherDTO dto) { //
      getConnection();

      try {
         String sql = "update teacher set tname=?, tphone=?, tgender=?, temail=?, type=? where tcode= ?";
         pstmt = conn.prepareStatement(sql);// ����

         pstmt.setString(1, dto.gettName());
         pstmt.setString(2, dto.gettPhone());
         pstmt.setInt(3, dto.gettGender());
         pstmt.setString(4, dto.gettEmail());
         pstmt.setString(5, dto.getType());
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
   }//����end

	
	//���
   public void insertArticle(TeacherDTO dto) {
      getConnection();
      String sql = "insert into teacher(tcode, tname, tgender, tphone, temail, type ) values(?, ?, ?, ?, ?, ?)";
      try {
         pstmt = conn.prepareStatement(sql);// ����
         pstmt.setString(1, dto.gettCode());
         pstmt.setString(2, dto.gettName());
         pstmt.setInt(3, dto.gettGender());
         pstmt.setString(4, dto.gettPhone());
         pstmt.setString(5, dto.gettEmail());
         pstmt.setString(6, dto.getType());
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
   }//��� end

   
   //����
   public void dropArticle(String tcode) {
      // DB
      getConnection(); // ����

      try {
         String sql = "delete teacher where tcode = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, tcode);
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
   }//���� end

   
 //���� ��û �� Tcode�� �˻� 
public int overlapArticle(TeacherDTO dto) {
	int sw = 0;
	getConnection();

	try {
		String sql = "select * from teacher where tCode=?";
		pstmt = conn.prepareStatement(sql);// ����

		pstmt.setString(1, dto.gettCode());

		sw = pstmt.executeUpdate();// ����

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


//���� ���� �ҷ�����(�����κ�)
public TeacherDTO teacherInfoArticle(TeacherDTO dto) {
	TeacherDTO tdto = new TeacherDTO();
	
	getConnection();
	try {
		String sql = "select * from teacher where tCode=?";
		pstmt = conn.prepareStatement(sql);// ����

		pstmt.setString(1, dto.gettCode());

		rs = pstmt.executeQuery();// ����

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
}//���� ���� �ҷ�����end


//���� ���� ����
public void changeArticle(TeacherDTO dto) {
	// DB
	getConnection();

	try {
		String sql = "update teacher set tname= ?, tgender=?, "
									  + "tphone=?, temail=?, type=? where tcode= ?";
		pstmt = conn.prepareStatement(sql);// ����
		pstmt.setString(1, dto.gettName());
		pstmt.setInt(2, dto.gettGender());
		pstmt.setString(3, dto.gettPhone());
		pstmt.setString(4, dto.gettEmail());
		pstmt.setString(5, dto.getType());
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
	
}//���� end


   
   
   
	}