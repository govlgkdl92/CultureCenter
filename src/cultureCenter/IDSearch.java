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
import java.util.ArrayList;
import java.util.List;

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
public class IDSearch extends JFrame implements ActionListener {
	private JLabel titleL, infoL, nameL, birthL;
	private JTextField nameT, birthT;
	private JButton serachBtn, cancelBtn;
	private BufferedImage img = null;
	private List<MemberDTO> dtoList = new ArrayList<MemberDTO>();

	public IDSearch() {
		// 아이디 입력창
		titleL = new JLabel("아이디 찾기");
		titleL.setFont(new Font("한컴 백제 M", Font.BOLD, 35));

		infoL = new JLabel("정확하게 입력해주세요.");
		infoL.setFont(new Font("한컴 백제 M", Font.PLAIN, 14));

		nameL = new JLabel("이    름");
		nameL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		nameT = new JTextField();
		nameT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		birthL = new JLabel("생년월일");
		birthL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		birthT = new JTextField();
		birthT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		// 찾기 버튼
		serachBtn = new JButton(new ImageIcon("lib/idSearch.png"));
		serachBtn.setBorderPainted(false);
		serachBtn.setContentAreaFilled(false);
		serachBtn.setOpaque(false);

		// 취소 버튼
		cancelBtn = new JButton(new ImageIcon("lib/cancelB.png"));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setOpaque(false);

		// 위치
		titleL.setBounds(110, 10, 250, 50);
		infoL.setBounds(70, 50, 250, 67);

		nameL.setBounds(70, 100, 80, 30);
		nameT.setBounds(170, 100, 150, 30);
		birthL.setBounds(70, 140, 80, 30);
		birthT.setBounds(170, 140, 150, 30);

		cancelBtn.setBounds(100, 195, 59, 40);
		serachBtn.setBounds(180, 195, 130, 40);

		// add ~ * ~
		Container c = this.getContentPane();
		c.add(titleL);
		c.add(infoL);

		c.add(nameL);
		c.add(nameT);

		c.add(birthL);
		c.add(birthT);

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
		setBounds(820, 410, 400, 300);
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
		String name = nameT.getText();
		String birth = birthT.getText();
		String id = "";

		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setName(name);
		dto.setBirth(birth);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		id = dao.idSearchArticle(dto);

		// setVisible(false);
		if ((nameT.getText()).equals("") || (nameT.getText()).equals(null) || (birthT.getText()).equals("")
				|| (birthT.getText()).equals(null)) { // 공백
			JOptionPane.showMessageDialog(this, "이름과 생년월일을 모두 입력해주세요.");
		} else if (id.equals("") || id == null) {
			JOptionPane.showMessageDialog(this, "입력하신 회원 정보가 없습니다");
		} else {
			JOptionPane.showMessageDialog(this, name + " 님의 아이디는 [ " + id + " ]입니다.");
			dispose();
		}
	}

}
