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


public class Menu extends JFrame implements ActionListener {
	
	int width = 500, height = 200;

	JButton play = new JButton("play");
	JButton exit = new JButton("exit");

	CardLayout layout = new CardLayout();

	JPanel panel = new JPanel();
	JPanel game = new JPanel();
	JPanel menu = new JPanel(); 

	public Menu() {

	    
	    
	    panel.setLayout(layout);
	    layout.addLayoutComponent(panel, "Menu");
	    addButtons();
	    
	    setSize(width, height);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    setTitle("Menu");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    requestFocus();

	}

	private void addButtons() {

	    play.addActionListener(this);
	    exit.addActionListener(this);

	    //menu buttons
	    menu.add(play);
	    menu.add(exit);
	    
	    game.setBackground(Color.MAGENTA);
	    menu.setBackground(Color.BLUE);

	    //adding children to parent Panel
	    panel.add(menu,"Menu");
	    panel.add(game,"Game");

	    add(panel);
	    layout.show(panel,"Menu");

	}

	public void actionPerformed(ActionEvent event) {

	    Object source = event.getSource();

	    if (source == exit) {
	        System.exit(0);
	    } 
	    else if (source == play) {
	    	Frame frame = new Frame();
	    	Screen.gameState();
	    	setVisible(false);
	    
	    }
	}

	
	
}
