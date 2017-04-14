import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class End extends JFrame implements ActionListener {
	
	int width = 300, height = 100;
	
	JButton play = new JButton("Yes");
	JButton exit = new JButton("No");
	
	CardLayout layout = new CardLayout();
	

	JPanel panel = new JPanel();
	
	JPanel menu = new JPanel(); 

	public End() {
		JOptionPane.showMessageDialog(menu, "Would you like to end the game?");
	    
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
	    	setVisible(false);
	    	Frame.screen.remove(Frame.end);
	    	Screen.isPaused = false;
	    	Screen.health = 0;
	    }


	}

	
	
}