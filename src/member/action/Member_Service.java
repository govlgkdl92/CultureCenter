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

		idLabel = new JLabel(id + " �� ȯ���մϴ�.");
		idLabel.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		questionL = new JLabel("�� ���� ������ �����ֽø� ���� ���� ���� �亯 �帮�ڽ��ϴ�.");
		questionL.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		chatL = new JLabel("*�ǽð� ����� 9��~18�ñ��� �����մϴ�. ����� ���Ͻ� ��� �ǽð� ��� ��ư�� �����ּ���.");
		chatL.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		// ��ư
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
		questionT.setFont(new Font("���� ���� M", Font.PLAIN, 20));

		JScrollPane scroll = new JScrollPane(questionT);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// ��ġ
		setLayout(null);

		idLabel.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);

		questionL.setBounds(70, 100, 600, 25);
		scroll.setBounds(70, 130, 600, 350);
		questionBtn.setBounds(680, 410, 170, 60);

		chatL.setBounds(30, 680, 700, 25);
		chatBtn.setBounds(30, 560, 235, 100);

		// â�� �߰�
		add(idLabel);
		add(logoutBtn);
		add(chatL);
		add(chatBtn);

		add(scroll);
		add(questionL);
		// add(questionT);
		add(questionBtn);

		// ��ư �̺�Ʈ
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
		if (e.getSource() == logoutBtn) { // �α׾ƿ� ��ư
			member.setVisible(false);
			new Login().event();
		} else if (e.getSource() == questionBtn) {
			int question = JOptionPane.showConfirmDialog(this, "���� ���� �� ȸ���Կ� ���Ϸ� �亯�� �����帳�ϴ�.\n���Ǹ� �����ðڽ��ϱ�?", "�����ϱ�",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (question == JOptionPane.YES_OPTION) {
				new ServiceEmail(id, questionT.getText());
				JOptionPane.showMessageDialog(this, "���� �Ϸ�");
			}
		} else if (e.getSource() == chatBtn) {
			new Chat_Client(id);
		}
	}

}
