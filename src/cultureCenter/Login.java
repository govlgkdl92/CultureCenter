package cultureCenter;

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

import admin.action.AdminLogin;
import member.action.Member;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class Login extends JFrame implements ActionListener {

	private JLabel titleL, pwL, idL, usL;
	private JTextField idT;
	private JPasswordField pwT;
	private JButton adminBtn, loginBtn, joinBtn, idSearchBtn, pwSearchBtn;
	private String id, pwd;
	private BufferedImage img = null;

	public Login() {
		// 아이디 입력창
		titleL = new JLabel("비트 문화센터");
		usL = new JLabel(new ImageIcon("lib/joinus.png"));

		titleL.setFont(new Font("한컴 바겐세일 B", Font.BOLD, 30));

		idL = new JLabel("아이디");
		idL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		idT = new JTextField();
		idT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		// 비밀번호 입력창
		pwL = new JLabel("비밀번호");
		pwL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		pwT = new JPasswordField();
		pwT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		pwT.setEchoChar('●');

		// 관리자 로그인 버튼
		adminBtn = new JButton(new ImageIcon("lib/adminBtn.png"));
		adminBtn.setBorderPainted(false);
		adminBtn.setContentAreaFilled(false);
		adminBtn.setOpaque(false);

		// 로그인 버튼
		loginBtn = new JButton(new ImageIcon("lib/loginBtn.png"));
		loginBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setOpaque(false);

		// 회원가입 버튼
		joinBtn = new JButton(new ImageIcon("lib/joinBtn.png"));
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setOpaque(false);

		// ID찾기 버튼
		idSearchBtn = new JButton(new ImageIcon("lib/idSearchBtn.png"));
		idSearchBtn.setBorderPainted(false);
		idSearchBtn.setContentAreaFilled(false);
		idSearchBtn.setOpaque(false);

		// PW찾기 버튼
		pwSearchBtn = new JButton(new ImageIcon("lib/pwSearchBtn.png"));
		pwSearchBtn.setBorderPainted(false);
		pwSearchBtn.setContentAreaFilled(false);
		pwSearchBtn.setOpaque(false);
		
		// 위치
		titleL.setBounds(380, 140, 250, 50);
		usL.setBounds(270, 90, 120, 67);

		idL.setBounds(330, 230, 80, 30);
		idT.setBounds(420, 230, 150, 30);
		pwL.setBounds(330, 290, 80, 30);
		pwT.setBounds(420, 290, 150, 30);

		adminBtn.setBounds(20, 450, 107, 25);
		loginBtn.setBounds(460, 380, 132, 50);
		joinBtn.setBounds(300, 380, 132, 50);
		idSearchBtn.setBounds(400, 450, 74, 20);
		pwSearchBtn.setBounds(500, 450, 88, 20);

		// add ~ * ~
		Container c = this.getContentPane();
		c.add(titleL);
		c.add(usL);

		c.add(idL);
		c.add(idT);

		c.add(pwL);
		c.add(pwT);

		c.add(adminBtn);
		c.add(loginBtn);
		c.add(joinBtn);
		c.add(idSearchBtn);
		c.add(pwSearchBtn);

		// 배경 Panel
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(650, 530);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("lib/backgrd.png"));
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
				int exit = JOptionPane.showConfirmDialog(Login.this, "정말 종료 하시겠습니까?", "비트문화센터 종료",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (exit == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
	}

	class bgPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	// 이벤트 생성
	public void event() {
		//pwT.setMnemonic(KeyEvent.VK_ENTER);
		adminBtn.addActionListener(this);
		loginBtn.addActionListener(this);
		joinBtn.addActionListener(this);
		idSearchBtn.addActionListener(this);
		pwSearchBtn.addActionListener(this);
	
		pwT.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == joinBtn) { // 회원가입
			new Join();
		} else if (e.getSource() == loginBtn) { // 로그인
			login();
		} else if (e.getSource() == adminBtn) { // 관리자 로그인
			setVisible(false);
			new AdminLogin();
		} else if (e.getSource() == idSearchBtn) { // ID찾기
			new IDSearch();
		} else if (e.getSource() == pwSearchBtn) { // PW찾기
			new PWSearch();
		}

	}

	private void login() {
		// 데이터
		String id = idT.getText();
		String pw = pwT.getText();

		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		int su = dao.loginArticle(dto);

		// setVisible(false);
		if ((idT.getText()).equals("") || (idT.getText()).equals(null) || (pwT.getText()).equals("")
				|| (pwT.getText()).equals(null)) { // 공백
			JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호를 입력해주세요");
		} else if (su == 1) {
			new Member(id);
			dispose();
		} else if (su == 0) {
			JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 올바르지 않습니다.");
		}

	}
}