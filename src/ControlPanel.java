import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ControlPanel extends JFrame implements ActionListener{
	Sensor[] sensors;
	JLabel[] nameLabels;
	JLabel[] valueLabels;
	JButton[] buttons;
	
	int sensorsNumber;
	
	ControlPanel(Sensor[] sensorList)
	{	
		this.sensors = sensorList;
		this.sensorsNumber = sensors.length;

		this.initializeNameLabels();
		this.initializeValueLabels();
		this.initializeButtons();
		this.initializeFrame();
		this.addComponents();
	}
	
	private void initializeFrame()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(160*this.sensorsNumber, 300);
		this.setVisible(true);
	}
	
	private void initializeNameLabels()
	{
		this.nameLabels = new JLabel[this.sensorsNumber];
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			this.nameLabels[i] = new JLabel();
			this.nameLabels[i].setText(this.sensors[i].getSensorName()); //set text of label
			this.nameLabels[i].setHorizontalAlignment(JLabel.CENTER);
			this.nameLabels[i].setForeground(Color.black); //set font color of text
			this.nameLabels[i].setFont(new Font("Arial",Font.PLAIN,16)); //set font of text
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
			String value = String.valueOf(this.sensors[i].getValue());
			this.valueLabels[i] = new JLabel();
			this.valueLabels[i].setText(value); //set text of label
			this.valueLabels[i].setHorizontalAlignment(JLabel.CENTER);
			this.valueLabels[i].setForeground(Color.black); //set font color of text
			this.valueLabels[i].setFont(new Font("Arial",Font.PLAIN,16)); //set font of text
			this.valueLabels[i].setBackground(Color.gray); //set background color
			this.valueLabels[i].setOpaque(true); //display background color
			this.valueLabels[i].setBounds(50+(150*i), 100, 120, 30); //set x,y position within frame as well as dimensions
		}
	}
	
	private void initializeButtons()
	{
		this.buttons = new JButton[this.sensorsNumber];
		
		for(int i = 0; i < this.sensorsNumber; i++)
		{
			this.buttons[i] = new JButton();
			this.buttons[i].setBounds(50+(150*i), 150, 120, 30);
			this.buttons[i].addActionListener(this);
			this.buttons[i].setText("Awaria");
			this.buttons[i].setHorizontalAlignment(JLabel.CENTER);
			this.buttons[i].setFont(new Font("Comic Sans",Font.BOLD,16));
			this.buttons[i].setForeground(Color.BLACK);
			this.buttons[i].setBackground(Color.LIGHT_GRAY);
			this.buttons[i].setBorder(BorderFactory.createEtchedBorder());
		}
	}
	
	private void addComponents()
	{
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			this.add(this.nameLabels[i]);
			this.add(this.valueLabels[i]);
			this.add(this.buttons[i]);
		}
	}
	
	public void updateValues()
	{
		for (int i = 0; i < this.sensorsNumber; i++)
		{
			String value = String.valueOf(this.sensors[i].getValue());
			this.valueLabels[i].setText(value); //set text of label	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < this.buttons.length; i++)
		{
			if (e.getSource() == this.buttons[i]) 
			{
				this.sensors[i].setMalfunction();
				if (this.sensors[i].getMalfunction())
				{
					this.buttons[i].setBackground(Color.RED);
				} else this.buttons[i].setBackground(Color.LIGHT_GRAY);
			}
		}
	}
}
