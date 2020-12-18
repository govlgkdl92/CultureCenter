package member.action;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import chat.Chat_Client;
import cultureCenter.Login;
import cultureCenter.ServiceEmail;

@SuppressWarnings("all")
public class Member_Service extends JPanel implements ActionListener {
	private String id;
	private JLabel idLabel, chatL, questionL;
	private JButton logoutBtn, chatBtn, questionBtn;
	private JTextArea questionT;
	private Member member;

	public Member_Service(String id, Member member) {
		this.id = id;
		this.member = member;

		idLabel = new JLabel(id + " 님 환영합니다.");
		idLabel.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		questionL = new JLabel("※ 문의 사항을 남겨주시면 빠른 시일 내에 답변 드리겠습니다.");
		questionL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		chatL = new JLabel("*실시간 상담은 9시~18시까지 가능합니다. 상담을 원하실 경우 실시간 상담 버튼을 눌러주세요.");
		chatL.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		// 버튼
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		chatBtn = new JButton(new ImageIcon("lib/chat.png"));
		chatBtn.setBorderPainted(false);
		chatBtn.setContentAreaFilled(false);
		chatBtn.setOpaque(false);

		questionBtn = new JButton(new ImageIcon("lib/questionBtn.png"));
		questionBtn.setBorderPainted(false);
		questionBtn.setContentAreaFilled(false);
		questionBtn.setOpaque(false);

		// text
		questionT = new JTextArea(20, 20);
		questionT.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));

		JScrollPane scroll = new JScrollPane(questionT);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// 위치
		setLayout(null);

		idLabel.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);

		questionL.setBounds(70, 100, 600, 25);
		scroll.setBounds(70, 130, 600, 350);
		questionBtn.setBounds(680, 410, 170, 60);

		chatL.setBounds(30, 680, 700, 25);
		chatBtn.setBounds(30, 560, 235, 100);

		// 창에 추가
		add(idLabel);
		add(logoutBtn);
		add(chatL);
		add(chatBtn);

		add(scroll);
		add(questionL);
		// add(questionT);
		add(questionBtn);

		// 버튼 이벤트
		logoutBtn.addActionListener(this);
		questionBtn.addActionListener(this);
		chatBtn.addActionListener(this);

	}

	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { // 로그아웃 버튼
			member.setVisible(false);
			new Login().event();
		} else if (e.getSource() == questionBtn) {
			int question = JOptionPane.showConfirmDialog(this, "빠른 시일 내 회원님에 메일로 답변을 보내드립니다.\n문의를 보내시겠습니까?", "문의하기",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (question == JOptionPane.YES_OPTION) {
				new ServiceEmail(id, questionT.getText());
				JOptionPane.showMessageDialog(this, "문의 완료");
			}
		} else if (e.getSource() == chatBtn) {
			new Chat_Client(id);
		}
	}

}
