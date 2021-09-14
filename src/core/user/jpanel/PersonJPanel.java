package core.user.jpanel;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.database.Database;
import other.AddComonents;
import other.Tool;

public class PersonJPanel {
	private static Color c = new Color(205,235,234);
	private static JPanel jp_show;
	private static JLabel jl_head;
	private static JLabel jl_line1;
	private static JLabel jl_name;
	private static JTextField jtf_name;
	private static JLabel jl_identity;
	private static JTextField jtf_identity;
	private static JLabel jl_phone;
	private static JTextField jtf_phone;
	private static JButton jb_alterPhone;
	private static Object jl_line2;
	
	private static String name;
	private static String id;
	private static String phone;
	private static JButton jb_alter;
	
	public static String username;
	private static JButton jb_cancel;
	
	public static void init(JFrame jf,String tusername) {
		username = tusername;
		jp_show = AddComonents.addJPanel(jf, 150, 78, 693, 445); jp_show.setBackground(c);
		jp_show.setBorder(BorderFactory.createLineBorder(Color.black, 1));jp_show.setVisible(false);
		jl_head = AddComonents.addJLabel("������Ϣ", jp_show, 300, 10, 100, 30);
		jb_cancel = AddComonents.addJButton("ȡ��", jp_show, 620, 11, 60, 30, true, true, false);
		jl_line1 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_show, 0, 30, 800, 30);
		
		jl_name = AddComonents.addJLabel("��֤������", jp_show, 180, 110, 100, 30);
		jtf_name = AddComonents.addJTextField(jp_show, 250, 110, 150, 30);jtf_name.setEditable(false);
		jl_identity = AddComonents.addJLabel("��֤��ݣ�", jp_show, 180, 160, 100, 30);
		jtf_identity = AddComonents.addJTextField(jp_show, 250, 160, 150, 30);jtf_identity.setEditable(false);
		jl_phone = AddComonents.addJLabel("��֤�ֻ���", jp_show, 180, 210, 100, 30);
		jtf_phone = AddComonents.addJTextField(jp_show, 250, 210, 150, 30);jtf_phone.setEditable(false);
		
		jb_alterPhone = AddComonents.addJButton("�����ֻ���", jp_show, 420, 210, 100, 30, true, true, false);
		jl_line2 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_show, 0, 315, 800, 30);
		jb_alter = AddComonents.addJButton("�޸�", jp_show, 275, 365, 120, 50, false, true, false);
		getUserInfo();
	}
	
	//ͨ���û������������ݿ����û���Ϣ
	public static void getUserInfo() {
		ResultSet rs = null;
		try {
			rs = new Database().getSelect("select name,id,phone from user1 where username='"+username+"';");
			rs.next();
			phone = rs.getString("phone");
			id = rs.getString("id");
			name = rs.getString("name");
			jtf_name.setText(name);
			jtf_identity.setText(id);
			jtf_phone.setText(phone);
		} catch (SQLException e) {
			System.out.println("��ѯ�û���Ϣʧ�ܣ�");
		}finally {
			if(rs!=null) {
				try {
					rs.last();
				} catch (SQLException e) {
					System.out.println("��ѯ�û���Ϣ�����ر���Դʧ�ܣ�");
				}
			}
		}
	}
	
	public static boolean alterPhone(JFrame jf) {
		String jtfContent = Tool.getTextValue(jtf_phone);	//��ȡ�ı�������
		if(jtfContent.equals(phone)) {
			JOptionPane.showMessageDialog(jf, "�޸�ʧ�ܣ��ֻ����ޱ䶯��");
			jtf_phone.setEditable(false);
			jb_alterPhone.setText("�����ֻ���");
			return false;
		}
		if(jtfContent.equals("")) {
			JOptionPane.showMessageDialog(jf, "�ֻ��Ų���Ϊ�գ����������룡");
			return false;
		}else if(!jtfContent.matches("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")) {
			JOptionPane.showMessageDialog(jf, "�ֻ��Ÿ�ʽ����ȷ�����������룡");
			return false;
		}else {
			try {
				new Database().setUpdate("update user1 set phone='"+jtfContent+"' where username='"+username+"';");
				return true;
			} catch (SQLException e) {
				System.out.println("�޸��ֻ���ʧ�ܣ�");
			}
		}
		return false;
		
	}
	
	public static String getPhone() {
		return phone;
	}

	public static JPanel getJp_show() {
		return jp_show;
	}
	public static JTextField getJtf_name() {
		return jtf_name;
	}
	public static JTextField getJtf_identity() {
		return jtf_identity;
	}
	public static JTextField getJtf_phone() {
		return jtf_phone;
	}
	public static JButton getJb_alterPhone() {
		return jb_alterPhone;
	}
	public static JButton getJb_alter() {
		return jb_alter;
	}
	public static JButton getJb_cancel() {
		return jb_cancel;
	}
	
}
