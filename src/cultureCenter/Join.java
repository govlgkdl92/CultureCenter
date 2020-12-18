package cultureCenter;

import java.awt.Color;
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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@SuppressWarnings("all")
public class Join extends JFrame implements ActionListener {

	private JLabel ExL, titleL, idL, pwL, pwOL, nameL, birthL, genderL, phoneNumL, phoneNumExL, emailL, emailVL,
			emailExL, interestL, interestExL;
	private JTextField idT, nameT, birthT, phoneNumT, emailT, emailVT;
	private JPasswordField pwT, pwOT;
	private JButton confirmBtn, cancelBtn, overlapBtn, verifyBtn, verifyOKBtn;
	private JRadioButton woman, man;
	private JCheckBox health, song, book, carpent, cook, art;
	private BufferedImage img = null;

	private JList<MemberDTO> list;
	private DefaultListModel<MemberDTO> model;

	private String random;
	private int mailox;
	
	public Join() {

		// 라벨 생성
		ExL = new JLabel(" * 은 필수 입력 사항입니다.");
		titleL = new JLabel("회원 가입");
		idL = new JLabel("*아    이    디");
		pwL = new JLabel("*비  밀  번  호");
		pwOL = new JLabel("*비밀 번호 확인");
		nameL = new JLabel(" 이          름 ");
		birthL = new JLabel("*생  년  월  일");
		genderL = new JLabel("*성          별");
		phoneNumL = new JLabel(" 전  화  번  호");
		phoneNumExL = new JLabel("'-'없이 적어주세요.  ex) 01012345678");
		emailL = new JLabel("*이 메 일  주소");
		emailExL = new JLabel("주소전체를 적어주세요. ex) bit123@bitcamp.com");
		emailVL = new JLabel(" 이 메 일  확인");
		interestL = new JLabel(" 관    심    사 ");
		interestExL = new JLabel("* 관심 분야에 모두 체크해주세요.");

		// 텍스트 필드 생성
		idT = new JTextField();
		nameT = new JTextField();
		birthT = new JTextField();
		phoneNumT = new JTextField("010");
		emailT = new JTextField();
		emailVT = new JTextField();

		// 텍스트페스워드
		pwT = new JPasswordField();
		pwT.setEchoChar('●');
		pwOT = new JPasswordField();
		pwOT.setEchoChar('●');
		// 버튼 생성
		confirmBtn = new JButton(new ImageIcon("lib/join.png"));
		confirmBtn.setBorderPainted(false);
		confirmBtn.setContentAreaFilled(false);
		confirmBtn.setOpaque(false);

		cancelBtn = new JButton(new ImageIcon("lib/cancel.png"));
		cancelBtn.setBorderPainted(false);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setOpaque(false);

		overlapBtn = new JButton(new ImageIcon("lib/overlap.png"));
		overlapBtn.setBorderPainted(false);
		overlapBtn.setContentAreaFilled(false);
		overlapBtn.setOpaque(false);

		verifyBtn = new JButton(new ImageIcon("lib/verify.png"));
		verifyBtn.setBorderPainted(false);
		verifyBtn.setContentAreaFilled(false);
		verifyBtn.setOpaque(false);

		// 라디오버튼 생성
		woman = new JRadioButton("여성", true);
		man = new JRadioButton("남성");
		woman.setBackground(new Color(250, 250, 250));
		man.setBackground(new Color(250, 250, 250));

		ButtonGroup button = new ButtonGroup();
		button.add(woman);
		button.add(man);

		// 체크박스 생성
		health = new JCheckBox("운동");
		health.setBackground(new Color(250, 250, 250));
		song = new JCheckBox("노래");
		song.setBackground(new Color(250, 250, 250));
		book = new JCheckBox("독서");
		book.setBackground(new Color(250, 250, 250));
		carpent = new JCheckBox("목공");
		carpent.setBackground(new Color(250, 250, 250));
		cook = new JCheckBox("요리");
		cook.setBackground(new Color(250, 250, 250));
		art = new JCheckBox("예술");
		art.setBackground(new Color(250, 250, 250));

		// 크기잡기
		titleL.setBounds(170, 10, 150, 50);
		titleL.setFont(new Font("한컴 백제 M", Font.BOLD, 30));

		ExL.setBounds(10, 65, 200, 15);
		ExL.setFont(new Font("한컴 백제 M", Font.PLAIN, 13));

		idL.setBounds(10, 80, 150, 50);
		idT.setBounds(150, 95, 200, 30);
		idL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		idT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		overlapBtn.setBounds(360, 95, 70, 30);

		pwL.setBounds(10, 120, 150, 50);
		pwT.setBounds(150, 135, 200, 30);
		pwL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		pwT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		pwOL.setBounds(10, 160, 150, 50);
		pwOT.setBounds(150, 175, 200, 30);
		pwOL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		pwOT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		nameL.setBounds(10, 200, 150, 50);
		nameT.setBounds(150, 215, 200, 30);
		nameL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		nameT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		birthL.setBounds(10, 240, 150, 50);
		birthT.setBounds(150, 255, 200, 30);
		birthL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		birthT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		genderL.setBounds(10, 280, 150, 50);
		woman.setBounds(150, 295, 100, 30);
		genderL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		woman.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		man.setBounds(250, 295, 200, 30);
		man.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		phoneNumL.setBounds(10, 320, 150, 50);
		phoneNumT.setBounds(150, 335, 250, 30);
		phoneNumL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		phoneNumT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		phoneNumExL.setBounds(150, 365, 250, 20);
		phoneNumExL.setFont(new Font("한컴 백제 M", Font.PLAIN, 12));

		emailL.setBounds(10, 380, 150, 50);
		emailL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		emailT.setBounds(150, 395, 250, 30);
		emailT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		emailVL.setBounds(10, 435, 150, 50);
		emailVL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		emailVT.setBounds(150, 450, 150, 30);
		emailVT.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		verifyBtn.setBounds(405, 395, 70, 30);

		emailExL.setBounds(150, 425, 350, 20);
		emailExL.setFont(new Font("한컴 백제 M", Font.PLAIN, 12));

		interestL.setBounds(10, 490, 150, 50);
		interestL.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		health.setBounds(150, 495, 70, 30);
		song.setBounds(230, 495, 70, 30);
		health.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		song.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		book.setBounds(310, 495, 70, 30);
		carpent.setBounds(150, 525, 70, 30);
		book.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		carpent.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		cook.setBounds(230, 525, 70, 30);
		art.setBounds(310, 525, 70, 30);
		cook.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		art.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		interestExL.setBounds(150, 540, 300, 50);
		interestExL.setFont(new Font("한컴 백제 M", Font.PLAIN, 12));

		confirmBtn.setBounds(120, 580, 140, 60);
		cancelBtn.setBounds(280, 580, 90, 60);
		confirmBtn.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		cancelBtn.setFont(new Font("한컴 백제 M", Font.BOLD, 18));

		// 창에 추가
		Container c = this.getContentPane();
		c.add(titleL);
		c.add(ExL);
		c.add(idL);
		c.add(idT);
		c.add(overlapBtn);
		c.add(pwL);
		c.add(pwT);
		c.add(pwOL);
		c.add(pwOT);
		c.add(nameL);
		c.add(nameT);
		c.add(birthL);
		c.add(birthT);
		c.add(genderL);
		c.add(woman);
		c.add(man);
		c.add(phoneNumL);
		c.add(phoneNumT);
		c.add(phoneNumExL);
		c.add(emailL);
		c.add(emailT);
		c.add(emailExL);
		c.add(emailVL);
		c.add(emailVT);
		c.add(verifyBtn);
		c.add(interestL);
		c.add(health);
		c.add(song);
		c.add(book);
		c.add(carpent);
		c.add(cook);
		c.add(art);
		c.add(interestExL);
		c.add(confirmBtn);
		c.add(cancelBtn);
		c.setBackground(new Color(250, 250, 250));

		// 배경 Panel
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setSize(500, 700);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("lib/flower.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
			System.exit(0);
		}
		// 이미지 삽입
		bgPanel panel = new bgPanel();
		panel.setSize(500, 800);
		layeredPane.add(panel);
		add(layeredPane);

		// Jlist 생성
		model = new DefaultListModel<MemberDTO>();
		list = new JList<MemberDTO>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 창 종료
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exit = JOptionPane.showConfirmDialog(Join.this, "취소 하시겠습니까?", "회원가입 취소", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (exit == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});// 이벤트

		// 버튼 이벤트
		confirmBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		overlapBtn.addActionListener(this);
		verifyBtn.addActionListener(this);

		// 창
		setTitle("비트문화센터 회원가입");
		setLayout(null); // 초기값이던 동서남북 Layout을 깨버린다
		setBounds(300, 200, 500, 700);
		setVisible(true);
		setResizable(false);

		// 모든 레코드를 꺼내서 JList에 넣기
		MemberDAO dao = MemberDAO.getInstance();
		List<MemberDTO> dtoList = dao.getMemberList();

		for (MemberDTO dto : dtoList) {
			model.addElement(dto);
		}

	}

