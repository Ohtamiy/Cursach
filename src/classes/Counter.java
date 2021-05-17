package classes;

import java.awt.Component;

import javax.swing.JTextField;

import service.IFromTo;

// �������� ��� ������� - �������

public class Counter implements IFromTo{
	
	public JTextField textField;
	private int count;
	
	public Counter(JTextField textField) { this.textField = textField; }

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) {  textField.setText(String.valueOf(++count)); }

	@Override
	public Component getComponent() { return null; }

	public void setCount(int count) { this.count = count; }
	public int getCount() { return count; }
}
