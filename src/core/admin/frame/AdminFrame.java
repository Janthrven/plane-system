package core.admin.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.admin.jpanel.InputJPanel;
import core.admin.jpanel.InquiryJPanel;
import core.admin.jpanel.MenuJPanel;
import core.admin.jpanel.StatisticsJPanel;
import core.admin.jpanel.WelcomeJPanel;
import core.admin.response.Response;
import other.AddComonents;
import other.Global;
import other.Ui;

/**
 * - ����Ա����
 * @author İ����
 *
 */
public class AdminFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8615943501450868493L;

	public AdminFrame(String name) {
		super();
		initFrame();
		createComonents(name);
	}
	
	//��ʼ����������
	public void initFrame() {
		this.setSize(Global.WIDTH,Global.HEIGHT);
		this.setTitle("�ѵ�¼");
		this.setResizable(false);
		Ui.setFrameImage(this, "plane.jpg");
		Ui.setFrameCenter(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);	
		this.setVisible(false);
		Global.setJf2(this);  //��������屣������
	}
	
	public void createComonents(String name) {
		
		WelcomeJPanel.init(this,name);
		MenuJPanel.init(this);
		InquiryJPanel.init(this);
		InputJPanel.init(this);
		StatisticsJPanel.init(this);
		JPanel jp_cover = Ui.setImage(this, "cover2.jpg");

		Response r = new Response(this);
		r.adminResponse();
		this.setVisible(true);
	}
}
