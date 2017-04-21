import java.awt.event.*;
import java.awt.*;

public class KeyHandle implements MouseMotionListener, MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) { //Handles mouse pressing 
		Screen.store.click(e.getButton());

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) { //Handles mouse dragging 
		Screen.mse = new Point((e.getX()) - ((Frame.size.width - Screen.myWidth) / 2),
				(e.getY()) - ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth) / 2));

	}

	@Override
	public void mouseMoved(MouseEvent e) { //handles mouse movement
		Screen.mse = new Point((e.getX()) - ((Frame.size.width - Screen.myWidth) / 2),
				(e.getY()) - ((Frame.size.height - (Screen.myHeight)) - (Frame.size.width - Screen.myWidth) / 2));

	}
}
