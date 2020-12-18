package cultureCenter;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class PWSearch extends JFrame implements ActionListener {
	private JLabel titleL, infoL, idL, nameL, birthL, emailL, emailVL;
	private JTextField idT, nameT, birthT, emailT, emailVT;
	private JButton serachBtn, cancelBtn, verifyBtn;
	private BufferedImage img = null;

	public PWSearch() {
		// 입력창
		titleL = new JLabel("비밀번호 찾기");
		titleL.setFont(new Font("한컴 백제 M", Font.BOLD, 35));

		idL = new JLabel("아 이 디");
		idL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		idT = new JTextField();
		idT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		infoL = new JLabel("* 정확하게 입력해주세요.");
		infoL.setFont(new Font("한컴 백제 M", Font.PLAIN, 14));

		nameL = new JLabel("이    름");
		nameL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		nameT = new JTextField();
		nameT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		birthL = new JLabel("생년월일");
		birthL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		birthT = new JTextField();
		birthT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		emailL = new JLabel("이 메 일");
		emailL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		emailT = new JTextField();
		emailT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		emailVL = new JLabel("인증번호");
		emailVL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		emailVT = new JTextField();
		emailVT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		// 찾기 버튼
		serachBtn = new JButton(new ImageIcon("lib/pwSearch.png"));
		serachBtn.setBorderPainted(false);
		serachBtn.setContentAreaFilled(false);
		serachBtn.setOpaque(false);

		// 취소 버튼
		cancelBtn = new JButton(new ImageIcon("lib/cancelB.png"));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setOpaque(false);

		// 인증확인 버튼
		verifyBtn = new JButton(new ImageIcon("lib/verify.png"));
		verifyBtn.setBorderPainted(false);
		verifyBtn.setContentAreaFilled(false);
		verifyBtn.setOpaque(false);

		// 위치
		titleL.setBounds(90, 10, 250, 50);
		infoL.setBounds(70, 50, 250, 67);

		idL.setBounds(50, 100, 80, 30);
		idT.setBounds(140, 100, 150, 30);
		nameL.setBounds(50, 140, 80, 30);
		nameT.setBounds(140, 140, 150, 30);
		birthL.setBounds(50, 180, 80, 30);
		birthT.setBounds(140, 180, 150, 30);
		emailL.setBounds(50, 220, 80, 30);
		emailT.setBounds(140, 220, 150, 30);
		verifyBtn.setBounds(290, 220, 80, 30);

		emailVL.setBounds(50, 260, 80, 30);
		emailVT.setBounds(140, 260, 100, 30);
		cancelBtn.setBounds(100, 305, 59, 40);
		serachBtn.setBounds(180, 305, 130, 40);

		// add ~ * ~
		Container c = this.getContentPane();
		c.add(titleL);
		c.add(infoL);
		c.add(idL);
		c.add(idT);

		c.add(nameL);
		c.add(nameT);

		c.add(birthL);
		c.add(birthT);
		c.add(emailL);
		c.add(emailT);
		c.add(verifyBtn);
		c.add(emailVL);
		c.add(emailVT);
		c.add(serachBtn);
		c.add(cancelBtn);

		// 배경 Panel
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(650, 530);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("lib/flower.jpg"));
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
		setBounds(820, 360, 400, 400);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		// 이벤트
		serachBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}

	class bgPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == serachBtn) { // 찾기
			search();
		} else if (e.getSource() == cancelBtn) { // 로그인
			setVisible(false);
		}
	}

	private void search() {
		// 데이터
		String id = idT.getText();
		String name = nameT.getText();
		String birth = birthT.getText();
		String email = emailT.getText();

		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setName(name);
		dto.setBirth(birth);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		String su = dao.idSearchArticle(dto);

		// setVisible(false);
		if ((nameT.getText()).equals("") || (nameT.getText()).equals(null) || (birthT.getText()).equals("")
				|| (birthT.getText()).equals(null) || (emailT.getText()).equals("")
				|| (emailT.getText()).equals(null)) { // 공백
			JOptionPane.showMessageDialog(this, "모든 정보를 입력해주세요.");
		} else if (su.equals("") || su == null) {
			JOptionPane.showMessageDialog(this, "입력하신 회원 정보가 없습니다");
		} else {
			dispose();
			new PwChange(idT.getText());
		}
	}

}
