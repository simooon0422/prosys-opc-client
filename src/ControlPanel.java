import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ControlPanel extends JFrame{
	Sensor[] sensors;
	JLabel[] labels;
	int sensorsNumber;
	
	ControlPanel(Sensor[] sensorList)
	{	
		this.sensors = sensorList;
		this.sensorsNumber = sensors.length;

		this.initializeLabels();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(450, 450);
		this.setVisible(true);
		this.addLabels();
	}
	
	void initializeLabels()
	{
		this.labels = new JLabel[sensorsNumber];
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			this.labels[i] = new JLabel();
			this.labels[i].setText(this.sensors[i].getName()); //set text of label
			this.labels[i].setForeground(Color.black); //set font color of text
			this.labels[i].setFont(new Font("MV Boli",Font.PLAIN,16)); //set font of text
			this.labels[i].setBackground(Color.cyan); //set background color
			this.labels[i].setOpaque(true); //display background color
			this.labels[i].setBounds(50, 50, 100, 30); //set x,y position within frame as well as dimensions
		}
	}
	
	void addLabels()
	{
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			this.add(this.labels[i]);
		}
	}
}
