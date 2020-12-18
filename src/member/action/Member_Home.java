package member.action;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cultureCenter.Login;
import lecture.bean.LectureDTO;
import lecture.dao.LectureDAO;

import java.awt.Color;

@SuppressWarnings("all")
public class Member_Home extends JPanel implements ActionListener {
   private String id;
   private JLabel idL, mainL, buisnessL1, buisnessL2, buisnessL3, buisnessL4;
   private JButton logoutBtn;
   private Member member;
   
   private JLabel mainlecture;
   private String name;
   private List<LectureDTO> list;
   private Vector<String> vector;
   
   
   public Member_Home(String id, Member member) {
      this.id = id;
      this.member = member;
      
      
      mainlecture = new JLabel();
      mainlecture.setForeground(Color.white);
      mainlecture.setFont(new Font("���� ���� M", Font.PLAIN, 20));
      
      // ������
   	  LectureDAO dao = LectureDAO.getInstance();
   	  name = dao.getHomeLectureList();
   	  
   
   	  
      idL = new JLabel(id+" �� ȯ���մϴ�.");
      idL.setFont(new Font("���� ���� M", Font.PLAIN, 20));
      
      buisnessL1 = new JLabel("��Ʈ��ȭ���� ����� ���ʱ� ������� 459(���ʵ�, ��Ϻ���)  | (����)�������� 112 | �����: ");
      buisnessL1.setFont(new Font("���� ���� M", Font.PLAIN, 15));
      buisnessL1.setForeground(Color.white); 
      
      buisnessL2 = new JLabel("\t"+"��â��, ���̽�, ������ | ����� ��Ϲ�ȣ : �̹߱�... | ����ǸŹ�ȣ : �߱޿������� ����");
      buisnessL2.setFont(new Font("���� ���� M", Font.PLAIN, 15));
      buisnessL2.setForeground(Color.white); 
      
      buisnessL3 = new JLabel("\t"+"\t"+"��ȭ : 02)��6��6-5��79 | FAX : ��2)76��-55��8 | Email : Bit@Oracle.com");
      buisnessL3.setFont(new Font("���� ���� M", Font.PLAIN, 15));
      buisnessL3.setForeground(Color.white); 
      
      buisnessL4 = new JLabel("\t"+"\t"+"\t"+"Copyright 2020 ��Ʈ��ȭ���� all rights reserved.");
      buisnessL4.setFont(new Font("���� ���� M", Font.PLAIN, 15));
      buisnessL4.setForeground(Color.white); 
      
      //��ư
      logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
      logoutBtn.setBorderPainted(false);
      logoutBtn.setContentAreaFilled(false);
      logoutBtn.setOpaque(false);
    
      //��ġ  
      buisnessL1.setBounds(15,550,800,80);
      buisnessL2.setBounds(15,570,800,80);
      buisnessL3.setBounds(15,590,800,80);
      buisnessL4.setBounds(15,610,800,80);
      
      idL.setBounds(750, 10, 150, 20);
	  logoutBtn.setBounds(900, 10, 67, 25);
	  
	  mainlecture.setBounds(650, 80, 400, 20);
	  mainlecture.setText("�ڡ�NEW CLASS�ڡ� "+name);
	  
      //�߰�
      add(idL);
      add(buisnessL1);
      add(buisnessL2);
      add(buisnessL3);
      add(buisnessL4);
      add(logoutBtn);
      add(mainlecture);
      
      // ��ư �̺�Ʈ
      logoutBtn.addActionListener(this); 

      setLayout(null);
      
      
   }
   
   
   public void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("lib/home2.png").getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == logoutBtn) { //�α׾ƿ� ��ư
         member.setVisible(false);
         new Login().event();
      } 
   }
}