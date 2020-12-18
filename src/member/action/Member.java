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
		tab.add("     홈     ", new Member_Home(id, this));
		tab.add("개인정보수정", new Member_MyInfo(id, this));
		tab.add(" 나의  강의  ", new Member_MyClass(id, this));
		tab.add(" 강사  소개  ", new Member_Lecturer(id, this));
		tab.add(" 수강  신청  ", new Member_Apply(id, this));
		tab.add(" 고객  센터  ", new Member_Service(id, this));

		add(tab, BorderLayout.CENTER);

		// 확인
		setTitle("비트문화센터");
		setBounds(300, 100, 1000, 800);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exit = JOptionPane.showConfirmDialog(Member.this, "정말 종료 하시겠습니까?", "비트문화센터 종료",
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
