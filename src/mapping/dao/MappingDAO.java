package mapping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mapping.bean.MappingDTO;

@SuppressWarnings("all")
public class MappingDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static MappingDAO instance;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	//private String url = "jdbc:oracle:thin:@58.123.1.248:1521:xe";	//�̽� ��
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//�п�
	private String username = "c##java";
	private String password = "bit";
	private String tCode;
	
	//������
	public MappingDAO() {
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
		public static MappingDAO getInstance() {
			if (instance == null) {
				synchronized (MappingDAO.class) {
					instance = new MappingDAO();// ��ó�� ������ �� �� �� ���� ����,�����ȴ�.
				}
			}
			return instance; // �� �ܿ��� �������� �ҷ���.
		}	
		
		
	// list ��� ����
	public List<MappingDTO> getMappingList() {
		List<MappingDTO> dtoList = new ArrayList<MappingDTO>();

		try {
			String sql = "select * from mapping";
			getConnection();

			pstmt = conn.prepareStatement(sql);// ����
			rs = pstmt.executeQuery(); // ����

			while (rs.next()) {
				MappingDTO dto = new MappingDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setLecName(rs.getString("lecName"));
				dto.setLecCode(rs.getInt("lecCode"));
				dto.settCode(rs.getString("tCode"));
				dto.setEval(rs.getString("eval"));
				dto.setSw(rs.getInt("sw"));
				
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
	public MappingDTO InfoArticle(MappingDTO dto) {
		MappingDTO infoDto = new MappingDTO();

		getConnection();

		try {
			String sql = "select * from mapping where id=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getId());
			
			rs = pstmt.executeQuery();// ����

			while (rs.next()) {
				infoDto.setId(rs.getString("id"));
				infoDto.setName(rs.getString("name"));
				infoDto.setLecName(rs.getString("lecName"));
				infoDto.setLecCode(rs.getInt("lecCode"));
				infoDto.settCode(rs.getString("tCode"));
				infoDto.setEval(rs.getString("eval"));
				infoDto.setSw(rs.getInt("sw"));
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

		return infoDto;
	}// �� ���� ���� end
	
	
	// ���� ���� Ȯ�� �κ�
	public MappingDTO teacherInfoArticle(MappingDTO dto) {
		MappingDTO infoDto = new MappingDTO();

		getConnection();

		try {
			String sql = "select * from mapping where tCode=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.gettCode());
			
			rs = pstmt.executeQuery();// ����

			while (rs.next()) {
				infoDto.setId(rs.getString("id"));
				infoDto.setName(rs.getString("name"));
				infoDto.setLecName(rs.getString("lecName"));
				infoDto.setLecCode(rs.getInt("lecCode"));
				infoDto.settCode(rs.getString("tCode"));
				infoDto.setEval(rs.getString("eval"));
				infoDto.setSw(rs.getInt("sw"));
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

		return infoDto;
	}// �� ���� ���� end
	
	//������û
	public void applyArticle(MappingDTO dto) {
		getConnection();

		try {
			String sql = "insert into mapping(id, name, lecName, lecCode, tCode) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getLecName());
			pstmt.setInt(4, dto.getLecCode());
			pstmt.setString(5, dto.gettCode());

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
		
	}//������û end

	//�̹� ��û�ߴ� �� Ȯ���ϱ�
	public int applyConfirmArticle(MappingDTO dto) {
		int su = 0;
		getConnection();

		try {
			String sql = "select * from mapping where id=? and lecCode=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getId());
			pstmt.setInt(2,  dto.getLecCode());
			
			su = pstmt.executeUpdate();// ����

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
		return su;		
	}//��û�ߴ��� Ȯ��

	
	//���� ��û �� �ο� �� +1
	public void plusToArticle(int lecMemNum, int lecCode) {
		// DB
		getConnection();

		try {
			String sql = "update lecture set lecMemNum= ? where lecCode= ?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setInt(1, (lecMemNum+1));
			pstmt.setInt(2, lecCode);

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
	}//���� ��û �� �ο� �� +1  end

	
	//�� ����Ʈ
	public List<MappingDTO> getEvalList(String tCode) {
		this.tCode = tCode;
		
		List<MappingDTO> dtoList = new ArrayList<MappingDTO>();

		try {
			String sql = "select * from mapping where tCode=?";
			getConnection();
						
			pstmt = conn.prepareStatement(sql);// ����
			
			pstmt.setString(1, tCode);
			rs = pstmt.executeQuery(); // ����

			while (rs.next()) {
				MappingDTO dtoE = new MappingDTO();
				dtoE.setId(rs.getString("id"));
				dtoE.setName(rs.getString("name"));
				dtoE.setLecCode(rs.getInt("lecCode"));
				dtoE.settCode(rs.getString("tCode"));
				dtoE.setEval(rs.getString("eval"));
				dtoE.setSw(rs.getInt("sw"));		
				dtoList.add(dtoE);
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
	}

	 
	 // ������ ���Ǹ� ��� ȸ�� ���
	 public List<MappingDTO> getMemberList(int lecCode) { 
	      List<MappingDTO> dtoList = new ArrayList<MappingDTO>();

	      try {
	         getConnection();
	         String sql = "select * from mapping where LecCode = ? ";
	         pstmt = conn.prepareStatement(sql);// ����
	         pstmt.setInt(1, lecCode);
	         rs = pstmt.executeQuery(); // ����

	         while (rs.next()) {
	            MappingDTO mappingDto = new MappingDTO();
	            mappingDto.setId(rs.getString("id"));
	            mappingDto.setName(rs.getString("name"));
	            dtoList.add(mappingDto);
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
	   }// ������ ���Ǹ� ��� ȸ�� ��� end
	 
	 

	   public void deleteLecture(int lecCode) { // ���»���
	      String sql = "delete mapping where lecCode=? ";
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

	//���� ���� ���
	public List<Integer> getMyLectureList(String id) {
		 List<Integer> dtoList = new ArrayList<Integer>();

	      try {
	         getConnection();
	         String sql = "select * from mapping where id = ? ";
	         pstmt = conn.prepareStatement(sql);// ����
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery(); // ����

	         while (rs.next()) {
	            
	            dtoList.add(rs.getInt("lecCode"));
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
	}
	

	//�� �ߺ� Ȯ��
	public int overlapSwArticle(MappingDTO dto) {
		int sw = 0;
		getConnection();

		try {
			String sql = "select * from mapping where lecCode=? and id=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setInt(1, dto.getLecCode());
			pstmt.setString(2, dto.getId());
			
			rs = pstmt.executeQuery(); // ����

	         while (rs.next()) {
	            
	        	 sw = rs.getInt("Sw");
	         }
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
		return sw;
	}//�� �ߺ� Ȯ�� end

	//�� ������Ʈ
	public void updateEvalArticle(MappingDTO dto) {
		// DB
		getConnection();

		try {
			String sql = "update mapping set eval= ?, sw =? where id= ? and LecCode=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getEval());
			pstmt.setInt(2, 1);
			pstmt.setString(3, dto.getId());
			pstmt.setInt(4, dto.getLecCode());

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
	}//�� ������Ʈ end

	
	//ȸ�� Ż�� �� ���� ����
	public void dropinfoArticle(MappingDTO dto) {
		// DB
		getConnection(); // ����

		try {
			String sql = "delete mapping where id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
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
		
	}
	
	
	//���� ���� �� ȸ�� ���� ����
	public void droplecture(MappingDTO dto) {
		// DB
		getConnection(); // ����

		try {
			String sql = "delete mapping where LecCode = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getLecCode());
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

	}

	//���� ���� �� ȸ�� ���� ����
	public void dropTeacherArticle(String code) {
		// DB
		getConnection(); // ����

		try {
			String sql = "delete mapping where tCode = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, code);
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
	}

	
	
	
}
