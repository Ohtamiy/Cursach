package classes;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JSlider;

import gui.MainGui;
import service.IFromTo;

// ������� ���������� ����������
public class Creator implements IFromTo,Runnable {
	
	public MainGui gui;
	public JSlider speed;
	public JLabel label;
	public Random rnd = new Random();
	public Visitor visitor;
	public Thread thread;
	
	public Map<Integer,Vector<Integer>> choices = Map.ofEntries(
		Map.entry(0, new Vector<Integer>() {{ add(0); }}),
		Map.entry(1, new Vector<Integer>() {{ add(1); add(2); }}),
		Map.entry(2, new Vector<Integer>() {{ add(2); add(1); }}),
		Map.entry(3, new Vector<Integer>() {{ add(1); add(3); }}),
		Map.entry(4, new Vector<Integer>() {{ add(3); add(1); }}),
		Map.entry(5, new Vector<Integer>() {{ add(2); add(3); }}),
		Map.entry(6, new Vector<Integer>() {{ add(3); add(2); }}),
		Map.entry(7, new Vector<Integer>() {{ add(1); add(2); add(3); }}),
		Map.entry(8, new Vector<Integer>() {{ add(1); add(3); add(2); }}),
		Map.entry(9, new Vector<Integer>() {{ add(2); add(1); add(3); }}),
		Map.entry(10, new Vector<Integer>() {{ add(2); add(3); add(1); }}),
		Map.entry(11, new Vector<Integer>() {{ add(3); add(1); add(2); }}),
		Map.entry(12, new Vector<Integer>() {{ add(3); add(2); add(1); }})
	);
	
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
			int counter = 0;
			do {
				// ��������� ������ �����������
				visitor = new Visitor(this.gui, this, 3, choices.get(rnd.nextInt(13)));
				// ��������� ���� ����
				(this.thread = new Thread(this.visitor)).start();
				System.out.println("Visitor#" + counter++ + " created. His choice: " +  visitor.productsToBuy);
				try {  
					// ������� ������ ���, ������� ������������
					Thread.sleep((long)(this.speed.getValue()*1000)+(long)(rnd.nextInt(300)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// ��������, ���� �� ���� ��������� ������ ������������
			} while(!this.gui.end);
//			try {
//				this.thread.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}

}
