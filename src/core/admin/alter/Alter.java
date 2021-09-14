package core.admin.alter;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import core.admin.inquiry.Inquiry;
import core.admin.jpanel.InquiryJPanel;
import core.database.Database;
import other.Tool;

/**
 * �޸����ݿ�
 * 1����ȡ��������
 * 2�����µ����ݿ���
 * 
 * @author İ����
 *
 */

public class Alter {

	static Object[][] temp;
	
	//���ͨ������ȡ�±�����ݣ���ԭ����������Ƚϣ�Ȼ��д�뵽���ݿ���
	public static int alter() {
		int count = 0;		//�޸ĵ���Ч����
		try {
			Object[][] arr = Tool.arrSort(Inquiry.submit());	//��ȡԭ������ݣ������ݿ�����
			String startCity = Tool.getTextValue(InquiryJPanel.getJtf_startCity());
			String startAirport = Tool.getTextValue(InquiryJPanel.getJtf_startAirport());
			String reachCity = Tool.getTextValue(InquiryJPanel.getJtf_reachCity());
			String reachAirport = Tool.getTextValue(InquiryJPanel.getJtf_reachAirport());
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(!arr[i][j].equals(temp[i][j])) {		//������ȣ������޸Ĺ���
						count++;
						String sql = "update flight set date='"+temp[i][0]+"',start_time='"+temp[i][2]
								+"',reach_time='"+temp[i][3]+"',used_time='"+temp[i][4]
										+"',seat_count="+temp[i][5]+",price="+temp[i][6]+ 
								" where start_city='"+startCity+"' and start_airport='"+startAirport+"' and reach_city='"+reachCity+"' and reach_airport='"+reachAirport+"' and date = '"+arr[i][0]+"' and flight_num='"+arr[i][1]+"';";
						new Database().setUpdate(sql);
						break;
					}
				}
			}
			return count;
		} catch (ClassNotFoundException | SQLException e) {
			count = -1;
			System.out.println("��ǰ�����Ѵ��ڸú��࣬�޸�ʧ�ܣ�");
		}
		return count;
	}
	
	//����ֶ��Ƿ�ͨ��
	public static boolean checkTableInfo(JFrame jf) {
		try {
			Object[][] arr = Inquiry.submit();	//��ñ���к���
			temp = new Object[arr.length][arr[0].length];	//��ʼ���ռ�
			//������������жϵ�ǰ�������к��У���Ӧ�����������༭״̬
			JTable table = InquiryJPanel.getTable();
			//������û�б��༭������ȡѡ����ѡ�������쳣����
			ListSelectionModel lsm = table.getSelectionModel();
			if(!lsm.isSelectionEmpty())
				table.getCellEditor(table.getSelectedRow(), table.getSelectedColumn()).stopCellEditing();
			//��ֵ
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if(!cheak(jf, String.valueOf(table.getValueAt(i,j)), j)) {
						return false;
					}else {
						temp[i][j] = table.getValueAt(i,j);		//ͬʱ��ֵ���������б�������������ν�Ƿ�ͨ������Ϊͨ���˲������������
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("�޸����ݿ��ʱ���ȡ���ݿ����ɱ��ʧ�ܣ�");
		}
		return true;
	}
	
	
	//��¼����ַ����ж�
	public static boolean cheak(JFrame jf ,String str, int column) {
		String reg1 = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		String reg2 = "^([0-1]\\d|2[0-3]):[0-5]\\d$";
		if(column == 0 && !str.matches(reg1)) {
			JOptionPane.showMessageDialog(jf, "¼������ڸ�ʽ����ȷ��");return false;
		}else if(column == 1 && str.equals("")) {
			JOptionPane.showMessageDialog(jf, "��¼�뺽�࣡");return false;
		}else if(column == 2 && str.equals("")){
			JOptionPane.showMessageDialog(jf, "��¼�����ʱ�䣡");return false;
		}else if(column == 2 && !str.matches(reg2)) {
			JOptionPane.showMessageDialog(jf, "���ʱ���ʽ����ȷ����ʽ����Ϊ��16:30��");return false;
		}else if(column == 3 && str.equals("")){
			JOptionPane.showMessageDialog(jf, "��¼�뽵��ʱ�䣡");return false;
		}else if(column == 3 && !str.matches(reg2)) {
			JOptionPane.showMessageDialog(jf, "����ʱ���ʽ����ȷ����ʽ����Ϊ��16:30��");return false;
		}else if(column == 4 && str.equals("")) {
			JOptionPane.showMessageDialog(jf, "��¼����ʱ����λΪ���ӣ�");return false;
		}else if(column == 4 && !str.matches("^\\d+$")) {
			JOptionPane.showMessageDialog(jf, "��ʱ��ʽ����ȷ����ʽ������120");return false;
		}else if(column == 5 && str.equals("")) {
			JOptionPane.showMessageDialog(jf, "��¼����λ����");return false;
		}else if(column == 5 && !str.matches("^\\d+$")) {
			JOptionPane.showMessageDialog(jf, "��λ����ʽ����ȷ������Ϊ���ڻ����0������");return false;
		}else if(column == 6 && str.equals("")) {
			JOptionPane.showMessageDialog(jf, "��¼��۸�");return false;
		}else if(column == 6 && !str.matches("^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$")) {
			JOptionPane.showMessageDialog(jf, "�۸�����ʽ����ȷ������Ϊ�����������");return false;
		}else {
			return true;
		}
	}
}
