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

		// �� �̸� ����
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);

		// DB
		MemberDAO daoM = MemberDAO.getInstance();

		dto = daoM.myInfoArticle(dto);
		myname = dto.getName();
		
		
		//Jlist ����
		model = new DefaultListModel<LectureDTO>();
		list = new JList<LectureDTO>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("HY�߰��", Font.PLAIN, 15));
		
		JScrollPane listl = new JScrollPane(list);
		listl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					
		// DB
		LectureDAO dao = LectureDAO.getInstance();
		
		//��� ���ڵ带 ������ JList�� �ֱ�
		List<LectureDTO> dtoList = dao.getLectureList();
		
		for(LectureDTO dtoL : dtoList) {
			model.addElement(dtoL);
		}
		
		idLabel = new JLabel(id + " �� ȯ���մϴ�.");
		idLabel.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		infoL = new JLabel("�� ��  �� ��");
		infoL.setFont(new Font("���� ���� M", Font.PLAIN, 45));
		
		listL = new JLabel("�� ü  �� ��  �� ��");
		listL.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		
		magamL = new JLabel("");
		magamL.setFont(new Font("���� ���� M", Font.PLAIN, 17));
		
		codeL = new JLabel("�� ��  �� �� : ");
		codeL.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		codeT = new JLabel();
		codeT.setFont(new Font("���� ���� M", Font.PLAIN, 20));	
		
		nameL = new JLabel("��   ��   �� : ");
		nameL.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		nameT = new JLabel();
		nameT.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		
		dateL = new JLabel("�� ��  �� ¥ : ");
		dateL.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		dateT = new JLabel();
		dateT.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		
		aL = new JLabel("");
		aL.setFont(new Font("���� ���� M", Font.PLAIN, 20));	
		toL = new JLabel("�� û  �� �� : ");
		toL.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		numT = new JLabel();
		numT.setFont(new Font("���� ���� M", Font.PLAIN, 20));
	
		toT = new JLabel();
		toT.setFont(new Font("���� ���� M", Font.PLAIN, 20));

		
		teacherL = new JLabel("��   ��   �� : ");
		teacherL.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		teacherT = new JLabel();
		teacherT.setFont(new Font("���� ���� M", Font.PLAIN, 20));

		
		contentL = new JLabel("��  �� : ");
		contentL.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		contentT = new JLabel();
		contentT.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		
		// ��ư
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		applyBtn = new JButton(new ImageIcon("lib/applyBtn.png"));
		applyBtn.setBorderPainted(false);
		applyBtn.setContentAreaFilled(false);
		applyBtn.setOpaque(false);
		
		
		// ��ġ
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
		
		// �߰�
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
		
		
		// ��ư �̺�Ʈ
		logoutBtn.addActionListener(this);
		applyBtn.addActionListener(this);
		
		//����Ʈ �̺�Ʈ
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
		String date = year+"�� "+month+"�� "+day+"��";
		
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
			magamL.setText("�ڸ����� �󸶳��� �ʾҽ��ϴ١�");
			magamL.setForeground(Color.orange);
		}else if(to-num==0) {
			magamL.setText("�ٽ�û ������");
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
		if (e.getSource() == logoutBtn) { // �α׾ƿ� ��ư
			member.setVisible(false);
			new Login().event();
		} else if (e.getSource() == applyBtn) { // ��û�ϱ� ��ư
			if(codeT.getText() == null || codeT.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "��û�Ͻ� ���� ���� �����ּ���.");
			}else if(numT.getText().equals(toT.getText())) {
				JOptionPane.showMessageDialog(this, "�ο� �ʰ��� ���� ��û�� �����Ǿ����ϴ�.");
			}else {
				int apply = JOptionPane.showConfirmDialog(this, "���� ��û �Ͻðڽ��ϱ�?", "���� ��û", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (apply == JOptionPane.YES_OPTION) {
					applyArticle();
				}	
			}	
		}
	}

	//���� ��û
	private void applyArticle() {
		//������ �Է�
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
		
		//��û�ߴ� �� �˻��ϱ�
		int su = dao.applyConfirmArticle(dto);
		
		if(su == 0) {
			//��û�ϱ�
			dao.applyArticle(dto);
			JOptionPane.showMessageDialog(this, "��û �Ϸ�Ǿ����ϴ�.");
			dao.plusToArticle(Integer.parseInt(numT.getText()), Integer.parseInt(codeT.getText()));
			numT.setText(Integer.toString((Integer.parseInt(numT.getText())+1)));
		}else if(su == 1) {
			JOptionPane.showMessageDialog(this, "�̹� ��û�Ͻ� �����Դϴ�.");
		}

	}

}
