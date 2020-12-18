package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Chat_Server {
	private ServerSocket ss;
	private List<Chat_Handler> list;

	public List<Chat_Handler> getList() {
		return list;
	}

	public void setList(List<Chat_Handler> list) {
		this.list = list;
	}

	public Chat_Server() {
		try {
			ss = new ServerSocket(9500);
			list = new ArrayList<Chat_Handler>();
			System.out.println("���� �غ� �Ϸ�");

			while (true) {
				Socket socket = ss.accept(); // Ŭ���̾�Ʈ ����ä��
				// handler ������ ����
				Chat_Handler handler = new Chat_Handler(socket, list);
				list.add(handler);
				System.out.println("������: "+list.size());
				
				// handler ������ ����
				handler.start();
				System.out.println("Ŭ���̾�Ʈ ����");
			} // while
		} catch (IOException e) {
			e.printStackTrace();
		} // try catch

	}// ������

	public static void main(String[] args) {
		new Chat_Server();
	}

}
