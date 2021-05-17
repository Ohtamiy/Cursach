package classes;

import java.awt.Graphics2D;
import java.util.Vector;

import gui.MainGui;
import service.IFromTo;

/* ³�������
 * �'��������� � ���������� ����� ����
 * ���� �������� �� 1 �� 3 ������
 * ϳ��� ���� ��� �� ����
 * ���� ���� ����������� �������� � ������� */
public class Visitor implements Runnable {

	public MainGui gui;
	public Creator creator;
	public Graphics2D g;
	public int maxQueueSize;
	public Vector<Integer> productsToBuy;
	
	// ����������� �����
	// ��������� �� ������� ����, ������� � ����������� ������� �����
	public Visitor(MainGui gui, Creator creator, int maxQueueSize, Vector<Integer> productsToBuy) {
		this.gui = gui;
		this.g = (Graphics2D)gui.getContentPane().getGraphics();
		this.creator = creator;
		this.maxQueueSize = maxQueueSize;
		this.productsToBuy = productsToBuy;
	}
	
	// ����� ���������� ��'���� � ����� �������
	public Thread moveFromTo(final IFromTo from, final IFromTo to) {

		return null;
	}

	// ����� ������� ���������
	// ���� ���, ���� ���� � ��
	@Override
	public void run() {
		
		
	}
	
	
	
}
