package admin.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cultureCenter.Login;
import lecture.bean.LectureDTO;
import lecture.dao.LectureDAO;
import mapping.bean.MappingDTO;
import mapping.dao.MappingDAO;

@SuppressWarnings("all")
public class Admin_Class extends JPanel implements ActionListener {
	private JLabel listL, lecInfoL, to1L, to2L;
	private JLabel deleteT, lecNameT, toT;
	private String id;
	private JLabel idLabel;
	private JButton logoutBtn, lecApplyBtn, lecDeleteBtn, refreshBtn;

	private List<LectureDTO> listA;
	private Vector<String> vectorA;

	private List<MappingDTO> listM;
	private Vector<String> vectorM;

	private DefaultTableModel modelA, modelM;
	private JTable allList, memberList;
	private Admin admin;

	public Admin_Class(String id, Admin admin) {
		this.id = id;
		this.admin = admin;
		setLayout(null);

		// ��ü ȸ�� table
		// ������
		listA = new ArrayList<LectureDTO>();

		// Ÿ��Ʋ
		vectorA = new Vector<String>();
		vectorA.add("�����ڵ�");
		vectorA.addElement("���Ǹ�");
		vectorA.addElement("��¥");
		vectorA.add("��û�ο�");
		vectorA.add("����");
		vectorA.addElement("�����");

		// �� ����
		modelA = new DefaultTableModel(vectorA, 0) {
			// �����ϸ� �ȵǴ� ĭ�� �͸��̳�Ŭ������ �̿��Ͽ� isCell�޼ҵ带 �ҷ��ͼ� �ذ��Ѵ�.
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		LectureDAO dao = LectureDAO.getInstance();
		listA = dao.getLectureList();

		// ���̺� �ӿ� ������ ���̱�
		for (LectureDTO dto : listA) {
			Vector<String> v = new Vector<String>();
			v.add(Integer.toString(dto.getLecCode()));
			v.add(dto.getLecName());
			String year = dto.getLecDate().substring(0, 2);
			String month = dto.getLecDate().substring(2, 4);
			String day = dto.getLecDate().substring(4, 6);
			
			v.add(year +"�� "+month+"�� "+day + "��");
			v.add(Integer.toString(dto.getLecMemNum()));
			v.add(Integer.toString(dto.getLecTO()));
			v.add(dto.getLecTeacher());

			// �� ���� ���� ���� ����� nullPointException�� ���.
			modelA.addRow(v);
		} // for

		
		// ���� ��û ȸ�� table
		// ������
		listM = new ArrayList<MappingDTO>();
	
		// Ÿ��Ʋ
		vectorM = new Vector<String>();
		vectorM.addElement("ID");
		vectorM.addElement("�� ��");

		// �� ����
		modelM = new DefaultTableModel(vectorM, 0) {
			// �����ϸ� �ȵǴ� ĭ�� �͸��̳�Ŭ������ �̿��Ͽ� isCell�޼ҵ带 �ҷ��ͼ� �ذ��Ѵ�.
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		// ���̺� ����
		allList = new JTable(modelA);		 
		allList.setRowHeight(20);
		allList.setFont(new Font("���� ���� M", Font.PLAIN, 16));
		//���̺� ���ΰ��� ��/��
	    allList.getColumnModel().getColumn(0).setPreferredWidth(50);  
	    allList.getColumnModel().getColumn(1).setPreferredWidth(140);
	    allList.getColumnModel().getColumn(2).setPreferredWidth(100);
	    allList.getColumnModel().getColumn(3).setPreferredWidth(60);
	    allList.getColumnModel().getColumn(4).setPreferredWidth(60);
	    allList.getColumnModel().getColumn(5).setPreferredWidth(60);

		JScrollPane ListA = new JScrollPane(allList);

		memberList = new JTable(modelM);
		JScrollPane ListM = new JScrollPane(memberList);
		memberList.setRowHeight(20); 
		memberList.setFont(new Font("���� ���� M", Font.PLAIN, 16));
		// ������
		idLabel = new JLabel(id + " �����ڴ�");
		idLabel.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		listL = new JLabel("��ü ���� ���");
		listL.setFont(new Font("���� ���� M", Font.PLAIN, 25));

		deleteT = new JLabel();
		deleteT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		deleteT.setForeground(Color.red);

		lecNameT = new JLabel();
		lecNameT.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		lecInfoL = new JLabel("���� ��û ȸ�� ���");
		lecInfoL.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		to1L = new JLabel("���� ���� : ");
		to1L.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		toT = new JLabel();
		toT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		to2L = new JLabel("��");
		to2L.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		// ��ư
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		lecApplyBtn = new JButton(new ImageIcon("lib/applyClass.png"));
		lecApplyBtn.setBorderPainted(false);
		lecApplyBtn.setContentAreaFilled(false);
		lecApplyBtn.setOpaque(false);

		lecDeleteBtn = new JButton("���� ����");
		// lecDeleteBtn.setBorderPainted(false);
		lecDeleteBtn.setContentAreaFilled(false);
		lecDeleteBtn.setOpaque(false);
		lecDeleteBtn.setFont(new Font("���� ���� M", Font.PLAIN, 17));

		refreshBtn= new JButton("���� ��ħ");
        refreshBtn.setContentAreaFilled(false);
        refreshBtn.setFont(new Font("���� ���� M", Font.PLAIN, 10));
        refreshBtn.setForeground(Color.gray);
		
		// ��ġ
		idLabel.setBounds(750, 10, 150, 20);

		listL.setBounds(45, 80, 150, 30);
		ListA.setBounds(45, 160, 560, 450);

		lecApplyBtn.setBounds(490, 100, 110, 41);

		lecDeleteBtn.setBounds(500, 620, 100, 30);
		refreshBtn.setBounds(400, 620, 80, 20);
		deleteT.setBounds(45, 130, 450, 30);

		lecNameT.setBounds(650, 100, 150, 30);
		lecInfoL.setBounds(805, 105, 150, 30);
		ListM.setBounds(650, 140, 230, 360);

		to1L.setBounds(650, 510, 80, 20);
		toT.setBounds(730, 510, 25, 20);
		to2L.setBounds(760, 510, 20, 20);

		logoutBtn.setBounds(900, 10, 67, 25);

		// �߰�
		add(idLabel);
		add(logoutBtn);
		add(listL);
		add(deleteT);
		add(lecInfoL);
		add(lecNameT);
		add(to1L);
		add(toT);
		add(to2L);
		add(lecApplyBtn);
		add(lecDeleteBtn);
		add(ListA);
		add(ListM);
		add(refreshBtn);
		// ��ư �̺�Ʈ
		logoutBtn.addActionListener(this);
		lecApplyBtn.addActionListener(this);
		lecDeleteBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
		allList.addMouseListener(new MouseAdapter() { // ���̺� Ŭ����
			@Override
			public void mouseClicked(MouseEvent e) {
				int allListRow = allList.getSelectedRow();
				String selectedName = (String) allList.getValueAt(allListRow, 1);
				String selectedDate = (String) allList.getValueAt(allListRow, 2);
				String selectedTO = (String) allList.getValueAt(allListRow, 4);
				String selectedMemnum = (String) allList.getValueAt(allListRow, 3);
				int remainMem = (Integer.parseInt(selectedTO)) - (Integer.parseInt(selectedMemnum));
				lecNameT.setText(selectedName);
				toT.setText(Integer.toString(remainMem));

				String selectedLecCode = (String) allList.getValueAt(allListRow, 0);
				int castedLecCode = Integer.parseInt(selectedLecCode);
				MappingDAO dao = MappingDAO.getInstance();
				listM = dao.getMemberList(castedLecCode);

				modelM.setRowCount(0);
				for (MappingDTO mappingDto : listM) {
					Vector<String> v = new Vector<String>();
					v.add(mappingDto.getId());
					v.add(mappingDto.getName());
					modelM.addRow(v);
				}

				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("YY�� MM�� dd�� E���� HH:mm:ss");
	
				if( (Integer.parseInt(selectedDate.substring(8, 10)) <= Integer.parseInt(sdf.format(date).substring(8,10)) &&
					Integer.parseInt(selectedDate.substring(4, 6)) <= Integer.parseInt(sdf.format(date).substring(4,6)) )||
					Integer.parseInt(selectedDate.substring(0, 2)) < Integer.parseInt(sdf.format(date).substring(0,2)) &&
					Integer.parseInt(selectedDate.substring(4, 6)) < Integer.parseInt(sdf.format(date).substring(4,6))) {
					deleteT.setText("�� ��¥�� ���� ["+selectedName+"] ���Ǹ� �������ּ���.");				
				}
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
		if (e.getSource() == logoutBtn) { // �α׾ƿ� ��ư
			admin.setVisible(false);
			new Login().event();
		} else if (e.getSource() == lecApplyBtn) {
			lecApply();
		} else if (e.getSource() == lecDeleteBtn) {
			lecDelete();
		}else if (e.getSource() == refreshBtn) { // �� ������ ��ư
			 refresh();	
		  }
	}

	//���ΰ�ħ
	private void refresh() {
		modelA.setRowCount(0);
		
		LectureDAO dao = LectureDAO.getInstance();
		listA = dao.getLectureList();

		// ���̺� �ӿ� ������ ���̱�
		for (LectureDTO dto : listA) {
			Vector<String> v = new Vector<String>();
			v.add(Integer.toString(dto.getLecCode()));
			v.add(dto.getLecName());
			String year = dto.getLecDate().substring(0, 2);
			String month = dto.getLecDate().substring(2, 4);
			String day = dto.getLecDate().substring(4, 6);
			
			v.add(year +"�� "+month+"�� "+day + "��");
			v.add(Integer.toString(dto.getLecMemNum()));
			v.add(Integer.toString(dto.getLecTO()));
			v.add(dto.getLecTeacher());

			// �� ���� ���� ���� ����� nullPointException�� ���.
			modelA.addRow(v);
		} // for
	}//���ΰ�ħ

	private void lecDelete() {
		LectureDAO dao = LectureDAO.getInstance();
		int selectedRow = allList.getSelectedRow();
		int deleteLecture = JOptionPane.showConfirmDialog(this, "���� ���Ǹ� �����Ͻðڽ��ϱ�?", "���ǻ���", JOptionPane.YES_NO_OPTION);
		if (deleteLecture == JOptionPane.YES_OPTION) {
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "���õ� ���ǰ� �����ϴ�");
				return;
			}
			String s = (String) allList.getValueAt(selectedRow, 0);
			int code = Integer.parseInt(s);
			
			MappingDAO mappingDao = MappingDAO.getInstance();
			MappingDTO mappingDto = new MappingDTO();
			mappingDto.setLecCode(code);
			mappingDao.droplecture(mappingDto);
			
			modelA.removeRow(selectedRow);
					
			dao.deleteLecture(code);
		
			JOptionPane.showMessageDialog(this, "�����Ϸ�");
		}
	}	
		
		private void lecApply() {
			String name = JOptionPane.showInputDialog(this, "���� ���� �Է��ϼ���");
			if (name == null) {
				return;
			}
			if (name.length() == 0) {
				JOptionPane.showMessageDialog(this, "���Ǹ��� �ʼ��׸��Դϴ�");
				return;
			}
			String date = JOptionPane.showInputDialog(this, "���� �Ⱓ�� �Է��ϼ���");
			if (date == null) {
				return;
			}
			String to = JOptionPane.showInputDialog(this, "���� ������ �Է��ϼ���");
			if (to == null) {
				return;
			}
			String teacher = JOptionPane.showInputDialog(this, "��� ���縦 �Է��ϼ���");
			if (teacher == null) {
				return;
			}
			String teacherCode = JOptionPane.showInputDialog(this, "��Ȯ�� �����ڵ带 �Է��ϼ���");
			if (teacherCode == null) {
				return;
			}
			String lecContent = JOptionPane.showInputDialog(this, "������ ���� �Ұ��� �Է��ϼ���.");
			if (lecContent == null) {
				return;
			}
			LectureDAO daoM = LectureDAO.getInstance();
			LectureDTO dto = new LectureDTO();

			dto.setLecName(name);
			dto.setLecDate(date);
			dto.setLecTO(Integer.parseInt(to));
			dto.setLecTeacher(teacher);
			dto.settCode(teacherCode);
			dto.setLecContent(lecContent);

			daoM.insertLecture(dto);

			Vector<String> v = new Vector<String>();
			v.add("�� ����");
			v.add(name);
			String year = date.substring(0, 2);
			String month = date.substring(2, 4);
			String day = date.substring(4, 6);
			
			v.add(year +"�� "+month+"�� "+day + "��");
			v.add(Integer.toString(dto.getLecMemNum()));
			v.add(to);
			v.add(teacher);
			modelA.addRow(v);

			JOptionPane.showMessageDialog(this, "���縦 �߰��߽��ϴ�");

		}
	

	
	
}