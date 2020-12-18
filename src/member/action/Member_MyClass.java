package member.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cultureCenter.Login;
import lecture.bean.LectureDTO;
import lecture.dao.LectureDAO;
import mapping.bean.MappingDTO;
import mapping.dao.MappingDAO;

@SuppressWarnings("all")
public class Member_MyClass extends JPanel implements ActionListener {
	private String id;
	private JLabel idLabel, titleL, evalinfoL, evalTeacher;
	private JTextField selectCode;
	private JButton logoutBtn, evalBtn, refreshBtn;
	private Member member;
	
	private List<LectureDTO> listMC;
	private List<Integer> listC;
	private Vector<String> vectorC;
	
	private JTextArea eval;
	
	private DefaultTableModel modelC;
	private JTable myclassList;
	
	public Member_MyClass(String id, Member member) {
		this.id = id;
		this.member = member;
		setLayout(null);
		
		
		// 나의 강의 목록 table
		// 데이터
		listC = new ArrayList<Integer>();		
		listMC = new ArrayList<LectureDTO>();	
		
		// 타이틀
		vectorC = new Vector<String>();
		vectorC.add("강의코드");
		vectorC.addElement("강의명");
		vectorC.addElement("날  짜");
		vectorC.add("정원");
		vectorC.addElement("강사명");
		vectorC.addElement("강의내용");
		
		// 모델 생성
		modelC = new DefaultTableModel(vectorC, 0) {
			// 수정하면 안되는 칸은 익명이너클래스를 이용하여 isCell메소드를 불러와서 해결한다.
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		
		MappingDAO daoM = MappingDAO.getInstance();
		listC = daoM.getMyLectureList(id);
	
		LectureDAO dao = LectureDAO.getInstance();	
		listMC = dao.getMyLectureList(listC);

		// 테이블 속에 데이터 붙이기
		for (LectureDTO dto : listMC) {
			Vector<String> v = new Vector<String>();
			v.add(Integer.toString(dto.getLecCode()));
			v.add(dto.getLecName());
		 
	         String year = dto.getLecDate().substring(0, 2);
	   	  	 String month = dto.getLecDate().substring(2, 4);
	   	  	 String day = dto.getLecDate().substring(4, 6);
	   	  	 String date = ("20"+year+"년  "+month+"월  "+day+"일");
			
			v.add(date);

			v.add(Integer.toString(dto.getLecTO()));
			v.add(dto.getLecTeacher());
			v.add(dto.getLecContent());

			// 모델 생성 보다 위에 만들면 nullPointException이 뜬다.
			modelC.addRow(v);		
		} // fo
		
		
		// 테이블 생성
		myclassList = new JTable(modelC);
		JScrollPane myclass= new JScrollPane(myclassList);
		
		myclassList.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));
		myclassList.setBackground(Color.white);
		myclassList.getColumnModel().getColumn(0).setPreferredWidth(20);  
		myclassList.getColumnModel().getColumn(1).setPreferredWidth(140);
		myclassList.getColumnModel().getColumn(2).setPreferredWidth(120);
		myclassList.getColumnModel().getColumn(3).setPreferredWidth(20);
		myclassList.getColumnModel().getColumn(4).setPreferredWidth(50);
		myclassList.getColumnModel().getColumn(5).setPreferredWidth(230);
		myclassList.setRowHeight(30);
		
