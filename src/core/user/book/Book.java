package core.user.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import core.database.Database;
import core.user.jpanel.BackAlterJPanel;
import core.user.jpanel.BookJPanel;
import other.Tool;

/**
 * ��������Ȼ��Ԥ�����ռ�Ԥ����Ϣ��
 * 
 * @author İ����
 *
 */

public class Book {
	
	public static String orderNum;
	public static String date;
	public static String flight;
	public static String username;
	public static String name;
	public static String identity;
	public static String phone;
	public static String startCity;
	public static String reachCity;
	private static String startAirport;
	private static String reachAirport;
	
	//��λ�ż�һ
	public static void setDownSeat() {
		String date = Tool.getTextValue(BookJPanel.getJtf_time());
		String flight = Tool.getTextValue(BookJPanel.getJtf_handle());
		String startAirport = Tool.getTextValue(BookJPanel.getJtf_startAirport());
		String reachAirport = Tool.getTextValue(BookJPanel.getJtf_reachAirport());
		String startCity = Tool.getTextValue(BookJPanel.getJtf_startCity());
		String reachCity = Tool.getTextValue(BookJPanel.getJtf_reachCity());
		try {
			new Database().setUpdate("update flight set seat_count =(seat_count-1) where flight_num='"+flight+"' and date='"+date+"' and start_city='"+startCity+"' and start_airport='"+startAirport+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"';");
		} catch (SQLException e) {
			System.out.println("�޸���λ����һ����ʧ�ܣ�");
		}
	}
	
	//��ú���۸�
	public static void setPrice() {
		String date = Tool.getTextValue(BookJPanel.getJtf_time());
		String flight = Tool.getTextValue(BookJPanel.getJtf_handle());
		String startAirport = Tool.getTextValue(BookJPanel.getJtf_startAirport());
		String reachAirport = Tool.getTextValue(BookJPanel.getJtf_reachAirport());
		String startCity = Tool.getTextValue(BookJPanel.getJtf_startCity());
		String reachCity = Tool.getTextValue(BookJPanel.getJtf_reachCity());
		try {
			ResultSet rs = new Database().getSelect("select price from flight where flight_num='"+flight+"' and date='"+date+"' and start_city='"+startCity+"' and start_airport='"+startAirport+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"';");
			while(rs.next()) {
				BookJPanel.getJl_money().setText(rs.getString("price"));
				break;
			}
		} catch (SQLException e) {
			System.out.println("��ú���۸�ʧ�ܣ�");
		}
	}

	//����������Ϣ�����ݿ���
	public static boolean insertOrder(String uname) {
		setRequestInfo(uname);
		String sql = "insert into orders (order_num,date,flight_num,start_city,start_airport,reach_city,reach_airport,username,name,id,phone)"
				+ " values ('"+orderNum+"','"+date+"','"+flight+"','"+startCity+"','"+startAirport+"','"+reachCity+"','"+reachAirport+"','"+username+"','"+name+"','"+identity+"','"+phone+"');";
		try {
			new Database().setInsert(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("���붩����������ʧ�ܣ�");	//�����������ͬ��ݣ������ʧ�ܣ�
			
			System.out.println(e);
			return false;
		}
	}
	
	//��ó˳�����Ϣ
	public static void setRequestInfo(String uname) {
		orderNum = setOrderNum();
		date = Tool.getTextValue(BookJPanel.getJtf_time());
		flight = Tool.getTextValue(BookJPanel.getJtf_handle());
		startCity = Tool.getTextValue(BookJPanel.getJtf_startCity());
		reachCity = Tool.getTextValue(BookJPanel.getJtf_reachCity());
		name = Tool.getTextValue(BookJPanel.getJtf_name());
		username = uname;
		identity = Tool.getTextValue(BookJPanel.getJtf_identity());
		phone = Tool.getTextValue(BookJPanel.getJtf_phone());
		startAirport = Tool.getTextValue(BookJPanel.getJtf_startAirport());
		reachAirport = Tool.getTextValue(BookJPanel.getJtf_reachAirport());
	}
	
	//�ύ�����ɶ�����
	public static String setOrderNum() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(date);
	}
	
