import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

	/*
	 * 	The menu class produces a menu with two options. One option begins produces the game panel and initializes the game loop.
	 * 	The other ends shuts the application down.
	 * 
	 * 	NOTE*
	 * 	This Screen has the ability to lay out rules and explain the game. 
	 */


public class DevTool extends JFrame implements ActionListener {
	
	int width = 500, height = 200;
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	
	CardLayout layout = new CardLayout();
	GridBagLayout L1 = new GridBagLayout();

	JPanel panel = new JPanel();
	
	JPanel menu = new JPanel(); 

	public DevTool() {

	    
	    
	    panel.setLayout(layout);
	    layout.addLayoutComponent(panel, "Menu");
	    menu.setLayout(L1);
	   
	    
	    setSize(width, height);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    setTitle("Dev Tool");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    requestFocus();

	}


	public void actionPerformed(ActionEvent event) {

	    Object source = event.getSource();

	}

}
