package core.admin.statistics;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JPanel;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
//import org.jfree.chart.plot.PiePlot;
//import org.jfree.data.general.DefaultPieDataset;
public class PieChart {
//	ChartPanel cp;
	public PieChart(){
//		DefaultPieDataset data = getDataSet();
//		JFreeChart chart = ChartFactory.createPieChart3D("ÿ�ܶ���ռ��",data,true,false,false);
//		PiePlot pieplot = (PiePlot) chart.getPlot();
//		DecimalFormat df = new DecimalFormat("0.00%");		//���һ��DecimalFormat������Ҫ������С������
//		NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����
		//���StandardPieSectionLabelGenerator����
//		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);
//		pieplot.setLabelGenerator(sp1);						//���ñ�ͼ��ʾ�ٷֱ�
		
		//û�����ݵ�ʱ����ʾ������
//		pieplot.setNoDataMessage("��������ʾ");
//		pieplot.setCircular(false);
	//	pieplot.setLabelGap(0.02D);
		
//		pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ
//		pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ
//		cp=new ChartPanel (chart,true);
//		chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
//		PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������
//		piePlot.setLabelFont(new Font("����",Font.BOLD,10));//�������
//		chart.getLegend().setItemFont(new Font("����",Font.BOLD,10));
	}
//	private static DefaultPieDataset getDataSet() {
//		String[] arr = StatisticsData.getOrderArrs();
//		DefaultPieDataset dataset = new DefaultPieDataset();
//		dataset.setValue("��һ�ܶ�����",Integer.valueOf(arr[0]));
//		dataset.setValue("�ڶ��ܶ�����",Integer.valueOf(arr[1]));
//		dataset.setValue("�����ܶ�����",Integer.valueOf(arr[2]));
//		dataset.setValue("�����ܶ�����",Integer.valueOf(arr[3]));
//		dataset.setValue("�����ܶ�����",Integer.valueOf(arr[4]));
//		return dataset;
	}
//	public ChartPanel getChartPanel(){
//		return cp;
//	}
//}