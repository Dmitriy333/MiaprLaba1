package by.bsuir.Main;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Screen() {
		repaint();
	}
	
	public void paint(Graphics g){
		g.drawRect(20, 20, 100, 200);
	}
}
