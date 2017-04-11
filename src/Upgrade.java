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


public class Upgrade extends JFrame implements ActionListener {
	
	int width = 300, height = 100;
	

	JButton play = new JButton("Yes");
	JButton exit = new JButton("No");

	static boolean hold = false;
	
	CardLayout layout = new CardLayout();
	

	JPanel panel = new JPanel();
	
	JPanel menu = new JPanel(); 

	public Upgrade() {
		JOptionPane.showMessageDialog(menu, "Upgrade your towers for $" + (1000 * Value.towerLevel) );
	   //hold = true;
	    
	    panel.setLayout(layout);
	    layout.addLayoutComponent(panel, "Menu");
	   
	    addButtons();
	    
	    setSize(width, height);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    setTitle("Menu");
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    requestFocus();

	}

	private void addButtons() {

	    menu.add(play);
	    menu.add(exit);
	    
	    menu.setBackground(Color.BLUE);

		
	    play.addActionListener(this);
	    exit.addActionListener(this);
	    

	    //adding children to parent Panel
	    panel.add(menu,"Menu");

	    add(panel);
	    layout.show(panel,"Menu");

	}

	public void actionPerformed(ActionEvent event) {

	    Object source = event.getSource();

	    if (source == exit) {
	        setVisible(false);
	    } 
	    else if (source == play) {
	    	if(Screen.money - (1000* Value.towerLevel) <0){
	    		JOptionPane.showMessageDialog(menu, "You don't have enough money");
	    	}
	    	else{
	    		Screen.money -= (1000 * Value.towerLevel);
	    		Block.towerSquareSize *= 2;
	    		Value.towerAtk += 2;
	    		Value.towerLevel += 1;
	    		setVisible(false);
	    	}
	    }


	}

	
	
}