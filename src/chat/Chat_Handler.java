package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Chat_Handler extends Thread {

	private Socket socket;
	private List<Chat_Handler> list;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	// ChatHandler
	public Chat_Handler(Socket socket, List<Chat_Handler> list) throws IOException {
		this.socket = socket;
		this.list = list;

		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		System.out.println("ChatHandler ����");
		
	}// ������


	@Override
	public void run() {
		try {
			InfoDTO dto;
			//Ŭ���̾�Ʈ�� ���� �޴� �� , �׷��Ƿ� new �� �ʿ� ����.
			dto = (InfoDTO) ois.readObject();
			// �г��� ����
			String nickName = dto.getNickName();
			
			// Join ����
			broadcast(dto);
			
			while(true) {
				dto = (InfoDTO) ois.readObject();
				// �г��� �ֱ�
				dto.setNickName(nickName);
				if(dto == null || dto.getCommand() == Info.EXIT) {
					System.out.println("EXIT");
					break;
				} 
				System.out.println(dto.getNickName() +  "(" +dto.getCommand() + ")"  + " : "+ dto.getMessage());
				//�޽����� ��� Ŭ���̾�Ʈ���� ������
				broadcast(dto);
			}
			//while end
			
			// Exit ���� 
			oos.writeObject(dto);
			oos.flush();
			
			
			// ������ ��� ��Ͽ��� ����
			list.remove(this);
			
			// Send (���� ����)
			if ( dto.getCommand() == Info.EXIT) {
				dto.setCommand(Info.SEND);
				dto.setMessage("���� �����Ͽ����ϴ�.");
			}
			
			broadcast(dto);
			ois.close();
			oos.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//run 

	public void broadcast(InfoDTO dto) {
		for(Chat_Handler handler : list) {
			try {
				handler.oos.writeObject(dto);
				handler.oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//for
	}//broadcast

}
