import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Frame extends JFrame {

	public static String title = "TD";
	public static Dimension size = new Dimension(1300, 900);

	public Frame() {
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		init();
		
		lay();
	}

	public void lay() {

		BufferedImage img = null;
		try {
			File f = new File("Resources/FB.jpg");
			img = ImageIO.read(f);
			System.out.println("File " + f.toString());
		} catch (Exception e) {
			System.out.println("Cannot read file: " + e);
		}

//		 setLayout(new BorderLayout());
//		 BackgroundPanel background = new BackgroundPanel(img);
//		 background.setImage(img);
//		 setContentPane( background );
//		 setVisible(true);
//		 background.setLayout(new FlowLayout());
//		 add(background);

	}

	public void init() {

		setLayout(new GridLayout(1, 1, 0, 0));

		Screen screen = new Screen(this);
		add(screen);

		setVisible(true);
	}

	public static void main(String args[]) {
		Frame frame = new Frame();

	}

}
