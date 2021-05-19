package classes;


import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.MainGui;
import service.IFromTo;

public class Visitor implements Runnable {

	public MainGui gui;
	public Creator creator;
	public int maxQueueSize;
	public Vector<Integer> productsToBuy;
	public JLabel label;
	public int products;
	public String[] pcts;
	
	public Visitor(MainGui gui, Creator creator, int maxQueueSize, Vector<Integer> productsToBuy) {
		this.gui = gui;
		this.creator = creator;
		this.maxQueueSize = maxQueueSize;
		this.productsToBuy = productsToBuy;
		this.products = productsToBuy.size();
		this.label = new JLabel();
		label.setBounds(this.creator.getComponent().getX(), this.creator.getComponent().getY(), 50, 70);
		this.gui.contentPane.add(label);
		this.pcts = new String[] { "/pictures/character_step1.png", "/pictures/character_step2.png" };
	}
	
	// ����� ���������� ��'���� � ����� �������
	public Thread moveFromTo(final IFromTo from, final IFromTo to) {
		// ��� ���������� � �������� ������
		Thread t = new Thread() {
			public void run() {
				// ���������� ��������� �������� ������� �
				int xFrom = from.getComponent().getX();
				int xTo = to.getComponent().getX();
				int lenX = xTo - xFrom;
				// ���������� ��������� �������� ������� �
				int yFrom = from.getComponent().getY();
				int yTo = to.getComponent().getY();
				int lenY = yTo - yFrom;
				// ���������� ������� ��������
				int len = (int)Math.round(Math.sqrt((double)(lenX * lenX + lenY * lenY)));
				// ���������� �������� ������� ����������  ���������           
				int lenT = (Visitor.this.label.getWidth() + Visitor.this.label.getHeight()) / 2;
				// ���������� ����� ����� ����������
				int n = len / lenT + 2;
				// ���������� ����� ����������           
				int dx = lenX / n;
				int dy = lenY / n;
				// ������ ��䳿 �³�������
				from.onOut(Visitor.this);
				// ���� ����������            
				for(int i = 0,x = xFrom,y = yFrom, counter = 0; i < n; ++i, x += dx, y += dy, ++counter) {
					// �������� ���������� ���� �� ����������
					if(counter > pcts.length-1) counter = 0;
					URL u = this.getClass().getResource(pcts[counter]);
					try {
						// ������� ����������
						Image image = ImageIO.read(u);
						// ���� �������� ����
						if(xFrom>xTo) image = mirror(image);
						// ������� ������ 
						image = image.getScaledInstance(Visitor.this.label.getWidth(),Visitor.this.label.getHeight(),Image.SCALE_SMOOTH);
						// ���������� �� ���������� ���� icon
						Visitor.this.label.setIcon(new ImageIcon(image));
						// ������������ ������ ��'����, �� ���� �������� ����������
						Visitor.this.label.setBounds(x, y, Visitor.this.label.getWidth(), Visitor.this.label.getHeight());
						try { // ������ ������ ���
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}   
						// ���������� ����������, ��� � ������ ����
						Visitor.this.label.setBounds(x, y, Visitor.this.label.getWidth(), Visitor.this.label.getHeight());
					}catch(IOException e) {
						e.printStackTrace(); 
					}
				}  
				// ����� �����������
				Visitor.this.label.setIcon(null); 
				// ������ ��䳿 "��������"
				to.onIn(Visitor.this);
			}
		};
		t.start(); 
		return t;
	}
	
	// ����� ������������ ����������
	private synchronized Image mirror(Image img) {
		// ������������� ����� ������������
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		// ������������ �����������
		tx.translate(-img.getWidth(null), 0);	
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);	
		img = op.filter((BufferedImage)img, null);
		return img;	
	}

	/* ����� ������� �� ���������
	 * �'��������� � ���������� ����� ����
	 * ���� ���������� � ��� �� �����
	 * ���� �������� �� 1 �� 3 ������
	 * ϳ��� ���� ��� �� ����
	 * ���� ���� ����������� �������� � ������� */
	@Override
	public void run() {
		try {
			// ������� ���� �� ����� �� ���������
			this.moveFromTo(this.creator, this.gui.lLabelRoute).join();
			// ���� �������� ���������
			if(this.productsToBuy.elementAt(0) == 0) {
				// ��� �� ������
				this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
				this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
			}
			else { // ��� �� ���������
				int count = 0;
				int currentsize = this.productsToBuy.size();
				// ���� �� ������ ����� ������������ �����
				while (currentsize != 0) {
					int i = this.productsToBuy.get(count);
					// ����� ���������� � ����� �����������
					switch(i) {
					case(1):{
						// ���� ����� �� �����������
						if(this.gui.queueArmor.getQueue().size() < this.maxQueueSize) {
							// ��� �� ������ ������� �����
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queueArmor).join();
							// �볺�� � ����������������������
							synchronized (this) {
								while(this.products == currentsize) { // ���� ����� �� ���� ������
									this.wait(); }} // �������� ������
							currentsize--;
							count++;
							// ��� �� ��������� � ��������� �������
							this.moveFromTo(this.gui.queueArmor, this.gui.lLabelRoute).join();
						}
						else { // ����������� �����, �볺�� ��������
							this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
							this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
						}
						break;}
					case(2):{ // ��������� � ������ ��������
						if(this.gui.queueWeapon.getQueue().size() < this.maxQueueSize) {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queueWeapon).join();
							synchronized (this) {
								while(this.products == currentsize) {
									this.wait(); }}
							currentsize--;
							this.moveFromTo(this.gui.queueWeapon, this.gui.lLabelRoute).join();
						}
						else {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
							this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
						}
						break;}
										
					case(3):{
						if(this.gui.queuePotion.getQueue().size() < this.maxQueueSize) {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queuePotion).join();
							synchronized (this) {
								while(this.products == currentsize) {
									this.wait(); }}
							currentsize--;
							this.moveFromTo(this.gui.queuePotion, this.gui.lLabelRoute).join();
						}
						else {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
							this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
						}
						break;}
					}
				}
				// ���� ����, �� ���� ������ �� ������, ������������ ����������� ����� �� ����
				if(this.gui.queueCashbox.getQueue().size() > maxQueueSize) {
					this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
					this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
				}
				else{ // ���� � ���� � ����
					this.moveFromTo(this.gui.lLabelRoute, this.gui.queueCashbox).join();
					synchronized (this) { // ������� ��������������
						while(!this.productsToBuy.isEmpty())
							this.wait();
					}
					this.moveFromTo(this.gui.queueCashbox, this.gui.lLabelExit).join();
				}
			}
		}
		catch (InterruptedException e) { e.printStackTrace(); }
	}

}
