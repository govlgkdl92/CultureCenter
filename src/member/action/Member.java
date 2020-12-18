package member.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import member.bean.MemberDTO;

@SuppressWarnings("all")
public class Member extends JFrame implements ActionListener {
	private MemberDTO dto;
	private String id;

	public Member(String id) {
		this.id = id;

		JTabbedPane tab = new JTabbedPane();
		tab.add("     Ȩ     ", new Member_Home(id, this));
		tab.add("������������", new Member_MyInfo(id, this));
		tab.add(" ����  ����  ", new Member_MyClass(id, this));
		tab.add(" ����  �Ұ�  ", new Member_Lecturer(id, this));
		tab.add(" ����  ��û  ", new Member_Apply(id, this));
		tab.add(" ��  ����  ", new Member_Service(id, this));

		add(tab, BorderLayout.CENTER);

		// Ȯ��
		setTitle("��Ʈ��ȭ����");
		setBounds(300, 100, 1000, 800);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exit = JOptionPane.showConfirmDialog(Member.this, "���� ���� �Ͻðڽ��ϱ�?", "��Ʈ��ȭ���� ����",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (exit == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
