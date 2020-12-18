package admin.action;

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

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import teacher.bean.TeacherDTO;
import teacher.dao.TeacherDAO;

@SuppressWarnings("all")
public class Admin_Teacher_Apply extends JFrame implements ActionListener {
   private JLabel codeL, nameL, phoneL, emailL, genderL, typeL, codeT;
   private JTextField nameT, phoneT, emailT, typeT;
   private JRadioButton male, female;
   private JButton applyBtn, cancelBtn;
   private String tcode;
   private BufferedImage img = null;

   public Admin_Teacher_Apply(String tcode) {
	  this.tcode = tcode;
	  
	  TeacherDTO dto = new TeacherDTO();
      dto.settCode(tcode);
      
      TeacherDAO dao = TeacherDAO.getInstance();
      
      dto = dao.teacherInfoArticle(dto);
	   
	  codeL = new JLabel("강사코드 : ");
      nameL = new JLabel("이    름 : ");
      genderL = new JLabel("성    별 : ");
      phoneL = new JLabel("전화번호 : ");
      emailL = new JLabel("이 메 일 : ");
      typeL = new JLabel("담당과목 : ");

      codeT = new JLabel(dto.gettCode());
      nameT = new JTextField(dto.gettName());
      phoneT = new JTextField(dto.gettPhone());
      emailT = new JTextField(dto.gettEmail());
      typeT = new JTextField(dto.getType());

      male = new JRadioButton("남성");
      female = new JRadioButton("여성");
      ButtonGroup button = new ButtonGroup();
      button.add(male);
      button.add(female);
      if (dto.gettGender() == 0) {
         male.setSelected(true);
      } else if (dto.gettGender() == 1) {
         female.setSelected(true);
      }

      applyBtn = new JButton("수정하기");
      applyBtn.setContentAreaFilled(false);
      cancelBtn = new JButton("취소");
      cancelBtn.setContentAreaFilled(false);
      
      codeL.setBounds(20, 20, 120, 50);
      codeL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
      codeT.setBounds(130, 30, 150, 30);
      nameL.setBounds(20, 70, 120, 50);
      nameL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
      nameT.setBounds(130, 80, 150, 30);
      genderL.setBounds(20, 120, 120, 50);
      genderL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
      male.setBounds(130, 130, 60, 30);
      male.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
      male.setBackground(new Color(250, 250, 250));
      female.setBounds(200, 130, 60, 30);
      female.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
      female.setBackground(new Color(250, 250, 250));
      phoneL.setBounds(20, 170, 120, 50);
      phoneL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
      phoneT.setBounds(130, 180, 150, 30);
      emailL.setBounds(20, 220, 120, 50);
      emailL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
      emailT.setBounds(130, 230, 150, 30);
      typeL.setBounds(20, 270, 120, 30);
      typeL.setFont(new Font("한컴 백제 M", Font.PLAIN, 25));
      typeT.setBounds(130, 270, 150, 30);

      applyBtn.setBounds(40, 340, 100, 30);
      cancelBtn.setBounds(160, 340, 100, 30);

      Container container = this.getContentPane();
      container.add(codeL);
      container.add(nameL);
      container.add(genderL);
      container.add(phoneL);
      container.add(emailL);
      container.add(typeL);
      container.add(codeT);
      container.add(nameT);
      container.add(phoneT);
      container.add(emailT);
      container.add(typeT);
      container.add(male);
      container.add(female);
      container.add(applyBtn);
      container.add(cancelBtn);

      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setSize(500, 500);
      layeredPane.setLayout(null);

      try {
         img = ImageIO.read(new File("lib/1.png"));
      } catch (IOException e) {
         JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
         System.exit(0);
      }

      bgPanel panel = new bgPanel();
      panel.setSize(500, 500);
      layeredPane.add(panel);
      add(layeredPane);

      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            int exit = JOptionPane.showConfirmDialog(Admin_Teacher_Apply.this, "취소 하시겠습니까?", "강사정보수정취소",
                  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (exit == JOptionPane.YES_OPTION) {
               dispose();
            }
         }
      });

      setTitle("강사정보수정");
      setLayout(null);
      setBounds(300, 200, 320, 430);
      setVisible(true);
      setResizable(false);

      applyBtn.addActionListener(this);
      cancelBtn.addActionListener(this);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (applyBtn == e.getSource()) {
    	  change();
    	  JOptionPane.showMessageDialog(this, "강사정보를 수정했습니다");
    	  setVisible(false);
      } else if (cancelBtn == e.getSource()) {
         int exit = JOptionPane.showConfirmDialog(Admin_Teacher_Apply.this, "취소 하시겠습니까?", "강사정보수정 취소",
             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         if (exit == JOptionPane.YES_OPTION) {
        	 dispose();
         }
      }
   }

   //정보 변경 
   private void change() {
	   TeacherDTO dto = new TeacherDTO();
	   dto.settCode(tcode);
	   dto.settName(nameT.getText());
	   dto.settPhone(phoneT.getText());
	   dto.settEmail(emailT.getText());
	   dto.setType(typeT.getText());

	   int gender = 0;
	   if( male.isSelected() ) {
		   gender = 0;
	   }else if(female.isSelected()) {
		   gender = 1;
	   }   
	   dto.settGender(gender);

		if (nameT.getText().equals("") || (nameT.getText()) == null) {
			JOptionPane.showMessageDialog(this, "입력하신 이름이 없습니다");
		} else if(phoneT.getText().equals("") || (phoneT.getText()) == null){
			JOptionPane.showMessageDialog(this, "입력하신 전화번호가 없습니다");	
		} else if(emailT.getText().equals("") || (emailT.getText()) == null){
			JOptionPane.showMessageDialog(this, "입력하신 메일이 없습니다");	
		} else if(typeT.getText().equals("") || (typeT.getText()) == null){
			JOptionPane.showMessageDialog(this, "입력하신 강의 유형이 없습니다");	
		} else {
			// DB
			TeacherDAO dao = TeacherDAO.getInstance();
			dao.changeArticle(dto);
		}
	   
	   
	   
   }//정보 변경 end

class bgPanel extends JPanel {
      public void paint(Graphics g) {
         g.drawImage(img, 0, 0, null);
      }
   }

}