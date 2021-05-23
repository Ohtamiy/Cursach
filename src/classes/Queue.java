package classes;

import java.awt.Component;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import service.IFromTo;

public class Queue implements IFromTo{

	private BlockingQueue<Visitor> queue;
	private int maxSize;
	private Counter counter;
	
	public Queue(int maxSize, Counter counter) { 
		this.maxSize = maxSize;
		this.queue = new ArrayBlockingQueue<>(maxSize);
		this.counter = counter;
	}

	@Override
	public void onOut(Visitor vis) { }

	// метод події "Прибуття"
	@Override
	public void onIn(Visitor vis) {
		synchronized (this) { // черга є спільновикористовуваними даними
			// якщо черга не переповнена
			if(this.queue.size() < maxSize) {
				// додаємо відвідувача і повідомляємо усіх слухачів
				this.addToQueue(vis);
				this.notify();
				return;
			}
		}
	}

	@Override
	public Component getComponent() { return counter.getComponent(); }
	
	public void addToQueue(Visitor vis) {
		this.queue.add(vis);
		counter.setCount(counter.getCount()+1);
	}
	
	public Visitor deleteFromQueue() {
		Visitor visitor = this.queue.remove();
		counter.setCount(counter.getCount()-1);
		return visitor;
	}
	
	public void setQueue(BlockingQueue<Visitor> queue) { this.queue = queue; }
	public BlockingQueue<Visitor> getQueue() { return queue; }
	
}
