import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {

	public static String title = "TD";	//The title for the application
	public static Dimension size = new Dimension(1300, 950);// the Dimensions of the application
		
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
		
		
		setLayout(new GridLayout(1, 1, 0, 0));
			
		Screen screen = new Screen(this);
		
		add(screen);
		

		setVisible(true);
	}

	public static void main(String args[]) {
		Menu menu = new Menu();

	}

}
