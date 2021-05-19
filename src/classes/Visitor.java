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

/* Відвідувач
 * З'являється в випадковий період часу
 * Може придбати від 1 до 3 товарів
 * Після чого йде на касу
 * Якщо каса переповнена виходить з системи */
public class Visitor implements Runnable {

	public MainGui gui;
	public Creator creator;
	public Graphics2D g;
	public int maxQueueSize;
	public Vector<Integer> productsToBuy;
	public JLabel label;
	public int products;
	
	// конструктор класу
	// посилання на головне вікно, джерело і максимальну довжину черги
	public Visitor(MainGui gui, Creator creator, int maxQueueSize, Vector<Integer> productsToBuy) {
		this.gui = gui;
		this.g = (Graphics2D)gui.getContentPane().getGraphics();
		this.creator = creator;
		this.maxQueueSize = maxQueueSize;
		this.productsToBuy = productsToBuy;
		this.products = productsToBuy.size();
		this.label = new JLabel();
	}
	
	// метод переміщення об'єкта зі зміною анімації
	public Thread moveFromTo(final IFromTo from, final IFromTo to) {
		Thread t = new Thread() {
			public void run() {
				//Розрахунок параметрів маршруту відносно Х
				int xFrom = Visitor.this.pointFrom(from).getX();
				int xTo = Visitor.this.pointTo(to).getX();
				if (xFrom > xTo) {
					//Перміщення зліва-направо
					xFrom = Visitor.this.pointTo(from).getX();
					xTo = Visitor.this.pointFrom(to).getX();
				}
				int lenX = xTo - xFrom;
				//Розрахунок параметрів маршруту відносно У
				int yFrom = Visitor.this.pointFrom(from).getY();
				int yTo = Visitor.this.pointTo(to).getY();
				int lenY = yTo - yFrom;
				//Розрахунок довжини маршруту
				int len = (int)Math.round(Math.sqrt((double)(lenX * lenX + lenY * lenY)));
				//Розрахунок середньої довжини зображення  відвідувача           
				int lenT = (50 + 50) / 2;
				//Розрахунок числа кроків переміщення
				int n = len / lenT + 1;
				//Розрахунок кроків переміщення           
				int dx = lenX / n;
				int dy = lenY / n;

				//Visitor.this.gui.println("Транзакция начинает перемщаться от " + from.getComponent().getName() + " к " + to.getComponent().getName());
				//Виклик події «Відправка»
				from.onOut(Visitor.this);
				//Додавання на графічну частину label
				//Visitor.this.gui.getFrame().add(Visitor.this.label,1);
				//Ініціалізація початкових точок та лічильника кадрів анімації
				int x = xFrom;
				int y = yFrom;
				//int counter=0;
				//Цикл переміщення            
				for(int i = 0; i < n; ++i) {
					//Умова повтору кадрів анимації (зациклення)
					//if(counter > pcts.length-1) {counter=0;}
					//Пошук кадру для відображення
					URL u = this.getClass().getResource("/pictures/character_stand.png");
					try {
						//Створення кадру
						Image   image = ImageIO.read(u);
						//Умова віддзеркалення
						if(xFrom>xTo) {image=mirror(image);}
						//Перенос кадру в ImageIcon
						ImageIcon im= new ImageIcon(image);
						//перенос кадру на JLabel
						Visitor.this.label.setIcon(im);
						//Переміщення Jlabel
						Visitor.this.label.setBounds(x, y,im.getIconWidth(),im.getIconHeight());
						//Затримка
						try {
							Thread.sleep(100);
						} catch (InterruptedException var16) {
							var16.printStackTrace();}
						//Повторення відображення                         						  
						Visitor.this.label.setBounds(x, y,im.getIconWidth(),im.getIconHeight());
						//Збільшення кроків та лічильника кадрів                   
						x += dx;
						y += dy;
						//counter++;
					}catch(IOException e) {
						e.printStackTrace(); 
					}
				}  
				//Робимо JLabel прозорий
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
	
	// відзеркалення зображення
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
