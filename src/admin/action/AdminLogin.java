package admin.action;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JTextField;

import admin.bean.AdminDTO;
import admin.dao.AdminDAO;
import cultureCenter.Login;

@SuppressWarnings("all")
public class AdminLogin extends JFrame implements ActionListener {
	private JLabel titleL, title2L, idL, pwL;
	private JTextField idT;
	private JPasswordField pwT;
	private JButton loginBtn, cancelBtn;

	BufferedImage img = null;

	public AdminLogin() {
		// ���̵� �Է�â
		titleL = new JLabel("��Ʈ ��ȭ����");
		titleL.setFont(new Font("���� �ٰռ��� B", Font.BOLD, 30));

		title2L = new JLabel("������[����] �α���");
		title2L.setFont(new Font("���� �ٰռ��� B", Font.BOLD, 30));

		idL = new JLabel("������ ���̵�");
		idL.setFont(new Font("���� ���� M", Font.BOLD, 18));

		idT = new JTextField();
		idT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		// ��й�ȣ �Է�â
		pwL = new JLabel("������ ��й�ȣ");
		pwL.setFont(new Font("���� ���� M", Font.BOLD, 18));

		pwT = new JPasswordField();
		pwT.setFont(new Font("���� ���� M", Font.BOLD, 18));
		pwT.setEchoChar('��');

		// �α��� ��ư
		loginBtn = new JButton(new ImageIcon("lib/loginBtn.png"));
		loginBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setOpaque(false);

		// ��� ��ư
		cancelBtn = new JButton(new ImageIcon("lib/cancelBtn.png"));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setOpaque(false);

		// ��ġ
		titleL.setBounds(80, 80, 400, 50);
		title2L.setBounds(50, 140, 400, 50);
		idL.setBounds(50, 230, 150, 30);
		idT.setBounds(200, 230, 150, 30);
		pwL.setBounds(50, 290, 150, 30);
		pwT.setBounds(200, 290, 150, 30);

		cancelBtn.setBounds(40, 380, 132, 50);
		loginBtn.setBounds(170, 380, 132, 50);

		// add ~ * ~
		Container c = this.getContentPane();
		c.add(titleL);
		c.add(title2L);
		c.add(idL);
		c.add(idT);

		c.add(pwL);
		c.add(pwT);

		c.add(loginBtn);
		c.add(cancelBtn);

		// ��� Panel
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(650, 530);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("lib/back.png"));
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
		setBounds(700, 300, 650, 530);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				new Login().event();
			}
		});

		// �̺�Ʈ
		cancelBtn.addActionListener(this);
		loginBtn.addActionListener(this);
		
		pwT.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
	}

	class bgPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginBtn) { // ������ �α���
			login();
		} else if (e.getSource() == cancelBtn) { // ���
			setVisible(false);
			new Login().event();
		}

	}

	private void login() {
		// ������
		String id = idT.getText();
		String pw = pwT.getText();

		// 	AdminDTO
		AdminDTO dto = new 	AdminDTO();
		dto.setId(id);
		dto.setPw(pw);

		// DB
		AdminDAO dao = 	AdminDAO.getInstance();

		int su = dao.loginArticle(dto);

		// setVisible(false);
		if ((idT.getText()).equals("") || (idT.getText()).equals(null) || (pwT.getText()).equals("")
				|| (pwT.getText()).equals(null)) { // ����
			JOptionPane.showMessageDialog(this, "���̵� �Ǵ� ��й�ȣ�� �Է����ּ���");
		} else if (su == 1) {
			setVisible(false);
			new Admin(idT.getText());
		} else if (su == 0) {
			JOptionPane.showMessageDialog(this, "���̵� �Ǵ� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
		}
		
		
		
	}
}
