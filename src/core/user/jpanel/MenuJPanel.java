package core.user.jpanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import other.AddComonents;

public class MenuJPanel {
	//��߲˵����+��ť
		private static JPanel jp_menu;	//һ��
		private static JButton jb_book;
		private static JButton jb_backAlter;
		private static JButton jb_person;
		
		public static void init(JFrame jf) {
			jp_menu = AddComonents.addJPanel(jf, 0, 78, 150, 445); jp_menu.setBackground(new Color(203,236,234));
			jp_menu.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			jb_book = AddComonents.addJButton("��ѯԤ��", jp_menu, 2, 2, 146, 50, false, true, false);
			jb_backAlter = AddComonents.addJButton("�˸Ĺ���", jp_menu, 2, 52, 146, 50, false, true, false);
			jb_person = AddComonents.addJButton("������Ϣ", jp_menu, 2, 102, 146, 50, false, true, false);
		}
		
		public static JPanel getJp_menu() {
			return jp_menu;
		}

		public static JButton getJb_book() {
			return jb_book;
		}

		public static JButton getJb_backAlter() {
			return jb_backAlter;
		}

		public static JButton getJb_person() {
			return jb_person;
		}

}
