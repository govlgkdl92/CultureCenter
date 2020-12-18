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
		
		//Jlist ����
		model = new DefaultListModel<MemberDTO>();
		list = new JList<MemberDTO>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("HY�߰��", Font.PLAIN, 20));
		
		JScrollPane listl = new JScrollPane(list);
		listl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					
		// DB
		MemberDAO dao = MemberDAO.getInstance();
		
		//��� ���ڵ带 ������ JList�� �ֱ�
		List<MemberDTO> dtoList = dao.getMemberList();
		
		for(MemberDTO dto : dtoList) {
			model.addElement(dto);
		}

		// ������
//		String[] inter = {"��  ü","��  ��","��  ��","��  ��","��  ��","��  ��","��  ��"};
//		interest = new JComboBox<String>(inter);
//		interest.setSelectedIndex(0);
			 
		idLabel = new JLabel(id+" �����ڴ�");
		idLabel.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		infoL = new JLabel("ȸ ��  �� ��");
		infoL.setFont(new Font("���� ���� M", Font.PLAIN, 35));
		
		idL = new JLabel("��   ��   �� : ");
		idL.setFont(new Font("���� ���� M", Font.PLAIN, 15));		
		idT = new JLabel();
		idT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		nameL = new JLabel("��        �� : ");
		nameL.setFont(new Font("���� ���� M", Font.PLAIN, 15));	
		nameT = new JLabel();
		nameT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		birthL = new JLabel("�� ��  �� �� : ");
		birthL.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		birthT = new JLabel();
		birthT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		genderL = new JLabel("��        �� : ");
		genderL.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		genderT = new JLabel();
		genderT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		phoneL = new JLabel("�� ȭ  �� ȣ : ");
		phoneL.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		phoneT = new JLabel();
		phoneT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		emailL = new JLabel("��   ��   �� : ");
		emailL.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		emailT = new JLabel();
		emailT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		interestL = new JLabel("��   ��   �� : ");
		interestL.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		interestT = new JLabel();
		interestT.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		info2L = new JLabel("* �ش� ȸ������ ���� ������");
		info2L.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		// text
		mailT = new JTextArea(9, 10);
		mailT.setFont(new Font("���� ���� M", Font.PLAIN, 20));

		JScrollPane mailscl = new JScrollPane(mailT);
		mailscl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// ��ư
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		sendMailBtn = new JButton(new ImageIcon("lib/sendMail.png"));
		sendMailBtn.setBorderPainted(false);
		sendMailBtn.setContentAreaFilled(false);
		sendMailBtn.setOpaque(false);
		
		allsendBtn = new JButton("��ü ȸ�� ������");
		allsendBtn.setContentAreaFilled(false);
		allsendBtn.setFont(new Font("���� ���� M", Font.PLAIN, 17));
		
		// ��ġ
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
		
		// �߰�
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
		
		// ��ư �̺�Ʈ
		logoutBtn.addActionListener(this);
		sendMailBtn.addActionListener(this);
		allsendBtn.addActionListener(this);
		//����Ʈ �̺�Ʈ
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
		birthT.setText(year+"�� "+month+"�� "+day+"��");
		
		gender = dto.getGender();
			 if (gender == 0) genderS = "�� ��";
		else if (gender == 1) genderS = "�� ��";
		genderT.setText(genderS);
			 
		health = dto.getHealth();
			 if (health == 0) healthS = "";
		else if (health == 1) healthS = "�� ��";
		
		song = dto.getSong();
			 if (song == 0) songS = "";
		else if (song == 1) songS = "�� ��";
		book = dto.getBook();
			 if (book == 0) bookS = "";
		else if (book == 1) bookS = "�� ��";
	
		carpent = dto.getCarpent();
			 if (carpent == 0) carpentS = "";
		else if (carpent == 1) carpentS = "�� ��";
			 
		cook = dto.getCook();
			 if (cook == 0) cookS = "";
		else if (cook == 1) cookS = "�� ��";
		 
		art = dto.getArt();
			 if (art == 0) artS = "";
		else if (art == 1) artS = "�� ��";
		interestT.setText(healthS+"  "+songS+"  "+bookS+"  "+carpentS+"  "+cookS+"  "+artS);
	
	}//valueChanged end
	
	
	public void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { //�α׾ƿ� ��ư
			admin.setVisible(false);
			new Login().event();
		} else if (e.getSource() == sendMailBtn) { //���� ������
			new SendEmailMember(emailT.getText(), mailT.getText());
			JOptionPane.showMessageDialog(this, "���� ���� �Ϸ�");
		} else if (e.getSource() == allsendBtn) { //���� ������
			MemberDAO dao = MemberDAO.getInstance();
			List<MemberDTO> dtoList = dao.getMemberList();
			new SendEmailAll(dtoList , mailT.getText());
			JOptionPane.showMessageDialog(this, "��ü ���� ���� �Ϸ�");
		}
	}
}
