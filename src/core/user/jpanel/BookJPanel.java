package core.user.jpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import other.AddComonents;
import other.Ui;

public class BookJPanel {
private static JPanel jp_show;	//һ�����
	
	private static JLabel jl_head;
	private static JLabel jl_line1;
	private static JLabel jl_line2;
	
	private static JPanel jp_inquiry;		//����
	private static JLabel jl_startCity ;
	public static JTextField jtf_startCity;
	private static JLabel jl_startAirport;
	private static JTextField jtf_startAirport;
	private static JLabel jl_reachCity;
	public static JTextField jtf_reachCity;
	private static JLabel jl_reachAirport;
	private static JTextField jtf_reachAirport;
	private static JLabel jl_time;
	private static JTextField jtf_time;
	private static JButton jb_inquiryAirport1;
	private static JButton jb_inquiryAirport2;
	private static JLabel jl_tips;
	private static JButton jb_inquiry;
	private static JButton jb_reflush;
	private static JButton jb_reback;
	private static JButton jb_cancel;
	
	private static JPanel jp_showTitle;
	private static JLabel jl_showInfo;
	private static JPanel jp_showTable;		//����
	private static JTable table;
	private static String selectedFlight = null;
	
	private static JPanel jp_showButtom;	//����
	private static JLabel jl_handle;
	private static JTextField jtf_handle;
	private static JButton jb_book;
	
	private static Color c = new Color(205,235,234);

	private static JPanel jp_showPassenger;		//����
	private static JButton jb_reback2;
	private static JLabel jl_head2;
	private static JLabel jl_line3;
	private static JLabel jl_name;
	private static JTextField jtf_name;
	private static JLabel jl_identity;
	private static JTextField jtf_identity;
	private static JLabel jl_phone;
	private static JTextField jtf_phone;
	private static JLabel jl_line4;
	private static JButton jb_useOneSelf;
	private static JButton jb_toPay;

	private static JPanel jp_showPay;	//����֧�����
	private static JButton jb_reback3;
	private static JLabel jl_head3;
	private static JLabel jl_line5;
	private static JLabel jl_line6;

	private static JPanel jp_wechat;
	private static JPanel jp_alipay;
	private static JLabel jl_paytips;
	private static JButton jb_paid;
	private static JLabel jl_money;

	private static JButton jb_today;



