package classes;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import gui.MainGui;

// касса

public class Cashbox extends AbstractService {

	public Cashbox(MainGui gui, JLabel label, Queue queue, JSlider workTime, 
			int maxsize, int amountOfPictures, JTextField served, JTextField amountIn) {
		this.gui = gui;
		this.label = label;
		this.queue = queue;
		this.workTime = workTime;
		this.maxsize = maxsize;
		this.amountOfPictures = amountOfPictures;
		this.served = served;
		this.amountIn = amountIn;
	}

	@Override
	public void run() {
		
		
	}

}
