package core.user.book;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import core.database.Database;
import core.user.jpanel.BackAlterJPanel;
import core.user.jpanel.BookJPanel;
import other.Tool;

/**
 * �����������˸Ĺ�����
 * @author İ����
 *
 */
public class Order {
	//����һ�����ţ����ڲ�������
	private static String usingOrderNum;	//��������ʹ�õĺ����
	private static String oldFlight;		//�ж��Ƿ��ǩΪͬһ������
	private static String oldTime;
	private static JButton alter;			//��ǩ��ť
	
	//��ǩ�޸ĺ�����Ϣ
	public static void updateOrder() {
		String orderNum = getUsingOrderNum();
		String sql = "UPDATE orders SET date = '"+Tool.getTextValue(BookJPanel.getJtf_time())+"',flight_num = '"
				+Tool.getTextValue(BookJPanel.getJtf_handle())+"' WHERE order_num = '"+orderNum+"';";
		try {
			new Database().setUpdate(sql);
		} catch (SQLException e) {
			System.out.println("��ǩ�޸ĺ�����Ϣʧ�ܣ�");
		}
			
	}
	
	//��Ʊ���߸�ǩ ��������Ʊģʽ����ǩģʽ
	public static void backTicket(String model) {
		//������λ����һ
		String orderNum = getUsingOrderNum();
		String sql1 = "UPDATE flight f,orders o SET f.seat_count = (f.seat_count + 1)" + 
					  "WHERE" + 
					  "	f.flight_num = o.flight_num" + 
					  "	AND f.date = o.date" + 
					  "	AND f.start_city = o.start_city" +
					  "	AND f.start_airport = o.start_airport" +
					  "	AND f.reach_city = o.reach_city" +
					  "	AND f.reach_airport = o.reach_airport" +
					  "	AND o.order_num = '"+orderNum+"'";
		String sql2 = "Delete from orders where order_num='"+orderNum+"'";
		try {
			new Database().setUpdate(sql1);
			if(model.equals("��Ʊģʽ")) {
				new Database().setDelete(sql2);
			}
		} catch (SQLException e) {
			System.out.println(model+"������ȡ���ݿ�ʧ�ܣ�");
		}
	}
	
	//��ǩģʽ
	public static void alterModel() {
		//��ת��ѯ����
		String orderNum = getUsingOrderNum();
		BackAlterJPanel.getJsp_show().setVisible(false);
		BookJPanel.getJp_show().setVisible(true);
		//
		String sql = "SELECT" + 
				     "  flight.date, flight.start_city, flight.start_airport, flight.reach_city, flight.reach_airport, flight.flight_num " + 
					 "FROM" + 
					 "  flight,orders " + 
					 "WHERE" + 
					 "	flight.flight_num = orders.flight_num " + 
					 "	AND flight.start_city = orders.start_city" +
					 "	AND flight.start_airport = orders.start_airport" +
					 "	AND flight.reach_city = orders.reach_city" +
					 "	AND flight.reach_airport = orders.reach_airport" +
					 "	AND flight.date = orders.date " + 
					 "	AND orders.order_num = '"+orderNum+"'";
		try {
			ResultSet rs = new Database().getSelect(sql); rs.next();
			BookJPanel.getJtf_time().setText(rs.getString("date"));
			//���ò����ɱ༭
			BookJPanel.getJtf_startCity().setText(rs.getString("start_city"));
			BookJPanel.getJtf_startCity().setEditable(false);
			BookJPanel.getJtf_startAirport().setText(rs.getString("start_airport"));
			BookJPanel.getJtf_startAirport().setEditable(false);
			BookJPanel.getJtf_reachCity().setText(rs.getString("reach_city"));
			BookJPanel.getJtf_reachCity().setEditable(false);
			BookJPanel.getJtf_reachAirport().setText(rs.getString("reach_airport"));
			BookJPanel.getJtf_reachAirport().setEditable(false);
			BookJPanel.getJtf_handle().setText(rs.getString("flight_num"));
			setOldFlight(rs.getString("flight_num"));
			setOldTime(rs.getString("date"));
			//�޸����ݿ�
			
		} catch (SQLException e) {
			System.out.println("��ǩ��ѯ���ݿ�ʧ�ܣ�");
		}
	}
	
	//���ûطǸ�ǩģʽ
	public static void cancelAlter() {
		//��ѯ�������û��������棬����Ϊfalse
		BookJPanel.getJp_inquiry().setVisible(true);
		BookJPanel.getJp_showTitle().setVisible(false);
		BookJPanel.getJp_showTable().setVisible(false);
		BookJPanel.getJp_showButtom().setVisible(false);
		BookJPanel.getJp_showPassenger().setVisible(false);
		BookJPanel.getJp_showPay().setVisible(false);
		
		BackAlterJPanel.getJsp_show().setVisible(true);
		BookJPanel.getJp_show().setVisible(false);
		BookJPanel.getJtf_startCity().setText("");
		BookJPanel.getJtf_startCity().setEditable(true);
		BookJPanel.getJtf_startAirport().setText("");
		BookJPanel.getJtf_startAirport().setEditable(true);
		BookJPanel.getJtf_reachCity().setText("");
		BookJPanel.getJtf_reachCity().setEditable(true);
		BookJPanel.getJtf_reachAirport().setText("");
		BookJPanel.getJtf_reachAirport().setEditable(true);
		BookJPanel.getJtf_time().setText("");
		BookJPanel.getJtf_handle().setText("");
		
	}
	public static JButton getAlter() {
		return alter;
	}

	public static void setAlter(JButton alter) {
		Order.alter = alter;
	}

	public static String getOldFlight() {
		return oldFlight;
	}

	public static void setOldFlight(String oldFlight) {
		Order.oldFlight = oldFlight;
	}

	public static String getUsingOrderNum() {
		return usingOrderNum;
	}

	public static void setUsingOrderNum(String usingOrderNum) {
		Order.usingOrderNum = usingOrderNum;
	}

	public static String getOldTime() {
		return oldTime;
	}

	public static void setOldTime(String oldTime) {
		Order.oldTime = oldTime;
	}

}
