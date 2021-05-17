package service;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import classes.Queue;
import gui.MainGui;

//продукт мясо

public class Meat extends AbstractService {

	public Meat(MainGui gui, JLabel label, Queue queue, JSlider workTime, 
			int maxsize, int amountOfPictures, JTextField served) {
		super(gui, label, queue, workTime, maxsize, amountOfPictures, served);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
