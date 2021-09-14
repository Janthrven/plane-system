package core.admin.inquiry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import core.admin.alter.Alter;
import core.admin.jpanel.InquiryJPanel;
import core.database.Database;
import other.Tool;

/**
 * ��ѯ����
 * 
 * @author İ����
 *
 */

public class Inquiry {
	
	private static JTextField jtf_startCity = InquiryJPanel.getJtf_startCity();
	private static JTextField jtf_startAirport = InquiryJPanel.getJtf_startAirport();
	private static JTextField jtf_reachCity = InquiryJPanel.getJtf_reachCity();
	private static JTextField jtf_reachAirport = InquiryJPanel.getJtf_reachAirport();
	private static JTextField jtf_time = InquiryJPanel.getJtf_time();
	private static JTextField jtf_handle = InquiryJPanel.getJtf_handle();
	
	private static String startCity;
	private static String startAirport;
	private static String reachCity;
	private static String reachAirport;
	private static String time;
	private static String handle;

	public static void getText() {
		startCity = Tool.getTextValue(jtf_startCity);
		startAirport = Tool.getTextValue(jtf_startAirport);
		reachCity = Tool.getTextValue(jtf_reachCity);
		reachAirport = Tool.getTextValue(jtf_reachAirport);
		time = Tool.getTextValue(jtf_time);
		handle = Tool.getTextValue(jtf_handle);
		handle = Tool.getTextValue(jtf_handle);
//		startCity = "����";
//		startAirport = "���ƻ���";
//		reachCity = "�Ϻ�";
//		reachAirport = "���Ż���";
//		time = "2020-06-04";
	}
	
	//ɾ�����޸Ĵ���
	public static void handle(JFrame jf) {
		if(InquiryJPanel.getJb_handle().getText().equals("ɾ������")) {
			int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ���ú�����?", "ɾ����ʾ��", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				int row = delete(jf);
				if(row == -1) {
					JOptionPane.showMessageDialog(jf, "ɾ��ʧ�ܣ������뺽��ţ�");
				}else if(row == 0) {
					JOptionPane.showMessageDialog(jf, "ɾ��ʧ�ܣ�"+time+"�����ں���"+handle);
				}else {
					JOptionPane.showMessageDialog(jf, "ɾ���ɹ���");
					jtf_handle.setText("");
					jtf_handle.requestFocus();
				}
			}
			
		}else {
			//�޸ĺ���
			if(Alter.checkTableInfo(jf)) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�޸ĺ�����?", "�޸���ʾ��", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					int count = Alter.alter();
					if(count == -1) {
						JOptionPane.showMessageDialog(jf, "��ǰ�����Ѵ��ڸú��࣬�޸�ʧ�ܣ�");
					}else if(count == 0){
						JOptionPane.showMessageDialog(jf, "�����ݸĶ���");
					}else {
						JOptionPane.showMessageDialog(jf, "�޸ĳɹ����Ķ�������Ϊ��"+count);
					}
				}
			}
		}
	}
	
	
	//ɾ������
	public static int delete(JFrame jf) {
		
		getText();
		int row = -1;	//-1Ϊ�գ�0Ϊɾ��ʧ�ܣ�1Ϊ�ɹ�
		if(handle.equals("")) {
			row = -1;
		}else {
			try {
				row = new Database().setDelete("delete from flight where flight_num='"+handle+"' and date='"+time+"';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	
	//��ʼƥ��
	public static Object[][] submit() throws ClassNotFoundException, SQLException {
		getText();
		InquiryJPanel.getJl_showInfo().setText("������Ϣ��"+startCity+"("+startAirport+") �� "+reachCity+"("+reachAirport+")");
		ResultSet rs = new Database().getSelect("select * from flight;");
		String[] temp = new String[1024];	//����һ����ʱ����
		Object[][] arr = null;				//����һ����ά���飬���ڷ���
		int arrlength = 0;
		while(rs.next()) {
			//ƥ�����ݿ�,��ʼ��ȡ���ݿ�����
			if(startCity.equals(rs.getString("start_city")) && startAirport.equals(rs.getString("start_airport"))
					&& reachCity.equals(rs.getString("reach_city")) && reachAirport.equals(rs.getString("reach_airport"))
					&& time.equals(rs.getString("date"))) {
				temp[arrlength++] = time+"#"+rs.getString("flight_num")+"#"+rs.getString("start_time")+"#"+rs.getString("reach_time")+"#"
						+rs.getString("used_time")+"#"+rs.getString("seat_count")+"#"+rs.getString("price");
			}
		}
		if(arrlength == 0) {
			arr = new Object[1][7];
		}else {
			arr = new Object[arrlength][7];
		}
		
		//��ȡ��ϣ���װ��һ����ά���鷵��
		if(arrlength == 0) {
			System.out.println("���޺���");
			arr[0][0] = "���޺���";	//���û�к������趨һ�������ı�־
		}else {
			for(int i = 0; i<arrlength; i++) {
				String[] s = temp[i].split("#");
				for(int j = 0; j<7; j++) {
					arr[i][j] = s[j];
				}
			}
		}
		rs.last();  //������������ص�
		return arr;
	}
	
	//����ʽ
	public static boolean check(JFrame jf) {
		getText();
		String reg1 = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|"
				+ "(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|"
				+ "(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|"
				+ "(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|"
				+ "((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		if(startCity.equals("")) {
			JOptionPane.showMessageDialog(jf, "������������У�");
			return false;
		}else if(reachCity.equals("")) {
			JOptionPane.showMessageDialog(jf, "�����뵽����У�");
			return false;
		}else if(startAirport.equals("")) {
			JOptionPane.showMessageDialog(jf, "���ѯ��ȡ����������");
			return false;
		}else if(reachAirport.equals("")) {
			JOptionPane.showMessageDialog(jf, "���ѯ��ȡ���������");
			return false;
		}else if(time.equals("")) {
			JOptionPane.showMessageDialog(jf, "�������ʱ�䣡");
			return false;
		}else if(!time.matches(reg1)) {	//"^^\\d{4}\\-\\d{1,2}\\-\\d{1,2}$"
			JOptionPane.showMessageDialog(jf, "��������ڸ�ʽ����ȷ��");
			return false;
		}else if(!Tool.Tomorrow(time)){
			JOptionPane.showMessageDialog(jf, "�޷���ѯ��ȥ�ĺ��࣡");
			return false;
		}else{
			return true;
		}
	}
	
	//���ݳ��У�ƥ�����ݿ��û���������list����
	public static List<String> getAirport(String str) throws ClassNotFoundException, SQLException {
		getText();
		List<String> airportlist = new ArrayList<String>();
		ResultSet rs = new Database().getSelect("select * from flight;");
		while(rs.next()) {
			//ƥ�����ݿ�
			if(str.equals(rs.getString("start_city"))) {
				if(!airportlist.contains(rs.getString("start_airport"))) {
					airportlist.add(rs.getString("start_airport"));
				}
			}
			if(str.equals(rs.getString("reach_city"))) {
				if(!airportlist.contains(rs.getString("reach_airport"))) {
					airportlist.add(rs.getString("reach_airport"));
				}
			}
		}
		rs.last();  //������������ص�
		return airportlist;
	}
	
	public static String getStartCity() {
		getText();
		return startCity;
	}

	public static String getReachCity() {
		getText();
		return reachCity;
	}
}
