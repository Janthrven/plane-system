package core.first.jpanel;

import java.awt.Color;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import other.AddComonents;
/**
 * -��ʼ�����ע�����
 * -�ṩ�ı�������Ļ�ȡ����
 * -�ṩ�ı�����������ý��㷽��
 * -�ṩ�ı��������������շ���
 * @author İ����
 *
 */
public class RegisterJPanel {
	
	private static JPanel jp_register;
	
	private static JLabel jl_introduce;
	private static JLabel jl_re_username;
	private static JLabel jl_re_password1;
	private static JLabel jl_re_password2;
	private static JLabel jl_re_name;
	private static JLabel jl_re_id;
	private static JLabel jl_re_phone;
	private static JTextField jtf_re_username;
	private static JPasswordField jtf_re_password1;
	private static JPasswordField jtf_re_password2;
	private static JTextField jtf_re_name;
	private static JTextField jtf_re_id;
	private static JTextField jtf_re_phone;
	private static JButton jb_re_cancelRegister;
	private static JButton jb_re_register;

	
	//�����������
	public static void resetAll() {
		jtf_re_username.setText("");
		jtf_re_password1.setText("");
		jtf_re_password2.setText("");
		jtf_re_name.setText("");
		jtf_re_id.setText("");
	}
	
	public static JPanel init(JFrame jf) {
		jp_register = AddComonents.addJPanel(jf, 225, 100, 400, 300);
		jp_register.setVisible(false);
		jp_register.setBorder(BorderFactory.createLineBorder(Color.white, 4));
		
		jl_introduce = AddComonents.addJLabel("�û�ע��", jp_register, 170, 2, 150, 30);
		jl_re_username = AddComonents.addJLabel("�û�����", jp_register, 50, 40, 60, 25);
		jtf_re_username = AddComonents.addJTextField(jp_register, 130, 40, 200, 25);
		
		jl_re_password1 = AddComonents.addJLabel("�ܡ��룺", jp_register, 50, 70, 90, 25);
		jtf_re_password1 = AddComonents.addJPasswordField(jp_register, 130, 70, 200, 25);
		
		jl_re_password2 = AddComonents.addJLabel("ȷ�����룺", jp_register, 50, 100, 80, 25);
		jtf_re_password2 = AddComonents.addJPasswordField(jp_register, 130, 100, 200, 25);
		
		jl_re_name = AddComonents.addJLabel("��ʵ������", jp_register, 50, 130, 80, 25);
		jtf_re_name = AddComonents.addJTextField(jp_register, 130, 130, 200, 25);
		
		jl_re_id = AddComonents.addJLabel("���֤�ţ�", jp_register, 50, 160, 80, 25);
		jtf_re_id = AddComonents.addJTextField(jp_register, 130, 160, 200, 25);
		
		jl_re_phone = AddComonents.addJLabel("�ֻ��ţ�", jp_register, 50, 190, 90, 25);
		jtf_re_phone = AddComonents.addJTextField(jp_register, 130, 190, 200, 25);
		
		jb_re_cancelRegister = AddComonents.addJButton("ȡ��", jp_register, 75, 240, 100, 40, false, true, false);
		jb_re_register = AddComonents.addJButton("ע��", jp_register, 230, 240, 100, 40, false, true, false);
		
		return jp_register;
	}
	
	//��ö�Ӧ�����-��ť
	public static JPanel getJp_register() {
		return jp_register;
	}
	
	public static JButton getJb_re_cancelRegister() {
		return jb_re_cancelRegister;
	}
	
	public static JButton getJb_re_register() {
		return jb_re_register;
	}
	
	//��ö�Ӧ�����-�ı���
	public static JTextField getJtf_re_username() {
		return jtf_re_username;
	}
	
	public static JPasswordField getJtf_re_password1() {
		return jtf_re_password1;
	}
	
	public static JPasswordField getJtf_re_password2() {
		return jtf_re_password2;
	}
	
	public static JTextField getJtf_re_name() {
		return jtf_re_name;
	}
	
	public static JTextField getJtf_re_id() {
		return jtf_re_id;
	}
	
	public static JTextField getJtf_re_phone() {
		return jtf_re_phone;
	}

	//���ý���
	public static void setJtf_re_usernameFocus() {
		jtf_re_username.requestFocus();
	}
	public static void setJtf_re_password1Focus() {
		jtf_re_password1.requestFocus();
	}
	public static void setJtf_re_password2Focus() {
		jtf_re_password2.requestFocus();
	}
	public static void setJtf_re_nameFocus() {
		jtf_re_name.requestFocus();
	}
	public static void setJtf_re_idFocus() {
		jtf_re_id.requestFocus();
	}
	public static void setJtf_re_phoneFocus() {
		jtf_re_phone.requestFocus();
	}
}
