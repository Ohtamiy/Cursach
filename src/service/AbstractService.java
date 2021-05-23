package service;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
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
	public Counter served;
	public Counter amountIn;
	public Vector<Visitor> visitor;
	public String[] pictures;
	
	public AbstractService(MainGui gui, JLabel label, Queue queue, JSlider workTime,
							int maxsize, Counter amountIn, Counter served) {
		this.gui = gui;
		this.label = label;
		this.queue = queue;
		this.workTime = workTime;
		this.maxsize = maxsize;
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
