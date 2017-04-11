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
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;

	JButton play;
	JButton exit;
	JButton easy;
	JButton norm;
	JButton hard;
	
	boolean modE = false;
	boolean modN = false;
	boolean modH = false;

	CardLayout layout = new CardLayout();
	GridBagLayout L1 = new GridBagLayout();

	JPanel panel = new JPanel();
	
	JPanel menu = new JPanel(); 

	public Menu() {

	    
	    
	    panel.setLayout(layout);
	    layout.addLayoutComponent(panel, "Menu");
	    menu.setLayout(L1);
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

	    
	    menu.setBackground(Color.BLUE);
	    
	    GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
		
		c.fill = GridBagConstraints.HORIZONTAL;
		}

		play = new JButton("Play");
		if (shouldWeightX) {
		c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		menu.add(play, c);

		exit = new JButton("Exit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		menu.add(exit, c);

		easy = new JButton("Easy");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		menu.add(easy, c);

		norm = new JButton("Normal");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		menu.add(norm, c);
		
		hard = new JButton("Hard");
		c.fill = GridBagConstraints.HORIZONTAL;    
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		menu.add(hard, c);
		
	    play.addActionListener(this);
	    exit.addActionListener(this);
	    
	    easy.addActionListener(this);
	    norm.addActionListener(this);
	    hard.addActionListener(this);

	    //adding children to parent Panel
	    panel.add(menu,"Menu");

	    add(panel);
	    layout.show(panel,"Menu");

	}

	public void actionPerformed(ActionEvent event) {

	    Object source = event.getSource();

	    if (source == exit) {
	        System.exit(0);
	    } 
	    else if (source == play) {
	    	if(modE == true){
	    		mod(-10);
	    		start();
	    	}
	    	else if(modH == true){
	    		mod(2);
	    		start();
	    	}
	    	else if(modN == true){
	    		start();
	    	}
	    
	    }
	    else if (source == easy){
	    	modE = true;
	    	modN = false;
	    	modH = false;
	    	shade();
	    }
	    else if (source == norm){
	    	modE = false;
	    	modN = true;
	    	modH = false;
	    	shade();
	    	
	    }
	    else if (source == hard){
	    	modE = false;
	    	modN = false;
	    	modH = true;
	    	shade();
	    }

	}
	public void mod(int x){
		for(int i = 0; i < Value.tMob.length; ++i){
			if(Value.tMob[i] + x < 0){
				Value.tMob[i] = 0;
			}
			else{
				Value.tMob[i] += x; 
			}
		}
		for(int i = 0; i < Value.deathScore.length; ++i){
			if(x < 0){
				Value.deathScore[i] /= 5;
			}
			else if(x > 0){
				Value.deathScore[i] *= 2;
			}
		}
	}
	
	public void start(){
    	Frame frame = new Frame();
    	Screen.gameState();
    	setVisible(false);
	}
	
	public void shade(){
		if(modE == true){
			easy.setBackground(Color.GREEN);
		}
		else{
			easy.setBackground(UIManager.getColor("control"));
		}
		if(modN == true){
			norm.setBackground(Color.GREEN);
		}
		else{
			norm.setBackground(UIManager.getColor("control"));
		}
		if(modH == true){
			hard.setBackground(Color.GREEN);
		}
		else{
			hard.setBackground(UIManager.getColor("control"));
		}
	}

	
	
}
