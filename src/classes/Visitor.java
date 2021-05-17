package classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Vector;

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
//		Thread t = new Thread() {
//			public void run() {
//				int hT = 15, wT=15 ;
//				//���������� ��������� �������� ������� �
//				int xFrom = Visitor.this.pointFrom(from).x;
//				int xTo = Visitor.this.pointTo(to).x;
//				if (xFrom > xTo) {
//					//��������� ����-�������
//					xFrom = Visitor.this.pointTo(from).x;
//					xTo = Visitor.this.pointFrom(to).x;
//				}
//				int lenX = xTo - xFrom;
//				//���������� ��������� �������� ������� �
//				int yFrom = Visitor.this.pointFrom(from).y;
//				int yTo = Visitor.this.pointTo(to).y;
//				int lenY = yTo - yFrom;
//				//���������� ������� ��������
//				int len = (int)Math.round(Math.sqrt((double)(lenX * lenX + lenY * lenY)));
//				//���������� �������� ������� ����������  ���������           
//				int lenT = (Visitor.this.hT + Visitor.this.wT) / 2;
//				//���������� ����� ����� ����������
//				int n = len / lenT + 1;
//				//���������� ����� ����������           
//				int dx = lenX / n;
//				int dy = lenY / n;
//
//				Visitor.this.gui.println("���������� �������� ����������� �� " + from.getComponent().getName() + " � " + to.getComponent().getName());
//				//������ ��䳿 �³�������
//				from.onOut(Visitor.this);
//				//��������� �� �������� ������� label
//				Visitor.this.gui.getFrame().add(Visitor.this.label,1);
//				//����������� ���������� ����� �� ��������� ����� �������
//				int x = xFrom;
//				int y = yFrom;
//				int counter=0;
//				//���� ����������            
//				for(int i = 0; i < n; ++i) {
//					//����� ������� ����� �������� (����������)
//					if(counter > pcts.length-1) {counter=0;}
//					//����� ����� ��� �����������
//					URL u = this.getClass().getResource(pcts[counter]);
//					try {
//						//��������� �����
//						Image   image = ImageIO.read(u);
//						//����� �������������
//						if(xFrom>xTo) {image=mirror(image);}
//						//������� ����� � ImageIcon
//						ImageIcon im= new ImageIcon(image);
//						//������� ����� �� JLabel
//						Visitor.this.label.setIcon(im);
//						//���������� Jlabel
//						Visitor.this.label.setBounds(x, y,im.getIconWidth(),im.getIconHeight());
//						//��������
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException var16) {
//							var16.printStackTrace();}
//						//���������� �����������                         						  
//						Visitor.this.label.setBounds(x, y,im.getIconWidth(),im.getIconHeight());
//						//��������� ����� �� ��������� �����                   
//						x += dx;
//						y += dy;
//						counter++;
//					}catch(IOException e) {
//						e.printStackTrace(); 
//					}
//				}  
//				//������ JLabel ��������
//				Visitor.this.label.setIcon(null); 
//				to.onIn(Visitor.this);
//			}
//		};
//		t.start(); 
		
		// ������� ����� �������� ����������
		Thread t = new Thread() {
			public void run() {
				//������ ����������
				int hT = 50, wT=50 ;
				// ��������� �������� ���������� �� �
				
				int xFrom = 300;
				int xTo = 500;
				//int xFrom = from.getComponent().getX();
				//int xTo = to.getComponent().getX();
				
//				if (xFrom > xTo) {
//					// ���� �������� ������-������
//					xFrom = pointTo(from).x;
//					xTo = pointFrom(to).x;
//				}
				int lenX = xTo - xFrom;
				// ��������� �������� ���������� �� �
				
				int yFrom = 100;
				int yTo = 700;
//				int yFrom = from.getComponent().getY();
//				int yTo = to.getComponent().getY();
				
				int lenY = yTo - yFrom;
				// ����� ��������
				int len = (int) (Math.round(Math
						.sqrt(lenX * lenX + lenY * lenY)));
				// ������� ����� ����������
				int lenT = (hT + wT) / 2;
				// ����� ����� �����������
				int n = len / lenT + 1;
				// ���� �����������
				int dx = lenX / n;
				int dy = lenY / n;
				//gui.println("���������� �������� ����������� �� "
						//+ from.getComponent().getName() + " � "
						//+ to.getComponent().getName());
				// ����� ������ ��������� ������� "�����������"
				from.onOut(Visitor.this);
				// ���� �����������
				g.setColor(Color.ORANGE);
				for (int x = xFrom, y = yFrom, i = 0; i < n; x += dx, y += dy) {
					// ������ ����������
					g.fillRect(x, y, wT, hT);
					try {
						// ��������
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// ��������� xor ���������� �������� ����
					g.fillRect(x, y, wT, hT);
				}
				// ����� ������ ��������� ������� "��������"
				to.onIn(Visitor.this);
			}
		};
		return t;
	}
	
//	private Component pointFrom(IFromTo from) {
//
//		return null;
//	}
//	
//	private Component pointTo(IFromTo to) {
//		
//		return to.getComponent();
//	}
	
	// ������������ ����������
	private synchronized Image mirror(Image img) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);			
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);	
		img = op.filter((BufferedImage)img, null);
		return img;	
	}

	// ����� ������� ���������
	// ���� ���, ���� ���� � ��
	@Override
	public void run() {
		try {	
			this.moveFromTo(this.creator, this.gui.lLabelRoute).join();
			if(this.productsToBuy.elementAt(0) == 0) {
				this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
				this.gui.text_fieldLost.setText(String.valueOf(Integer.parseInt(this.gui.text_fieldLost.getText()) + 1));
			}
			else {
				int count = 0;
				while (!this.productsToBuy.isEmpty()) {
					int i = this.productsToBuy.get(count);
					int currentsize = this.productsToBuy.size();
					switch(i) {
					case(1):{
						if(this.gui.queueBread.getQueue().size() <= this.maxQueueSize) {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queueBread).join();
							synchronized (this) {
								while(this.productsToBuy.size()==currentsize) {
									this.wait(); }}}					
						count++;
						this.moveFromTo(this.gui.queueBread, this.gui.lLabelRoute).join();
						break;}
							
					case(2):{
						if(this.gui.queueMilk.getQueue().size() <= this.maxQueueSize) {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queueMilk).join();
							synchronized (this) {
								while(this.productsToBuy.size()==currentsize) {
									this.wait(); }}}
						count++;
						this.moveFromTo(this.gui.queueMilk, this.gui.lLabelRoute).join();
						break;}
										
					case(3):{
						if(this.gui.queueMeat.getQueue().size() <= this.maxQueueSize) {
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queueMeat).join();

							synchronized (this) {
								while(this.productsToBuy.size()==currentsize) {
									this.wait(); }}}
						count++;
						this.moveFromTo(this.gui.queueMeat, this.gui.lLabelRoute).join();	
						break;}
					}
				}
				
			}
			this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
		}
		catch (InterruptedException e) { e.printStackTrace(); }
	}

}
