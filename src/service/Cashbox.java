package service;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JSlider;

import classes.Counter;
import classes.Queue;
import gui.MainGui;

public class Cashbox extends AbstractService {
	
	public Cashbox(MainGui gui, JLabel label, Queue queue, JSlider workTime,
			int maxsize, Counter amountIn, Counter served) {
		super(gui, label, queue, workTime, maxsize, amountIn, served);
		this.pictures = new String[] {
			"/pictures/cashbox.png",
			"/pictures/cashbox_not.png",
			"/pictures/cashbox_done.png"
		};
	}

	/* Метод імітації роботи каси
	 * Очікує надходження покупців
	 * Якщо є відвідувач у черзіЮ, почати обслуговування 
	 * Обслуговуванян залежить від кількості покупок клієнта
	 * Під час обслуговування змінюєтсья картинка (мультиплікація) */
	@Override
	public void run() {
		// поки не кінець роботи чи черга не порожня
		while(!this.gui.getEndState() || this.queue.getQueue().size() > 0) {
			synchronized(this.queue) { // черга є спільновикористовуваними даними
				// якщо черга порожня
				while(this.queue.getQueue().size() <= 0) {
					try {
						display(pictures[0]);
						this.queue.wait(); // очікуємо
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// якщо не порожня, забираємо відвідувача
				this.visitor.add(this.queue.deleteFromQueue());
				this.amountIn.setCount(this.amountIn.getCount()+1);
				this.queue.notify(); // сповіщаємо чергу
			}
			// для кожного відвідувача в черзі
			for(int k = 0; k < this.visitor.size(); k++) {
				synchronized (this.visitor.get(k)) {
					try {
						// обслуговуємо
						display(pictures[1]);
						Thread.sleep(this.visitor.get(k).getProductsToBuy().size()*1500);
						display(pictures[2]);
						Thread.sleep(500);
					}
					catch (Exception e) { e.printStackTrace(); }
					// повідомляємо покупця
					this.visitor.get(k).clearProductsToBuy();
					this.visitor.get(k).notify();
					synchronized (this.amountIn) {
						this.amountIn.setCount(this.amountIn.getCount()-1);
					}
					this.served.setCount(this.served.getCount()+1);
				}
			}
			this.visitor.clear();
		}
	}

}
