package classes;

import java.awt.Component;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JLabel;
import javax.swing.JTextField;

import service.IFromTo;

public class Queue implements IFromTo{

	private BlockingQueue<Visitor> queue;
	public JTextField textField;
	public int maxSize;
	
	public Queue(JTextField textField, int maxSize) { 
		this.textField = textField; 
		this.maxSize = maxSize;
		this.queue = new ArrayBlockingQueue<>(maxSize);
	}

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) {
		synchronized (this) {
			if(this.queue.size() < maxSize)
				this.addToQueue(vis);
			this.notify();
			return;
		}
	}

	@Override
	public Component getComponent() { return textField; }
	
	public void addToQueue(Visitor vis) {
		this.queue.add(vis);
		textField.setText(String.valueOf(Integer.parseInt(textField.getText())+1));
	}
	
	public Visitor deleteFromQueue() {
		Visitor visitor = this.queue.remove();
		textField.setText(String.valueOf(Integer.parseInt(textField.getText())-1));
		return visitor;
	}
	
	public void setQueue(BlockingQueue<Visitor> queue) { this.queue = queue; }
	public BlockingQueue<Visitor> getQueue() { return queue; }
	
}
