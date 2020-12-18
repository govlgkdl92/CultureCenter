package admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import admin.bean.AdminDTO;

public class AdminDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private static AdminDAO instance;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	//private String url = "jdbc:oracle:thin:@58.123.1.248:1521:xe";	//이슬 집
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//학원
	private String username = "c##java";
	private String password = "bit";

	public AdminDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static AdminDAO getInstance() {
		if (instance == null) {
			synchronized (AdminDAO.class) {
				instance = new AdminDAO();
			}
		}
		return instance;
	}

	public int loginArticle(AdminDTO dto) {
		int su = 0;
		getConnection();

		try {
			String sql = "select * from admin where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());

			su = pstmt.executeUpdate();

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

}
