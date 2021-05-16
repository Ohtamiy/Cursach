package classes;

import java.awt.Component;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JSlider;

import gui.MainGui;

// ������� ���������� ����������
public class Creator implements IFromTo,Runnable {
	
	public MainGui gui;
	public JSlider speed;
	public JLabel label;
	public Random rnd;
	public Visitor visitor;
	public Thread thread;
	
	// ��������� �� ������� ����, ������� �������� �����������, label ��'����
	public Creator(MainGui gui, JSlider speed, JLabel label) {
		this.gui = gui;
		this.speed = speed;
		this.label = label;
	}

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) { }

	@Override
	public Component getComponent() { return label; }

	// ����� ������ ������� ����������
	@Override
	public void run() {
		// ������� �������� � ���������
		synchronized (gui) {
			do {
				// ��������� ������ �����������
				visitor = new Visitor(this.gui, this, 3, choose());
				// ��������� ���� ����
				(this.thread = new Thread(this.visitor)).start();
				try {  
					// ������� ������ ���, ������� ������������
					Thread.sleep((long)(this.speed.getValue()*1000)+(long)(rnd.nextInt(300)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// ��������, ���� �� ���� ��������� ������ ������������
			} while(!this.gui.end);
		}
	}
	
	// ����� ��� ������ ������� �������� ��� ���������
	// � ���� ����, �� �������� ����� � �������� ��� ����� �������
	public Vector<Integer> choose(){
		int amount = rnd.nextInt(3); // �������� �� 0 �� 3
		Vector<Integer> arr = new Vector<>();	
		// ��� � � ��� ����, �� �������� ������ �������
		arr.add(amount); 
		// ������� ���������� ������ �������
		if(amount == 2) { arr.add(1); arr.add(3); } 
		else if(amount == 1) { arr.add(3); arr.add(2); }
		else { arr.add(1); arr.add(2); }
		return arr;
	}
}
