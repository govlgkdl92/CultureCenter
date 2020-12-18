package chat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class Chat_Client extends JFrame implements ActionListener, Runnable {
   private JTextArea output;
   private JTextField input;
   private JButton sendBtn;

   private Socket socket;
   
   private ObjectInputStream ois;
   private ObjectOutputStream oos;
   
   Chat_Server list;
   
   private String id;

   // 대화명, 클라이언트 OutputStream 저장용 대화방(HashMap) 정의
   Map<String, ObjectOutputStream> clients; 
   
   public Chat_Client(String id) {
      this.id = id;

      clients = Collections.synchronizedMap( //
              new HashMap<String, ObjectOutputStream>());
    
      // 버튼 생성
      sendBtn = new JButton(new ImageIcon("lib/pen.png"));

      // 텍스트
      input = new JTextField(15);
      output = new JTextArea();
      input.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
      output.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
      
      // 화면 Text 커서 안나오게 , 입력 안되게 하는 것.
      output.setEditable(false);

      JPanel area = new JPanel();
      area.setLayout(new BorderLayout());
      area.add("Center", input);
      area.add("East", sendBtn);

      JScrollPane scroll = new JScrollPane(output);
      scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

      Container c = this.getContentPane();
      c.add("Center", scroll);
      c.add("South", area);

      // 창 띄우기
      setTitle("채팅봇");
      setBounds(400, 300, 400, 400);
      setVisible(true);
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

      // 종료 이벤트
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
        	 //closeSocket();
        	 InfoDTO dto = new InfoDTO();
				dto.setCommand(Info.EXIT);
					try {
						oos.writeObject(dto);
						oos.flush();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
            // 소켓은 응답을 받고 함께 끊어야 한다.한쪽만 끊을 시 상대방이 계속 추적하기때문에 에러가 걸림.
         }
      });
      
      service();

   }// 생성자

   public void service() {
      // 소켓 생성
      try {
         // localhost는 컴퓨터 네트워크에서 사용하는 루프백 호스트명으로, 자신의 컴퓨터를 의미한다.
         // https://ko.wikipedia.org/wiki/Localhost
    	// 소켓 생성
         socket = new Socket("127.0.0.1", 9500); //학원
 
         //socket = new Socket("58.123.1.248", 9500);   //이슬집
         ois = new ObjectInputStream(socket.getInputStream());
		 oos = new ObjectOutputStream(socket.getOutputStream());
         
      } catch (UnknownHostException e) {
         System.out.println("서버를 찾을 수 없습니다.");
         e.printStackTrace();
         setVisible(false);
         
      } catch (IOException e) {
         JOptionPane.showMessageDialog(this, "현재는 상담 가능 시간이 아닙니다.");
         setVisible(false);
         System.out.println("서버와 연결이 안되어있습니다.");
         e.printStackTrace();
      }

      // [join] 서버로 닉네임 보내기
   		try {
   			InfoDTO dto = new InfoDTO();
   			dto.setCommand(Info.JOIN);
   			dto.setNickName(id);
   			oos.writeObject(dto);
   			oos.flush();
   		
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   		
   		// [join] end

      // 스레드 생성
      Thread th = new Thread(this);

      // 스레드 실행
      th.start();

      // 이벤트
      sendBtn.addActionListener(this);
      input.addActionListener(this); // JTextField에서 엔터

   }


   
   // ActionListener
   @Override
   public void actionPerformed(ActionEvent e) {
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
		// [send] end

   }// actionPerformed

   // 스레드
   @Override
   public void run() {
  
      while (!socket.isClosed()) {
         try {
        	InfoDTO dto; //굳이 new로 생성할 필요없다.
			//서버로부터 받는 쪽
			dto = (InfoDTO) ois.readObject();
	
			if(dto == null || dto.getCommand() == Info.EXIT) {
				ois.close();
				oos.close();
				socket.close();
				setVisible(false);

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

   // Socket Close
   public void closeSocket() {
      try {
         if (ois != null) ois.close();
         if (oos != null) oos.close();
         if (!socket.isClosed()) {
            socket.close();            
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   

}