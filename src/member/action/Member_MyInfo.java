package member.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import cultureCenter.Login;
import cultureCenter.PwChange;
import cultureCenter.SendEmail;
import mapping.bean.MappingDTO;
import mapping.dao.MappingDAO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class Member_MyInfo extends JPanel implements ActionListener {
	private JLabel titleL, idL, myIdL, pwL, nameL, myNameL, birthL, myBirthL, genderL, myGenderL, phoneNumL, emailL,
			interestL, idLabel;
	private JTextField phoneNumT, emailT, emailVT;
	private JButton pwBtn, logoutBtn, dropBtn, changePBtn, changeBtn, verifyBtn, verifyOkBtn, verifySendBtn;
	private JCheckBox health, song, book, carpent, cook, art;
	private String id;
	private Member member;

	private JList<MemberDTO> list;
	private DefaultListModel<MemberDTO> model;

	private String myname, mybirth, myphone, myemail, mygender;
	private int myhealth, mysong, mybook, mycarpent, mycook, myart, gender;

	private String random;
	
	public Member_MyInfo(String id, Member member) {
		this.id = id;
		this.member = member;

		// ����
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		dto = dao.myInfoArticle(dto);
		myname = dto.getName();
		mybirth = dto.getBirth();
		myphone = dto.getPhone();
		myemail = dto.getEmail();
		gender = dto.getGender();
		if (gender == 0)
			mygender = "�� ��";
		else if (gender == 1)
			mygender = "�� ��";

		myhealth = dto.getHealth();
		mysong = dto.getSong();
		mybook = dto.getBook();
		mycarpent = dto.getCarpent();
		mycook = dto.getCook();
		myart = dto.getArt();

		// ������
		idLabel = new JLabel(id + " �� ȯ���մϴ�.");
		idLabel.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		titleL = new JLabel("���� ����");
		titleL.setFont(new Font("���� ���� M", Font.BOLD, 30));

		idL = new JLabel("�� �� �� : ");
		idL.setFont(new Font("���� ���� M", Font.BOLD, 20));
		myIdL = new JLabel(id);
		myIdL.setFont(new Font("���� ���� M", Font.BOLD, 20));

		pwL = new JLabel("��й�ȣ : ");
		pwL.setFont(new Font("���� ���� M", Font.BOLD, 20));

		nameL = new JLabel("��    �� : ");
		nameL.setFont(new Font("���� ���� M", Font.BOLD, 20));
		myNameL = new JLabel(myname);
		myNameL.setFont(new Font("���� ���� M", Font.BOLD, 20));

		birthL = new JLabel("������� : ");
		birthL.setFont(new Font("���� ���� M", Font.BOLD, 20));
		myBirthL = new JLabel(mybirth);
		myBirthL.setFont(new Font("���� ���� M", Font.BOLD, 20));

		genderL = new JLabel("��    �� :  " + mygender);
		genderL.setFont(new Font("���� ���� M", Font.BOLD, 20));

		myGenderL = new JLabel();
		myGenderL.setFont(new Font("���� ���� M", Font.BOLD, 20));

		phoneNumL = new JLabel("��ȭ��ȣ : ");
		phoneNumL.setFont(new Font("���� ���� M", Font.BOLD, 20));
		phoneNumT = new JTextField(myphone, 10);
		phoneNumT.setFont(new Font("���� ���� M", Font.BOLD, 20));

		emailL = new JLabel("�� �� �� : ");
		emailL.setFont(new Font("���� ���� M", Font.BOLD, 20));
		emailT = new JTextField(myemail, 10);
		emailT.setFont(new Font("���� ���� M", Font.BOLD, 20));

		emailVT = new JTextField(10);
		emailVT.setFont(new Font("���� ���� M", Font.BOLD, 20));

		interestL = new JLabel("�� �� �� : ");
		interestL.setFont(new Font("���� ���� M", Font.BOLD, 20));

		health = new JCheckBox("�");
		health.setFont(new Font("���� ���� M", Font.BOLD, 20));
		health.setBackground(new Color(250, 250, 250));
		if (myhealth == 1)
			health.setSelected(true);
		if (myhealth == 0)
			health.setSelected(false);

		song = new JCheckBox("�뷡");
		song.setFont(new Font("���� ���� M", Font.BOLD, 20));
		song.setBackground(new Color(250, 250, 250));
		if (mysong == 1)
			song.setSelected(true);
		if (mysong == 0)
			song.setSelected(false);

		book = new JCheckBox("����");
		book.setFont(new Font("���� ���� M", Font.BOLD, 20));
		book.setBackground(new Color(250, 250, 250));
		if (mybook == 1)
			book.setSelected(true);
		if (mybook == 0)
			book.setSelected(false);

		carpent = new JCheckBox("���");
		carpent.setFont(new Font("���� ���� M", Font.BOLD, 20));
		carpent.setBackground(new Color(250, 250, 250));
		if (mycarpent == 1)
			carpent.setSelected(true);
		if (mycarpent == 0)
			carpent.setSelected(false);

		cook = new JCheckBox("�丮");
		cook.setFont(new Font("���� ���� M", Font.BOLD, 20));
		cook.setBackground(new Color(250, 250, 250));
		if (mycook == 1)
			cook.setSelected(true);
		if (mycook == 0)
			cook.setSelected(false);

		art = new JCheckBox("����");
		art.setFont(new Font("���� ���� M", Font.BOLD, 20));
		art.setBackground(new Color(250, 250, 250));
		if (myart == 1)
			art.setSelected(true);
		if (myart == 0)
			art.setSelected(false);

		// ��ư
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		pwBtn = new JButton(new ImageIcon("lib/changeB.png"));
		pwBtn.setBorderPainted(false);
		pwBtn.setContentAreaFilled(false);
		pwBtn.setOpaque(false);

		dropBtn = new JButton(new ImageIcon("lib/drop.png"));
		dropBtn.setBorderPainted(false);
		dropBtn.setContentAreaFilled(false);
		dropBtn.setOpaque(false);

		changeBtn = new JButton(new ImageIcon("lib/interChange.png"));
		changeBtn.setBorderPainted(false);
		changeBtn.setContentAreaFilled(false);
		changeBtn.setOpaque(false);

		changePBtn = new JButton(new ImageIcon("lib/phoneChange.png"));
		changePBtn.setBorderPainted(false);
		changePBtn.setContentAreaFilled(false);
		changePBtn.setOpaque(false);

		verifyBtn = new JButton(new ImageIcon("lib/verifyMyInfo.png"));
		verifyBtn.setBorderPainted(false);
		verifyBtn.setContentAreaFilled(false);
		verifyBtn.setOpaque(false);
		verifyBtn.setEnabled(false);

		verifyOkBtn = new JButton(new ImageIcon("lib/emailChange.png"));
		verifyOkBtn.setBorderPainted(false);
		verifyOkBtn.setContentAreaFilled(false);
		verifyOkBtn.setOpaque(false);
		verifyOkBtn.setEnabled(false);

		verifySendBtn = new JButton(new ImageIcon("lib/verifySend.png"));
		verifySendBtn.setBorderPainted(false);
		verifySendBtn.setContentAreaFilled(false);
		verifySendBtn.setOpaque(false);

		// Jlist ����
		model = new DefaultListModel<MemberDTO>();
		list = new JList<MemberDTO>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// ��ġ
		idLabel.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);

		titleL.setBounds(100, 40, 150, 50);

		idL.setBounds(100, 105, 100, 20);
		myIdL.setBounds(200, 105, 200, 20);

		pwL.setBounds(100, 144, 100, 20);
		pwBtn.setBounds(200, 140, 108, 25);

		nameL.setBounds(100, 180, 100, 20);
		myNameL.setBounds(200, 180, 200, 20);

		birthL.setBounds(100, 220, 100, 20);
		myBirthL.setBounds(200, 220, 200, 20);

		genderL.setBounds(100, 260, 300, 20);
		myGenderL.setBounds(200, 260, 200, 20);

		phoneNumL.setBounds(100, 305, 100, 20);
		phoneNumT.setBounds(200, 300, 250, 30);

		changePBtn.setBounds(460, 305, 101, 25);

		emailL.setBounds(100, 345, 100, 20);
		emailT.setBounds(200, 340, 250, 30);
		verifySendBtn.setBounds(460, 345, 75, 25);

		emailVT.setBounds(200, 380, 150, 30);
		verifyBtn.setBounds(370, 380, 70, 30);

		verifyOkBtn.setBounds(460, 385, 101, 25);

		interestL.setBounds(100, 445, 100, 20);

		health.setBounds(200, 445, 70, 20);
		song.setBounds(280, 445, 70, 20);
		book.setBounds(360, 445, 70, 20);
		carpent.setBounds(200, 495, 70, 20);
		cook.setBounds(280, 495, 70, 20);
		art.setBounds(360, 495, 70, 20);
		changeBtn.setBounds(460, 495, 103, 25);

		dropBtn.setBounds(850, 680, 57, 20);

		// �߰�
		add(idLabel);
		add(logoutBtn);

		add(titleL);
		add(idL);
		add(myIdL);
		add(pwL);
		add(pwBtn);
		add(nameL);
		add(myNameL);
		add(birthL);
		add(myBirthL);
		add(genderL);
		add(myGenderL);

		add(phoneNumL);
		add(phoneNumT);
		add(changePBtn);

		add(emailL);
		add(emailT);
		add(emailVT);
		add(verifySendBtn);
		add(verifyBtn);
		add(verifyOkBtn);
		add(interestL);
		add(health);
		add(song);
		add(book);
		add(carpent);
		add(cook);
		add(art);

		add(changeBtn);
		add(dropBtn);

		// ��ư �̺�Ʈ
		logoutBtn.addActionListener(this);
		pwBtn.addActionListener(this);
		changePBtn.addActionListener(this);
		verifySendBtn.addActionListener(this);
		verifyBtn.addActionListener(this);
		verifyOkBtn.addActionListener(this);
		changeBtn.addActionListener(this);
		dropBtn.addActionListener(this);

		setLayout(null);

	}

	// ���ȭ��
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}// ���

	// actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { // �α׾ƿ� ��ư
			member.setVisible(false);
			new Login().event();
		} else if (e.getSource() == pwBtn) { // ��й�ȣ ���� ��ư
			new PwChange(id);
		} else if (e.getSource() == dropBtn) { // ȸ�� Ż�� ��ư
			dropinfo();
		} else if (e.getSource() == changePBtn) { // �� ��ȣ ���� ��ư
			phoneChange();
		} else if (e.getSource() == verifySendBtn) { // �̸��� ���� ������ ��ư
			// MemberDTO
			MemberDTO dto = new MemberDTO();
			dto.setEmail(emailT.getText());

			// DB
			MemberDAO dao = MemberDAO.getInstance();
			int su = dao.emailConfirmArticle(dto);
			
			if (su == 1) {
				JOptionPane.showMessageDialog(this, "�̹� �����Ͻ� �̸����Դϴ�.");
			} else if(su == 0) {
				random = Integer.toString((int)(Math.random()*1000000));
				
				JOptionPane.showMessageDialog(this, "�ش� ���Ͽ� ���� ��ȣ�� �����Ͽ����ϴ�.\n������ȣ�� '�̸��� Ȯ��'���� �ۼ����ּ���.");
				new SendEmail(emailT.getText(), random);
				
				verifyBtn.setEnabled(true);	
			}
			
		} else if (e.getSource() == verifyBtn) { // �̸��� ���� Ȯ�� ��ư
			if(emailVT.getText().equals(random)) {
				verifyOkBtn.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(this, "������ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		} else if (e.getSource() == verifyOkBtn) { // �̸��� ���� ��ư
			emailChange();
			JOptionPane.showMessageDialog(this, "�̸��� ������ �Ϸ�Ǿ����ϴ�.");
		} else if (e.getSource() == changeBtn) { // ���ɻ���� ��ư
			interestChange();
		}
	}// actionPerformed end

	

	// ���ɻ� ����
	private void interestChange() {

		int health = this.health.isSelected() ? 1 : 0;
		int song = this.song.isSelected() ? 1 : 0;
		int book = this.book.isSelected() ? 1 : 0;
		int carpent = this.carpent.isSelected() ? 1 : 0;
		int cook = this.cook.isSelected() ? 1 : 0;
		int art = this.art.isSelected() ? 1 : 0;

		// MemberDTO
		MemberDTO dto = new MemberDTO();

		dto.setHealth(health);
		dto.setSong(song);
		dto.setBook(book);
		dto.setCarpent(carpent);
		dto.setCook(cook);
		dto.setArt(art);
		dto.setId(id);

		// DB
		MemberDAO dao = MemberDAO.getInstance();
		dao.interestChangeArticle(dto);

		JOptionPane.showMessageDialog(this, "���ɻ� ������ �Ϸ�Ǿ����ϴ�.");

	}// interest ���� end

	// �̸��� ����
	private void emailChange() {
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setEmail(emailT.getText());
		dto.setId(id);

		if (emailT.getText().equals("") || (emailT.getText()) == null) {
			JOptionPane.showMessageDialog(this, "�Է��Ͻ� ȸ�� ������ �����ϴ�");
		} else {
			// DB
			MemberDAO dao = MemberDAO.getInstance();
			dao.emailChangeArticle(dto);

			JOptionPane.showMessageDialog(this, "�̸��� ������ �Ϸ�Ǿ����ϴ�.");
		}
	}// email ���� end

	// ����ȣ ����
	private void phoneChange() {
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setPhone(phoneNumT.getText());
		dto.setId(id);

		if (phoneNumT.getText().equals("") || (phoneNumT.getText()) == null) {
			JOptionPane.showMessageDialog(this, "�Է��Ͻ� ȸ�� ������ �����ϴ�");
		} else {
			// DB
			MemberDAO dao = MemberDAO.getInstance();
			dao.phoneChangeArticle(dto);

			JOptionPane.showMessageDialog(this, "�ڵ��� ��ȣ ������ �Ϸ�Ǿ����ϴ�.");
		}
	}// ����ȣ ����end

	//��û ���� ����
	private void dropinfo() {
		MappingDTO dto = new MappingDTO();
		dto.setId(id);
		
		//DB
		MappingDAO dao = MappingDAO.getInstance();
		int dropMember = JOptionPane.showConfirmDialog(this, "���� Ż�� �Ͻðڽ��ϱ�?", "ȸ��Ż��", JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE);
	      if (dropMember == JOptionPane.YES_OPTION) {
	    	  dao.dropinfoArticle(dto);
	    	  drop();
	    	  
	      }
		
	}

	
	
	// Ż��
	private void drop() {
	    // MemberDTO
	    MemberDTO dto = new MemberDTO();
	    dto.setId(id);

	    // DB
	    MemberDAO dao = MemberDAO.getInstance();
	      
        dao.dropArticle(dto);
        
        JOptionPane.showMessageDialog(this, "���������� Ż��Ǿ����ϴ�.");
        member.setVisible(false);
        new Login().event();
         
	      
	   }// Ż��

}