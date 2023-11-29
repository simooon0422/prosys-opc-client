import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ControlPanel extends JFrame{
	ControlPanel()
	{	
		JLabel label = new JLabel(); //create a label
		initializeLabels(label);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(450, 450);
		this.setVisible(true);
		this.add(label);
	}
	
	void initializeLabels(JLabel label)
	{
		label.setText("Temperature"); //set text of label
		label.setForeground(Color.black); //set font color of text
		label.setFont(new Font("MV Boli",Font.PLAIN,16)); //set font of text
		label.setBackground(Color.cyan); //set background color
		label.setOpaque(true); //display background color
		label.setBounds(50, 50, 100, 30); //set x,y position within frame as well as dimensions
	}
}