	public static void init(JFrame jf) {
		jp_show = AddComonents.addJPanel(jf, 150, 78, 693, 445); jp_show.setBackground(c);
		jp_show.setBorder(BorderFactory.createLineBorder(Color.black, 1));jp_show.setVisible(false);
		
		//��Ӳ�ѯ�������
		jp_inquiry = AddComonents.addJPanel_2(jp_show, 2, 2, 688, 440); jp_inquiry.setBackground(c); jp_inquiry.setVisible(true);
		jl_head = AddComonents.addJLabel("�����ѯ", jp_inquiry, 300, 10, 100, 30);
		jl_line1 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_inquiry, 0, 30, 800, 30);
		jl_startCity = AddComonents.addJLabel("�������У�", jp_inquiry, 25, 80, 100, 30);
		jtf_startCity = AddComonents.addJTextField(jp_inquiry, 95, 80, 150, 30);
		jl_startAirport = AddComonents.addJLabel("����������", jp_inquiry, 310, 80, 100, 30);
		jtf_startAirport = AddComonents.addJTextField(jp_inquiry, 380, 80, 150, 30);
		jb_inquiryAirport1 = AddComonents.addJButton("��ѯ����", jp_inquiry, 550, 80, 90, 30, true, true, false);
		jl_reachCity = AddComonents.addJLabel("������У�", jp_inquiry, 25, 130, 100, 30);
		jtf_reachCity = AddComonents.addJTextField(jp_inquiry, 95, 130, 150, 30);
		jl_reachAirport = AddComonents.addJLabel("���������", jp_inquiry, 310, 130, 100, 30);
		jtf_reachAirport = AddComonents.addJTextField(jp_inquiry, 380, 130, 150, 30);
		jb_inquiryAirport2 = AddComonents.addJButton("��ѯ����", jp_inquiry, 550, 130, 90, 30, true, true, false);
		jl_time = AddComonents.addJLabel("�������ڣ�", jp_inquiry, 25, 180, 100, 30);
		jtf_time = AddComonents.addJTextField(jp_inquiry, 95, 180, 150, 30);
		jl_tips = AddComonents.addJLabel("(��ʽ��2020-06-04)", jp_inquiry, 110, 210, 150, 30);
		jb_today = AddComonents.addJButton("��ѯ����", jp_inquiry, 260, 180, 90, 30, true, true, false);
		jl_line2 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_inquiry, 0, 315, 800, 30);
		
		jb_inquiry = AddComonents.addJButton("��ѯ����", jp_inquiry, 275, 365, 120, 50, false, true, false);
		jb_cancel = AddComonents.addJButton("ȡ��", jp_inquiry, 620, 11, 60, 30, true, true, false);
		
		//������-ͷ���
		jp_showTitle = AddComonents.addJPanel_2(jp_show, 1, 1, 691, 54);jp_showTitle.setVisible(false);
		jl_showInfo = AddComonents.addJLabel("null", jp_showTitle, 15, 4, 500, 50);
		jb_reflush = AddComonents.addJButton("ˢ��", jp_showTitle, 545, 11, 60, 30, true, true, false);
		jb_reback = AddComonents.addJButton("����", jp_showTitle, 620, 11, 60, 30, true, true, false);
		//������-������
		jp_showTable = AddComonents.addJPanel_2(jp_show, 1, 55, 691, 300);jp_showTable.setVisible(false);	//691,300
		//������-��ť���
		jp_showButtom = AddComonents.addJPanel_2(jp_show, 2, 355, 689, 88);jp_showButtom.setVisible(false);
		jl_handle = AddComonents.addJLabel("�������ѡ����ҪԤ���ĺ���ţ�", jp_showButtom, 90, 25, 200, 30);
		jtf_handle = AddComonents.addJTextField(jp_showButtom, 295, 27, 180, 30);
		jb_book = AddComonents.addJButton("Ԥ������", jp_showButtom, 500, 20, 100, 40, false, true, false);
		
		//¼��˳�����Ϣ����
		jp_showPassenger = AddComonents.addJPanel_2(jp_show, 2, 2, 688, 440); jp_showPassenger.setBackground(c);jp_showPassenger.setVisible(false);
		jb_reback2 = AddComonents.addJButton("����", jp_showPassenger, 620, 11, 60, 30, true, true, false);
		jl_head2 = AddComonents.addJLabel("������˳�����Ϣ", jp_showPassenger, 280, 10, 130, 30);
		jl_line3 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_showPassenger, 0, 30, 800, 30);
		jl_line4 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_showPassenger, 0, 315, 800, 30);
		jl_name = AddComonents.addJLabel("�˳���������", jp_showPassenger, 165, 110, 100, 30);
		jtf_name = AddComonents.addJTextField(jp_showPassenger, 260, 110, 150, 30);
		jl_identity = AddComonents.addJLabel("�˳������֤��", jp_showPassenger, 165, 160, 100, 30);
		jtf_identity = AddComonents.addJTextField(jp_showPassenger, 260, 160, 150, 30);
		jl_phone = AddComonents.addJLabel("�˳����ֻ��ţ�", jp_showPassenger, 165, 210, 100, 30);
		jtf_phone = AddComonents.addJTextField(jp_showPassenger, 260, 210, 150, 30);
		jb_useOneSelf = AddComonents.addJButton("�ñ�����Ϣ", jp_showPassenger, 420, 210, 100, 30, true, true, false);
		jb_toPay = AddComonents.addJButton("ȥ֧��", jp_showPassenger, 275, 365, 120, 50, false, true, false);
		
		//֧������
		jp_showPay = AddComonents.addJPanel_2(jp_show, 2, 2, 688, 440); jp_showPay.setBackground(c);jp_showPay.setVisible(false);
		jb_reback3 = AddComonents.addJButton("����", jp_showPay, 610, 11, 60, 30, true, true, false);
		jl_head3 = AddComonents.addJLabel("��֧����                        Ԫ", jp_showPay, 250, 10, 150, 30);
		jl_money = AddComonents.addJLabel("1300.00", jp_showPay, 300, 10, 130, 30);jl_money.setFont(new Font("Default", 1, 18));
		jl_line5 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_showPay, 0, 30, 800, 30);
		jp_wechat = AddComonents.addCode(jp_showPay, "wechat.jpg", 100, 80);
		jp_alipay = AddComonents.addCode(jp_showPay, "alipay.jpg", 360, 80);
		jl_paytips = AddComonents.addJLabel("΢��֧��                                                       "
				+ "           ֧����֧��", jp_showPay, 180, 295, 800, 30);
		jl_line6 = AddComonents.addJLabel("______________________________"
				+ "__________________________________________"
				+ "__________________________________________", jp_showPay, 0, 315, 800, 30);
		jb_paid = AddComonents.addJButton("����֧��", jp_showPay, 275, 365, 120, 50, false, true, false);
	}
	
	
	public static void addTable(Object[][] arr) {
		jp_showTable.setVisible(false);
		jp_showTable = AddComonents.addJPanel_2(jp_show, 1, 55, 691, 300);	//��ԭ���Ӱ�أ������½�����ֵ
		Object[] title = {"����","�����","���ʱ��","����ʱ��","��ʱ(����)","��λ��","Ʊ��(Ԫ)"};	//��ͷ
		table = AddComonents.addJTable2(title, arr, jp_showTable, true);
		
		//��ӱ���ȡ������Ӧ��Ȼ���õ�ǰ�ĺ����
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				selectedFlight = String.valueOf(arr[table.getSelectedRow()][1]);
				jtf_handle.setText(selectedFlight);
			}
			
		});
		
	}

	
	public static JPanel getJp_show() {
		return jp_show;
	}

	public static JLabel getJl_head() {
		return jl_head;
	}

	public static JLabel getJl_line1() {
		return jl_line1;
	}

	public static JLabel getJl_line2() {
		return jl_line2;
	}
	public static JPanel getJp_inquiry() {
		return jp_inquiry;
	}
	public static JLabel getJl_startCity() {
		return jl_startCity;
	}
	public static JTextField getJtf_startCity() {
		return jtf_startCity;
	}
	public static JLabel getJl_startAirport() {
		return jl_startAirport;
	}
	public static JTextField getJtf_startAirport() {
		return jtf_startAirport;
	}
	public static JLabel getJl_reachCity() {
		return jl_reachCity;
	}
	public static JTextField getJtf_reachCity() {
		return jtf_reachCity;
	}
	public static JLabel getJl_reachAirport() {
		return jl_reachAirport;
	}
	public static JTextField getJtf_reachAirport() {
		return jtf_reachAirport;
	}
	public static JLabel getJl_time() {
		return jl_time;
	}
	public static JTextField getJtf_time() {
		return jtf_time;
	}
	public static JButton getJb_inquiryAirport1() {
		return jb_inquiryAirport1;
	}
	public static JButton getJb_inquiryAirport2() {
		return jb_inquiryAirport2;
	}
	public static JLabel getJl_tips() {
		return jl_tips;
	}
	public static JButton getJb_inquiry() {
		return jb_inquiry;
	}
	public static JButton getJb_reflush() {
		return jb_reflush;
	}
	public static JButton getJb_reback() {
		return jb_reback;
	}
	public static JButton getJb_cancel() {
		return jb_cancel;
	}
	public static JPanel getJp_showTitle() {
		return jp_showTitle;
	}
	public static JLabel getJl_showInfo() {
		return jl_showInfo;
	}
	public static JPanel getJp_showTable() {
		return jp_showTable;
	}
	public static JTable getTable() {
		return table;
	}
	public static JPanel getJp_showButtom() {
		return jp_showButtom;
	}
	public static JLabel getJl_handle() {
		return jl_handle;
	}
	public static JTextField getJtf_handle() {
		return jtf_handle;
	}
	public static JButton getJb_book() {
		return jb_book;
	}
	public static JPanel getJp_showPassenger() {
		return jp_showPassenger;
	}
	public static JButton getJb_reback2() {
		return jb_reback2;
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
	public static JButton getJb_useOneSelf() {
		return jb_useOneSelf;
	}
	public static JButton getJb_toPay() {
		return jb_toPay;
	}
	public static JButton getJb_reback3() {
		return jb_reback3;
	}
	public static JButton getJb_paid() {
		return jb_paid;
	}
	public static JPanel getJp_showPay() {
		return jp_showPay;
	}
	public static JLabel getJl_money() {
		return jl_money;
	}
	public static JButton getJb_today() {
		return jb_today;
	}
	
}
