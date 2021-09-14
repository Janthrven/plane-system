package core.admin.statistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import core.admin.jpanel.StatisticsJPanel;
import core.database.Database;
import other.Global;

//ͳ�������Ӧ��
public class StatisticsData {
	public static String startCity;
	public static String startAirport;
	public static String reachCity;
	public static String reachAirport;
	public static String year;
	public static String month;
	public static String flight;
	private static ResultSet rs = null;	//�����ѯ��
	private static int flightCount = 0;
	private static List<String[]> list;
	private static String[] orderArrs;
	private static String[] rateArrs;
	//����ֶ�
	public static void setText() {
		startCity = StatisticsJPanel.getStartCity();
		startAirport = StatisticsJPanel.getStartAirport();
		reachCity = StatisticsJPanel.getReachCity();
		reachAirport = StatisticsJPanel.getReachAirport();
		year = StatisticsJPanel.getYear();
		month = StatisticsJPanel.getMonth();
		flight = StatisticsJPanel.getFlight();
	}
	
	//��ò�ѯ��
	public static void getData() {
		setText();
		String sql = "select * from flight where flight_num='"
				+flight+"' and start_city='"+startCity+"' and start_airport='"+startAirport
				+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"';";
		try {
			rs = new Database().getSelect(sql);
		} catch (SQLException e) {
			System.out.println("ͳ�����ݻ�ò�ѯ��ʧ�ܣ�");
		}
		flightCount = getFlightCount(rs);
		list = getCountAndRate(rs);
	}
	
	public static Object[][] getArr(){
		getData();
		Object[][] arr = new Object[1][7];	//���������
		arr[0][0] = getFlightCount(rs);
		if(getFlightCount(rs) == 0) {
			Object[][] temp= {{"��","��","��","��","��","��","��"}};
			JOptionPane.showMessageDialog(Global.getJf2(), "���޺��౨��");
			return temp;
		}
		for (int i = 1; i < arr[0].length; i++) {
			arr[0][i] = orderArrs[i-1] +" & "+rateArrs[i-1]+"%";
		}
		return arr;
	}
	
	
	
	//��ú�����
	public static int getFlightCount(ResultSet rs) {
		int count = 0;
		try {
			rs.last();
			count = rs.getRow();	//������һ�м�Ϊ����
			rs.beforeFirst();		//��Ϊͷλ��
		} catch (SQLException e) {
			System.out.println("ͳ�����ݻ�ú�����ʧ�ܣ�");
		}
		return count;
	}
	
	//���ÿ�ܵ�������������������
	public static List<String[]> getCountAndRate(ResultSet rs) {
		List<String[]> list = new ArrayList<String[]>();
		String tempdate;	//��ʱ����
		int[] orderTempArr = new int[6];				//��Ŷ�������ͨ���жϼ��ٵ���λ����
		orderArrs = new String[6];
		rateArrs = new String[6];
		try {
			//ɨ��
			while(rs.next()) {
				String date = rs.getString("date");	//������ڣ��ж��Ƕ��ٺ�
				int seatCount = Integer.valueOf(rs.getString("seat_count"));
				for(int i = 1; i<=31; i++) {
					if(i<10) {
						tempdate = year+"-"+month+"-"+"0"+i;
					}else {
						tempdate = year+"-"+month+"-"+i;
					}
					//���������ȣ��ó���ǰ�������ݣ��õ�ĳ�ա�����λ��
					if(tempdate.equals(date)) {
						if(i>=1 && i<=7) {			//��һ��
							orderTempArr[0] += Global.SEAT_COUMT-seatCount;	  //��õ�1�ܶ�����
						}else if(i>=8 && i<=14) {	//�ڶ���
							orderTempArr[1] += Global.SEAT_COUMT-seatCount;	  //��õ�2�ܶ�����
						}else if(i>=15 && i<=21) {	//������
							orderTempArr[2] += Global.SEAT_COUMT-seatCount;	  //��õ�3�ܶ�����
						}else if(i>=22 & i<=28) {	//������
							orderTempArr[3] += Global.SEAT_COUMT-seatCount;	  //��õ�4�ܶ�����
						}else {						//������
							orderTempArr[4] += Global.SEAT_COUMT-seatCount;	  //��õ�5�ܶ�����
						}
						orderTempArr[5] += Global.SEAT_COUMT-seatCount;	 	 //����ܶ�����
					}
				}
			}
			rs.beforeFirst();	//��Ϊͷλ��
			for (int i = 0; i < rateArrs.length; i++) {
				//���ɱ���
				double rate = 0;
				if(i != rateArrs.length-1) {	
					rate = (double)orderTempArr[i]/(orderTempArr[orderTempArr.length-1]);
				}
				else{	//���������λ
					rate = (double)orderTempArr[i]/(Global.SEAT_COUMT*getFlightCount(rs));
				}
				DecimalFormat df = new DecimalFormat("#.00");
				rateArrs[i] = df.format(rate*100);
				orderArrs[i] = String.valueOf(orderTempArr[i]);
			}
			list.add(orderArrs);
			list.add(rateArrs);
			return list;
		} catch (SQLException e) {
			System.out.println("��ü�������ʧ�ܣ�");
			return list;
		}
	}

	public static String[] getOrderArrs() {
		return orderArrs;
	}
	public static int getFlightCount() {
		return flightCount;
	}
	
}
