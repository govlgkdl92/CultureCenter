package chat;

import java.io.Serializable;

enum Info {
	JOIN, EXIT, SEND
}

public class InfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nickName;
	private String message;
	private Info command;
	
	//게터
	public String getNickName() {
		return nickName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Info getCommand() {
		return command;
	}
	
	//세터
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setCommand(Info command) {
		this.command = command;
	}
	
}
