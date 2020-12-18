package admin.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cultureCenter.Login;

@SuppressWarnings("all")
public class Admin_Home extends JPanel implements ActionListener {
	private String id;
	private JLabel idLabel, mainL, buisnessL1, buisnessL2, buisnessL3, buisnessL4;;
	private JButton logoutBtn;
	private Admin admin;
	
	public Admin_Home(String id, Admin admin) {
		this.id = id;
		this.admin = admin;
		
		idLabel = new JLabel(id+" 관리자님");
		idLabel.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
		
		mainL = new JLabel("BIT문화센터");
	    mainL.setFont(new Font("한컴 백제 M", Font.PLAIN, 80));
	  
	    buisnessL1 = new JLabel("비트문화센터 서울시 서초구 강남대로 459(서초동, 백암빌딩)  | (전국)국번없이 112 | 공동운영: ");
	    buisnessL1.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
	    buisnessL1.setForeground(Color.WHITE); 
	      
	    buisnessL2 = new JLabel("\t"+"이창렬, 김이슬, 정해찬 | 사업자 등록번호 : 미발급... | 통신판매번호 : 발급예정일자 미정");
	    buisnessL2.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
	    buisnessL2.setForeground(Color.WHITE); 
	      
	    buisnessL3 = new JLabel("\t"+"\t"+"전화 : 02)●6●6-5●79 | FAX : ●2)76●-55●8 | Email : Bit@Oracle.com");
	    buisnessL3.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
	    buisnessL3.setForeground(Color.WHITE); 
	      
	    buisnessL4 = new JLabel("\t"+"\t"+"\t"+"Copyright 2020 비트문화센터 all rights reserved.");
	    buisnessL4.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
	    buisnessL4.setForeground(Color.WHITE); 
	      
		//버튼
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);
		
		//위치
		idLabel.setBounds(750,10,150,20);
		mainL.setBounds(300,150,500,80);
	    buisnessL1.setBounds(15,600,800,80);
	    buisnessL2.setBounds(15,615,800,80);
	    buisnessL3.setBounds(15,630,800,80);
	    buisnessL4.setBounds(15,645,800,80);
	    logoutBtn.setBounds(900,10,67,25);
	
		//추가
		add(idLabel);
		add(mainL);
	    add(buisnessL1);
	    add(buisnessL2);
	    add(buisnessL3);
	    add(buisnessL4);
		add(logoutBtn);
		
		// 버튼 이벤트
		logoutBtn.addActionListener(this);
		
		setLayout(null);
		
	}
	
	
	public void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("lib/home.jpg").getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { //로그아웃 버튼
			admin.setVisible(false);
			new Login().event();
		} 
	}
}
