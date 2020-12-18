package member.action;

import java.awt.Color;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cultureCenter.Login;
import lecture.bean.LectureDTO;
import lecture.dao.LectureDAO;
import mapping.bean.MappingDTO;
import mapping.dao.MappingDAO;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class Member_Apply extends JPanel implements ActionListener, ListSelectionListener {
	private String id, myname, tCode;
	private JLabel idLabel, infoL, codeL, nameL, dateL, toL, teacherL, contentL, listL, aL, magamL;
	private JLabel codeT, nameT, dateT, numT, toT, teacherT, contentT;
	private JButton logoutBtn, applyBtn;
	private Member member;
	private JList<LectureDTO> list; 
	private DefaultListModel<LectureDTO> model;
	
	public Member_Apply(String id, Member member) {
		this.id = id;
		this.member = member;

		// 내 이름 정보
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);

		// DB
		MemberDAO daoM = MemberDAO.getInstance();

		dto = daoM.myInfoArticle(dto);
		myname = dto.getName();
		
		
		//Jlist 생성
		model = new DefaultListModel<LectureDTO>();
		list = new JList<LectureDTO>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("HY견고딕", Font.PLAIN, 15));
		
		JScrollPane listl = new JScrollPane(list);
		listl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					
		// DB
		LectureDAO dao = LectureDAO.getInstance();
		
		//모든 레코드를 꺼내서 JList에 넣기
		List<LectureDTO> dtoList = dao.getLectureList();
		
		for(LectureDTO dtoL : dtoList) {
			model.addElement(dtoL);
		}
		
		idLabel = new JLabel(id + " 님 환영합니다.");
		idLabel.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		infoL = new JLabel("강 의  정 보");
		infoL.setFont(new Font("한컴 백제 M", Font.PLAIN, 45));
		
		listL = new JLabel("전 체  강 의  목 록");
		listL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		
		magamL = new JLabel("");
		magamL.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));
		
		codeL = new JLabel("강 의  코 드 : ");
		codeL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		codeT = new JLabel();
		codeT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));	
		
		nameL = new JLabel("강   의   명 : ");
		nameL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		nameT = new JLabel();
		nameT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		
		dateL = new JLabel("강 의  날 짜 : ");
		dateL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		dateT = new JLabel();
		dateT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		
		aL = new JLabel("");
		aL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));	
		toL = new JLabel("신 청  인 원 : ");
		toL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		numT = new JLabel();
		numT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
	
		toT = new JLabel();
		toT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));

		
		teacherL = new JLabel("강   사   명 : ");
		teacherL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		teacherT = new JLabel();
		teacherT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));

		
		contentL = new JLabel("소  개 : ");
		contentL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		contentT = new JLabel();
		contentT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		
		// 버튼
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		applyBtn = new JButton(new ImageIcon("lib/applyBtn.png"));
		applyBtn.setBorderPainted(false);
		applyBtn.setContentAreaFilled(false);
		applyBtn.setOpaque(false);
		
		
		// 위치
		idLabel.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);
		
		listl.setBounds(90, 110, 340, 460);
		listL.setBounds(90, 60, 180, 40);
		
		infoL.setBounds(570, 80, 250, 50);
		magamL.setBounds(470, 150, 250, 40);
		codeL.setBounds(470, 200, 140, 40);
		codeT.setBounds(605, 200, 200, 40);
		nameL.setBounds(470, 250, 140, 40);
		nameT.setBounds(605, 250, 300, 40);
		dateL.setBounds(470, 300, 140, 40);
		dateT.setBounds(605, 300, 180, 40);
		toL.setBounds(470, 350, 140, 40);
		
		numT.setBounds(605, 350, 30, 40);
		aL.setBounds(640, 350, 10, 40);
		toT.setBounds(655, 350, 30, 40);
		
		teacherL.setBounds(470, 400, 140, 40);
		teacherT.setBounds(605, 400, 200, 40);
		contentL.setBounds(470, 450, 140, 40);
		contentT.setBounds(605, 450, 330, 60);
		applyBtn.setBounds(720, 540, 205, 76);
		
		// 추가
		add(idLabel);
		add(logoutBtn);
		add(listl);
		add(listL);
		add(infoL);
		add(codeL);
		add(codeT);
		add(nameL);
		add(nameT);
		add(dateL);
		add(dateT);
		add(toL);
		add(numT);
		add(aL);
		add(toT);
		add(teacherL);
		add(teacherT);
		add(contentL);
		add(contentT);
		add(magamL);
		add(applyBtn);
		
		
		// 버튼 이벤트
		logoutBtn.addActionListener(this);
		applyBtn.addActionListener(this);
		
		//리스트 이벤트
		list.addListSelectionListener(this);	
				
		setLayout(null);
	}
	
	//valueChanged
	@Override
	public void valueChanged(ListSelectionEvent e) {
		LectureDTO dto = list.getSelectedValue();
		
		if(list.getSelectedIndex() == -1) return;
		
		int code = dto.getLecCode();
		codeT.setText(Integer.toString(code));
		nameT.setText(dto.getLecName());
		
		String year = "20"+dto.getLecDate().substring(0,2);
		String month = dto.getLecDate().substring(2,4);
		String day = dto.getLecDate().substring(4,6);
		String date = year+"년 "+month+"월 "+day+"일";
		
		dateT.setText(date);

		int num = dto.getLecMemNum();
		numT.setText(Integer.toString(num));
		int to = dto.getLecTO();
		toT.setText(Integer.toString(to));
		
		teacherT.setText(dto.getLecTeacher());
		contentT.setText(dto.getLecContent());
		aL.setText("/");
		tCode = dto.gettCode();

		if(to-num<=3 && to-num>0) {
			magamL.setText("★마감이 얼마남지 않았습니다★");
			magamL.setForeground(Color.orange);
		}else if(to-num==0) {
			magamL.setText("☆신청 마감☆");
			magamL.setForeground(Color.red);
		}else {
			magamL.setText("");
		}
		
	}//valueChanged end

	
	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { // 로그아웃 버튼
			member.setVisible(false);
			new Login().event();
		} else if (e.getSource() == applyBtn) { // 신청하기 버튼
			if(codeT.getText() == null || codeT.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "신청하실 강의 명을 눌러주세요.");
			}else if(numT.getText().equals(toT.getText())) {
				JOptionPane.showMessageDialog(this, "인원 초과로 수강 신청이 마감되었습니다.");
			}else {
				int apply = JOptionPane.showConfirmDialog(this, "수강 신청 하시겠습니까?", "수강 신청", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (apply == JOptionPane.YES_OPTION) {
					applyArticle();
				}	
			}	
		}
	}

	//수강 신청
	private void applyArticle() {
		//데이터 입력
		String id = this.id;
		String name = myname;
		int lecCode = Integer.parseInt(codeT.getText());
		String lecName = teacherT.getText();
		String tCode = this.tCode;

		//MappingDTO
		MappingDTO dto = new MappingDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setLecCode(lecCode);
		dto.setLecName(lecName);
		dto.settCode(tCode);

		//DB
		MappingDAO dao = MappingDAO.getInstance();
		
		//신청했는 지 검색하기
		int su = dao.applyConfirmArticle(dto);
		
		if(su == 0) {
			//신청하기
			dao.applyArticle(dto);
			JOptionPane.showMessageDialog(this, "신청 완료되었습니다.");
			dao.plusToArticle(Integer.parseInt(numT.getText()), Integer.parseInt(codeT.getText()));
			numT.setText(Integer.toString((Integer.parseInt(numT.getText())+1)));
		}else if(su == 1) {
			JOptionPane.showMessageDialog(this, "이미 신청하신 강의입니다.");
		}

	}

}
