package service;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import classes.Queue;
import gui.MainGui;

// продукт хлеб

public class Bread extends AbstractService {

	public Bread(MainGui gui, JLabel label, Queue queue, JSlider workTime, 
			int maxsize, int amountOfPictures, JTextField served) {
		super(gui, label, queue, workTime, maxsize, amountOfPictures, served);
	}

	@Override 
	public void run() {
		
	}

}
