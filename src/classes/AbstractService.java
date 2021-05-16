package classes;

import java.awt.Component;
import java.net.URL;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import gui.MainGui;

/* Абстрактний клас для каси та 3 товарів
 * Необхідний для того, щоб відображати анімацію об'єкта незважаючи на його тип */
public abstract class AbstractService implements IFromTo, Runnable {
	
	public MainGui gui;
	public JLabel label;
	public Queue queue;
	public JSlider workTime;
	public int maxsize;
	public int amountOfPictures;
	public JTextField served;
	public JTextField amountIn;
	public Random rnd;
	
	//public Vector<Visitor> visitor;
	
	/* посилання на головне вікно, label знаходження об'єкта, чергу об'єкта,
	 * слідер часу обслуговування, максимальний розмір, кількість картинок,
	 * посилання на JTextField поточної кількості всередині об'єкта */
	public AbstractService(MainGui gui, JLabel label, Queue queue, JSlider workTime,
							int maxsize, int amountOfPictures, JTextField served) {
		this.gui = gui;
		this.label = label;
		this.queue = queue;
		this.workTime = workTime;
		this.maxsize = maxsize;
		this.amountOfPictures = amountOfPictures;
		this.served = served;
	}
	
	public AbstractService() {
		super();
	}

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) { }

	// повертає label, де занходиться об'єкт
	@Override
	public Component getComponent() { return label; }
	
	// метод відображення роботи об'єкту, саме анімація зміни картинок
	protected void ShowWorking(String[] pictures) {
		
	}
	
	// метод відображення кадру
	protected void display(String picture) {
		URL u = getClass().getResource(picture);
		ImageIcon image = new ImageIcon(u);
		label.setIcon(image);
	}

}
