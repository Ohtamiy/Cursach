package service;

import java.awt.Component;
import java.net.URL;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import classes.Counter;
import classes.Queue;
import classes.Visitor;
import gui.MainGui;

/* ����������� ���� ��� ���� �� 3 ������
 * ���������� ��� ����, ��� ���������� ������� ��'���� ���������� �� ���� ��� */
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
	public Vector<Visitor> visitor = new Vector<>(3);
	
	//public Vector<Visitor> visitor;
	
	/* ��������� �� ������� ����, label ����������� ��'����, ����� ��'����,
	 * ����� ���� ��������������, ������������ �����, ������� ��������,
	 * ��������� �� JTextField ������� ������� �������� ��'���� */
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
	}
	
	public AbstractService() { super(); }

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) { }

	// ������� label, �� ����������� ��'���
	@Override
	public Component getComponent() { return label; }
	
	// ����� ����������� ������ ��'����, ���� ������� ���� ��������
	protected void ShowWorking(String pictures) {
		
	}
	
	// ����� ����������� �����
	protected void display(String picture) {
		URL u = getClass().getResource(picture);
		ImageIcon image = new ImageIcon(u);
		label.setIcon(image);
	}

}
