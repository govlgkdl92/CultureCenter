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
		
		idLabel = new JLabel(id+" �����ڴ�");
		idLabel.setFont(new Font("���� ���� M", Font.PLAIN, 15));
		
		mainL = new JLabel("BIT��ȭ����");
	    mainL.setFont(new Font("���� ���� M", Font.PLAIN, 80));
	  
	    buisnessL1 = new JLabel("��Ʈ��ȭ���� ����� ���ʱ� ������� 459(���ʵ�, ��Ϻ���)  | (����)�������� 112 | �����: ");
	    buisnessL1.setFont(new Font("���� ���� M", Font.PLAIN, 15));
	    buisnessL1.setForeground(Color.WHITE); 
	      
	    buisnessL2 = new JLabel("\t"+"��â��, ���̽�, ������ | ����� ��Ϲ�ȣ : �̹߱�... | ����ǸŹ�ȣ : �߱޿������� ����");
	    buisnessL2.setFont(new Font("���� ���� M", Font.PLAIN, 15));
	    buisnessL2.setForeground(Color.WHITE); 
	      
	    buisnessL3 = new JLabel("\t"+"\t"+"��ȭ : 02)��6��6-5��79 | FAX : ��2)76��-55��8 | Email : Bit@Oracle.com");
	    buisnessL3.setFont(new Font("���� ���� M", Font.PLAIN, 15));
	    buisnessL3.setForeground(Color.WHITE); 
	      
	    buisnessL4 = new JLabel("\t"+"\t"+"\t"+"Copyright 2020 ��Ʈ��ȭ���� all rights reserved.");
	    buisnessL4.setFont(new Font("���� ���� M", Font.PLAIN, 15));
	    buisnessL4.setForeground(Color.WHITE); 
	      
		//��ư
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);
		
		//��ġ
		idLabel.setBounds(750,10,150,20);
		mainL.setBounds(300,150,500,80);
	    buisnessL1.setBounds(15,600,800,80);
	    buisnessL2.setBounds(15,615,800,80);
	    buisnessL3.setBounds(15,630,800,80);
	    buisnessL4.setBounds(15,645,800,80);
	    logoutBtn.setBounds(900,10,67,25);
	
		//�߰�
		add(idLabel);
		add(mainL);
	    add(buisnessL1);
	    add(buisnessL2);
	    add(buisnessL3);
	    add(buisnessL4);
		add(logoutBtn);
		
		// ��ư �̺�Ʈ
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
		if (e.getSource() == logoutBtn) { //�α׾ƿ� ��ư
			admin.setVisible(false);
			new Login().event();
		} 
	}
}
