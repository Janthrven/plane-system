package core.first.response;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.first.jpanel.BottomJPanel;
import core.first.jpanel.LoginJPanel;
import core.first.jpanel.RegisterJPanel;
import core.first.login.Login;
import core.first.register.Register;
import other.Global;

/**
 * 
 * - ��ť��Ӧ��
 * 
 * @author İ����
 *
 */
public class Response {

	private JFrame jf;
	
	public Response(JFrame jf) {
		super();
		this.jf = jf;
	}

	public void firstResponse() {
		
		JPanel jp_login = LoginJPanel.getJp_login();
		JPanel jp_register = RegisterJPanel.getJp_register();
		
		//�ײ��û���¼��ť��Ӧ
		BottomJPanel.getJb_bo_userLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_login.setVisible(true);
				jp_register.setVisible(false);
			}
		});
		
		//�ײ��û�ע����Ӧ
		BottomJPanel.getJb_bo_register().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_login.setVisible(false);
				jp_register.setVisible(true);
			}
		});
		
		//��¼���---ȡ����¼��ť
		LoginJPanel.getJb_lo_cancelLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_login.setVisible(false);
			}
		});
		
		//ע�����---ȡ��ע�ᰴť
		RegisterJPanel.getJb_re_cancelRegister().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jp_register.setVisible(false);
			}
		});
		
		//ע�����---ע�ᰴť
		RegisterJPanel.getJb_re_register().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Register(jf);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//��¼���--��¼��ť
		LoginJPanel.getJb_lo_login().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Login(jf);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
