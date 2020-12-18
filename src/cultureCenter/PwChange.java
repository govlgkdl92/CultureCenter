package cultureCenter;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class PwChange extends JFrame implements ActionListener {
	private JLabel titleL, pwL, pwOL;
	private JPasswordField pwT, pwOT;
	private JButton changeBtn, cancelBtn;
	private BufferedImage img = null;
	private String id;

	public PwChange(String id) {
		this.id = id;

		System.out.println(id);

		titleL = new JLabel("��й�ȣ ����");
		titleL.setFont(new Font("���� ���� M", Font.BOLD, 30));

		pwL = new JLabel("������ ��й�ȣ");
		pwL.setFont(new Font("���� ���� M", Font.PLAIN, 18));

		pwOL = new JLabel("��й�ȣ Ȯ  ��");
		pwOL.setFont(new Font("���� ���� M", Font.PLAIN, 18));

		// �ؽ�Ʈ�佺����
		pwT = new JPasswordField();
		pwT.setEchoChar('��');
		pwOT = new JPasswordField();
		pwOT.setEchoChar('��');

		// ã�� ��ư
		changeBtn = new JButton(new ImageIcon("lib/changeBtn.png"));
		changeBtn.setBorderPainted(false);
		changeBtn.setContentAreaFilled(false);
		changeBtn.setOpaque(false);

		// ��� ��ư
		cancelBtn = new JButton(new ImageIcon("lib/cancelB.png"));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setOpaque(false);

		// ��ġ
		titleL.setBounds(80, 10, 250, 50);

		pwL.setBounds(20, 80, 120, 30);
		pwT.setBounds(160, 80, 160, 30);

		pwOL.setBounds(20, 120, 120, 30);
		pwOT.setBounds(160, 120, 160, 30);

		cancelBtn.setBounds(80, 195, 59, 40);
		changeBtn.setBounds(160, 195, 130, 40);

		// add ~ * ~
		Container c = this.getContentPane();
		c.add(titleL);

		c.add(pwL);
		c.add(pwT);
		c.add(pwOL);
		c.add(pwOT);

		c.add(changeBtn);
		c.add(cancelBtn);

		// ��� Panel
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(650, 530);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("lib/flower.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�̹��� �ҷ����� ����");
			System.exit(0);
		}
		// �̹��� ����
		bgPanel panel = new bgPanel();
		panel.setSize(650, 530);
		layeredPane.add(panel);
		add(layeredPane);

		setLayout(null);

		// �⺻ ������ ����.
		setLayout(null);
		setBounds(840, 410, 360, 300);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

		// �̺�Ʈ
		changeBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}

	class bgPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == changeBtn) { // ����
			search();
		} else if (e.getSource() == cancelBtn) { // ���
			setVisible(false);
		}
	}

	private void search() {
		// ������
		String pw = pwT.getText();

		System.out.println(pwT.getText());
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setPw(pw);
		dto.setId(id);

		if (!pwT.getText().equals(pwOT.getText())) {
			JOptionPane.showMessageDialog(this, "��й�ȣ Ȯ���� �������� �ʽ��ϴ�.");

		} else if (pwT.getText().equals("") || (pwT.getText()) == null) {
			JOptionPane.showMessageDialog(this, "�Է��Ͻ� ȸ�� ������ �����ϴ�");
		} else {
			// DB
			MemberDAO dao = MemberDAO.getInstance();
			dao.PwChangeArticle(dto);

			JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
			dispose();
		}

	}

}
