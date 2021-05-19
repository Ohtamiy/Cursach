package classes;

import java.awt.Component;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import service.IFromTo;

public class Queue implements IFromTo{

	private BlockingQueue<Visitor> queue;
	public int maxSize;
	public Counter counter;
	
	public Queue(int maxSize, Counter counter) { 
		this.maxSize = maxSize;
		this.queue = new ArrayBlockingQueue<>(maxSize);
		this.counter = counter;
	}

	@Override
	public void onOut(Visitor vis) { }

	// ����� ��䳿 "��������"
	@Override
	public void onIn(Visitor vis) {
		synchronized (this) { // ����� � ����������������������� ������
			// ���� ����� �� �����������
			if(this.queue.size() < maxSize) {
				// ������ ��������� � ����������� ��� ��������
				this.addToQueue(vis);
				this.notify();
				return;
			}
		}
	}

	@Override
	public Component getComponent() { return counter.textField; }
	
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
