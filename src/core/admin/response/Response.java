package core.admin.response;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.admin.input.Input;
import core.admin.inquiry.Inquiry;
import core.admin.jpanel.InputJPanel;
import core.admin.jpanel.InquiryJPanel;
import core.admin.jpanel.MenuJPanel;
import core.admin.jpanel.StatisticsJPanel;
import core.admin.jpanel.WelcomeJPanel;
import other.Global;
import other.Tool;

/**
 * - admin����Ա���水ť����
 * @author İ����
 *
 */

public class Response {
	
	private JFrame jf;
	public Response(JFrame jf) {
		super();
		this.jf = jf;
	}
	
	public void adminResponse() {
		//�˵�-�����ѯ
		MenuJPanel.getJb_inquiry().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_show().setVisible(true);
				InputJPanel.getJp_show().setVisible(false);
				StatisticsJPanel.getJp_show().setVisible(false);
			}
		});
		//�˵�-����¼��
		MenuJPanel.getJb_input().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_show().setVisible(false);
				InputJPanel.getJp_show().setVisible(true);
				StatisticsJPanel.getJp_show().setVisible(false);
			}
		});

		//�˵�-ͳ�Ʊ���
		MenuJPanel.getJb_statistics().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_show().setVisible(false);
				InputJPanel.getJp_show().setVisible(false);
				StatisticsJPanel.getJp_show().setVisible(true);
			}
		});
		
		
		//�����ѯ-��ѯ����
		InquiryJPanel.getJb_inquiry().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Inquiry.check(jf)) {
					try {
						InquiryJPanel.getJp_inquiry().setVisible(false);
						InquiryJPanel.getJp_showTitle().setVisible(true);
						InquiryJPanel.getJp_showTable().setVisible(true);
						InquiryJPanel.getJp_showButtom().setVisible(true);
						Object[][] arr = Inquiry.submit();
						if(arr[0][0].equals("���޺���")) {
							for (int i = 0; i < arr[0].length; i++) {
								arr[0][i] = "���޺���";
							}
							InquiryJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "���������޺��࣡");
						}else {
							InquiryJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "����ʾȫ�����࣡");
						}
						InquiryJPanel.getTable().setEnabled(true);
						InquiryJPanel.getTable().getSelectionModel().clearSelection();	//���ѡ���к��˳��༭
						InquiryJPanel.getTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
						//�жϱ��״̬������ˢ��ǰ��״̬
						if(InquiryJPanel.getJrb_alter().isSelected()) {
							InquiryJPanel.getJtf_handle().setEditable(false);
							InquiryJPanel.getTable().setEnabled(true);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//�����ѯ-ȡ��
		InquiryJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_show().setVisible(false);
			}
		});
		//�����ѯ-ˢ��
		InquiryJPanel.getJb_reflush().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Inquiry.check(jf)) {
					try {
						InquiryJPanel.getJp_inquiry().setVisible(false);
						InquiryJPanel.getJp_showTitle().setVisible(true);
						InquiryJPanel.getJp_showTable().setVisible(true);
						InquiryJPanel.getJp_showButtom().setVisible(true);
						
						Object[][] arr = Inquiry.submit();
						
						if(arr[0][0].equals("���޺���")) {
							for (int i = 0; i < arr[0].length; i++) {
								arr[0][i] = "���޺���";
							}
							InquiryJPanel.addTable(arr);
							JOptionPane.showMessageDialog(jf, "���������޺��࣡");
						}else {
							InquiryJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "����ʾȫ�����࣡");
						}
						InquiryJPanel.getTable().setEnabled(true);
						InquiryJPanel.getTable().getSelectionModel().clearSelection();	//���ѡ���к��˳��༭
						InquiryJPanel.getTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
						//�жϱ��״̬������ˢ��ǰ��״̬
						if(InquiryJPanel.getJrb_alter().isSelected()) {
							InquiryJPanel.getJtf_handle().setEditable(false);
							InquiryJPanel.getTable().setEnabled(true);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//�����ѯ-����
		InquiryJPanel.getJb_reback().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJp_showTitle().setVisible(false);
				InquiryJPanel.getJp_showTable().setVisible(false);
				InquiryJPanel.getJp_showButtom().setVisible(false);
				InquiryJPanel.getJp_inquiry().setVisible(true);
			}
		});
		//�����ѯ-��ѯ����1
		InquiryJPanel.getJb_inquiryAirport1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(String.valueOf(InquiryJPanel.jtf_startCity.getText()).trim().equals("")) {
						JOptionPane.showMessageDialog(jf, "������������У�");
					}else {
						List<String> airportlist = Inquiry.getAirport(Inquiry.getStartCity());
						String airport = "";
						if(airportlist.size()==0) {
							JOptionPane.showMessageDialog(jf, "��δ��ѯ���ó��еĻ�����");
						}else {
							String msg = "��Ϊ���ѯ��"+airportlist.size()+"��������";
							for (String s : airportlist) {
								msg+=s+";";
								airport = s;
							}
							JOptionPane.showMessageDialog(jf, msg+"��Ϊ���Զ����һ��������");
							InquiryJPanel.getJtf_startAirport().setText(airport);
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//�����ѯ-��ѯ����2
		InquiryJPanel.getJb_inquiryAirport2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(String.valueOf(InquiryJPanel.jtf_reachCity.getText()).trim().equals("")) {
						JOptionPane.showMessageDialog(jf, "������������У�");
					}else {
						List<String> airportlist = Inquiry.getAirport(Inquiry.getReachCity());
						String airport = "";
						if(airportlist.size()==0) {
							JOptionPane.showMessageDialog(jf, "��δ��ѯ���ó��еĻ�����");
						}else {
							String msg = "��Ϊ���ѯ��"+airportlist.size()+"��������";
							for (String s : airportlist) {
								msg+=s+";";
								airport = s;
							}
							JOptionPane.showMessageDialog(jf, msg+"��Ϊ���Զ����һ��������");
							InquiryJPanel.getJtf_reachAirport().setText(airport);
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//��������
		InquiryJPanel.getJb_today().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tool.setToday(InquiryJPanel.getJtf_time());
			}
		});
		//�����ѯ-��ѡ��-ɾ��
		InquiryJPanel.getJrb_delete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJrb_delete().setSelected(true);
				if(InquiryJPanel.getJrb_delete().isSelected()) {
					InquiryJPanel.getJrb_alter().setSelected(false);
					InquiryJPanel.getJb_handle().setText("ɾ������");
					InquiryJPanel.getJtf_handle().setEditable(true);
					InquiryJPanel.getTable().setEnabled(true);
					InquiryJPanel.getTable().getSelectionModel().clearSelection();	//���ѡ���к��˳��༭
					InquiryJPanel.getTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
					JOptionPane.showMessageDialog(jf, "��ѡ��Ϊɾ��������������ɾ���ĺ���ţ�");
				}
			}
		});
		//�����ѯ-��ѡ��-�޸�
		InquiryJPanel.getJrb_alter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InquiryJPanel.getJrb_alter().setSelected(true);
				if(InquiryJPanel.getJrb_alter().isSelected()) {
					InquiryJPanel.getJrb_delete().setSelected(false);
					InquiryJPanel.getJb_handle().setText("�޸ĺ���");
					InquiryJPanel.getJtf_handle().setEditable(false);
					InquiryJPanel.getTable().setEnabled(true);
					InquiryJPanel.getTable().getSelectionModel().clearSelection();	//���ѡ���к��˳��༭
					InquiryJPanel.getTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
					JOptionPane.showMessageDialog(jf, "��ѡ��Ϊ�޸ģ���ֱ���ڱ�����޸ģ�����Ų�֧���޸ģ���");
				}
			}
		});
		
		//�����ѯ-ɾ�����޸Ĵ���
		InquiryJPanel.getJb_handle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inquiry.handle(jf);
			}
		});
		
		
		//����¼��-ȷ��¼��
		InputJPanel.getJb_input().addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(new Input(jf).cheak()) {
					try {
						new Input(jf).submit();
						JOptionPane.showMessageDialog(jf, "��¼��һ�����࣡");
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(jf, "¼��ʧ�ܣ���ǰ�����Ѵ��ڸú��࣬������¼�룡");
					}
				}
			}
		});
		//����¼��-ȡ��
		InputJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InputJPanel.getJp_show().setVisible(false);
			}
		});
		
		//ͳ�Ʊ���-ȡ��
		StatisticsJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StatisticsJPanel.getJp_show().setVisible(false);
			}
		});
		
		//ע��
		WelcomeJPanel.getJb_quit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Global.getJf2().setVisible(false);
				Global.getJf1().setVisible(true);
			}
		});
	}
}
