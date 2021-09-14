package core.first.frame;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.first.jpanel.BottomJPanel;
import core.first.jpanel.LoginJPanel;
import core.first.jpanel.RegisterJPanel;
import core.first.response.Response;
import other.Global;
import other.Ui;


/**
 * -��ʼ��������
 * 
 * @author İ����
 *
 */
public class FirstFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public FirstFrame() {
		super();
		initFrame();
		createComonents();
		setCover();
	}
	
	//��ʼ����������
	public void initFrame() {
		this.setSize(Global.WIDTH,Global.HEIGHT);
		this.setTitle("���ն�Ʊϵͳ");
		this.setResizable(false);
		Ui.setFrameImage(this, "plane.jpg");
		Ui.setFrameCenter(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);	
		this.setVisible(true);
		Global.setJf1(this);  //��������屣������
	}
	
	//���ñ���ͼ
	public void setCover() {
		JPanel jp_cover = Ui.setImage(this, "cover.jpg");
	}
	
	//�����������¼/ע����桢��ť
	public void createComonents() {
		
		//��������������ȡ
		JPanel jp_login = LoginJPanel.init(this);		//��¼���
		JPanel jp_register = RegisterJPanel.init(this);	//ע�����
		JPanel jp_bottom = BottomJPanel.init(this);		//��ť���
		
		Response r = new Response(this);
		r.firstResponse();
	}
	
}
