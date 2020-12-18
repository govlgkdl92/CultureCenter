package admin.action;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.bean.AdminDTO;
import admin.dao.AdminDAO;
import cultureCenter.Login;

@SuppressWarnings("all")
public class AdminLogin extends JFrame implements ActionListener {
	private JLabel titleL, title2L, idL, pwL;
	private JTextField idT;
	private JPasswordField pwT;
	private JButton loginBtn, cancelBtn;

	BufferedImage img = null;

	public AdminLogin() {
		// 아이디 입력창
		titleL = new JLabel("비트 문화센터");
		titleL.setFont(new Font("한컴 바겐세일 B", Font.BOLD, 30));

		title2L = new JLabel("관리자[강사] 로그인");
		title2L.setFont(new Font("한컴 바겐세일 B", Font.BOLD, 30));

		idL = new JLabel("관리자 아이디");
		idL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		idT = new JTextField();
		idT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		// 비밀번호 입력창
		pwL = new JLabel("관리자 비밀번호");
		pwL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		pwT = new JPasswordField();
		pwT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		pwT.setEchoChar('●');

		// 로그인 버튼
		loginBtn = new JButton(new ImageIcon("lib/loginBtn.png"));
		loginBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setOpaque(false);

		// 취소 버튼
		cancelBtn = new JButton(new ImageIcon("lib/cancelBtn.png"));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setOpaque(false);

		// 위치
		titleL.setBounds(80, 80, 400, 50);
		title2L.setBounds(50, 140, 400, 50);
		idL.setBounds(50, 230, 150, 30);
		idT.setBounds(200, 230, 150, 30);
		pwL.setBounds(50, 290, 150, 30);
		pwT.setBounds(200, 290, 150, 30);

		cancelBtn.setBounds(40, 380, 132, 50);
		loginBtn.setBounds(170, 380, 132, 50);

		// add ~ * ~
		Container c = this.getContentPane();
		c.add(titleL);
		c.add(title2L);
		c.add(idL);
		c.add(idT);

		c.add(pwL);
		c.add(pwT);

		c.add(loginBtn);
		c.add(cancelBtn);

		// 배경 Panel
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(650, 530);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("lib/back.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
			System.exit(0);
		}
		// 이미지 삽입
		bgPanel panel = new bgPanel();
		panel.setSize(650, 530);
		layeredPane.add(panel);
		add(layeredPane);

		setLayout(null);

		// 기본 프레임 생성.
		setLayout(null);
		setBounds(700, 300, 650, 530);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				new Login().event();
			}
		});

		// 이벤트
		cancelBtn.addActionListener(this);
		loginBtn.addActionListener(this);
		
		pwT.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
	}

	class bgPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginBtn) { // 관리자 로그인
			login();
		} else if (e.getSource() == cancelBtn) { // 취소
			setVisible(false);
			new Login().event();
		}

	}

	private void login() {
		// 데이터
		String id = idT.getText();
		String pw = pwT.getText();

		// 	AdminDTO
		AdminDTO dto = new 	AdminDTO();
		dto.setId(id);
		dto.setPw(pw);

		// DB
		AdminDAO dao = 	AdminDAO.getInstance();

		int su = dao.loginArticle(dto);

		// setVisible(false);
		if ((idT.getText()).equals("") || (idT.getText()).equals(null) || (pwT.getText()).equals("")
				|| (pwT.getText()).equals(null)) { // 공백
			JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호를 입력해주세요");
		} else if (su == 1) {
			setVisible(false);
			new Admin(idT.getText());
		} else if (su == 0) {
			JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 올바르지 않습니다.");
		}
		
		
		
	}
}
