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

		idL = new JLabel(id + " �� ȯ���մϴ�.");
		idL.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		// ��ư
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		// ��ġ
		idL.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);

		// �߰�
		add(idL);
		add(logoutBtn);

		// ��ư �̺�Ʈ
		logoutBtn.addActionListener(this);
		setLayout(null);

		introduceL = new JLabel("����Ұ�");
		introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 40));
		introduceL.setBounds(120, 45, 600, 40);
		add(introduceL);

		JTabbedPane jtab = new JTabbedPane(); // JTabbedPane ��ü ����

		jtab.addTab("�� ��", new JPanel011());
		jtab.addTab("�� ��", new JPanel022());
		jtab.addTab("�� ��", new JPanel033());
		jtab.addTab("�� ��", new JPanel044());
		jtab.addTab("�� ��", new JPanel055());
		jtab.addTab("�� ��", new JPanel066());

		// jtab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jtab.setBounds(110, 90, 750, 590);
		add(jtab);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	
	class JPanel011 extends JPanel { // 1�� �гο
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/HealthTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel011() {// 1��° �г� ������
			setLayout(null);

			introduceL = new JLabel("����ȯ(Park Taehwan)");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 35));
			introduceL.setBounds(300, 110, 600, 45);
			add(introduceL);

			introduceL = new JLabel("�ٴ��� ���� ���� ����");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(400, 175, 600, 30);
			add(introduceL);

			introduceL = new JLabel("Ǫ�� �ٴ� �ؿ��� �ߵ� �ο��");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(400, 205, 600, 30);
			add(introduceL);

			introduceL = new JLabel("����Ӱ� ������ �밨������");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(400, 235, 600, 30);
			add(introduceL);

			introduceL = new JLabel("���� ���� �ҳ��� �츮���̴�");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(400, 265, 600, 30);
			add(introduceL);

			introduceL = new JLabel("Yeah!");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(400, 295, 600, 30);
			add(introduceL);
		}
	}

	class JPanel022 extends JPanel { // 2��° �гγ뷡
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/SingTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel022() { // 2��° �г� ������
			setLayout(null);

			introduceL = new JLabel("�ۺ���(����)");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 35));
			introduceL.setBounds(400, 110, 600, 45);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(400, 175, 600, 30);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(400, 200, 600, 30);
			add(introduceL);

			introduceL = new JLabel("����~~~~~~~~~~~~");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(400, 225, 600, 30);
			add(introduceL);

		}
	}

	class JPanel033 extends JPanel { // 3��° �гε���
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/BookTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel033() { // 3��° �г� ������
			setLayout(null);

			introduceL = new JLabel("��ũ���׽� ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 35));
			introduceL.setBounds(90, 130, 600, 45);
			add(introduceL);

			introduceL = new JLabel("(Socrates)");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 35));
			introduceL.setBounds(90, 170, 600, 30);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(600, 200, 600, 30);
			add(introduceL);

			introduceL = new JLabel("�� �ڽ��� �˶�.");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(500, 225, 600, 30);
			add(introduceL);

		}
	}

	class JPanel044 extends JPanel { // 4�� �гθ��
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/CarportTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel044() { // 4��° �г� ������
			setLayout(null);
			
			introduceL = new JLabel("�׷�Ʈ (Groot)");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 35));
			introduceL.setBounds(100, 130, 600, 45);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(100, 175, 600, 30);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(100, 200, 600, 30);
			add(introduceL);

			introduceL = new JLabel("I am Groot...");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(100, 225, 600, 30);
			add(introduceL);

		}
	}

	class JPanel055 extends JPanel { // 5�� �гο丮
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/CookTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel055() { // 5��° �г� ������
			setLayout(null);
			
			introduceL = new JLabel("������(�ֺ�)");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 45));
			introduceL.setBounds(100, 140, 600, 45);
			add(introduceL);

			introduceL = new JLabel(" ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(200, 175, 600, 30);
			add(introduceL);

			introduceL = new JLabel("�� �丮�� ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(100, 220, 600, 30);
			add(introduceL);

			introduceL = new JLabel("���ͳ����θ� �����Ϸ� �ϴ°�?");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 25));
			introduceL.setBounds(100, 255, 600, 30);
			add(introduceL);

		}
	}

	class JPanel066 extends JPanel { // 6�� �гο���
		BufferedImage img;

		public void paintComponent(Graphics g) {
			g.drawImage(new ImageIcon("lib/ArtTeacher.jpg").getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}

		public JPanel066() { // 6��° �г� ������
			setLayout(null);
			introduceL = new JLabel("��ν� ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 35));
			introduceL.setBounds(80, 90, 600, 50);
			add(introduceL);

			introduceL = new JLabel("(Robert Norman Ross)");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 30));
			introduceL.setBounds(80, 130, 600, 45);
			add(introduceL);

			introduceL = new JLabel("�׸������� ����� ���� ������ �Ŀ��� �������ϴ�.  ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 22));
			introduceL.setBounds(40, 220, 600, 30);
			add(introduceL);

			introduceL = new JLabel("�굵 �ű� �� ������. ���� �ְ� �Ҽ� �ְ��. ������, ");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 22));
			introduceL.setBounds(40, 250, 600, 30);
			add(introduceL);

			introduceL = new JLabel("���� ������ ���� ���̶��� ������ �и����� �ۿ� �����.");
			introduceL.setFont(new Font("���� ���� M", Font.PLAIN, 22));
			introduceL.setBounds(40, 280, 600, 30);
			add(introduceL);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { // �α׾ƿ� ��ư
			member.setVisible(false);
			new Login().event();
		}
	}

}