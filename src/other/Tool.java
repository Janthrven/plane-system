package other;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tool {
	
	//�����ά���飬��ʱ���������Ȼ�󷵻أ����µ���
	public static Object[][] arrSort(Object[][] arr){
		Object[] temp = new Object[arr[0].length];		//	������ʱһ��һά����
		for(int i=0; i<arr.length-1; i++){   			//��ʾ������һ��arr.length-1�Ρ�
	         for(int j = arr.length-1; j>i; j--){
	        	 int time1 = Integer.parseInt(((arr[j][2]).toString().split(":"))[0]+((arr[j][2]).toString().split(":"))[1]);
	        	 int time2 = Integer.parseInt(((arr[j-1][2]).toString().split(":"))[0]+((arr[j-1][2]).toString().split(":"))[1]);
	        	 if(time1<time2) {
	        		 for (int k = 0; k < temp.length; k++) {
	        			 temp[k] = arr[j][k];
	        		 }
	        		 for (int k = 0; k < arr[j].length; k++) {
	        			 arr[j][k] = arr[j-1][k];
	        		 }
	        		 for (int k = 0; k < arr[j].length; k++) {
	        			 arr[j-1][k] = temp[k];
	        		 }
	        	 }
	         }
		}
		return arr;
	}
	
	//����JTextField�����ض�Ӧ��ֵ
	public static String getTextValue(JTextField jtf) {
		return String.valueOf(jtf.getText()).trim();
	}
	
	//������壬���ñ߿�
	public static void setBorder(JPanel jp) {
		jp.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	}
	
	//��ý�������ں�������������Ƚ�
	public static boolean Tomorrow(String date) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(d);
		if(Integer.valueOf(date.split("-")[0]+date.split("-")[1]+date.split("-")[2])
				>= Integer.valueOf(today.split("-")[0]+today.split("-")[1]+today.split("-")[2])) {
			return true;
		}else {
			return false;
		}
	}
	
	//�����ı���Ϊ����
	public static void setToday(JTextField jtf) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(d);
		jtf.setText(today);
	}
	
}
