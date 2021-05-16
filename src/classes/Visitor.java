package classes;

import java.awt.Graphics2D;
import java.util.Vector;

import gui.MainGui;

/* Відвідувач
 * З'являється в випадковий період часу
 * Може придбати від 1 до 3 товарів
 * Після чого йде на касу
 * Якщо каса переповнена виходить з системи */
public class Visitor implements Runnable {

	public MainGui gui;
	public Creator creator;
	public Graphics2D g;
	public int maxQueueSize;
	public Vector<Integer> productsToBuy;
	
	// конструктор класу
	// посилання на головне вікно, джерело і максимальну довжину черги
	public Visitor(MainGui gui, Creator creator, int maxQueueSize, Vector<Integer> productsToBuy) {
		this.gui = gui;
		this.g = (Graphics2D)gui.getContentPane().getGraphics();
		this.creator = creator;
		this.maxQueueSize = maxQueueSize;
		this.productsToBuy = productsToBuy;
	}
	
	// метод переміщення об'єкта зі зміною анімації
	public Thread moveFromTo(final IFromTo from, final IFromTo to) {

		return null;
	}

	// метод імітації відвідувача
	// куди піде, потім куди і тд
	@Override
	public void run() {
		
		
	}
	
	
	
}
