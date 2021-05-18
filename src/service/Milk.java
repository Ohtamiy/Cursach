package service;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import classes.Counter;
import classes.Queue;
import gui.MainGui;

//продукт молоко

public class Milk extends AbstractService {

	public Milk(MainGui gui, JLabel label, Queue queue, JSlider workTime,
			int maxsize, int amountOfPictures, Counter amountIn, Counter served) {
		super(gui, label, queue, workTime, maxsize, amountOfPictures, amountIn, served);
	}

	@Override
	public void run() {
		while(!this.gui.end || this.queue.getQueue().size() > 0) {
			synchronized(this.queue) {
				while(!this.queue.getQueue().isEmpty()) {
					while(this.queue.getQueue().size() <= 0) {
						//this.display("/res/eye/little_eye.gif");
						//this.gui.println(this.label.getName() + " ждет, появления транзакции в очереди");
						try {
							this.queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if(!this.queue.getQueue().isEmpty()) {
						//this.gui.println(this.label.getName() + " заказывает транзакцию из очереди");

						this.visitor.add(this.queue.deleteFromQueue());
						//this.amountinAtr.setText(String.valueOf((Integer.parseInt(this.amountinAtr.getText())+1)));
						this.queue.notify();
					}
				}
			}
			//Кінець блоку синхронізації
			//this.gui.println(this.label.getName() + " ждет, пока транзакция попадет к нему");


			//Імітація роботи атракціону
			//this.ShowWorking("/res/eye/");   


			//this.display("/res/eye/little_eye.gif");
			//this.gui.println(this.label.getName() + " остановлен");
			for(int k = 0; k < this.visitor.size(); k++) {
				synchronized (this.visitor.get(k)) {
					try {
						Thread.sleep(workTime.getValue());	       
						//this.display("/res/other/"+this.vis.get(k).getPlace().size()+"11.png");  
						this.amountIn.setCount(this.amountIn.getCount()+1);
					}
					catch (Exception e) { e.printStackTrace(); }
					//this.gui.println(this.label.getName() + " остановлен");
					//this.display("/res/other/ticketBox.png"); 
					this.visitor.get(k).notify();
					this.served.setCount(this.served.getCount()+1);
					//this.gui.println(this.vis.get(k).place.toString());
				}
			}
			//Очищення апарату
			//this.amountinAtr.setText("0");   
			//this.amountinAtr.setForeground(Color.black);
			this.visitor.clear();

		}
	}

}
