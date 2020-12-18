package chat;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import admin.action.Admin;
import cultureCenter.Login;

@SuppressWarnings("all")
public class Admin_Service extends JPanel implements ActionListener, Runnable {
	private JTextArea output;
	private JTextField input;
	private JButton sendBtn;

	private Socket socket;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private String id;
	static String number = "0";
	   
	private JLabel idLabel;
	private JButton logoutBtn;
	private Admin admin;

	// ��ȭ��, Ŭ���̾�Ʈ OutputStream ����� ��ȭ��(HashMap) ����
	Map<String, ObjectOutputStream> clients; 
	   
	public Admin_Service(String id, Admin admin) {
		this.id = id;
		this.admin = admin;

		clients = Collections.synchronizedMap( //
	              new HashMap<String, ObjectOutputStream>());
	    
		 
		idLabel = new JLabel(id +" �����ڴ�");
		idLabel.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		// ��ư
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		// ��ư ����
		sendBtn = new JButton("������", new ImageIcon("lib/pen.gif"));

		// �ؽ�Ʈ
		input = new JTextField(15);
		output = new JTextArea();
		input.setFont(new Font("���� ���� M", Font.PLAIN, 20));
		output.setFont(new Font("���� ���� M", Font.PLAIN, 15));

		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroll);

		// ȭ�� Text Ŀ�� �ȳ����� , �Է� �ȵǰ� �ϴ� ��.
		output.setEditable(false);

		// ��ġ
		scroll.setBounds(70, 130, 700, 450);
		input.setBounds(70, 600, 700, 30);
		sendBtn.setBounds(780, 600, 100, 30);

		idLabel.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);

		// �߰�
		add(input);
		add(scroll);
		add(sendBtn);
		add(idLabel);
		add(logoutBtn);

		// ��ư �̺�Ʈ
		logoutBtn.addActionListener(this);

		setLayout(null);
		service();
	}

	public void service() {
		// ���� ����
		 
		 
		System.out.println(clients.size());
		 
		try {
			socket = new Socket("192.168.0.20", 9500); //�п�
			//socket = new Socket("58.123.1.248", 9500);	//�̽�
			
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e) {
			System.out.println("������ ã�� �� �����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "����� ��� ���� �ð��� �ƴմϴ�.");
			System.out.println("������ ������ �ȵǾ��ֽ��ϴ�.");
			e.printStackTrace();
			System.exit(0);
		}

		// [join] ������ �г��� ������
		try {
			InfoDTO dto = new InfoDTO();
			dto.setCommand(Info.JOIN);
			dto.setNickName("������("+id+"):");
			oos.writeObject(dto);
			oos.flush();
	
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ������ ����
		Thread th = new Thread(this);

		// ������ ����
		th.start();

		// �̺�Ʈ
		sendBtn.addActionListener(this);
		input.addActionListener(this); // JTextField���� ����

	}

	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { // �α׾ƿ� ��ư
			admin.setVisible(false);
			new Login().event();
		} else {
			// [send] ������ ������ ��
			
			InfoDTO dto = new InfoDTO();
			String msg= input.getText();
			
			dto.setCommand(Info.SEND);	
			dto.setMessage(msg);
			try {
				oos.writeObject(dto);
				oos.flush();
			} catch(IOException e1) {
				e1.printStackTrace();
			}
			
			input.setText("");

		} // actionPerformed
	}

	// ������
	@Override
	public void run() {
		System.out.println(number);
		while (true) {
			try {
				InfoDTO dto; //���� new�� ������ �ʿ����.
				//�����κ��� �޴� ��
				dto = (InfoDTO) ois.readObject();
				number = "1";
				if(dto == null || dto.getCommand() == Info.EXIT) {
					ois.close();
					oos.close();
					socket.close();
					System.exit(0);
					
				}else if ( dto.getCommand() == Info.JOIN) {
					output.append("["+dto.getNickName() + "] ���� �����Ͽ����ϴ�." + "\n");
					
					
				}else if ( dto.getCommand() == Info.SEND) {
					output.append("["+dto.getNickName() + "] " + dto.getMessage() +"\n");
				
					//��ũ�ѹ� ���󰡵��� �ϴ� ��
					int pos = output.getText().length();
						//��ũ�ѹ� ��ġ ���� ����ִ� �Լ�	
					output.setCaretPosition(pos);

				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} // while

	}// run()
}
