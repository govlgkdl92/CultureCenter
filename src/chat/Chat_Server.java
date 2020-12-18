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
			System.out.println("서버 준비 완료");

			while (true) {
				Socket socket = ss.accept(); // 클라이언트 낚아채기
				// handler 스레드 생성
				Chat_Handler handler = new Chat_Handler(socket, list);
				list.add(handler);
				System.out.println("사이즈: "+list.size());
				
				// handler 스레드 실행
				handler.start();
				System.out.println("클라이언트 접속");
			} // while
		} catch (IOException e) {
			e.printStackTrace();
		} // try catch

	}// 생성자

	public static void main(String[] args) {
		new Chat_Server();
	}

}
