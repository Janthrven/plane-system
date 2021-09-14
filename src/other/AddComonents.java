package other;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * -����������
 * @author İ����
 *
 */

public class AddComonents {
	//������
	public static JPanel addJPanel(JFrame jf, int x, int y ,int width, int height) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(x, y, width, height);
		jf.add(jp);
		Reflush.reflush(jp);
		return jp;
	}
	//��Ӷ������
	public static JPanel addJPanel_2(JPanel jpanel, int x, int y ,int width, int height) {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(x, y, width, height);
		jpanel.add(jp);
		jp.setVisible(true);
		return jp;
	}
	
	//��Ӱ�ť
	private static Color c = new Color(187,206,219);
	public static JButton addJButton(String s, JPanel jp, int x, int y ,int width, int height,
			boolean opaque, boolean border, boolean left) {
		
		JButton jb = new JButton(s);
		jb.setBounds(x, y, width, height);
		if(opaque) {
			jb.setBackground(c);
			jb.setOpaque(false);
		}
		jb.setBorderPainted(border);
		if(left) {
			jb.setHorizontalAlignment(JButton.LEFT);
			jb.setMargin(new Insets(0,10,0,0));
		}
		
		jp.add(jb); 
		Reflush.reflush(jb);
		return jb;
	}
	//��ӱ�ǩ
	public static JLabel addJLabel(String s, JPanel jp, int x, int y ,int width, int height) {
		JLabel jl = new JLabel(s);
		jl.setBounds(x, y, width, height);
		jp.add(jl);
		Reflush.reflush(jl);
		return jl;
	}
	//����ı���
	public static JTextField addJTextField(JPanel jp, int x, int y ,int width, int height) {
		JTextField jtf = new JTextField(10);
		jtf.setBounds(x, y, width, height);
		jp.add(jtf);
		Reflush.reflush(jtf);
		return jtf;
	}
	
	//��������ı���
	public static JPasswordField addJPasswordField(JPanel jp, int x, int y ,int width, int height) {
		JPasswordField jpf = new JPasswordField(10);
		jpf.setBounds(x, y, width, height);
		jp.add(jpf);
		Reflush.reflush(jpf);
		return jpf;
	}
	
	//����ı��򷵻ط�װ�õ����
	public static JTextArea addJTextArea(JPanel jp, int x, int y ,int width, int height) {
		JTextArea jta = new JTextArea();
		jta.setBounds(x, y, width, height);
		jp.add(jta);
		jta.setVisible(false);
		jta.setLineWrap(true);  		//�Զ�����
		jta.setWrapStyleWord(true);  	//������
		return jta;
	}
	
	//��ӵ�ѡ��
	public static JRadioButton addJRadioButton(String s, JPanel jp, int x, int y ,int width, int height) {
		JRadioButton jrb = new JRadioButton(s);
		jrb.setBounds(x, y, width, height);
		jp.add(jrb);
		Reflush.reflush(jrb);
		return jrb;
	}	
	
	//���ö�ά��ͼ�귵��һ�����
	public static JPanel addCode(JPanel j,String img,int x, int y) {
		Icon i = new ImageIcon("src\\image\\"+img);
		JLabel jl = new JLabel(i);
		JPanel jp = new JPanel(); jp.setBackground(new Color(205,235,234));
		jl.setBounds(0, 0, 90,90);
		jp.setBounds(x, y, 205,205);
		jp.add(jl);
		j.add(jp);
		Reflush.reflush(jp);
		return jp;
	}	
		
	//��ӱ�񣺴�����塢��ͷ������
	public static JTable addJTable(Object[] title, Object[][] arr, JPanel jp, boolean edit) {
		
		//�趨һ��ģʽ��ֻ�ɱ༭һ����
		DefaultTableModel model = new DefaultTableModel(arr, title) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				//Ĭ���趨����������в������޸�
				if(column != 1) {
					return true;
				}else {
					return false;
				}
			}
		};
		JTable table = new JTable(model);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 30));	//���ñ�ͷ�Ŀ�
		
		JScrollPane jsp = new JScrollPane(table);		//�ѱ��ŵ������������
		jp.setLayout(new BorderLayout());			//������ͨ���Ϊ�߽粼�֣��Ӷ�����������ͨ���
		jp.add(jsp, BorderLayout.CENTER);			//�ѹ����������ӵ���ͨ�����
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //������ݾ�����ʾ
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(edit);		//�����Ƿ�ɱ༭
		table.updateUI();
		return table;
	}
	
	//��ӱ�񣺴�����塢��ͷ������
	public static JTable addJTable2(Object[] title, Object[][] arr, JPanel jp, boolean edit) {
		
		//�趨һ��ģʽ��ֻ�ɱ༭һ����
		DefaultTableModel model = new DefaultTableModel(arr, title) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				//Ĭ���趨����������в������޸�
				return false;
			}
		};
		JTable table = new JTable(model);
		table.getTableHeader().setPreferredSize(
				new Dimension(table.getTableHeader().getPreferredSize().width, 30));	//���ñ�ͷ�Ŀ�
		
		JScrollPane jsp = new JScrollPane(table);		//�ѱ��ŵ������������
		jp.setLayout(new BorderLayout());			//������ͨ���Ϊ�߽粼�֣��Ӷ�����������ͨ���
		jp.add(jsp, BorderLayout.CENTER);			//�ѹ����������ӵ���ͨ�����
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   //������ݾ�����ʾ
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
		
		table.setEnabled(edit);		//�����Ƿ�ɱ༭
		table.updateUI();
		return table;
	}
	//��ӱ�񣺴�����塢���顢��ʾͷ
	public static JComboBox addJComboBox(JPanel j, List<String> list, String title, int x, int y, int width, int height) {
		JComboBox<String> cmb = new JComboBox<String>();    //����JComboBox
		cmb.setBounds(x, y, width, height);
        cmb.addItem(title);    	//�������б������һ��
        for (String str : list) {
			cmb.addItem(str);
		}
        j.add(cmb);
        return cmb;
	}
}
