package admin.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import teacher.bean.TeacherDTO;
import teacher.dao.TeacherDAO;
import cultureCenter.Login;
import mapping.bean.MappingDTO;
import mapping.dao.MappingDAO;

@SuppressWarnings("all")
public class Admin_Teacher extends JPanel implements ActionListener, ListSelectionListener {
   private String id, code;
   private JLabel idLabel, titleL, teacherListL, tevalL;
   
   private JTable teacherListT;
   private List<TeacherDTO> list;
   private Vector<String> vector, vectorE;
   private DefaultTableModel model;
   private DefaultListModel<TeacherDTO> listModel;

   private JTextField tCode;
   private JButton logoutBtn, modifyBtn, deleteBtn, regBtn, refreshBtn;
   private Admin admin;

   private JList<TeacherDTO> Elist; 
   private DefaultListModel<TeacherDTO> Emodel;

   private JList<MappingDTO> EvalList;
   private DefaultListModel<MappingDTO> EvalModel;
  

   public Admin_Teacher(String id, Admin admin) {
      this.id = id;
      this.admin = admin;

      
      //강사 Table
      TeacherDAO dao = TeacherDAO.getInstance();
      list = dao.getTeacherList();

      vector = new Vector<String>();
      vector.add("강사코드");
      vector.add("강사이름");
      vector.add("성별");
      vector.add("전화번호");
      vector.add("이메일");
      vector.add("담당과목");

      model = new DefaultTableModel(vector, 0) {
         @Override
         public boolean isCellEditable(int r, int c) {
            return false;
         }
      };
      listModel = new DefaultListModel<TeacherDTO>();
         
      for (TeacherDTO dto : list) {
         Vector<String> vector = new Vector<String>();
         vector.add(dto.gettCode());
         vector.add(dto.gettName());
         
         String gender = new String();
         if(dto.gettGender() == 1) { gender = "여성"; }
         else if(dto.gettGender() == 0) { gender = "남성"; }
         
         vector.add(gender);
         
         String tel1 = dto.gettPhone().substring(0, 3);
            String tel2 = dto.gettPhone().substring(3, 7);
            String tel3 = dto.gettPhone().substring(7, 11);
            String phone = (tel1+" - "+tel2+" - "+tel3);
            vector.add(phone);

         vector.add(dto.gettEmail());
         vector.add(dto.getType());
         model.addRow(vector);
         listModel.addElement(dto);
      }
 
    
      // JLabel
      idLabel = new JLabel(id + " 관리자님");
      idLabel.setFont(new Font("한컴 백제 M", Font.PLAIN, 15));
      titleL = new JLabel("강사 관리");
      titleL.setFont(new Font("한컴 백제 M", Font.PLAIN, 35));
      teacherListL = new JLabel("강사정보편집");
      teacherListL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));
      tevalL = new JLabel("강사평가조회");
      tevalL.setFont(new Font("한컴 백제 M", Font.PLAIN, 20));

      // JTable
      teacherListT = new JTable(model);
      JScrollPane tableScrollIncluded = new JScrollPane(teacherListT);
      
      teacherListT.setRowHeight(20);
      teacherListT.setFont(new Font("한컴 백제 M", Font.PLAIN, 16));
      
      teacherListT.getColumnModel().getColumn(0).setPreferredWidth(40);  
      teacherListT.getColumnModel().getColumn(1).setPreferredWidth(60);
      teacherListT.getColumnModel().getColumn(2).setPreferredWidth(40);
      teacherListT.getColumnModel().getColumn(3).setPreferredWidth(130);
      teacherListT.getColumnModel().getColumn(4).setPreferredWidth(130);
      teacherListT.getColumnModel().getColumn(5).setPreferredWidth(60);
      
      tCode = new JTextField();
     
    //Jlist 생성
   Emodel = new DefaultListModel<TeacherDTO>();
   Elist = new JList<TeacherDTO>(Emodel);
   Elist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   Elist.setFont(new Font("HY견고딕", Font.PLAIN, 12));
   
            
   // DB
   TeacherDAO Edao = TeacherDAO.getInstance();
   
   //모든 레코드를 꺼내서 JList에 넣기
   List<TeacherDTO> dtoEvalList = Edao.getTeacherList();
   
   for(TeacherDTO Edto : dtoEvalList) {
      Emodel.addElement(Edto);
   }
   
   //
     JScrollPane eevalList = new JScrollPane(Elist);
   eevalList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

   
       //평가리스트
      EvalModel = new DefaultListModel<MappingDTO>();
      EvalList = new JList<MappingDTO>(EvalModel);
      EvalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      EvalList.setFont(new Font("HY견고딕", Font.PLAIN, 12));
        
      JScrollPane TEvalList = new JScrollPane(EvalList);
      TEvalList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);  
  
      // 버튼
      logoutBtn = new JButton(new ImageIcon("lib/logoutBtn.png"));
      logoutBtn.setBorderPainted(false);
      logoutBtn.setContentAreaFilled(false);
      logoutBtn.setOpaque(false);
      regBtn = new JButton("강사신규등록");
      regBtn.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));
      modifyBtn = new JButton("강사정보수정");
      modifyBtn.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));
      deleteBtn = new JButton("강사정보삭제");  
      deleteBtn.setFont(new Font("한컴 백제 M", Font.PLAIN, 17));
      
      refreshBtn= new JButton("새로 고침");
      refreshBtn.setContentAreaFilled(false);
      refreshBtn.setFont(new Font("한컴 백제 M", Font.PLAIN, 10));
      refreshBtn.setForeground(Color.gray);
      
      // 위치     
      idLabel.setBounds(750, 10, 150, 20);
      logoutBtn.setBounds(900, 10, 67, 25);
      refreshBtn.setBounds(867, 105, 80, 20);
      titleL.setBounds(30, 10, 150, 60);
      teacherListL.setBounds(30, 80, 150, 50);
      tableScrollIncluded.setBounds(30, 130, 920, 280);
      
      regBtn.setBounds(500, 420, 120, 30);
      modifyBtn.setBounds(650, 420, 120, 30);
      deleteBtn.setBounds(800, 420, 120, 30);
      
      tevalL.setBounds(30, 430, 150, 50);
      eevalList.setBounds(30, 480, 150, 200);
      TEvalList.setBounds(200, 480, 750, 200);
      tCode.setBounds(200, 690, 80, 20);
      
      // 추가
      add(tCode);
      add(idLabel);
      add(logoutBtn);
      add(titleL);
      add(teacherListL);
      
      add(tableScrollIncluded);
      
      add(eevalList);
      add(TEvalList);
      add(regBtn);
      add(modifyBtn);
      add(deleteBtn);
      add(tevalL);
      add(refreshBtn);

      // 버튼 이벤트
      logoutBtn.addActionListener(this);
      
      regBtn.addActionListener(this);
      regBtn.setContentAreaFilled(false);
      regBtn.setOpaque(false);
      
      modifyBtn.addActionListener(this);
      modifyBtn.setContentAreaFilled(false);
      modifyBtn.setOpaque(false);
      
      deleteBtn.addActionListener(this);
      deleteBtn.setContentAreaFilled(false);
      deleteBtn.setOpaque(false);
      refreshBtn.addActionListener(this);
      Elist.addListSelectionListener(this);

      Elist.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
             TeacherDTO E = Elist.getSelectedValue();
             
             E.settCode(E.gettCode());;

          }
       });

      setLayout(null);
      

   }

   private void delete() {
      TeacherDAO dao = TeacherDAO.getInstance();
      int selectedRow = teacherListT.getSelectedRow();
      int deleteTeacher = JOptionPane.showConfirmDialog(this, "정말 강사정보를 삭제하시겠습니까?", "강사정보삭제",
            JOptionPane.YES_NO_OPTION);
      if (deleteTeacher == JOptionPane.YES_OPTION) {
         if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "선택된 강사가 없습니다");
            return;
         }
         String code = (String) teacherListT.getValueAt(selectedRow, 0);
         MappingDAO daoM = MappingDAO.getInstance();
         daoM.dropTeacherArticle(code);
         dao.dropArticle(code);
         model.removeRow(selectedRow);
         JOptionPane.showMessageDialog(this, "삭제완료");
      }

   }

   private void insert() {
      
     // 강사코드 검증 
      String tcode = JOptionPane.showInputDialog(this, "강사코드를 입력하세요");
      if (tcode == null || tcode == "-1" || tcode.length() <= 0) {
        JOptionPane.showInputDialog(this, "수정할 강사코드가 선택되지 않았습니다.");
         return;
      }
      for (int i = 0; i < model.getRowCount(); i++) { // 중복검사
         if (tcode.equals(model.getValueAt(i, 0))) {
            JOptionPane.showMessageDialog(this, "사용중인 강사코드 입니다");
            return;
         }
      }
      
      // 강사이름 검증
      String tname = JOptionPane.showInputDialog(this, "강사이름을 입력하세요");
      if (tname == null  || tname == "-1" || tname.length() <= 0) {
        JOptionPane.showMessageDialog(this, "강사이름은 필수항목입니다");
         return;
      }
      
      // 성별 검증
      String gender = "";
      String tgender = JOptionPane.showInputDialog(this, "성별을 입력하세요 (여성은 1, 남성은 0)");
      int genderIntVal = 0;
      if (tgender == null  || tgender == "-1" ) {
        JOptionPane.showInputDialog(this, "성별이 선택되지 않았습니다.");
         return;
      } else {
         genderIntVal = Integer.parseInt(tgender);
          if (genderIntVal == 1) { gender = "여성"; }
          else if (genderIntVal == 0) { gender = "남성"; }
          else {
             JOptionPane.showMessageDialog(this, "성별을 정확하게 입력하지 않았습니다.");
             return;
          }
      }
      
      // 휴대폰 검증 ( 숫자만 입력, 10 ~ 11자리 )
      String tphone = JOptionPane.showInputDialog(this, "전화번호를 입력하세요");
      String tel1 = "", tel2 = "", tel3 = "", phone = "";
      if (tphone == null  || tphone == "-1" ) {
         return;
      } else {
         // 휴대폰 자리수 검증
          if (!(tphone.length() >= 10 && tphone.length() <= 11)) {
             JOptionPane.showMessageDialog(this, "입력된 휴대폰 번호를 확인해주세요.");
             return;
          } else {
             tphone = tphone.replaceAll("[^0-9]","");
             tel1 = tphone.substring(0, 3);
             tel2 = tphone.substring(3, 7);
             tel3 = tphone.substring(7, 11);
             phone = tel1 + " - " + tel2 + " - " + tel3;
          }
      }
      
      // 이메일 검증
      String temail = JOptionPane.showInputDialog(this, "이메일을 입력하세요");
      if (temail == null  || temail == "-1" ) {
         return;
      }
      
      // 담당과목 검증
      String type = JOptionPane.showInputDialog(this, "담당과목을 입력하세요");
      if (type == null  || type == "-1" || type.length() <= 0) {
        JOptionPane.showMessageDialog(this, "담당과목은 필수항목입니다");
         return;
      }

      TeacherDTO dto = new TeacherDTO();
      dto.settCode(tcode);
      dto.settName(tname);
      dto.settGender(Integer.parseInt(tgender));
      dto.settPhone(tphone);
      dto.settEmail(temail);
      dto.setType(type);
      TeacherDAO dao = TeacherDAO.getInstance();
      dao.insertArticle(dto);

      Vector<String> v = new Vector<String>();
      v.add(tcode);
      v.add(tname);
      v.add(gender);
      v.add(phone);
      v.add(temail);
      v.add(type);
      model.addRow(v);

      JOptionPane.showMessageDialog(this, "강사를 추가했습니다");
   }

   private void modify() {
      String tcode = JOptionPane.showInputDialog(this, "수정할 강사코드를 입력하세요");
      TeacherDTO dto = new TeacherDTO();
      dto.settCode(tcode);
      TeacherDAO dao = TeacherDAO.getInstance();
      int sw = dao.overlapArticle(dto);
      
      // 강사코드 검증
      if (tcode == null  || tcode == "-1" || tcode.length() <= 0) {
    	  JOptionPane.showMessageDialog(this, "수정할 강사코드가 선택되지 않았습니다.");
          return;
      }else if(sw == 0) {
    	  JOptionPane.showMessageDialog(this, "입력하신 강사 정보가 없습니다.");
          return;
      }else if(sw == 1) {
    	  new Admin_Teacher_Apply(tcode);  
      }
      
      
      
   }// modify 끝

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
      } else if (e.getSource() == regBtn) { // 신규등록
         insert();
      } else if (e.getSource() == modifyBtn) { // 수정
         modify();
      } else if (e.getSource() == deleteBtn) { // 삭제
         delete();
      }else if (e.getSource() == refreshBtn) { // 평가 보내기 버튼
		 refresh();	
	  }
   }

   //새로고침
   private void refresh() {
	   model.setRowCount(0);
	   //강사 Table
	   TeacherDAO dao = TeacherDAO.getInstance();
	   list = dao.getTeacherList();
	      
	   listModel = new DefaultListModel<TeacherDTO>();
       
	   for (TeacherDTO dto : list) {
         Vector<String> vector = new Vector<String>();
         vector.add(dto.gettCode());
         vector.add(dto.gettName());
         
         String gender = new String();
         if(dto.gettGender() == 1) { gender = "여성"; }
         else if(dto.gettGender() == 0) { gender = "남성"; }
         
         vector.add(gender);
         
         String tel1 = dto.gettPhone().substring(0, 3);
            String tel2 = dto.gettPhone().substring(3, 7);
            String tel3 = dto.gettPhone().substring(7, 11);
            String phone = (tel1+" - "+tel2+" - "+tel3);
            vector.add(phone);

         vector.add(dto.gettEmail());
         vector.add(dto.getType());
         model.addRow(vector);
         listModel.addElement(dto);
	   }
   } // 새로고침
   
   
   @Override
   public void valueChanged(ListSelectionEvent e) {
      //tEvalmodel.removeRow(row);
         EvalModel.removeAllElements();
         TeacherDTO dto = Elist.getSelectedValue();

       if(Elist.getSelectedIndex() == -1) return;
   
       tCode.setText(dto.gettCode());
       
          // DB
          MappingDAO daoM = MappingDAO.getInstance();

          
          //모든 레코드를 꺼내서 JList에 넣기
          List<MappingDTO> dtoList = daoM.getEvalList(tCode.getText());
          
          for(MappingDTO dtoL : dtoList) {
             EvalModel.addElement(dtoL);             
          }
   }

}