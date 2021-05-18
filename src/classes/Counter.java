package classes;

import java.awt.Component;

import javax.swing.JTextField;

import service.IFromTo;

// счетчики для всякого - разного

public class Counter implements IFromTo {
	
	public JTextField textField;
	private int count;
	
	public Counter(JTextField textField) { this.textField = textField; }

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) {  textField.setText(String.valueOf(++count)); }

	@Override
	public Component getComponent() { return null; }

	public void setCount(int count) { 
		this.count = count;
		textField.setText(String.valueOf(count)); 
	}
	public int getCount() { 
		this.count = Integer.parseInt(textField.getText()); 
		return count;
	}
}