		evalTeacher = new JLabel("선생님께 한줄 평가를 보내주세요.");
		evalTeacher.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));
		
		titleL = new JLabel("나의 강의 목록");
		titleL.setFont(new Font("한컴 백제 M", Font.PLAIN, 23));
		
		evalinfoL = new JLabel("0/40");
		evalinfoL.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));
		
		selectCode = new JTextField("");
		
		eval = new JTextArea();
		eval.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		idLabel = new JLabel(id + " 님 환영합니다.");
		idLabel.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		// 버튼
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		evalBtn = new JButton("평가 보내기");
		evalBtn.setContentAreaFilled(false);
		evalBtn.setEnabled(false);
		evalBtn.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));

		refreshBtn= new JButton("새로 고침");
		refreshBtn.setContentAreaFilled(false);
		refreshBtn.setFont(new Font("한컴 백제 M", Font.PLAIN, 10));
		refreshBtn.setForeground(Color.gray);
		
		// 위치
		titleL.setBounds(75, 65, 180, 50);
		
		refreshBtn.setBounds(770, 90, 80, 20);
		idLabel.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);
		
		myclass.setBounds(75, 120, 780, 350);
	
		evalinfoL.setBounds(650, 650, 50, 30);
		evalBtn.setBounds(720, 650, 130, 30);
		
		evalTeacher.setBounds(75, 490, 500, 50);
		eval.setBounds(75, 540, 780, 100);
		
		selectCode.setBounds(75, 790, 500, 50);
		
		// 추가
		add(idLabel);
		add(logoutBtn);
		add(myclass);
		add(evalBtn);
		add(eval);
		add(evalTeacher);
		add(evalinfoL);
		add(titleL);
		add(selectCode);
		add(refreshBtn);
		
		// 버튼 이벤트
		logoutBtn.addActionListener(this);
		evalBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
		
		myclassList.addMouseListener(new MouseAdapter() { // 테이블 클릭시
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int allListRow = myclassList.getSelectedRow();
				String selectedCode = (String) myclassList.getValueAt(allListRow, 0);
				String selectedName = (String) myclassList.getValueAt(allListRow, 1);
				String selectedTeacher = (String)myclassList.getValueAt(allListRow, 4);
				
				
				selectCode.setText(selectedCode);
				evalTeacher.setText("["+selectedName+"]  "+selectedTeacher+" 강사님 평가하기");
				evalBtn.setEnabled(true);
			}
		});
		
		eval.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				evalinfoL.setText(eval.getText().length()+"/40");
			}
		});

	}

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
		}else if (e.getSource() == evalBtn) { // 평가 보내기 버튼
			sw();	
		}else if (e.getSource() == refreshBtn) { //새로고침 버튼
			refresh();	
		}
	}
	

	//새로고침
	private void refresh() {
		modelC.setRowCount(0);
		MappingDAO daoM = MappingDAO.getInstance();
		listC = daoM.getMyLectureList(id);
	
		LectureDAO dao = LectureDAO.getInstance();	
		listMC = dao.getMyLectureList(listC);

		// 테이블 속에 데이터 붙이기
		for (LectureDTO dto : listMC) {
			Vector<String> v = new Vector<String>();
			v.add(Integer.toString(dto.getLecCode()));
			v.add(dto.getLecName());
		 
	         String year = dto.getLecDate().substring(0, 2);
	   	  	 String month = dto.getLecDate().substring(2, 4);
	   	  	 String day = dto.getLecDate().substring(4, 6);
	   	  	 String date = ("20"+year+"년  "+month+"월  "+day+"일");
			
			v.add(date);

			v.add(Integer.toString(dto.getLecTO()));
			v.add(dto.getLecTeacher());
			v.add(dto.getLecContent());

			// 모델 생성 보다 위에 만들면 nullPointException이 뜬다.
			modelC.addRow(v);		
		} // 
	}//새로고침 끝

	private void sendSw() {
		// 데이터
		int lecCode = Integer.parseInt(selectCode.getText());

		System.out.println(selectCode);
		// MappingDTO
		MappingDTO dto = new MappingDTO();
		dto.setEval(eval.getText());
		dto.setLecCode(lecCode);
		dto.setId(id);
			
		if(eval.getText().length() > 40) {
			JOptionPane.showMessageDialog(this, "40글자 이내로 작성해주세요.");
			return;
		}else if (eval.getText().equals("") || (eval.getText()) == null) {
			JOptionPane.showMessageDialog(this, "입력하신 글이 없습니다");
		}else {
			// DB
			MappingDAO dao = MappingDAO.getInstance();
			dao.updateEvalArticle(dto);

			JOptionPane.showMessageDialog(this, "평가 보내기가 완료되었습니다.");
			eval.setText("");
		}
	}

	// 평가 중복 확인
	private void sw() {
		// 데이터
		int lecCode = Integer.parseInt(selectCode.getText());

		// MappingDTO
		MappingDTO dto = new MappingDTO();
		dto.setLecCode(lecCode);
		dto.setId(id);
		
		// DB
		MappingDAO dao = MappingDAO.getInstance();

		int MappingSw = dao.overlapSwArticle(dto);


		if( MappingSw == 1) {
			JOptionPane.showMessageDialog(this, "이미 평가를 보낸 강의입니다");
		}else if(MappingSw == 0){
			sendSw();
		}	
	}

}
