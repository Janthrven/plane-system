package core.admin.jpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import other.AddComonents;

/**
 * -����Ա��¼����Ĳ˵���壬������ť���ṩget������
 * @author İ����
 *
 */

public class MenuJPanel {
	//��߲˵����+��ť
	private static JPanel jp_menu;	//һ��
	private static JButton jb_inquiry;
	private static JButton jb_input;
	private static JButton jb_statistics;
	
	
	public static void init(JFrame jf) {
		jp_menu = AddComonents.addJPanel(jf, 0, 78, 150, 445); jp_menu.setBackground(new Color(203,236,234));
		jp_menu.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		jb_inquiry = AddComonents.addJButton("�ۺϲ�ѯ", jp_menu, 2, 2, 146, 50, false, true, false);
		jb_input = AddComonents.addJButton("����¼��", jp_menu, 2, 52, 146, 50, false, true, false);
		jb_statistics = AddComonents.addJButton("ͳ�Ʊ���", jp_menu, 2, 102, 146, 50, false, true, false);
	}
	
	public static JPanel getJp_menu() {
		return jp_menu;
	}

	public static JButton getJb_inquiry() {
		return jb_inquiry;
	}

	public static JButton getJb_input() {
		return jb_input;
	}

	public static JButton getJb_statistics() {
		return jb_statistics;
	}
}
