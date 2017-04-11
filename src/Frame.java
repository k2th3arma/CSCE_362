import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame implements ActionListener {

	public static String title = "TD";	//The title for the application
	public static Dimension size = new Dimension(1300, 950);// the Dimensions of the application
	
	JButton end = new JButton("End");
	static Screen screen;
		
	public Frame() {
		
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		init();
		
	}

	//produces the layout for the game panel
	public void init() {		
		
		
		setLayout(new GridLayout(1,1,0,0));

		
		screen = new Screen(this);
		
		end.setBackground(Color.WHITE);
		screen.add(end);
		end.addActionListener(this);
	
		add(screen);
		
		

		setVisible(true);
	}
	
	public static void window(){
		//System.exit(HIDE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent event) {

	    Object source = event.getSource();
	    
	    if( source == end){
	    	End end = new End();
	    }

	}


	public static void main(String args[]) {
		Menu menu = new Menu();

	}

}
