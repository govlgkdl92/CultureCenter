package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.bean.MemberDTO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static MemberDAO instance;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	//private String url = "jdbc:oracle:thin:@58.123.1.248:1521:xe";	//�̽� ��
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//�п�
	private String username = "c##java";
	private String password = "bit";

	// ������
	public MemberDAO() {
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
	public static MemberDAO getInstance() {
		if (instance == null) {
			synchronized (MemberDAO.class) {
				instance = new MemberDAO();// ��ó�� ������ �� �� �� ���� ����,�����ȴ�.
			}
		}
		return instance; // �� �ܿ��� �������� �ҷ���.
	}

	// list ��� ����
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> dtoList = new ArrayList<MemberDTO>();

		try {
			String sql = "select * from member order by name";
			getConnection();

			pstmt = conn.prepareStatement(sql);// ����
			rs = pstmt.executeQuery(); // ����

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getInt("gender"));
				dto.setPhone(rs.getString("phone"));
				dto.setHealth(rs.getInt("health"));
				dto.setSong(rs.getInt("song"));
				dto.setBook(rs.getInt("book"));
				dto.setCarpent(rs.getInt("carpent"));
				dto.setCook(rs.getInt("cook"));
				dto.setArt(rs.getInt("art"));
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

	// 1. ��� - joinArticle
	public void joinArticle(MemberDTO dto) {

		getConnection();

		try {
			String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getBirth());
			pstmt.setInt(5, dto.getGender());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getEmail());
			pstmt.setInt(8, dto.getHealth());
			pstmt.setInt(9, dto.getSong());
			pstmt.setInt(10, dto.getBook());
			pstmt.setInt(11, dto.getCarpent());
			pstmt.setInt(12, dto.getCook());
			pstmt.setInt(13, dto.getArt());

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
	}// insertArticle end

	// 2.�α���
	public int loginArticle(MemberDTO dto) {
		int su = 0;
		getConnection();

		try {
			String sql = "select * from member where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());

			su = pstmt.executeUpdate();// ����

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
		return su;
	}

	// ���̵� ã��
	public String idSearchArticle(MemberDTO dto) {
		String id = "";
		getConnection();

		try {
			String sql = "select * from member where name=? and birth=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirth());

			rs = pstmt.executeQuery();// ����

			while (rs.next()) {
				id = rs.getString("id");
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
		return id;
	}

	// �ߺ� Ȯ��
	public int overlapArticle(MemberDTO dto) {
		int sw = 0;
		getConnection();

		try {
			String sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getId());

			sw = pstmt.executeUpdate();// ����

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
	}// �ߺ� Ȯ�� end
	
	
	
	//�̸��� �ߺ� Ȯ��
	public int emailConfirmArticle(MemberDTO dto) {
		int sw = 0;
		getConnection();

		try {
			String sql = "select * from member where email=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getEmail());

			sw = pstmt.executeUpdate();// ����

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
	}//�̸��� �ߺ� Ȯ�� end

	// �н����� ����
	public void PwChangeArticle(MemberDTO dto) {
		// DB
		getConnection();

		try {
			String sql = "update member set pw= ? where id= ?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getId());

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

	// ������ Ȯ�� �κ�
	public MemberDTO myInfoArticle(MemberDTO dto) {
		MemberDTO myDto = new MemberDTO();

		getConnection();

		try {
			String sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getId());

			rs = pstmt.executeQuery();// ����

			while (rs.next()) {
				myDto.setName(rs.getString("name"));
				myDto.setBirth(rs.getString("birth"));
				myDto.setPhone(rs.getString("phone"));
				myDto.setEmail(rs.getString("email"));
				myDto.setGender(rs.getInt("gender"));
				myDto.setHealth(rs.getInt("health"));
				myDto.setSong(rs.getInt("song"));
				myDto.setBook(rs.getInt("book"));
				myDto.setCarpent(rs.getInt("carpent"));
				myDto.setCook(rs.getInt("cook"));
				myDto.setArt(rs.getInt("art"));

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
	}// �� ���� ���� end

	// �ڵ��� ���� �κ�
	public void phoneChangeArticle(MemberDTO dto) {
		// DB
		getConnection();

		try {
			String sql = "update member set phone= ? where id= ?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getPhone());
			pstmt.setString(2, dto.getId());

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
	}// ����ȣ ���� end

	// ���ɻ� ����
	public void interestChangeArticle(MemberDTO dto) {
		// DB
		getConnection();

		try {
			String sql = "update member set health= ?, song=?, book=?," + " carpent=?, cook=?, art = ? where id= ?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setInt(1, dto.getHealth());
			pstmt.setInt(2, dto.getSong());
			pstmt.setInt(3, dto.getBook());
			pstmt.setInt(4, dto.getCarpent());
			pstmt.setInt(5, dto.getCook());
			pstmt.setInt(6, dto.getArt());
			pstmt.setString(7, dto.getId());

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
	}// ���ɻ� ���� end

	// email ����
	public void emailChangeArticle(MemberDTO dto) {
		// DB
		getConnection();

		try {
			String sql = "update member set email= ? where id= ?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getId());

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
	}// �̸��� ���� end

	// Ż��
	public void dropArticle(MemberDTO dto) {
		// DB
		getConnection(); // ����

		try {
			String sql = "delete member where id = ?";
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
	}// Ż�� end

	
	
	// � �����
	public String memHealthArticle(MemberDTO dto) {
		String id = "";
		getConnection();

		try {
			String sql = "select * from member where name=? and birth=?";
			pstmt = conn.prepareStatement(sql);// ����

			pstmt.setInt(1, dto.getHealth());

			rs = pstmt.executeQuery();// ����

			while (rs.next()) {
				id = rs.getString("id");
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
		return id;
	}

}
