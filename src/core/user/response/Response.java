package core.user.response;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.user.book.Book;
import core.user.book.Inquiry;
import core.user.book.Order;
import core.user.jpanel.BackAlterJPanel;
import core.user.jpanel.BookJPanel;
import core.user.jpanel.MenuJPanel;
import core.user.jpanel.OrderJPanel;
import core.user.jpanel.PersonJPanel;
import core.user.jpanel.WelcomeJPanel;
import other.Global;
import other.Tool;

/*
 * �û����尴ť��Ӧ��
 */
public class Response {
	private JFrame jf;
	private String username;
	public Response(JFrame jf,String username) {
		super();
		this.jf = jf;
		this.username = username;
	}
	
	public void userResponse() {
		MenuJPanel.getJb_book().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					JOptionPane.showMessageDialog(jf, "���ڽ��и�ǩ������");
				}else {
					BookJPanel.getJp_show().setVisible(true);
					PersonJPanel.getJp_show().setVisible(false);
					BackAlterJPanel.getJsp_show().setVisible(false);
				}
			}
		});
		
		//����Ԥ��-��ѯ����
		BookJPanel.getJb_inquiry().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Inquiry.check(jf)) {
					try {
						BookJPanel.getJp_inquiry().setVisible(false);
						BookJPanel.getJp_showTitle().setVisible(true);
						BookJPanel.getJp_showTable().setVisible(true);
						BookJPanel.getJp_showButtom().setVisible(true);
						BookJPanel.getJp_showPassenger().setVisible(false);
						BookJPanel.getJp_showPay().setVisible(false);
						Object[][] arr = Inquiry.submit();		//��ȡ���ݿ�ı��
						if(arr[0][0].equals("���޺���")) {
							for (int i = 0; i < arr[0].length; i++) {
								arr[0][i] = "���޺���";
							}
							BookJPanel.addTable(Tool.arrSort(arr));		//����󡢵�����ӱ�񷽷�
							JOptionPane.showMessageDialog(jf, "���������޺��࣡");
						}else {
							BookJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "����ʾȫ�����࣡");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		//����Ԥ��-ȡ��
		BookJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫȡ����ǩ��?", "��ǩ��ʾ��", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						Global.setAlter(false); //�޸ĸ�ǩ��־
						Order.cancelAlter();
						JOptionPane.showMessageDialog(Global.getJf3(), "��ǩ��ȡ����");
					}
				}else {
					BookJPanel.getJp_show().setVisible(false);
				}
			}
		});
		//�����ѯ-ˢ��
		BookJPanel.getJb_reflush().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Inquiry.check(jf)) {
					try {
						BookJPanel.getJp_inquiry().setVisible(false);
						BookJPanel.getJp_showTitle().setVisible(true);
						BookJPanel.getJp_showTable().setVisible(true);
						BookJPanel.getJp_showButtom().setVisible(true);
						BookJPanel.getJp_showPassenger().setVisible(false);
						BookJPanel.getJp_showPay().setVisible(false);
						Object[][] arr = Inquiry.submit();	
						
						if(arr[0][0].equals("���޺���")) {
							for (int i = 0; i < arr[0].length; i++) {
								arr[0][i] = "���޺���";
							}
							BookJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "���������޺��࣡");
						}else {
							BookJPanel.addTable(Tool.arrSort(arr));
							JOptionPane.showMessageDialog(jf, "����ʾȫ�����࣡");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//�����ѯ-��ѯ����1
		BookJPanel.getJb_inquiryAirport1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Global.getAlter()) {
						JOptionPane.showMessageDialog(jf, "���ڽ��и�ǩ������");
					}else {
						//������
						if(String.valueOf(BookJPanel.jtf_startCity.getText()).trim().equals("")) {
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
								BookJPanel.getJtf_startAirport().setText(airport);
							}
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//�����ѯ-��ѯ����2
		BookJPanel.getJb_inquiryAirport2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Global.getAlter()) {
						JOptionPane.showMessageDialog(jf, "���ڽ��и�ǩ������");
					}else {
						//������
						if(String.valueOf(BookJPanel.jtf_reachCity.getText()).trim().equals("")) {
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
								BookJPanel.getJtf_reachAirport().setText(airport);
							}
						}
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//��������
		BookJPanel.getJb_today().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tool.setToday(BookJPanel.getJtf_time());
			}
		});
		//��ѯԤ��-����
		BookJPanel.getJb_reback().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookJPanel.getJp_inquiry().setVisible(true);
				BookJPanel.getJp_showTitle().setVisible(false);
				BookJPanel.getJp_showTable().setVisible(false);
				BookJPanel.getJp_showButtom().setVisible(false);
				BookJPanel.getJp_showPassenger().setVisible(false);
				BookJPanel.getJp_showPay().setVisible(false);
			}
		});
		//��ѯԤ��-Ԥ������
		BookJPanel.getJb_book().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					
					//��⵽�Ǹ�ǩģʽ
					int flag = Book.cheakFlight(username);
					if(flag==1) {	//�������ͨ��
						//���μ���Ƿ�Ϊͳһ����
						if(!((Tool.getTextValue(BookJPanel.getJtf_handle()).equals(Order.getOldFlight()))
								&& (Tool.getTextValue(BookJPanel.getJtf_time()).equals(Order.getOldTime())))) {
							//��ǩ�ɹ���Ҫ�������²���
							Order.backTicket("��ǩģʽ");	//ԭ��������λ�������ݿ���+1
							Book.setDownSeat();			//ѡ�еĺ�����λ�������ݿ���-1
							Order.updateOrder(); 		//�޸Ķ�����Ϣ
							JOptionPane.showMessageDialog(jf, "��ǩ�ɹ���������ת������ҳ��...");
							Global.setAlter(false); //�޸ĸ�ǩ��־Ϊfalse
							BookJPanel.getJp_show().setVisible(false);
							Order.cancelAlter();	//���ûطǸ�ǩģʽ
							JOptionPane.showMessageDialog(jf, "��ˢ����Ķ�����");
//							Order.getAlter().setText("�Ѹ�ǩ");
							
						}else {
							JOptionPane.showMessageDialog(jf, "��ǩ���º���;ɺ���һ�£����޸ģ�");
						}
						
					}else if(flag==2) {
						JOptionPane.showMessageDialog(jf, "�������ѡ�񺽰�ţ�");
					}else if(flag==3) {
						JOptionPane.showMessageDialog(jf, "��ǰ������Ʊ���㣡");
					}else {
						JOptionPane.showMessageDialog(jf, "δ֪���󣬸�ǩʧ�ܣ�");
					}
				}else {
					// 1�ɹ���2Ϊ�գ�3��λΪ0�ˣ�4δ֪����
					int flag = Book.cheakFlight(username);
					if(flag==1) {	//���ͨ��
						BookJPanel.getJp_inquiry().setVisible(false);
						BookJPanel.getJp_showTitle().setVisible(false);
						BookJPanel.getJp_showTable().setVisible(false);
						BookJPanel.getJp_showButtom().setVisible(false);
						BookJPanel.getJp_showPassenger().setVisible(true);
						BookJPanel.getJp_showPay().setVisible(false);
					}else if(flag==2) {
						JOptionPane.showMessageDialog(jf, "�������ѡ�񺽰�ţ�");
					}else if(flag==3) {
						JOptionPane.showMessageDialog(jf, "��ǰ������Ʊ���㣡");
					}else {
						JOptionPane.showMessageDialog(jf, "δ֪����Ԥ��ʧ�ܣ�");
					}
				}
			}
		});
		//���ز�ѯԤ������
		BookJPanel.getJb_reback2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookJPanel.getJp_inquiry().setVisible(false);
				BookJPanel.getJp_showTitle().setVisible(true);
				BookJPanel.getJp_showTable().setVisible(true);
				BookJPanel.getJp_showButtom().setVisible(true);
				BookJPanel.getJp_showPassenger().setVisible(false);
				BookJPanel.getJp_showPay().setVisible(false);
			}
		});
		//��������Ϣ
		BookJPanel.getJb_useOneSelf().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book.getOneSelf(username);
			}
		});
		//ȥ֧��
		BookJPanel.getJb_toPay().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Book.cheakFull(jf,username)) {
					BookJPanel.getJp_inquiry().setVisible(false);
					BookJPanel.getJp_showTitle().setVisible(false);
					BookJPanel.getJp_showTable().setVisible(false);
					BookJPanel.getJp_showButtom().setVisible(false);
					BookJPanel.getJp_showPassenger().setVisible(false);
					BookJPanel.getJp_showPay().setVisible(true);
					Book.setPrice(); //��֧������ǰ�����ü۸�
				}
			}
		});
		//֧�����淵��
		BookJPanel.getJb_reback3().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookJPanel.getJp_inquiry().setVisible(false);
				BookJPanel.getJp_showTitle().setVisible(false);
				BookJPanel.getJp_showTable().setVisible(false);
				BookJPanel.getJp_showButtom().setVisible(false);
				BookJPanel.getJp_showPassenger().setVisible(true);
				BookJPanel.getJp_showPay().setVisible(false);
			}
		});
		//֧���ɹ�
		BookJPanel.getJb_paid().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Book.insertOrder(username)) {	//���붩����
					Book.setDownSeat();		//��λ�ż�1
					JOptionPane.showMessageDialog(jf, "֧���ɹ����뵽�˸Ĺ���ҳ���ѯ�ɻ�Ʊ������");
					Book.setAllEmpty();
				}else {
					JOptionPane.showMessageDialog(jf, "�Ѵ���Ԥ���˵�ǰ���࣬����ʧ�ܣ���");
				}
				//���س�ʼ����
				BookJPanel.getJp_inquiry().setVisible(true);
				BookJPanel.getJp_showTitle().setVisible(false);
				BookJPanel.getJp_showTable().setVisible(false);
				BookJPanel.getJp_showButtom().setVisible(false);
				BookJPanel.getJp_showPassenger().setVisible(false);
				BookJPanel.getJp_showPay().setVisible(false);
			}
		});
		
		
		//�˵�-�˸Ĺ���
		MenuJPanel.getJb_backAlter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					JOptionPane.showMessageDialog(jf, "���ڽ��и�ǩ������");
				}else {
					BookJPanel.getJp_show().setVisible(false);
					BackAlterJPanel.getJsp_show().setVisible(true);
					PersonJPanel.getJp_show().setVisible(false);
					Global.setOrderCount(OrderJPanel.setReflush(BackAlterJPanel.getJp_show(), username));
					if(Global.getOrderCount() != 0) {
						BackAlterJPanel.getJl_tips().setVisible(false);
					}else {
						BackAlterJPanel.getJl_tips().setVisible(true);
					}
					JOptionPane.showMessageDialog(jf, "��ȡ��ϣ��Ѿ���ʾ"+Global.getOrderCount()+"��������");
				}
			}
		});
		//�˸Ĺ���-ȡ��
		BackAlterJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BackAlterJPanel.getJsp_show().setVisible(false);
			}
		});
		//�˸Ĺ���-ˢ��
		BackAlterJPanel.getJb_reflush().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Global.setOrderCount(OrderJPanel.setReflush(BackAlterJPanel.getJp_show(), username));
				if(Global.getOrderCount() != 0) {
					BackAlterJPanel.getJl_tips().setVisible(false);
				}else {
					BackAlterJPanel.getJl_tips().setVisible(true);
				}
				JOptionPane.showMessageDialog(jf, "ˢ�³ɹ����Ѿ���ʾ"+Global.getOrderCount()+"��������");
			}
		});
		
		//�˵�-������Ϣ
		MenuJPanel.getJb_person().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Global.getAlter()) {
					JOptionPane.showMessageDialog(jf, "���ڽ��и�ǩ������");
				}else {
					BookJPanel.getJp_show().setVisible(false);
					BackAlterJPanel.getJsp_show().setVisible(false);
					PersonJPanel.getJp_show().setVisible(true);
				}
			}
		});
		
		//�򿪸����ֻ���Ȩ��
		PersonJPanel.getJb_alterPhone().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PersonJPanel.getJb_alterPhone().getText().equals("�ݲ�����")) {
					PersonJPanel.getJtf_phone().setEditable(false);
					PersonJPanel.getJtf_phone().setText(PersonJPanel.getPhone());
					PersonJPanel.getJb_alterPhone().setText("�����ֻ���");
				}else {
					PersonJPanel.getJtf_phone().setEditable(true);
					PersonJPanel.getJb_alterPhone().setText("�ݲ�����");
				}
			}
		});
		//�����ֻ���ȡ�ı�������
		PersonJPanel.getJb_alter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PersonJPanel.alterPhone(jf)) {
					JOptionPane.showMessageDialog(jf, "���ֻ����޸ĳɹ���");
					PersonJPanel.getJtf_phone().setEditable(false);
					PersonJPanel.getJb_alterPhone().setText("�����ֻ���");
				}
			}
		});
		//������Ϣ-ȡ��
		PersonJPanel.getJb_cancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonJPanel.getJp_show().setVisible(false);
			}
		});
		//ע��
		WelcomeJPanel.getJb_quit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Global.getJf3().setVisible(false);
				Global.getJf1().setVisible(true);
			}
		});
	}
}
