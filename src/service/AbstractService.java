package service;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;

import classes.Counter;
import classes.Queue;
import classes.Visitor;
import gui.MainGui;

public abstract class AbstractService implements IFromTo, Runnable {
	
	public MainGui gui;
	public JLabel label;
	public Queue queue;
	public JSlider workTime;
	public int maxsize;
	public int amountOfPictures;
	public Counter served;
	public Counter amountIn;
	public Random rnd;
	public Vector<Visitor> visitor;
	public String[] pictures;
	
	public AbstractService(MainGui gui, JLabel label, Queue queue, JSlider workTime,
							int maxsize, int amountOfPictures, Counter amountIn, Counter served) {
		this.gui = gui;
		this.label = label;
		this.queue = queue;
		this.workTime = workTime;
		this.maxsize = maxsize;
		this.amountOfPictures = amountOfPictures;
		this.served = served;
		this.amountIn = amountIn;
		this.visitor = new Vector<>(maxsize);
	}

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) { }

	@Override
	public Component getComponent() { return label; }
	
	// ����� ����������� ������ ��'����, ���� ������� ���� ��������
	protected void ShowWorking(String pictures) {
//		// �������� ������� �����
//		int n = amountOfPictures;
//		//�������� ��� ������ �ᒺ���
//		int minMilsTime = this.minWorkTimeSlider.getValue()*1000;
//		// ��������� ���� ��� ���������� ����������� ���� ������ �������
//		int step = minMilsTime / n;
//		// ������ ��������� �������� ��� ������ �������
//		int milsTime = (int)((double)minMilsTime * (1.0D + 2.0D * Math.random()));
//		//������� ���������� ��� ������
//		milsTime = milsTime / step * step;
//		//����������� ��� ����� ������
//		//this.gui.println(this.label.getName() + " ��������� � ������ ������������������ " + step * n);
//		//��������� �������� ��� �������� �����
//		int counter=0;
//		//���� ��� ����������� ����� �� ������������ ��������� ���
//		for(int i = 0; milsTime > 0; milsTime -= step) {
//			//���������� ��������
//			if(counter==this.amountofpictures) {counter=0;}
//			// ���������� ���� ����
//			this.display(picts+counter+".gif");
//			//����� ������
//			System.out.println("Slide# "+ counter);
//			//���������� �� ���������� ����� 
//			counter++;
//			try {
//				//��������
//				Thread.sleep((long)step);
//			} catch (InterruptedException var8) {
//				var8.printStackTrace();
//			}
//
//			++i;
//		}
//		//����������� ��� ��������� ������
//		//this.gui.println(this.label.getName() + " �������� ������ ������������������ " + milsTime);

	}
	
	// ����� ����������� �����
	protected void display(String picture) throws IOException {
		// URL - ��������� �� ���� ����� ���� ���������� ����
		URL u = getClass().getResource(picture);
		// ������� ���������� � icon
		Image image = ImageIO.read(u);
		image = image.getScaledInstance(this.label.getWidth(),this.label.getHeight(),Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(image));
	}

}
