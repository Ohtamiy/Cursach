package service;

import javax.swing.JLabel;
import javax.swing.JSlider;

import classes.Counter;
import classes.Queue;
import gui.MainGui;

public class Weapon extends AbstractService {

	public Weapon(MainGui gui, JLabel label, Queue queue, JSlider workTime,
			int maxsize, int amountOfPictures, Counter amountIn, Counter served) {
		super(gui, label, queue, workTime, maxsize, amountOfPictures, amountIn, served);
		this.pictures = new String[] {
			"/pictures/fullShelf_weapon.png",
			"/pictures/emptyShelf_weapon.png",
			"/pictures/wideShelf.png"
		};
	}

	@Override
	public void run() {
		int count = 0;
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
						Thread.sleep(workTime.getValue()*1000);
						this.amountIn.setCount(this.amountIn.getCount()-1);
						if(count > pictures.length - 1) count = 0;
						this.display(pictures[count++]);
					}
					catch (Exception e) { e.printStackTrace(); }
					this.visitor.get(k).products--;
					this.visitor.get(k).notify();
					this.served.setCount(this.served.getCount()+1);
				}
			}
			this.visitor.clear();
		}
	}

}
