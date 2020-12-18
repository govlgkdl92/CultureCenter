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
		tab.add("   Ȩ        ", new Admin_Home(id, this));
		tab.add(" ���� ����  ", new Admin_Class(id, this));
		tab.add(" ���� ����  ", new Admin_Teacher(id, this));
		tab.add(" ȸ�� ����  ", new Admin_Member(id, this));
		tab.add(" �� ����  ", new Admin_Service(id, this));

		add(tab, BorderLayout.CENTER);

		setTitle("��Ʈ��ȭ����(������)");
		setBounds(300, 100, 1000, 800);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exit = JOptionPane.showConfirmDialog(Admin.this, "���� ���� �Ͻðڽ��ϱ�?", "��Ʈ��ȭ���� ����",
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
