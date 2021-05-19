package classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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
	public JLabel label;
	public int products;
	
	// ����������� �����
	// ��������� �� ������� ����, ������� � ����������� ������� �����
	public Visitor(MainGui gui, Creator creator, int maxQueueSize, Vector<Integer> productsToBuy) {
		this.gui = gui;
		this.g = (Graphics2D)gui.getContentPane().getGraphics();
		this.creator = creator;
		this.maxQueueSize = maxQueueSize;
		this.productsToBuy = productsToBuy;
		this.products = productsToBuy.size();
		this.label = new JLabel();
	}
	
	// ����� ���������� ��'���� � ����� �������
	public Thread moveFromTo(final IFromTo from, final IFromTo to) {
		Thread t = new Thread() {
			public void run() {
				//���������� ��������� �������� ������� �
				int xFrom = Visitor.this.pointFrom(from).getX();
				int xTo = Visitor.this.pointTo(to).getX();
				if (xFrom > xTo) {
					//��������� ����-�������
					xFrom = Visitor.this.pointTo(from).getX();
					xTo = Visitor.this.pointFrom(to).getX();
				}
				int lenX = xTo - xFrom;
				//���������� ��������� �������� ������� �
				int yFrom = Visitor.this.pointFrom(from).getY();
				int yTo = Visitor.this.pointTo(to).getY();
				int lenY = yTo - yFrom;
				//���������� ������� ��������
				int len = (int)Math.round(Math.sqrt((double)(lenX * lenX + lenY * lenY)));
				//���������� �������� ������� ����������  ���������           
				int lenT = (50 + 50) / 2;
				//���������� ����� ����� ����������
				int n = len / lenT + 1;
				//���������� ����� ����������           
				int dx = lenX / n;
				int dy = lenY / n;

				//Visitor.this.gui.println("���������� �������� ����������� �� " + from.getComponent().getName() + " � " + to.getComponent().getName());
				//������ ��䳿 �³�������
				from.onOut(Visitor.this);
				//��������� �� �������� ������� label
				//Visitor.this.gui.getFrame().add(Visitor.this.label,1);
				//����������� ���������� ����� �� ��������� ����� �������
				int x = xFrom;
				int y = yFrom;
				//int counter=0;
				//���� ����������            
				for(int i = 0; i < n; ++i) {
					//����� ������� ����� �������� (����������)
					//if(counter > pcts.length-1) {counter=0;}
					//����� ����� ��� �����������
					URL u = this.getClass().getResource("/pictures/character_stand.png");
					try {
						//��������� �����
						Image   image = ImageIO.read(u);
						//����� �������������
						if(xFrom>xTo) {image=mirror(image);}
						//������� ����� � ImageIcon
						ImageIcon im= new ImageIcon(image);
						//������� ����� �� JLabel
						Visitor.this.label.setIcon(im);
						//���������� Jlabel
						Visitor.this.label.setBounds(x, y,im.getIconWidth(),im.getIconHeight());
						//��������
						try {
							Thread.sleep(100);
						} catch (InterruptedException var16) {
							var16.printStackTrace();}
						//���������� �����������                         						  
						Visitor.this.label.setBounds(x, y,im.getIconWidth(),im.getIconHeight());
						//��������� ����� �� ��������� �����                   
						x += dx;
						y += dy;
						//counter++;
					}catch(IOException e) {
						e.printStackTrace(); 
					}
				}  
				//������ JLabel ��������
				Visitor.this.label.setIcon(null); 
				to.onIn(Visitor.this);
			}
		};
		t.start(); 
		return t;
	}
	
	private Component pointFrom(IFromTo from) {	
		return from.getComponent();
	}
	
	private Component pointTo(IFromTo to) {
		return to.getComponent();
	}
	
	// ������������ ����������
	private synchronized Image mirror(Image img) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);			
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);	
		img = op.filter((BufferedImage)img, null);
		return img;	
	}

	@Override
	public void run() {
		try {	
			this.moveFromTo(this.creator, this.gui.lLabelRoute).join();
			if(this.productsToBuy.elementAt(0) == 0) {
				this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
				this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
			}
			else {
				int count = 0;
				while (!this.productsToBuy.isEmpty()) {
					int i = this.productsToBuy.get(count);
					int currentsize = this.productsToBuy.size();
					switch(i) {
					case(1):{
						if(this.gui.queueArmor.getQueue().size() <= this.maxQueueSize) {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queueArmor).join();
							synchronized (this) {
								while(this.productsToBuy.size()==currentsize) {
									this.wait(); }}
						}					
						count++;
						this.moveFromTo(this.gui.queueArmor, this.gui.lLabelRoute).join();
						break;}
							
					case(2):{
						if(this.gui.queueWeapon.getQueue().size() <= this.maxQueueSize) {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queueWeapon).join();
							synchronized (this) {
								while(this.productsToBuy.size()==currentsize) {
									this.wait(); }}
						}
						count++;
						this.moveFromTo(this.gui.queueWeapon, this.gui.lLabelRoute).join();
						break;}
										
					case(3):{
						if(this.gui.queuePotion.getQueue().size() <= this.maxQueueSize) {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queuePotion).join();
							synchronized (this) {
								while(this.productsToBuy.size()==currentsize) {
									this.wait(); }}
						}
						count++;
						this.moveFromTo(this.gui.queuePotion, this.gui.lLabelRoute).join();	
						break;}
					}
				}
				if(this.gui.queueCashbox.getQueue().size() > maxQueueSize) {
					this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
					this.gui.text_fieldLost.setText(String.valueOf(Integer.parseInt(this.gui.text_fieldLost.getText())+1));
				}
				else this.moveFromTo(this.gui.lLabelRoute, this.gui.queueCashbox).join();
			}
		}
		catch (InterruptedException e) { e.printStackTrace(); }
	}

}
