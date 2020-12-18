package cultureCenter;

import java.awt.Color;
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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class Join extends JFrame implements ActionListener {

	private JLabel ExL, titleL, idL, pwL, pwOL, nameL, birthL, genderL, phoneNumL, phoneNumExL, emailL, emailVL,
			emailExL, interestL, interestExL;
	private JTextField idT, nameT, birthT, phoneNumT, emailT, emailVT;
	private JPasswordField pwT, pwOT;
	private JButton confirmBtn, cancelBtn, overlapBtn, verifyBtn, verifyOKBtn;
	private JRadioButton woman, man;
	private JCheckBox health, song, book, carpent, cook, art;
	private BufferedImage img = null;

	private JList<MemberDTO> list;
	private DefaultListModel<MemberDTO> model;

	private String random;
	private int mailox;
	
	public Join() {

		// �� ����
		ExL = new JLabel(" * �� �ʼ� �Է� �����Դϴ�.");
		titleL = new JLabel("ȸ�� ����");
		idL = new JLabel("*��    ��    ��");
		pwL = new JLabel("*��  ��  ��  ȣ");
		pwOL = new JLabel("*��� ��ȣ Ȯ��");
		nameL = new JLabel(" ��          �� ");
		birthL = new JLabel("*��  ��  ��  ��");
		genderL = new JLabel("*��          ��");
		phoneNumL = new JLabel(" ��  ȭ  ��  ȣ");
		phoneNumExL = new JLabel("'-'���� �����ּ���.  ex) 01012345678");
		emailL = new JLabel("*�� �� ��  �ּ�");
		emailExL = new JLabel("�ּ���ü�� �����ּ���. ex) bit123@bitcamp.com");
		emailVL = new JLabel(" �� �� ��  Ȯ��");
		interestL = new JLabel(" ��    ��    �� ");
		interestExL = new JLabel("* ���� �о߿� ��� üũ���ּ���.");

		// �ؽ�Ʈ �ʵ� ����
		idT = new JTextField();
		nameT = new JTextField();
		birthT = new JTextField();
		phoneNumT = new JTextField("010");
		emailT = new JTextField();
		emailVT = new JTextField();

		// �ؽ�Ʈ�佺����
		pwT = new JPasswordField();
		pwT.setEchoChar('��');
		pwOT = new JPasswordField();
		pwOT.setEchoChar('��');
		// ��ư ����
		confirmBtn = new JButton(new ImageIcon("lib/join.png"));
		confirmBtn.setBorderPainted(false);
		confirmBtn.setContentAreaFilled(false);
		confirmBtn.setOpaque(false);

		cancelBtn = new JButton(new ImageIcon("lib/cancel.png"));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setOpaque(false);

		overlapBtn = new JButton(new ImageIcon("lib/overlap.png"));
		overlapBtn.setBorderPainted(false);
		overlapBtn.setContentAreaFilled(false);
		overlapBtn.setOpaque(false);

		verifyBtn = new JButton(new ImageIcon("lib/verify.png"));
		verifyBtn.setBorderPainted(false);
		verifyBtn.setContentAreaFilled(false);
		verifyBtn.setOpaque(false);

		// ������ư ����
		woman = new JRadioButton("����", true);
		man = new JRadioButton("����");
		woman.setBackground(new Color(250, 250, 250));
		man.setBackground(new Color(250, 250, 250));

		ButtonGroup button = new ButtonGroup();
		button.add(woman);
		button.add(man);

		// üũ�ڽ� ����
		health = new JCheckBox("�");
		health.setBackground(new Color(250, 250, 250));
		song = new JCheckBox("�뷡");
		song.setBackground(new Color(250, 250, 250));
		book = new JCheckBox("����");
		book.setBackground(new Color(250, 250, 250));
		carpent = new JCheckBox("���");
		carpent.setBackground(new Color(250, 250, 250));
		cook = new JCheckBox("�丮");
		cook.setBackground(new Color(250, 250, 250));
		art = new JCheckBox("����");
		art.setBackground(new Color(250, 250, 250));

		// ũ�����
		titleL.setBounds(170, 10, 150, 50);
		titleL.setFont(new Font("���� ���� M", Font.BOLD, 30));

		ExL.setBounds(10, 65, 200, 15);
		ExL.setFont(new Font("���� ���� M", Font.PLAIN, 13));

		idL.setBounds(10, 80, 150, 50);
		idT.setBounds(150, 95, 200, 30);
		idL.setFont(new Font("���� ���� M", Font.BOLD, 18));
		idT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		overlapBtn.setBounds(360, 95, 70, 30);

		pwL.setBounds(10, 120, 150, 50);
		pwT.setBounds(150, 135, 200, 30);
		pwL.setFont(new Font("���� ���� M", Font.BOLD, 18));
		pwT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		pwOL.setBounds(10, 160, 150, 50);
		pwOT.setBounds(150, 175, 200, 30);
		pwOL.setFont(new Font("���� ���� M", Font.BOLD, 18));
		pwOT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		nameL.setBounds(10, 200, 150, 50);
		nameT.setBounds(150, 215, 200, 30);
		nameL.setFont(new Font("���� ���� M", Font.BOLD, 18));
		nameT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		birthL.setBounds(10, 240, 150, 50);
		birthT.setBounds(150, 255, 200, 30);
		birthL.setFont(new Font("���� ���� M", Font.BOLD, 18));
		birthT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		genderL.setBounds(10, 280, 150, 50);
		woman.setBounds(150, 295, 100, 30);
		genderL.setFont(new Font("���� ���� M", Font.BOLD, 18));
		woman.setFont(new Font("���� ���� M", Font.BOLD, 18));

		man.setBounds(250, 295, 200, 30);
		man.setFont(new Font("���� ���� M", Font.BOLD, 18));

		phoneNumL.setBounds(10, 320, 150, 50);
		phoneNumT.setBounds(150, 335, 250, 30);
		phoneNumL.setFont(new Font("���� ���� M", Font.BOLD, 18));
		phoneNumT.setFont(new Font("���� ���� M", Font.BOLD, 18));
		phoneNumExL.setBounds(150, 365, 250, 20);
		phoneNumExL.setFont(new Font("���� ���� M", Font.PLAIN, 12));

		emailL.setBounds(10, 380, 150, 50);
		emailL.setFont(new Font("���� ���� M", Font.BOLD, 18));
		emailT.setBounds(150, 395, 250, 30);
		emailT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		emailVL.setBounds(10, 435, 150, 50);
		emailVL.setFont(new Font("���� ���� M", Font.BOLD, 18));

		emailVT.setBounds(150, 450, 150, 30);
		emailVT.setFont(new Font("���� ���� M", Font.BOLD, 18));
		verifyBtn.setBounds(405, 395, 70, 30);

		emailExL.setBounds(150, 425, 350, 20);
		emailExL.setFont(new Font("���� ���� M", Font.PLAIN, 12));

		interestL.setBounds(10, 490, 150, 50);
		interestL.setFont(new Font("���� ���� M", Font.BOLD, 18));

		health.setBounds(150, 495, 70, 30);
		song.setBounds(230, 495, 70, 30);
		health.setFont(new Font("���� ���� M", Font.BOLD, 18));
		song.setFont(new Font("���� ���� M", Font.BOLD, 18));

		book.setBounds(310, 495, 70, 30);
		carpent.setBounds(150, 525, 70, 30);
		book.setFont(new Font("���� ���� M", Font.BOLD, 18));
		carpent.setFont(new Font("���� ���� M", Font.BOLD, 18));

		cook.setBounds(230, 525, 70, 30);
		art.setBounds(310, 525, 70, 30);
		cook.setFont(new Font("���� ���� M", Font.BOLD, 18));
		art.setFont(new Font("���� ���� M", Font.BOLD, 18));
		interestExL.setBounds(150, 540, 300, 50);
		interestExL.setFont(new Font("���� ���� M", Font.PLAIN, 12));

		confirmBtn.setBounds(120, 580, 140, 60);
		cancelBtn.setBounds(280, 580, 90, 60);
		confirmBtn.setFont(new Font("���� ���� M", Font.BOLD, 18));
		cancelBtn.setFont(new Font("���� ���� M", Font.BOLD, 18));

		// â�� �߰�
		Container c = this.getContentPane();
		c.add(titleL);
		c.add(ExL);
		c.add(idL);
		c.add(idT);
		c.add(overlapBtn);
		c.add(pwL);
		c.add(pwT);
		c.add(pwOL);
		c.add(pwOT);
		c.add(nameL);
		c.add(nameT);
		c.add(birthL);
		c.add(birthT);
		c.add(genderL);
		c.add(woman);
		c.add(man);
		c.add(phoneNumL);
		c.add(phoneNumT);
		c.add(phoneNumExL);
		c.add(emailL);
		c.add(emailT);
		c.add(emailExL);
		c.add(emailVL);
		c.add(emailVT);
		c.add(verifyBtn);
		c.add(interestL);
		c.add(health);
		c.add(song);
		c.add(book);
		c.add(carpent);
		c.add(cook);
		c.add(art);
		c.add(interestExL);
		c.add(confirmBtn);
		c.add(cancelBtn);
		c.setBackground(new Color(250, 250, 250));

		// ��� Panel
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(500, 700);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("lib/flower.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�̹��� �ҷ����� ����");
			System.exit(0);
		}
		// �̹��� ����
		bgPanel panel = new bgPanel();
		panel.setSize(500, 800);
		layeredPane.add(panel);
		add(layeredPane);

		// Jlist ����
		model = new DefaultListModel<MemberDTO>();
		list = new JList<MemberDTO>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// â ����
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exit = JOptionPane.showConfirmDialog(Join.this, "��� �Ͻðڽ��ϱ�?", "ȸ������ ���", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (exit == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});// �̺�Ʈ

		// ��ư �̺�Ʈ
		confirmBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		overlapBtn.addActionListener(this);
		verifyBtn.addActionListener(this);

		// â
		setTitle("��Ʈ��ȭ���� ȸ������");
		setLayout(null); // �ʱⰪ�̴� �������� Layout�� ��������
		setBounds(300, 200, 500, 700);
		setVisible(true);
		setResizable(false);

		// ��� ���ڵ带 ������ JList�� �ֱ�
		MemberDAO dao = MemberDAO.getInstance();
		List<MemberDTO> dtoList = dao.getMemberList();

		for (MemberDTO dto : dtoList) {
			model.addElement(dto);
		}

	}

	class bgPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	int overlapSw = -1;

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmBtn) { // Ȯ�ι�ư
			 confirm();
	
		} else if (e.getSource() == cancelBtn) { // ��ҹ�ư
			int exit = JOptionPane.showConfirmDialog(Join.this, "��� �Ͻðڽ��ϱ�?", "ȸ������ ���", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (exit == JOptionPane.YES_OPTION) {
				dispose();
			}
			
		} else if (e.getSource() == overlapBtn) { // �ߺ�Ȯ��
			if (idT.getText().equals("") || idT.getText().equals(null)) {
				JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���.");
			} else {
				overlapArticle();

			}
			
		} else if (e.getSource() == verifyBtn) { // �̸�������
			random = Integer.toString((int)(Math.random()*1000000));
			
			if (emailT.getText().equals("") || emailT.getText().equals(null)) {
				JOptionPane.showMessageDialog(this, "�̸����� �Է����ּ���.");
			} else {
				overlapMailArticle();
				
				if (mailox == 1) {
					JOptionPane.showMessageDialog(this, "�̹� �����Ͻ� �̸����Դϴ�.");
				} else if(mailox == 0) {
					random = Integer.toString((int)(Math.random()*1000000));
					
					JOptionPane.showMessageDialog(this, "�ش� ���Ͽ� ���� ��ȣ�� �����Ͽ����ϴ�.\n������ȣ�� '�̸��� Ȯ��'���� �ۼ����ּ���.");
					new SendEmail(emailT.getText(), random);
				}
			}
		}

	}

	//���� �� ����
	private void confirm() {
		if (idT.getText().equals("") || idT.getText().equals(null) || pwT.getText().equals("")
				|| pwT.getText().equals(null) || birthT.getText().equals("") || birthT.getText().equals(null)
				|| emailT.getText().equals("") || emailVT.getText().equals("") || emailT.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "�ʼ��׸��� �Է����ּ���");
			
		} else { // DB�� ȸ������ ����
			
			if (overlapSw != 0) {
				JOptionPane.showMessageDialog(this, "���̵� �ߺ� Ȯ���� �ʿ��մϴ�.");
				
			} else if(birthT.getText().length() != 6 ) {
				JOptionPane.showMessageDialog(this, "6�ڸ��� ��������� �Է����ּ���.");
				
			} else if(!pwT.getText().equals(pwOT.getText()) ) {
				JOptionPane.showMessageDialog(this, "��� ��ȣ�� ��ġ���� �ʽ��ϴ�.");
				
			} else if(pwT.getText().length() < 8 ) {
				JOptionPane.showMessageDialog(this, "��й�ȣ�� 8�ڸ� �̻����� �Է����ּ���.");
				
			} else if(phoneNumT.getText().length() != 11 && phoneNumT.getText().length() != 0 ) {
				JOptionPane.showMessageDialog(this, "�ڵ��� ��ȣ�� �ڸ����� �ùٸ��� �ʽ��ϴ�.");
				
			} else if(emailVT.getText().equals(random)) {
				joinArticle();
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "�ùٸ� �̸��� ������ �ʿ��մϴ�.");
			}
		}
	}//���� �� ���� end

	// ���� �ߺ�
	private int overlapMailArticle() {
		//������ 
		String mail = emailT.getText();
		
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setEmail(mail);

		// DB
		MemberDAO dao = MemberDAO.getInstance();
		mailox = dao.emailConfirmArticle(dto);
		
		
		return mailox;
	}

		
	// ���̵� �ߺ�
	private int overlapArticle() {
		// ������
		String id = idT.getText();

		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		overlapSw = dao.overlapArticle(dto);

		if (overlapSw == 0) {
			JOptionPane.showMessageDialog(this, "��� ������ ���̵��Դϴ�.");
		} else if (overlapSw == 1) {
			JOptionPane.showMessageDialog(this, "�̹� ������� ���̵��Դϴ�.");
		}

		return overlapSw;
	}

	private void joinArticle() {
		// ������
		String id = idT.getText();
		String pw = pwT.getText();
		String name = nameT.getText();
		String birth = birthT.getText();
		int gender = 0;
		if (man.isSelected())
			gender = 0;
		else if (woman.isSelected())
			gender = 1;
		String phone = phoneNumT.getText();
		String email = emailT.getText();

		int health = this.health.isSelected() ? 1 : 0;
		int song = this.song.isSelected() ? 1 : 0;
		int book = this.book.isSelected() ? 1 : 0;
		int carpent = this.carpent.isSelected() ? 1 : 0;
		int cook = this.cook.isSelected() ? 1 : 0;
		int art = this.art.isSelected() ? 1 : 0;

		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setBirth(birth);
		dto.setGender(gender);
		dto.setPhone(phone);
		dto.setEmail(email);
		dto.setHealth(health);
		dto.setSong(song);
		dto.setBook(book);
		dto.setCarpent(carpent);
		dto.setCook(cook);
		dto.setArt(art);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		dao.joinArticle(dto);

		// ����
		model.addElement(dto);

		clear(); // �ʱ�ȭ
		JOptionPane.showMessageDialog(this, "���� �Ϸ�Ǿ����ϴ�.");
		setVisible(false);
	}

	private void clear() {
		idT.setText("");
		pwT.setText("");
		pwOT.setText("");
		nameT.setText("");

		birthT.setText("");
		woman.setSelected(true);
		phoneNumT.setText("");
		emailT.setText("");
		emailVT.setText("");
		health.setSelected(false);
		song.setSelected(false);
		book.setSelected(false);
		carpent.setSelected(false);
		cook.setSelected(false);
		art.setText("");
	}

}
