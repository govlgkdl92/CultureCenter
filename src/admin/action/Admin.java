package admin.action;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import chat.Admin_Service;

@SuppressWarnings("all")
public class Admin extends JFrame {
	private String id;
	
	public Admin(String id) {
		this.id = id;
		
		JTabbedPane tab = new JTabbedPane();
		tab.add("   홈        ", new Admin_Home(id, this));
		tab.add(" 강의 관리  ", new Admin_Class(id, this));
		tab.add(" 강사 관리  ", new Admin_Teacher(id, this));
		tab.add(" 회원 관리  ", new Admin_Member(id, this));
		tab.add(" 고객 센터  ", new Admin_Service(id, this));

		add(tab, BorderLayout.CENTER);

		setTitle("비트문화센터(관리자)");
		setBounds(300, 100, 1000, 800);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exit = JOptionPane.showConfirmDialog(Admin.this, "정말 종료 하시겠습니까?", "비트문화센터 종료",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (exit == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		setResizable(false);
		setVisible(true);
	}

	

}