	/**
	 * ��麽����Ƿ�����ͬʱ�ж���λ���Ƿ�Ϊ0
	 * @param uname
	 * @return int 1�ɹ���2Ϊ�գ�3��λΪ0�ˣ�4δ֪����
	 */
	public static int cheakFlight(String uname) {
		//�Ȼ�ȡ��λ��
		setRequestInfo(uname);
		String jtfContent = Tool.getTextValue(BookJPanel.getJtf_handle());
		if(jtfContent.equals("")) {
			return 2;
		}
		String sql = "select seat_count from flight where date='"+date+"' and start_city='"+startCity+"' and start_airport='"+startAirport+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"';";
		try {
			ResultSet rs = new Database().getSelect(sql); rs.next();
			int count = Integer.valueOf(rs.getString("seat_count"));	//�õ���λ��
		    if(count == 0){
				return 3;
			}else {
				flight = jtfContent;
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("�����λ��ʧ�ܣ�");
			return 4;
		}
		
	}
	//����Ƿ���Ԥ���ú�����
	public static boolean cheakBooked(String uname) {
		setRequestInfo(uname);	
		//������������ڣ�����ţ����֤��
		String sql = "select * from orders where start_city='"+startCity+"' and start_airport='"+startAirport+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"' and id='"+identity+"' and date='"+date+"' and flight_num='"+flight+"';";
		try {
			ResultSet rs = new Database().getSelect(sql);
			if(rs.next()) {
				return true;	//�����˵�����ݿ������ݣ�����ͨ��
			}else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("��ѯ��Ԥ��ʧ�ܣ�");
			return false;
		}
	}
	
	
	//���˳�����Ϣ�Ƿ����벢��ûԤ��
	public static boolean cheakFull(JFrame jf,String uname) {
		setRequestInfo(uname);
		if(name.equals("")) {
			JOptionPane.showMessageDialog(jf, "������˳���������");return false;
		}else if(identity.equals("")) {
			JOptionPane.showMessageDialog(jf, "���������֤�ţ�");return false;
		}else if(!identity.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")) {
			JOptionPane.showMessageDialog(jf, "���֤�Ÿ�ʽ����ȷ�����������룡");return false;
		}else if(phone.equals("")) {
			JOptionPane.showMessageDialog(jf, "������˳����ֻ��ţ�");return false;
		}else if(!phone.matches("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")) {
			JOptionPane.showMessageDialog(jf, "�˳����ֻ��Ÿ�ʽ����ȷ�����������룡");return false;
		}else if(cheakBooked(uname)){
			JOptionPane.showMessageDialog(jf, "�ó˳����Թ���ǰ����Ļ�Ʊ��Ԥ��ʧ�ܣ�");return false;
		}else {
			return true;
		}
	}
	
	//���˳��˸�����Ϣ
	public static void getOneSelf(String username) {
		ResultSet rs = null;
		try {
			rs = new Database().getSelect("select name,id,phone from user1 where username='"+username+"';");
			rs.next();
			BookJPanel.getJtf_name().setText(rs.getString("name"));
			BookJPanel.getJtf_identity().setText(rs.getString("id"));
			BookJPanel.getJtf_phone().setText(rs.getString("phone"));
		} catch (SQLException e) {
			System.out.println("����û���Ϣʧ�ܣ�");
		}finally {
			if(rs!=null) {
				try {
					rs.last();
				} catch (SQLException e) {
					System.out.println("����û���Ϣ�����ر���Դʧ�ܣ�");
				}
			}
		}
	}
	
	public static void setAllEmpty() {
		BookJPanel.getJtf_startCity().setText("");
		BookJPanel.getJtf_startAirport().setText("");
		BookJPanel.getJtf_reachCity().setText("");
		BookJPanel.getJtf_reachAirport().setText("");
		BookJPanel.getJtf_time().setText("");
		BookJPanel.getJtf_handle().setText("");
		BookJPanel.getJtf_name().setText("");
		BookJPanel.getJtf_identity().setText("");
		BookJPanel.getJtf_phone().setText("");
	}
	
	public static String getDate() {
		return date;
	}
	public static String getFlight() {
		return flight;
	}
	public static String getName() {
		return name;
	}
	public static String getIdentity() {
		return identity;
	}
	public static String getPhone() {
		return phone;
	}
	public static String getOrderNum() {
		return orderNum;
	}
	
}
