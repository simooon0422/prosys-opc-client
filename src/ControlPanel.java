import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ControlPanel extends JFrame{
	Sensor[] sensors;
	JLabel[] nameLabels;
	JLabel[] valueLabels;
	int sensorsNumber;
	
	ControlPanel(Sensor[] sensorList)
	{	
		this.sensors = sensorList;
		this.sensorsNumber = sensors.length;

		this.initializeNameLabels();
		this.initializeValueLabels();
		this.initializeFrame();
		this.addLabels();
	}
	
	private void initializeFrame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(200*this.sensorsNumber, 450);
		this.setVisible(true);
	}
	
	private void initializeNameLabels()
	{
		this.nameLabels = new JLabel[this.sensorsNumber];
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			this.nameLabels[i] = new JLabel();
			this.nameLabels[i].setText(this.sensors[i].getName()); //set text of label
			this.nameLabels[i].setHorizontalAlignment(JLabel.CENTER);
			this.nameLabels[i].setForeground(Color.black); //set font color of text
			this.nameLabels[i].setFont(new Font("MV Boli",Font.PLAIN,16)); //set font of text
			this.nameLabels[i].setBackground(Color.cyan); //set background color
			this.nameLabels[i].setOpaque(true); //display background color
			this.nameLabels[i].setBounds(50+(150*i), 50, 120, 30); //set x,y position within frame as well as dimensions
		}
	}
	
	private void initializeValueLabels()
	{
		this.valueLabels = new JLabel[this.sensorsNumber];
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			this.valueLabels[i] = new JLabel();
			this.valueLabels[i].setText(Float.toString(this.sensors[i].getValue())); //set text of label
			this.valueLabels[i].setHorizontalAlignment(JLabel.CENTER);
			this.valueLabels[i].setForeground(Color.black); //set font color of text
			this.valueLabels[i].setFont(new Font("MV Boli",Font.PLAIN,16)); //set font of text
			this.valueLabels[i].setBackground(Color.gray); //set background color
			this.valueLabels[i].setOpaque(true); //display background color
			this.valueLabels[i].setBounds(50+(150*i), 100, 120, 30); //set x,y position within frame as well as dimensions
		}
	}
	
	private void addLabels()
	{
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			this.add(this.nameLabels[i]);
			this.add(this.valueLabels[i]);
		}
	}
	
	public void updateValues()
	{
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			this.valueLabels[i].setText(Float.toString(this.sensors[i].getValue())); //set text of label	
		}
	}
}
