package classes;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import gui.MainGui;

//продукт молоко

public class Milk extends AbstractService {

	public Milk(MainGui gui, JLabel label, Queue queue, JSlider workTime, 
			int maxsize, int amountOfPictures, JTextField served) {
		super(gui, label, queue, workTime, maxsize, amountOfPictures, served);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
