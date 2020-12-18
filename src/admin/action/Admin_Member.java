package admin.action;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cultureCenter.Login;
import cultureCenter.SendEmailAll;
import cultureCenter.SendEmailMember;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class Admin_Member extends JPanel implements ActionListener, ListSelectionListener {
	private String id, phoneS, birthS, genderS, healthS, songS, bookS, carpentS, cookS, artS;
	private int gender, health, song, book, carpent, cook, art;
	private JLabel idLabel, infoL, idL, nameL, birthL, genderL, phoneL, emailL, interestL, info2L;
	private JLabel idT, nameT, birthT, genderT, phoneT, emailT, interestT;
	private JButton logoutBtn, sendMailBtn, allsendBtn;
	private JTextArea mailT;
	private Admin admin;
	private JList<MemberDTO> list; 
	private DefaultListModel<MemberDTO> model;
//	private JComboBox<String> interest;
	
	public Admin_Member(String id, Admin admin) {
		this.id = id;
		this.admin = admin;
		
		//Jlist 생성
		model = new DefaultListModel<MemberDTO>();
		list = new JList<MemberDTO>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("HY견고딕", Font.PLAIN, 20));
		
		JScrollPane listl = new JScrollPane(list);
		listl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					
		// DB
		MemberDAO dao = MemberDAO.getInstance();
		
		//모든 레코드를 꺼내서 JList에 넣기
		List<MemberDTO> dtoList = dao.getMemberList();
		
		for(MemberDTO dto : dtoList) {
			model.addElement(dto);
		}

		// 생성자
//		String[] inter = {"전  체","운  동","노  래","독  서","목  공","요  리","예  술"};
//		interest = new JComboBox<String>(inter);
//		interest.setSelectedIndex(0);
			 
		idLabel = new JLabel(id+" 관리자님");
		idLabel.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		infoL = new JLabel("회 원  정 보");
		infoL.setFont(new Font("한컴 백제 M", Font.PLAIN, 35));
		
		idL = new JLabel("아   이   디 : ");
		idL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));		
		idT = new JLabel();
		idT.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		nameL = new JLabel("이        름 : ");
		nameL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));	
		nameT = new JLabel();
		nameT.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		birthL = new JLabel("생 년  월 일 : ");
		birthL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		birthT = new JLabel();
		birthT.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		genderL = new JLabel("성        별 : ");
		genderL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		genderT = new JLabel();
		genderT.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		phoneL = new JLabel("전 화  번 호 : ");
		phoneL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		phoneT = new JLabel();
		phoneT.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		emailL = new JLabel("이   메   일 : ");
		emailL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		emailT = new JLabel();
		emailT.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		interestL = new JLabel("관   심   사 : ");
		interestL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		interestT = new JLabel();
		interestT.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		info2L = new JLabel("* 해당 회원에게 매일 보내기");
		info2L.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		// text
		mailT = new JTextArea(9, 10);
		mailT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));

		JScrollPane mailscl = new JScrollPane(mailT);
		mailscl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// 버튼
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		sendMailBtn = new JButton(new ImageIcon("lib/sendMail.png"));
		sendMailBtn.setBorderPainted(false);
		sendMailBtn.setContentAreaFilled(false);
		sendMailBtn.setOpaque(false);
		
		allsendBtn = new JButton("전체 회원 보내기");
		allsendBtn.setContentAreaFilled(false);
		allsendBtn.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));
		
		// 위치
		idLabel.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);

		listl.setBounds(100, 110, 280, 485);
		//interest.setBounds(100, 80, 80, 20);
		
		infoL.setBounds(540, 80, 250, 40);

		idL.setBounds(440, 150, 95, 20);
		idT.setBounds(540, 150, 150, 20);
		
		nameL.setBounds(440, 175, 95, 20);
		nameT.setBounds(540, 175, 120, 20);
		
		birthL.setBounds(440, 200, 250, 20);
		birthT.setBounds(540, 200, 120, 20);
		
		genderL.setBounds(440, 225, 95, 20);
		genderT.setBounds(540, 225, 60, 20);
		
		phoneL.setBounds(440, 250, 95, 20);
		phoneT.setBounds(540, 250, 150, 20);
		
		emailL.setBounds(440, 275, 95, 20);
		emailT.setBounds(540, 275, 250, 20);
		
		interestL.setBounds(440, 300, 95, 20);
		interestT.setBounds(540, 300, 300, 20);
		
		info2L.setBounds(440, 360, 250, 20);
		allsendBtn.setBounds(440, 610, 137, 30);
		
		sendMailBtn.setBounds(800, 560, 97, 30);
		mailscl.setBounds(440, 386, 340, 210);
		
		// 추가
		add(idLabel);
		add(logoutBtn);
		add(listl);
		//add(interest);
		add(infoL);
		
		add(idL);
		add(idT);
		add(nameL);
		add(nameT);
		add(birthL);
		add(birthT);
		add(phoneL);
		add(phoneT);
		add(emailL);
		add(emailT);
		add(interestL);
		add(interestT);
		add(info2L);
		add(sendMailBtn);	
		add(allsendBtn);
		add(mailscl);
		
		// 버튼 이벤트
		logoutBtn.addActionListener(this);
		sendMailBtn.addActionListener(this);
		allsendBtn.addActionListener(this);
		//리스트 이벤트
		list.addListSelectionListener(this);	
				
		setLayout(null);
		
	}
	
	//valueChanged
	@Override
	public void valueChanged(ListSelectionEvent e) {
		MemberDTO dto = list.getSelectedValue();
		
		if(list.getSelectedIndex() == -1) return;
		
		idT.setText(dto.getId());		
		nameT.setText(dto.getName());	
				
		phoneS = dto.getPhone();
		String tel1 = phoneS.substring(0, 3);
		String tel2 = phoneS.substring(3, 7);
		String tel3 = phoneS.substring(7, 11);
		phoneT.setText(tel1+"-"+tel2+"-"+tel3);
		
		emailT.setText(dto.getEmail());
	
		birthS = dto.getBirth();
		String year = birthS.substring(0, 2);
		String month = birthS.substring(2, 4);
		String day = birthS.substring(4, 6);
		birthT.setText(year+"년 "+month+"월 "+day+"일");
		
		gender = dto.getGender();
			 if (gender == 0) genderS = "남 성";
		else if (gender == 1) genderS = "여 성";
		genderT.setText(genderS);
			 
		health = dto.getHealth();
			 if (health == 0) healthS = "";
		else if (health == 1) healthS = "운 동";
		
		song = dto.getSong();
			 if (song == 0) songS = "";
		else if (song == 1) songS = "노 래";
		book = dto.getBook();
			 if (book == 0) bookS = "";
		else if (book == 1) bookS = "독 서";
	
		carpent = dto.getCarpent();
			 if (carpent == 0) carpentS = "";
		else if (carpent == 1) carpentS = "목 공";
			 
		cook = dto.getCook();
			 if (cook == 0) cookS = "";
		else if (cook == 1) cookS = "요 리";
		 
		art = dto.getArt();
			 if (art == 0) artS = "";
		else if (art == 1) artS = "예 술";
		interestT.setText(healthS+"  "+songS+"  "+bookS+"  "+carpentS+"  "+cookS+"  "+artS);
	
	}//valueChanged end
	
	
	public void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { //로그아웃 버튼
			admin.setVisible(false);
			new Login().event();
		} else if (e.getSource() == sendMailBtn) { //메일 보내기
			new SendEmailMember(emailT.getText(), mailT.getText());
			JOptionPane.showMessageDialog(this, "메일 전송 완료");
		} else if (e.getSource() == allsendBtn) { //메일 보내기
			MemberDAO dao = MemberDAO.getInstance();
			List<MemberDTO> dtoList = dao.getMemberList();
			new SendEmailAll(dtoList , mailT.getText());
			JOptionPane.showMessageDialog(this, "전체 메일 전송 완료");
		}
	}
}
