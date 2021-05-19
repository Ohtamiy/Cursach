package service;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;

import classes.Counter;
import classes.Queue;
import classes.Visitor;
import gui.MainGui;

public abstract class AbstractService implements IFromTo, Runnable {
	
	public MainGui gui;
	public JLabel label;
	public Queue queue;
	public JSlider workTime;
	public int maxsize;
	public int amountOfPictures;
	public Counter served;
	public Counter amountIn;
	public Random rnd;
	public Vector<Visitor> visitor;
	public String[] pictures;
	
	public AbstractService(MainGui gui, JLabel label, Queue queue, JSlider workTime,
							int maxsize, int amountOfPictures, Counter amountIn, Counter served) {
		this.gui = gui;
		this.label = label;
		this.queue = queue;
		this.workTime = workTime;
		this.maxsize = maxsize;
		this.amountOfPictures = amountOfPictures;
		this.served = served;
		this.amountIn = amountIn;
		this.visitor = new Vector<>(maxsize);
	}

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) { }

	@Override
	public Component getComponent() { return label; }
	
	// метод відображення роботи об'єкту, саме анімація зміни картинок
	protected void ShowWorking(String pictures) {
//		// Отримуємо кількість кадрів
//		int n = amountOfPictures;
//		//Отримуємо час роботи об’єкту
//		int minMilsTime = this.minWorkTimeSlider.getValue()*1000;
//		// Створюємо крок для формування остаточного часу роботи апарату
//		int step = minMilsTime / n;
//		// Додаємо випадкову величину для роботи апарату
//		int milsTime = (int)((double)minMilsTime * (1.0D + 2.0D * Math.random()));
//		//Формуємо остаточний час роботи
//		milsTime = milsTime / step * step;
//		//Повідомлення про старт роботи
//		//this.gui.println(this.label.getName() + " приступил к работе продолжительностью " + step * n);
//		//Створюємо лічильник для підрахнку кадрів
//		int counter=0;
//		//цикл для відображення кадрів за встановлений формулами час
//		for(int i = 0; milsTime > 0; milsTime -= step) {
//			//зациклення анимації
//			if(counter==this.amountofpictures) {counter=0;}
//			// відображуємо один кадр
//			this.display(picts+counter+".gif");
//			//номер слайду
//			System.out.println("Slide# "+ counter);
//			//переходимо до наступного кадру 
//			counter++;
//			try {
//				//Затримка
//				Thread.sleep((long)step);
//			} catch (InterruptedException var8) {
//				var8.printStackTrace();
//			}
//
//			++i;
//		}
//		//Повідомлення про закінчення роботи
//		//this.gui.println(this.label.getName() + " закончил работу продолжительностью " + milsTime);

	}
	
	// метод відображення кадру
	protected void display(String picture) throws IOException {
		// URL - посилання на файл через його абсолютний шлях
		URL u = getClass().getResource(picture);
		// зчитуємо зображення в icon
		Image image = ImageIO.read(u);
		image = image.getScaledInstance(this.label.getWidth(),this.label.getHeight(),Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(image));
	}

}
