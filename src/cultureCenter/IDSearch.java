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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class IDSearch extends JFrame implements ActionListener {
	private JLabel titleL, infoL, nameL, birthL;
	private JTextField nameT, birthT;
	private JButton serachBtn, cancelBtn;
	private BufferedImage img = null;
	private List<MemberDTO> dtoList = new ArrayList<MemberDTO>();

	public IDSearch() {
		// ���̵� �Է�â
		titleL = new JLabel("���̵� ã��");
		titleL.setFont(new Font("���� ���� M", Font.BOLD, 35));

		infoL = new JLabel("��Ȯ�ϰ� �Է����ּ���.");
		infoL.setFont(new Font("���� ���� M", Font.PLAIN, 14));

		nameL = new JLabel("��    ��");
		nameL.setFont(new Font("���� ���� M", Font.BOLD, 18));

		nameT = new JTextField();
		nameT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		birthL = new JLabel("�������");
		birthL.setFont(new Font("���� ���� M", Font.BOLD, 18));

		birthT = new JTextField();
		birthT.setFont(new Font("���� ���� M", Font.BOLD, 18));

		// ã�� ��ư
		serachBtn = new JButton(new ImageIcon("lib/idSearch.png"));
		serachBtn.setBorderPainted(false);
		serachBtn.setContentAreaFilled(false);
		serachBtn.setOpaque(false);

		// ��� ��ư
		cancelBtn = new JButton(new ImageIcon("lib/cancelB.png"));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setOpaque(false);

		// ��ġ
		titleL.setBounds(110, 10, 250, 50);
		infoL.setBounds(70, 50, 250, 67);

		nameL.setBounds(70, 100, 80, 30);
		nameT.setBounds(170, 100, 150, 30);
		birthL.setBounds(70, 140, 80, 30);
		birthT.setBounds(170, 140, 150, 30);

		cancelBtn.setBounds(100, 195, 59, 40);
		serachBtn.setBounds(180, 195, 130, 40);

		// add ~ * ~
		Container c = this.getContentPane();
		c.add(titleL);
		c.add(infoL);

		c.add(nameL);
		c.add(nameT);

		c.add(birthL);
		c.add(birthT);

		c.add(serachBtn);
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
		setBounds(820, 410, 400, 300);
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
		serachBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}

	class bgPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == serachBtn) { // ã��
			search();
		} else if (e.getSource() == cancelBtn) { // �α���
			setVisible(false);
		}
	}

	private void search() {
		// ������
		String name = nameT.getText();
		String birth = birthT.getText();
		String id = "";

		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setName(name);
		dto.setBirth(birth);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		id = dao.idSearchArticle(dto);

		// setVisible(false);
		if ((nameT.getText()).equals("") || (nameT.getText()).equals(null) || (birthT.getText()).equals("")
				|| (birthT.getText()).equals(null)) { // ����
			JOptionPane.showMessageDialog(this, "�̸��� ��������� ��� �Է����ּ���.");
		} else if (id.equals("") || id == null) {
			JOptionPane.showMessageDialog(this, "�Է��Ͻ� ȸ�� ������ �����ϴ�");
		} else {
			JOptionPane.showMessageDialog(this, name + " ���� ���̵�� [ " + id + " ]�Դϴ�.");
			dispose();
		}
	}

}
