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
	//private String url = "jdbc:oracle:thin:@58.123.1.248:1521:xe";	//이슬 집
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//학원
	private String username = "c##java";
	private String password = "bit";
	private String tCode;
	
	//생성자
	public MappingDAO() {
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
		public static MappingDAO getInstance() {
			if (instance == null) {
				synchronized (MappingDAO.class) {
					instance = new MappingDAO();// 맨처음 실행할 때 딱 한 번만 실행,생성된다.
				}
			}
			return instance; // 그 외에는 기존값을 불러옴.
		}	
		
		
	// list 목록 생성
	public List<MappingDTO> getMappingList() {
		List<MappingDTO> dtoList = new ArrayList<MappingDTO>();

		try {
			String sql = "select * from mapping";
			getConnection();

			pstmt = conn.prepareStatement(sql);// 생성
			rs = pstmt.executeQuery(); // 실행

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
	}// list 목록 생성 end

	
	
	
	// 내정보 확인 부분
	public MappingDTO InfoArticle(MappingDTO dto) {
		MappingDTO infoDto = new MappingDTO();

		getConnection();

		try {
			String sql = "select * from mapping where id=?";
			pstmt = conn.prepareStatement(sql);// 생성

			pstmt.setString(1, dto.getId());
			
			rs = pstmt.executeQuery();// 실행

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
	}// 내 정보 수정 end
	
	
	// 강사 정보 확인 부분
	public MappingDTO teacherInfoArticle(MappingDTO dto) {
		MappingDTO infoDto = new MappingDTO();

		getConnection();

		try {
			String sql = "select * from mapping where tCode=?";
			pstmt = conn.prepareStatement(sql);// 생성

			pstmt.setString(1, dto.gettCode());
			
			rs = pstmt.executeQuery();// 실행

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
	}// 내 정보 수정 end
	
	//수강신청
	public void applyArticle(MappingDTO dto) {
		getConnection();

		try {
			String sql = "insert into mapping(id, name, lecName, lecCode, tCode) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);// 생성

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getLecName());
			pstmt.setInt(4, dto.getLecCode());
			pstmt.setString(5, dto.gettCode());

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
		
	}//수강신청 end

	//이미 신청했는 지 확인하기
	public int applyConfirmArticle(MappingDTO dto) {
		int su = 0;
		getConnection();

		try {
			String sql = "select * from mapping where id=? and lecCode=?";
			pstmt = conn.prepareStatement(sql);// 생성

			pstmt.setString(1, dto.getId());
			pstmt.setInt(2,  dto.getLecCode());
			
			su = pstmt.executeUpdate();// 실행

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
	}//신청했는지 확인

	
	//수강 신청 시 인원 수 +1
	public void plusToArticle(int lecMemNum, int lecCode) {
		// DB
		getConnection();

		try {
			String sql = "update lecture set lecMemNum= ? where lecCode= ?";
			pstmt = conn.prepareStatement(sql);// 생성

			pstmt.setInt(1, (lecMemNum+1));
			pstmt.setInt(2, lecCode);

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
	}//수강 신청 시 인원 수 +1  end

	
	//평가 리스트
	public List<MappingDTO> getEvalList(String tCode) {
		this.tCode = tCode;
		
		List<MappingDTO> dtoList = new ArrayList<MappingDTO>();

		try {
			String sql = "select * from mapping where tCode=?";
			getConnection();
						
			pstmt = conn.prepareStatement(sql);// 생성
			
			pstmt.setString(1, tCode);
			rs = pstmt.executeQuery(); // 실행

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

	 
	 // 선택한 강의를 듣는 회원 목록
	 public List<MappingDTO> getMemberList(int lecCode) { 
	      List<MappingDTO> dtoList = new ArrayList<MappingDTO>();

	      try {
	         getConnection();
	         String sql = "select * from mapping where LecCode = ? ";
	         pstmt = conn.prepareStatement(sql);// 생성
	         pstmt.setInt(1, lecCode);
	         rs = pstmt.executeQuery(); // 실행

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
	   }// 선택한 강의를 듣는 회원 목록 end
	 
	 

	   public void deleteLecture(int lecCode) { // 강좌삭제
	      String sql = "delete mapping where lecCode=? ";
	      getConnection();
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, lecCode);
	         pstmt.executeUpdate();// 실행

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

	//나의 강의 목록
	public List<Integer> getMyLectureList(String id) {
		 List<Integer> dtoList = new ArrayList<Integer>();

	      try {
	         getConnection();
	         String sql = "select * from mapping where id = ? ";
	         pstmt = conn.prepareStatement(sql);// 생성
	         pstmt.setString(1, id);
	         rs = pstmt.executeQuery(); // 실행

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
	

	//평가 중복 확인
	public int overlapSwArticle(MappingDTO dto) {
		int sw = 0;
		getConnection();

		try {
			String sql = "select * from mapping where lecCode=? and id=?";
			pstmt = conn.prepareStatement(sql);// 생성

			pstmt.setInt(1, dto.getLecCode());
			pstmt.setString(2, dto.getId());
			
			rs = pstmt.executeQuery(); // 실행

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
	}//평가 중복 확인 end

	//평가 업데이트
	public void updateEvalArticle(MappingDTO dto) {
		// DB
		getConnection();

		try {
			String sql = "update mapping set eval= ?, sw =? where id= ? and LecCode=?";
			pstmt = conn.prepareStatement(sql);// 생성

			pstmt.setString(1, dto.getEval());
			pstmt.setInt(2, 1);
			pstmt.setString(3, dto.getId());
			pstmt.setInt(4, dto.getLecCode());

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
	}//평가 업데이트 end

	
	//회원 탈퇴 시 정보 삭제
	public void dropinfoArticle(MappingDTO dto) {
		// DB
		getConnection(); // 접속

		try {
			String sql = "delete mapping where id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
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
		
	}
	
	
	//강의 삭제 시 회원 정보 삭제
	public void droplecture(MappingDTO dto) {
		// DB
		getConnection(); // 접속

		try {
			String sql = "delete mapping where LecCode = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dto.getLecCode());
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

	}

	//강사 삭제 시 회원 정보 삭제
	public void dropTeacherArticle(String code) {
		// DB
		getConnection(); // 접속

		try {
			String sql = "delete mapping where tCode = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, code);
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
	}

	
	
	
}
