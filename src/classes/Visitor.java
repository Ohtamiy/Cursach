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
	
	// метод переміщення об'єкта зі зміною анімації
	public Thread moveFromTo(final IFromTo from, final IFromTo to) {
		// все виконується у окремому потоці
		Thread t = new Thread() {
			public void run() {
				// розрахунок параметрів маршруту відносно Х
				int xFrom = from.getComponent().getX();
				int xTo = to.getComponent().getX();
				int lenX = xTo - xFrom;
				// розрахунок параметрів маршруту відносно У
				int yFrom = from.getComponent().getY();
				int yTo = to.getComponent().getY();
				int lenY = yTo - yFrom;
				// розрахунок довжини маршруту
				int len = (int)Math.round(Math.sqrt((double)(lenX * lenX + lenY * lenY)));
				// розрахунок середньої довжини зображення  відвідувача           
				int lenT = (Visitor.this.label.getWidth() + Visitor.this.label.getHeight()) / 2;
				// розрахунок числа кроків переміщення
				int n = len / lenT + 2;
				// розрахунок кроків переміщення           
				int dx = lenX / n;
				int dy = lenY / n;
				// виклик події «Відправка»
				from.onOut(Visitor.this);
				// цикл переміщення            
				for(int i = 0,x = xFrom,y = yFrom, counter = 0; i < n; ++i, x += dx, y += dy, ++counter) {
					// отримуємо абсолютний шлях до зображення
					if(counter > pcts.length-1) counter = 0;
					URL u = this.getClass().getResource(pcts[counter]);
					try {
						// зчитуємо зображення
						Image image = ImageIO.read(u);
						// якщо рухається вліво
						if(xFrom>xTo) image = mirror(image);
						// змінюємо розміри 
						image = image.getScaledInstance(Visitor.this.label.getWidth(),Visitor.this.label.getHeight(),Image.SCALE_SMOOTH);
						// вставляємо як зображення типу icon
						Visitor.this.label.setIcon(new ImageIcon(image));
						// встановлюємо розміри об'єкта, де буде розміщено зображення
						Visitor.this.label.setBounds(x, y, Visitor.this.label.getWidth(), Visitor.this.label.getHeight());
						try { // чекаємо деякий час
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}   
						// повторюємо зображення, але у новому місці
						Visitor.this.label.setBounds(x, y, Visitor.this.label.getWidth(), Visitor.this.label.getHeight());
					}catch(IOException e) {
						e.printStackTrace(); 
					}
				}  
				// кінець пересування
				Visitor.this.label.setIcon(null); 
				// виклик події "Прибуття"
				to.onIn(Visitor.this);
			}
		};
		t.start(); 
		return t;
	}
	
	// метод відзеркалення зображення
	private synchronized Image mirror(Image img) {
		// використовуємо афінні перетворення
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		// відзеркалюємо вертикально
		tx.translate(-img.getWidth(null), 0);	
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);	
		img = op.filter((BufferedImage)img, null);
		return img;	
	}

	/* Метод імітації дій відвідувача
	 * З'являється у випадковий період часу
	 * Може передумати і піти на вихід
	 * Може придбати від 1 до 3 товарів
	 * Після чого йде на касу
	 * Якщо каса переповнена виходить з системи */
	@Override
	public void run() {
		try {
			// початок руху від входу до вказівника
			this.moveFromTo(this.creator, this.gui.lLabelRoute).join();
			// якщо відвідувач передумав
			if(this.productsToBuy.elementAt(0) == 0) {
				// йде до виходу
				this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
				this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
			}
			else { // йде за покупками
				int count = 0;
				int currentsize = this.productsToBuy.size();
				// поки не вибрав увесь запланований товар
				while (currentsize != 0) {
					int i = this.productsToBuy.get(count);
					// товар обираються у певній послідовності
					switch(i) {
					case(1):{
						// якщо черга не переповнена
						if(this.gui.queueArmor.getQueue().size() < this.maxQueueSize) {
							// йде до товару займати чергу
							this.moveFromTo(this.gui.lLabelRoute, this.gui.queueArmor).join();
							// клієнт є спільновикористовуваним
							synchronized (this) {
								while(this.products == currentsize) { // поки товар не було обрано
									this.wait(); }} // очіється сигнал
							currentsize--;
							count++;
							// йде до вказівника з придбаним товаром
							this.moveFromTo(this.gui.queueArmor, this.gui.lLabelRoute).join();
						}
						else { // переповнена черга, клієнт виходить
							this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
							this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
						}
						break;}
					case(2):{ // аналогічно з іншими товарами
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
				// після того, як було обрано усі товари, перевіряється заповненість черги на касу
				if(this.gui.queueCashbox.getQueue().size() > maxQueueSize) {
					this.moveFromTo(this.gui.lLabelRoute, this.gui.lLabelExit).join();
					this.gui.cLost.setCount(this.gui.cLost.getCount()+1);
				}
				else{ // якщо є місце у черзі
					this.moveFromTo(this.gui.lLabelRoute, this.gui.queueCashbox).join();
					synchronized (this) { // очікуємо обслуговування
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
