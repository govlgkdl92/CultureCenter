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
		System.out.println("ChatHandler 생성");
		
	}// 생성자


	@Override
	public void run() {
		try {
			InfoDTO dto;
			//클라이언트로 부터 받는 쪽 , 그러므로 new 할 필요 없다.
			dto = (InfoDTO) ois.readObject();
			// 닉네임 저장
			String nickName = dto.getNickName();
			
			// Join 전송
			broadcast(dto);
			
			while(true) {
				dto = (InfoDTO) ois.readObject();
				// 닉네임 넣기
				dto.setNickName(nickName);
				if(dto == null || dto.getCommand() == Info.EXIT) {
					System.out.println("EXIT");
					break;
				} 
				System.out.println(dto.getNickName() +  "(" +dto.getCommand() + ")"  + " : "+ dto.getMessage());
				//메시지를 모든 클라이언트에게 보내기
				broadcast(dto);
			}
			//while end
			
			// Exit 전송 
			oos.writeObject(dto);
			oos.flush();
			
			
			// 퇴장한 사람 목록에서 삭제
			list.remove(this);
			
			// Send (퇴장 전송)
			if ( dto.getCommand() == Info.EXIT) {
				dto.setCommand(Info.SEND);
				dto.setMessage("님이 퇴장하였습니다.");
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
