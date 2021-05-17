package classes;

import java.awt.Component;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JLabel;
import javax.swing.JTextField;

import service.IFromTo;

public class Queue implements IFromTo{

	private BlockingQueue<Visitor> queue = new ArrayBlockingQueue<>(3);
	public JTextField textField;
	
	public Queue(JTextField textField) { this.textField = textField; }

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) {
		synchronized (this) {
			if(this.queue.size() < 4)
				this.addToQueue(vis);
			this.notify();
			return;
		}
	}

	@Override
	public Component getComponent() { return null; }
	
	public void addToQueue(Visitor vis) {
		this.queue.add(vis);
		textField.setText(String.valueOf(Integer.parseInt(textField.getText())+1));
	}
	
	public void deleteFromQueue() {
		this.queue.remove();
		textField.setText(String.valueOf(Integer.parseInt(textField.getText())-1));
	}
	
	public void setQueue(BlockingQueue<Visitor> queue) { this.queue = queue; }
	public BlockingQueue<Visitor> getQueue() { return queue; }
	
}
