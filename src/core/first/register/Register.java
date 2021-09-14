package core.first.register;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.database.Database;
import core.first.jpanel.LoginJPanel;
import core.first.jpanel.RegisterJPanel;
import other.Tool;

/**
 * -��ʼ��Ϣ������
 * @author İ����
 *
 */
public class Register {
	
	private JFrame jf = null;
	private JTextField jtf_re_username = RegisterJPanel.getJtf_re_username();
	private JPasswordField jpf_re_password1 = RegisterJPanel.getJtf_re_password1();
	private JPasswordField jpf_re_password2 = RegisterJPanel.getJtf_re_password2();
	private JTextField jtf_re_name = RegisterJPanel.getJtf_re_name();
	private JTextField jtf_re_id = RegisterJPanel.getJtf_re_id();
	private JTextField jtf_re_phone = RegisterJPanel.getJtf_re_phone();
	private UserBank ub; 
	
	public Register(JFrame jf) throws ClassNotFoundException, SQLException {
		super();
		this.jf = jf;
		getContent();
		check();
	}
	
	
	//������������
	public void getContent() throws ClassNotFoundException, SQLException {
		ub = new UserBank(
			Tool.getTextValue(jtf_re_username),
			Tool.getTextValue(jpf_re_password1),
			Tool.getTextValue(jpf_re_password2),
			Tool.getTextValue(jtf_re_name),
			Tool.getTextValue(jtf_re_id),
			Tool.getTextValue(jtf_re_phone)
		);
	}
	
	//����ֶκͼ�����ݿ��Ƿ������ͬ�û�������ͨ������д�����ݿ�
	public void check() throws SQLException, ClassNotFoundException {
		
		//��һ�μ��
		if(Check.checkAll(jf, ub)) {
			//ͨ��
			Database db = new Database();
			ResultSet rs = db.getSelect("select * from user1");
			
			boolean secondcheck = true;		//�ڶ��μ����
			while(rs.next()) {
				//����Ѿ������˺ţ��򲻲������ݿ�
				if(ub.getUsername().equals(rs.getString("username"))) {
					JOptionPane.showMessageDialog(jf, "���˺���ע�ᣬ��ֱ�ӵ�¼��");
					rs.last();  //������������ص�
					secondcheck = false;
					break;
				}
			}
			//�ڶ��μ��ͨ����˵����ע�ᣬ����룡
			if(secondcheck) {
				db.setUpdate("INSERT INTO USER1 (username,password,name,id,phone) VALUES "
						+ "('" + ub.getUsername() + "','" + ub.getPassword1() + "','"+ ub.getName() + "','"+ ub.getId() +"','"+ub.getPhone()+"');" );
				JOptionPane.showMessageDialog(jf, "��ϲ�㣬ע��ɹ���");
				//ע��ɹ�����ת��¼����
				LoginJPanel.getJp_login().setVisible(true);
				RegisterJPanel.getJp_register().setVisible(false);
				
				//�����ı���
				RegisterJPanel.resetAll();
			}
		}
	}
}
