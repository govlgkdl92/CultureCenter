package member.action;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cultureCenter.Login;

import java.awt.image.BufferedImage;

@SuppressWarnings("all")
public class Member_Lecturer extends JPanel implements ActionListener {
	private String id;
	private JLabel idL, introduceL;
	private JButton logoutBtn;
	private Member member;

	public Member_Lecturer(String id, Member member) {

		this.id = id;
		this.member = member;

		idL = new JLabel(id + " 님 환영합니다.");
		idL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		// 버튼
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		// 위치
		idL.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);

		// 추가
		add(idL);
		add(logoutBtn);

		// 버튼 이벤트
		logoutBtn.addActionListener(this);
		setLayout(null);

		introduceL = new JLabel("강사소개");
		introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 40));
		introduceL.setBounds(120, 45, 600, 40);
		add(introduceL);

		JTabbedPane jtab = new JTabbedPane(); // JTabbedPane 객체 생성

		jtab.addTab("운 동", new JPanel011());
		jtab.addTab("노 래", new JPanel022());
		jtab.addTab("독 서", new JPanel033());
		jtab.addTab("목 공", new JPanel044());
		jtab.addTab("요 리", new JPanel055());
		jtab.addTab("예 술", new JPanel066());

		// jtab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jtab.setBounds(110, 90, 750, 590);
		add(jtab);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	
	class JPanel011 extends JPanel { // 1번 패널운동
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/HealthTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel011() {// 1번째 패널 생성자
			setLayout(null);

			introduceL = new JLabel("박태환(Park Taehwan)");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 35));
			introduceL.setBounds(300, 110, 600, 45);
			add(introduceL);

			introduceL = new JLabel("바다의 왕자 마린 보이");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(400, 175, 600, 30);
			add(introduceL);

			introduceL = new JLabel("푸른 바다 밑에서 잘도 싸우는");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(400, 205, 600, 30);
			add(introduceL);

			introduceL = new JLabel("슬기롭고 씩씩한 용감스러운");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(400, 235, 600, 30);
			add(introduceL);

			introduceL = new JLabel("마린 보이 소년은 우리편이다");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(400, 265, 600, 30);
			add(introduceL);

			introduceL = new JLabel("Yeah!");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(400, 295, 600, 30);
			add(introduceL);
		}
	}

	class JPanel022 extends JPanel { // 2번째 패널노래
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/SingTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel022() { // 2번째 패널 생성자
			setLayout(null);

			introduceL = new JLabel("송복희(송해)");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 35));
			introduceL.setBounds(400, 110, 600, 45);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(400, 175, 600, 30);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(400, 200, 600, 30);
			add(introduceL);

			introduceL = new JLabel("전국~~~~~~~~~~~~");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(400, 225, 600, 30);
			add(introduceL);

		}
	}

	class JPanel033 extends JPanel { // 3번째 패널독서
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/BookTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel033() { // 3번째 패널 생성자
			setLayout(null);

			introduceL = new JLabel("소크라테스 ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 35));
			introduceL.setBounds(90, 130, 600, 45);
			add(introduceL);

			introduceL = new JLabel("(Socrates)");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 35));
			introduceL.setBounds(90, 170, 600, 30);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(600, 200, 600, 30);
			add(introduceL);

			introduceL = new JLabel("너 자신을 알라.");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(500, 225, 600, 30);
			add(introduceL);

		}
	}

	class JPanel044 extends JPanel { // 4번 패널목공
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/CarportTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel044() { // 4번째 패널 생성자
			setLayout(null);
			
			introduceL = new JLabel("그루트 (Groot)");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 35));
			introduceL.setBounds(100, 130, 600, 45);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(100, 175, 600, 30);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(100, 200, 600, 30);
			add(introduceL);

			introduceL = new JLabel("I am Groot...");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(100, 225, 600, 30);
			add(introduceL);

		}
	}

	class JPanel055 extends JPanel { // 5번 패널요리
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/CookTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel055() { // 5번째 패널 생성자
			setLayout(null);
			
			introduceL = new JLabel("백종원(주부)");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 45));
			introduceL.setBounds(100, 140, 600, 45);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(200, 175, 600, 30);
			add(introduceL);

			introduceL = new JLabel("왜 요리를 ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(100, 220, 600, 30);
			add(introduceL);

			introduceL = new JLabel("인터넷으로만 공부하려 하는가?");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
			introduceL.setBounds(100, 255, 600, 30);
			add(introduceL);

		}
	}

	class JPanel066 extends JPanel { // 6번 패널예술
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/ArtTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel066() { // 6번째 패널 생성자
			setLayout(null);
			introduceL = new JLabel("밥로스 ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 35));
			introduceL.setBounds(80, 90, 600, 50);
			add(introduceL);

			introduceL = new JLabel("(Robert Norman Ross)");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 30));
			introduceL.setBounds(80, 130, 600, 45);
			add(introduceL);

			introduceL = new JLabel("그림에서는 당신이 가장 강력한 파워를 가졌습니다.  ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 22));
			introduceL.setBounds(40, 220, 600, 30);
			add(introduceL);

			introduceL = new JLabel("산도 옮길 수 있지요. 강도 휘게 할수 있고요. 하지만, ");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 22));
			introduceL.setBounds(40, 250, 600, 30);
			add(introduceL);

			introduceL = new JLabel("저는 집에서 가진 힘이라고는 쓰레기 분리수거 밖에 없어요.");
			introduceL.setFont(new Font("한컴 백제 M", Font.PLAIN, 22));
			introduceL.setBounds(40, 280, 600, 30);
			add(introduceL);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { // 로그아웃 버튼
			member.setVisible(false);
			new Login().event();
		}
	}

}