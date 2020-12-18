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
      mainlecture.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
      
      // 데이터
   	  LectureDAO dao = LectureDAO.getInstance();
   	  name = dao.getHomeLectureList();
   	  
   
   	  
      idL = new JLabel(id+" 님 환영합니다.");
      idL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
      
      buisnessL1 = new JLabel("비트문화센터 서울시 서초구 강남대로 459(서초동, 백암빌딩)  | (전국)국번없이 112 | 공동운영: ");
      buisnessL1.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
      buisnessL1.setForeground(Color.white); 
      
      buisnessL2 = new JLabel("\t"+"이창렬, 김이슬, 정해찬 | 사업자 등록번호 : 미발급... | 통신판매번호 : 발급예정일자 미정");
      buisnessL2.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
      buisnessL2.setForeground(Color.white); 
      
      buisnessL3 = new JLabel("\t"+"\t"+"전화 : 02)●6●6-5●79 | FAX : ●2)76●-55●8 | Email : Bit@Oracle.com");
      buisnessL3.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
      buisnessL3.setForeground(Color.white); 
      
      buisnessL4 = new JLabel("\t"+"\t"+"\t"+"Copyright 2020 비트문화센터 all rights reserved.");
      buisnessL4.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
      buisnessL4.setForeground(Color.white); 
      
      //버튼
      logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
      logoutBtn.setBorderPainted(false);
      logoutBtn.setContentAreaFilled(false);
      logoutBtn.setOpaque(false);
    
      //위치  
      buisnessL1.setBounds(15,550,800,80);
      buisnessL2.setBounds(15,570,800,80);
      buisnessL3.setBounds(15,590,800,80);
      buisnessL4.setBounds(15,610,800,80);
      
      idL.setBounds(750, 10, 150, 20);
	  logoutBtn.setBounds(900, 10, 67, 25);
	  
	  mainlecture.setBounds(650, 80, 400, 20);
	  mainlecture.setText("★★NEW CLASS★★ "+name);
	  
      //추가
      add(idL);
      add(buisnessL1);
      add(buisnessL2);
      add(buisnessL3);
      add(buisnessL4);
      add(logoutBtn);
      add(mainlecture);
      
      // 버튼 이벤트
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
      if (e.getSource() == logoutBtn) { //로그아웃 버튼
         member.setVisible(false);
         new Login().event();
      } 
   }
}