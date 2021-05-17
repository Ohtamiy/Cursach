package classes;

import java.awt.Component;

import javax.swing.JLabel;

import service.IFromTo;

public class LabelsToGo implements IFromTo{
	
	public JLabel label;
	
	public LabelsToGo(JLabel label) { this.label = label; }

	@Override
	public void onOut(Visitor vis) { }

	@Override
	public void onIn(Visitor vis) { }

	@Override
	public Component getComponent() { return label; }

}