	class bgPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	int overlapSw = -1;

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmBtn) { // 확인버튼
			 confirm();
	
		} else if (e.getSource() == cancelBtn) { // 취소버튼
			int exit = JOptionPane.showConfirmDialog(Join.this, "취소 하시겠습니까?", "회원가입 취소", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (exit == JOptionPane.YES_OPTION) {
				dispose();
			}
			
		} else if (e.getSource() == overlapBtn) { // 중복확인
			if (idT.getText().equals("") || idT.getText().equals(null)) {
				JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.");
			} else {
				overlapArticle();

			}
			
		} else if (e.getSource() == verifyBtn) { // 이메일인증
			random = Integer.toString((int)(Math.random()*1000000));
			
			if (emailT.getText().equals("") || emailT.getText().equals(null)) {
				JOptionPane.showMessageDialog(this, "이메일을 입력해주세요.");
			} else {
				overlapMailArticle();
				
				if (mailox == 1) {
					JOptionPane.showMessageDialog(this, "이미 가입하신 이메일입니다.");
				} else if(mailox == 0) {
					random = Integer.toString((int)(Math.random()*1000000));
					
					JOptionPane.showMessageDialog(this, "해당 메일에 인증 번호를 전송하였습니다.\n인증번호를 '이메일 확인'란에 작성해주세요.");
					new SendEmail(emailT.getText(), random);
				}
			}
		}

	}

	//가입 시 조건
	private void confirm() {
		if (idT.getText().equals("") || idT.getText().equals(null) || pwT.getText().equals("")
				|| pwT.getText().equals(null) || birthT.getText().equals("") || birthT.getText().equals(null)
				|| emailT.getText().equals("") || emailVT.getText().equals("") || emailT.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "필수항목을 입력해주세요");
			
		} else { // DB에 회원정보 전송
			
			if (overlapSw != 0) {
				JOptionPane.showMessageDialog(this, "아이디 중복 확인이 필요합니다.");
				
			} else if(birthT.getText().length() != 6 ) {
				JOptionPane.showMessageDialog(this, "6자리의 생년월일을 입력해주세요.");
				
			} else if(!pwT.getText().equals(pwOT.getText()) ) {
				JOptionPane.showMessageDialog(this, "비밀 번호가 일치하지 않습니다.");
				
			} else if(pwT.getText().length() < 8 ) {
				JOptionPane.showMessageDialog(this, "비밀번호는 8자리 이상으로 입력해주세요.");
				
			} else if(phoneNumT.getText().length() != 11 && phoneNumT.getText().length() != 0 ) {
				JOptionPane.showMessageDialog(this, "핸드폰 번호의 자릿수가 올바르지 않습니다.");
				
			} else if(emailVT.getText().equals(random)) {
				joinArticle();
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "올바른 이메일 인증이 필요합니다.");
			}
		}
	}//가입 시 조건 end

	// 메일 중복
	private int overlapMailArticle() {
		//데이터 
		String mail = emailT.getText();
		
		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setEmail(mail);

		// DB
		MemberDAO dao = MemberDAO.getInstance();
		mailox = dao.emailConfirmArticle(dto);
		
		
		return mailox;
	}

		
	// 아이디 중복
	private int overlapArticle() {
		// 데이터
		String id = idT.getText();

		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		overlapSw = dao.overlapArticle(dto);

		if (overlapSw == 0) {
			JOptionPane.showMessageDialog(this, "사용 가능한 아이디입니다.");
		} else if (overlapSw == 1) {
			JOptionPane.showMessageDialog(this, "이미 사용중인 아이디입니다.");
		}

		return overlapSw;
	}

	private void joinArticle() {
		// 데이터
		String id = idT.getText();
		String pw = pwT.getText();
		String name = nameT.getText();
		String birth = birthT.getText();
		int gender = 0;
		if (man.isSelected())
			gender = 0;
		else if (woman.isSelected())
			gender = 1;
		String phone = phoneNumT.getText();
		String email = emailT.getText();

		int health = this.health.isSelected() ? 1 : 0;
		int song = this.song.isSelected() ? 1 : 0;
		int book = this.book.isSelected() ? 1 : 0;
		int carpent = this.carpent.isSelected() ? 1 : 0;
		int cook = this.cook.isSelected() ? 1 : 0;
		int art = this.art.isSelected() ? 1 : 0;

		// MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setBirth(birth);
		dto.setGender(gender);
		dto.setPhone(phone);
		dto.setEmail(email);
		dto.setHealth(health);
		dto.setSong(song);
		dto.setBook(book);
		dto.setCarpent(carpent);
		dto.setCook(cook);
		dto.setArt(art);

		// DB
		MemberDAO dao = MemberDAO.getInstance();

		dao.joinArticle(dto);

		// 응답
		model.addElement(dto);

		clear(); // 초기화
		JOptionPane.showMessageDialog(this, "가입 완료되었습니다.");
		setVisible(false);
	}

	private void clear() {
		idT.setText("");
		pwT.setText("");
		pwOT.setText("");
		nameT.setText("");

		birthT.setText("");
		woman.setSelected(true);
		phoneNumT.setText("");
		emailT.setText("");
		emailVT.setText("");
		health.setSelected(false);
		song.setSelected(false);
		book.setSelected(false);
		carpent.setSelected(false);
		cook.setSelected(false);
		art.setText("");
	}

}
