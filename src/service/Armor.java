package service;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JSlider;

import classes.Counter;
import classes.Queue;
import gui.MainGui;

public class Armor extends AbstractService {
	
	public Armor(MainGui gui, JLabel label, Queue queue, JSlider workTime,
			int maxsize, Counter amountIn, Counter served) {
		super(gui, label, queue, workTime, maxsize, amountIn, served);
		this.pictures = new String[] {
			"/pictures/fullShelf_protection.png",
			"/pictures/emptyShelf_protection.png",
			"/pictures/wideShelf.png"
		};
		try {
			this.display(pictures[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Метод імітації роботи товару
	 * Очікує надходження покупців
	 * Якщо є відвідувач у черзі, почати обслуговування 
	 * Час обслуговуванян обирається користувачем
	 * Під час обслуговування змінюєтсья картинка (мультиплікація) */
	@Override 
	public void run() {
		int count = 0;
		// поки не кінець або черга не порожня
		while(!this.gui.getEndState() || this.queue.getQueue().size() > 0) {
			synchronized(this.queue) {
				// якщо черга порожня
				while(this.queue.getQueue().size() <= 0) {
					try {
						this.queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// забираємо покупця і повідомляємо у чергу
				this.visitor.add(this.queue.deleteFromQueue());
				this.amountIn.setCount(this.amountIn.getCount()+1);
				this.queue.notify();
			}
			// для кожного відвідувача в черзі
			for(int k = 0; k < this.visitor.size(); k++) {
				synchronized (this.visitor.get(k)) {
					try {
						// обслуговуємо
						Thread.sleep(workTime.getValue()*1000);	     
						if(count > pictures.length - 1) count = 0;
						this.display(pictures[count++]);
					}
					catch (Exception e) { e.printStackTrace(); }
					// повідомляємо покупця
					this.visitor.get(k).setCountOfProducts();
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
