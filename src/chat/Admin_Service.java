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

	// 대화명, 클라이언트 OutputStream 저장용 대화방(HashMap) 정의
	Map<String, ObjectOutputStream> clients; 
	   
	public Admin_Service(String id, Admin admin) {
		this.id = id;
		this.admin = admin;

		clients = Collections.synchronizedMap( //
	              new HashMap<String, ObjectOutputStream>());
	    
		 
		idLabel = new JLabel(id +" 관리자님");
		idLabel.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		// 버튼
		logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
		logoutBtn.setBorderPainted(false);
		logoutBtn.setContentAreaFilled(false);
		logoutBtn.setOpaque(false);

		// 버튼 생성
		sendBtn = new JButton("보내기", new ImageIcon("lib/pen.gif"));

		// 텍스트
		input = new JTextField(15);
		output = new JTextArea();
		input.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
		output.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));

		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroll);

		// 화면 Text 커서 안나오게 , 입력 안되게 하는 것.
		output.setEditable(false);

		// 위치
		scroll.setBounds(70, 130, 700, 450);
		input.setBounds(70, 600, 700, 30);
		sendBtn.setBounds(780, 600, 100, 30);

		idLabel.setBounds(750, 10, 150, 20);
		logoutBtn.setBounds(900, 10, 67, 25);

		// 추가
		add(input);
		add(scroll);
		add(sendBtn);
		add(idLabel);
		add(logoutBtn);

		// 버튼 이벤트
		logoutBtn.addActionListener(this);

		setLayout(null);
		service();
	}

	public void service() {
		// 소켓 생성
		 
		 
		System.out.println(clients.size());
		 
		try {
			socket = new Socket("192.168.0.20", 9500); //학원
			//socket = new Socket("58.123.1.248", 9500);	//이슬
			
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "현재는 상담 가능 시간이 아닙니다.");
			System.out.println("서버와 연결이 안되어있습니다.");
			e.printStackTrace();
			System.exit(0);
		}

		// [join] 서버로 닉네임 보내기
		try {
			InfoDTO dto = new InfoDTO();
			dto.setCommand(Info.JOIN);
			dto.setNickName("관리자("+id+"):");
			oos.writeObject(dto);
			oos.flush();
	
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 스레드 생성
		Thread th = new Thread(this);

		// 스레드 실행
		th.start();

		// 이벤트
		sendBtn.addActionListener(this);
		input.addActionListener(this); // JTextField에서 엔터

	}

	public void paintComponent(Graphics g) {
		g.drawImage(new ImageIcon("lib/1.png").getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) { // 로그아웃 버튼
			admin.setVisible(false);
			new Login().event();
		} else {
			// [send] 서버로 보내는 쪽
			
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

	// 스레드
	@Override
	public void run() {
		System.out.println(number);
		while (true) {
			try {
				InfoDTO dto; //굳이 new로 생성할 필요없다.
				//서버로부터 받는 쪽
				dto = (InfoDTO) ois.readObject();
				number = "1";
				if(dto == null || dto.getCommand() == Info.EXIT) {
					ois.close();
					oos.close();
					socket.close();
					System.exit(0);
					
				}else if ( dto.getCommand() == Info.JOIN) {
					output.append("["+dto.getNickName() + "] 님이 입장하였습니다." + "\n");
					
					
				}else if ( dto.getCommand() == Info.SEND) {
					output.append("["+dto.getNickName() + "] " + dto.getMessage() +"\n");
				
					//스크롤바 따라가도록 하는 것
					int pos = output.getText().length();
						//스크롤바 위치 값을 잡아주는 함수	
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
