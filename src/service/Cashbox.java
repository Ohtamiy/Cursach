package service;

import javax.swing.JLabel;
import javax.swing.JSlider;

import classes.Counter;
import classes.Queue;
import gui.MainGui;

public class Cashbox extends AbstractService {
	
	public Cashbox(MainGui gui, JLabel label, Queue queue, JSlider workTime,
			int maxsize, int amountOfPictures, Counter amountIn, Counter served) {
		super(gui, label, queue, workTime, maxsize, amountOfPictures, amountIn, served);
		this.pictures = new String[] {
			"/pictures/cashbox.png"
//			"/pictures/emptyShelf_potion.png",
//			"/pictures/shelf_potion.png"
		};
	}

	@Override
	public void run() {
		while(!this.gui.end || this.queue.getQueue().size() > 0) {
			synchronized(this.queue) {
				while(this.queue.getQueue().size() <= 0) {
					try {
						this.queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				this.visitor.add(this.queue.deleteFromQueue());
				this.amountIn.setCount(this.amountIn.getCount()+1);
				this.queue.notify();
			}
			for(int k = 0; k < this.visitor.size(); k++) {
				synchronized (this.visitor.get(k)) {
					try {
						Thread.sleep(this.visitor.get(k).productsToBuy.size()*1500);
						this.amountIn.setCount(this.amountIn.getCount()-1);
					}
					catch (Exception e) { e.printStackTrace(); }
					this.visitor.get(k).productsToBuy.clear();
					this.visitor.get(k).notify();
					this.served.setCount(this.served.getCount()+1);
				}
			}
			this.visitor.clear();
		}
	}

}
