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

		// 정보
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
			mygender = "남 성";
		else if (gender == 1)
			mygender = "여 성";

		myhealth = dto.getHealth();
		mysong = dto.getSong();
		mybook = dto.getBook();
		mycarpent = dto.getCarpent();
		mycook = dto.getCook();
		myart = dto.getArt();

		// 생성자
		idLabel = new JLabel(id + " 님 환영합니다.");
		idLabel.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		titleL = new JLabel("나의 정보");
		titleL.setFont(new Font("한컴 백제 M", Font.BOLD, 30));

		idL = new JLabel("아 이 디 : ");
		idL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		myIdL = new JLabel(id);
		myIdL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		pwL = new JLabel("비밀번호 : ");
		pwL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		nameL = new JLabel("이    름 : ");
		nameL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		myNameL = new JLabel(myname);
		myNameL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		birthL = new JLabel("생년월일 : ");
		birthL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		myBirthL = new JLabel(mybirth);
		myBirthL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		genderL = new JLabel("성    별 :  " + mygender);
		genderL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		myGenderL = new JLabel();
		myGenderL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		phoneNumL = new JLabel("전화번호 : ");
		phoneNumL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		phoneNumT = new JTextField(myphone, 10);
		phoneNumT.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		emailL = new JLabel("이 메 일 : ");
		emailL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		emailT = new JTextField(myemail, 10);
		emailT.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		emailVT = new JTextField(10);
		emailVT.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		interestL = new JLabel("관 심 사 : ");
		interestL.setFont(new Font("한컴 백제 M", Font.BOLD, 20));

		health = new JCheckBox("운동");
		health.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		health.setBackground(new Color(250, 250, 250));
		if (myhealth == 1)
			health.setSelected(true);
		if (myhealth == 0)
			health.setSelected(false);

		song = new JCheckBox("노래");
		song.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		song.setBackground(new Color(250, 250, 250));
		if (mysong == 1)
			song.setSelected(true);
		if (mysong == 0)
			song.setSelected(false);

		book = new JCheckBox("독서");
		book.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		book.setBackground(new Color(250, 250, 250));
		if (mybook == 1)
			book.setSelected(true);
		if (mybook == 0)
			book.setSelected(false);

		carpent = new JCheckBox("목공");
		carpent.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		carpent.setBackground(new Color(250, 250, 250));
		if (mycarpent == 1)
			carpent.setSelected(true);
		if (mycarpent == 0)
			carpent.setSelected(false);

		cook = new JCheckBox("요리");
		cook.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		cook.setBackground(new Color(250, 250, 250));
		if (mycook == 1)
			cook.setSelected(true);
		if (mycook == 0)
			cook.setSelected(false);

		art = new JCheckBox("예술");
		art.setFont(new Font("한컴 백제 M", Font.BOLD, 20));
		art.setBackground(new Color(250, 250, 250));
		if (myart == 1)
			art.setSelected(true);
		if (myart == 0)
			art.setSelected(false);

		// 버튼
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

		// Jlist 생성
		model = new DefaultListModel<MemberDTO>();
		list = new JList<MemberDTO>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 위치
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

		// 추가
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

		// 버튼 이벤트
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

	// 배경화면
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}// 배경

	// actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { // 로그아웃 버튼
			member.setVisible(false);
			new Login().event();
		} else if (e.getSource() == pwBtn) { // 비밀번호 변경 버튼
			new PwChange(id);
		} else if (e.getSource() == dropBtn) { // 회원 탈퇴 버튼
			dropinfo();
		} else if (e.getSource() == changePBtn) { // 폰 번호 수정 버튼
			phoneChange();
		} else if (e.getSource() == verifySendBtn) { // 이메일 인증 보내기 버튼
			// MemberDTO
			MemberDTO dto = new MemberDTO();
			dto.setEmail(emailT.getText());

			// DB
			MemberDAO dao = MemberDAO.getInstance();
			int su = dao.emailConfirmArticle(dto);
			
			if (su == 1) {
				JOptionPane.showMessageDialog(this, "이미 가입하신 이메일입니다.");
			} else if(su == 0) {
				random = Integer.toString((int)(Math.random()*1000000));
				
				JOptionPane.showMessageDialog(this, "해당 메일에 인증 번호를 전송하였습니다.\n인증번호를 '이메일 확인'란에 작성해주세요.");
				new SendEmail(emailT.getText(), random);
				
				verifyBtn.setEnabled(true);	
			}
			
		} else if (e.getSource() == verifyBtn) { // 이메일 인증 확인 버튼
			if(emailVT.getText().equals(random)) {
				verifyOkBtn.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(this, "인증번호가 일치하지 않습니다.");
			}
		} else if (e.getSource() == verifyOkBtn) { // 이메일 수정 버튼
			emailChange();
			JOptionPane.showMessageDialog(this, "이메일 변경이 완료되었습니다.");
		} else if (e.getSource() == changeBtn) { // 관심사수정 버튼
			interestChange();
		}
	}// actionPerformed end

	

	// 관심사 수정
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

		JOptionPane.showMessageDialog(this, "관심사 변경이 완료되었습니다.");

	}// interest 수정 end

	// 이메일 수정
	private void emailChange() {
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setEmail(emailT.getText());
		dto.setId(id);

		if (emailT.getText().equals("") || (emailT.getText()) == null) {
			JOptionPane.showMessageDialog(this, "입력하신 회원 정보가 없습니다");
		} else {
			// DB
			MemberDAO dao = MemberDAO.getInstance();
			dao.emailChangeArticle(dto);

			JOptionPane.showMessageDialog(this, "이메일 변경이 완료되었습니다.");
		}
	}// email 수정 end

	// 폰번호 수정
	private void phoneChange() {
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setPhone(phoneNumT.getText());
		dto.setId(id);

		if (phoneNumT.getText().equals("") || (phoneNumT.getText()) == null) {
			JOptionPane.showMessageDialog(this, "입력하신 회원 정보가 없습니다");
		} else {
			// DB
			MemberDAO dao = MemberDAO.getInstance();
			dao.phoneChangeArticle(dto);

			JOptionPane.showMessageDialog(this, "핸드폰 번호 변경이 완료되었습니다.");
		}
	}// 폰번호 수정end

	//신청 정보 삭제
	private void dropinfo() {
		MappingDTO dto = new MappingDTO();
		dto.setId(id);
		
		//DB
		MappingDAO dao = MappingDAO.getInstance();
		int dropMember = JOptionPane.showConfirmDialog(this, "정말 탈퇴 하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE);
	      if (dropMember == JOptionPane.YES_OPTION) {
	    	  dao.dropinfoArticle(dto);
	    	  drop();
	    	  
	      }
		
	}

	
	
	// 탈퇴
	private void drop() {
	    // MemberDTO
	    MemberDTO dto = new MemberDTO();
	    dto.setId(id);

	    // DB
	    MemberDAO dao = MemberDAO.getInstance();
	      
        dao.dropArticle(dto);
        
        JOptionPane.showMessageDialog(this, "정상적으로 탈퇴되었습니다.");
        member.setVisible(false);
        new Login().event();
         
	      
	   }// 탈퇴

}