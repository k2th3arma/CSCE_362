import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Objects;

import javax.swing.*;


public class Player extends JFrame{
	
	int width = 300, height = 100;
	
	JButton play = new JButton("Send");

	CardLayout layout = new CardLayout();
	
	JPanel menu = new JPanel(); 

	public Player() {
		
		String s = "Bob";
		s = (String)JOptionPane.showInputDialog(menu, "Whats your name?", "",
		                    JOptionPane.PLAIN_MESSAGE);
		
		Data data = new Data();
		
		try {
			data.compile(s, Screen.score);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
