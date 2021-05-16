package classes;

import java.awt.Component;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JSlider;

import gui.MainGui;

// Джерело надходжень відвідувачів
public class Creator implements IFromTo,Runnable {
	
	public MainGui gui;
	public JSlider speed;
	public JLabel label;
	public Random rnd;
	public Visitor visitor;
	public Thread thread;
	
	// посилання на головне вікно, слайдер швдикості надходження, label об'єкта
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

	// метод роботи джерела відвідувачів
	@Override
	public void run() {
		// спільним ресурсом є інтерфейс
		synchronized (gui) {
			do {
				// створюємо нового користувача
				visitor = new Visitor(this.gui, this, 3, choose());
				// запускаємо його потік
				(this.thread = new Thread(this.visitor)).start();
				try {  
					// очікуємо певний час, обраний користувачем
					Thread.sleep((long)(this.speed.getValue()*1000)+(long)(rnd.nextInt(300)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// виконуємо, поки не було завершено процес користувачем
			} while(!this.gui.end);
		}
	}
	
	// метод для вибору кількості продуктів для придбання
	// є шанс того, що покупець вийде з магазину без єдиної покупки
	public Vector<Integer> choose(){
		int amount = rnd.nextInt(3); // генеруємо від 0 до 3
		Vector<Integer> arr = new Vector<>();	
		// тут і є той шанс, що покупець покине магазин
		arr.add(amount); 
		// варіанти заповнення масиву покупок
		if(amount == 2) { arr.add(1); arr.add(3); } 
		else if(amount == 1) { arr.add(3); arr.add(2); }
		else { arr.add(1); arr.add(2); }
		return arr;
	}
}
