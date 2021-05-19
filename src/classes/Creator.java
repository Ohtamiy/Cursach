package classes;

import java.awt.Component;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JSlider;

import gui.MainGui;
import service.IFromTo;

public class Creator implements IFromTo,Runnable {
	
	public MainGui gui;
	public JSlider speed;
	public JLabel label;
	public Random rnd;
	public Visitor visitor;
	public Thread thread;
	public int maxQueueSize;
	
	public Map<Integer,Vector<Integer>> choices = Map.ofEntries(
		Map.entry(0, new Vector<Integer>() {{ add(0); }}),
		Map.entry(1, new Vector<Integer>() {{ add(1); }}),
		Map.entry(2, new Vector<Integer>() {{ add(2); }}),
		Map.entry(3, new Vector<Integer>() {{ add(3); }}),
		Map.entry(4, new Vector<Integer>() {{ add(1); add(2); }}),
		Map.entry(5, new Vector<Integer>() {{ add(2); add(1); }}),
		Map.entry(6, new Vector<Integer>() {{ add(1); add(3); }}),
		Map.entry(7, new Vector<Integer>() {{ add(3); add(1); }}),
		Map.entry(8, new Vector<Integer>() {{ add(2); add(3); }}),
		Map.entry(9, new Vector<Integer>() {{ add(3); add(2); }}),
		Map.entry(10, new Vector<Integer>() {{ add(1); add(2); add(3); }}),
		Map.entry(11, new Vector<Integer>() {{ add(1); add(3); add(2); }}),
		Map.entry(12, new Vector<Integer>() {{ add(2); add(1); add(3); }}),
		Map.entry(13, new Vector<Integer>() {{ add(2); add(3); add(1); }}),
		Map.entry(14, new Vector<Integer>() {{ add(3); add(1); add(2); }}),
		Map.entry(15, new Vector<Integer>() {{ add(3); add(2); add(1); }})
	);
	
	public Creator(MainGui gui, JSlider speed, JLabel label) {
		this.gui = gui;
		this.speed = speed;
		this.label = label;
		this.maxQueueSize = this.gui.maxQueueSize;
		this.rnd = new Random();
	}

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) { }

	@Override
	public Component getComponent() { return label; }

	// метод роботи джерела відвідувачів
	@Override
	public void run() {
		// спільним ресурсом є інтерфейс
		synchronized (gui) {
			int counter = 0;
			do {
				// створюємо нового користувача
				visitor = new Visitor(this.gui, this, maxQueueSize, choices.get(rnd.nextInt(16)));
				// запускаємо його потік
				(this.thread = new Thread(this.visitor)).start();
				System.out.println("Visitor#" + counter++ + " created. His choice: " +  visitor.productsToBuy);
				try {  
					// очікуємо певний час, обраний користувачем
					Thread.sleep(this.speed.getValue()*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// виконуємо, поки не було завершено процес користувачем
			} while(!this.gui.end);
		}
	}

}
