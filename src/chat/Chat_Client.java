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

   // ��ȭ��, Ŭ���̾�Ʈ OutputStream ����� ��ȭ��(HashMap) ����
   Map<String, ObjectOutputStream> clients; 
   
   public Chat_Client(String id) {
      this.id = id;

      clients = Collections.synchronizedMap( //
              new HashMap<String, ObjectOutputStream>());
    
      // ��ư ����
      sendBtn = new JButton(new ImageIcon("lib/pen.png"));

      // �ؽ�Ʈ
      input = new JTextField(15);
      output = new JTextArea();
      input.setFont(new Font("���� ���� M", Font.PLAIN, 20));
      output.setFont(new Font("���� ���� M", Font.PLAIN, 15));
      
      // ȭ�� Text Ŀ�� �ȳ����� , �Է� �ȵǰ� �ϴ� ��.
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

      // â ����
      setTitle("ä�ú�");
      setBounds(400, 300, 400, 400);
      setVisible(true);
      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

      // ���� �̺�Ʈ
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
            // ������ ������ �ް� �Բ� ����� �Ѵ�.���ʸ� ���� �� ������ ��� �����ϱ⶧���� ������ �ɸ�.
         }
      });
      
      service();

   }// ������

   public void service() {
      // ���� ����
      try {
         // localhost�� ��ǻ�� ��Ʈ��ũ���� ����ϴ� ������ ȣ��Ʈ������, �ڽ��� ��ǻ�͸� �ǹ��Ѵ�.
         // https://ko.wikipedia.org/wiki/Localhost
    	// ���� ����
         socket = new Socket("127.0.0.1", 9500); //�п�
 
         //socket = new Socket("58.123.1.248", 9500);   //�̽���
         ois = new ObjectInputStream(socket.getInputStream());
		 oos = new ObjectOutputStream(socket.getOutputStream());
         
      } catch (UnknownHostException e) {
         System.out.println("������ ã�� �� �����ϴ�.");
         e.printStackTrace();
         setVisible(false);
         
      } catch (IOException e) {
         JOptionPane.showMessageDialog(this, "����� ��� ���� �ð��� �ƴմϴ�.");
         setVisible(false);
         System.out.println("������ ������ �ȵǾ��ֽ��ϴ�.");
         e.printStackTrace();
      }

      // [join] ������ �г��� ������
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

      // ������ ����
      Thread th = new Thread(this);

      // ������ ����
      th.start();

      // �̺�Ʈ
      sendBtn.addActionListener(this);
      input.addActionListener(this); // JTextField���� ����

   }


   
   // ActionListener
   @Override
   public void actionPerformed(ActionEvent e) {
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
		// [send] end

   }// actionPerformed

   // ������
   @Override
   public void run() {
  
      while (!socket.isClosed()) {
         try {
        	InfoDTO dto; //���� new�� ������ �ʿ����.
			//�����κ��� �޴� ��
			dto = (InfoDTO) ois.readObject();
	
			if(dto == null || dto.getCommand() == Info.EXIT) {
				ois.close();
				oos.close();
				socket.close();
				setVisible(false);

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