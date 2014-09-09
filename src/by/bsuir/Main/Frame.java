package by.bsuir.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;

import by.bsuir.substances.ClasterSystem;
import by.bsuir.substances.Point;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private boolean setColor = false;
	private Map<Point, Color> map = new HashMap<Point, Color>();
	private static final long serialVersionUID = 1L;

	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setResizable(true);
		setTitle("graphics");
		init();
	}

	public void init() {
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1, 1, 0, 0));
		setVisible(true);
	}

	public void drawPoints(List<ClasterSystem> system, List<Point> centers) {
		Graphics g = this.getGraphics();
		g.clearRect(0, 0, 700, 700);
		Random random = new Random();
		if (!setColor) {
			for (int i = 0; i < centers.size(); i++) {
				Color c = new Color(random.nextInt(255), random.nextInt(255),
						random.nextInt(255));
				map.put(centers.get(i), c);
			}
			setColor = true;
		}
		for (ClasterSystem clasterSystem : system) {
			for (Point center : centers) {
				if (clasterSystem.getCenter().equals(center)) {
					g.setColor(map.get(center));
					break;
				}
			}
			if (clasterSystem.getPoint().equals(clasterSystem.getCenter())) {
				g.fillOval(clasterSystem.getPoint().getX(), clasterSystem
						.getPoint().getY(), 30, 30);
			} else {
				g.fillOval(clasterSystem.getPoint().getX(), clasterSystem
						.getPoint().getY(), 10, 10);
			}
		}
	}

	public void drawBoundPointsWithCenters(List<ClasterSystem> system) {
		Graphics g = this.getGraphics();
		for (ClasterSystem clasterSystem : system) {
			g.drawLine(clasterSystem.getPoint().getX(), clasterSystem
					.getPoint().getY(), clasterSystem.getCenter().getX(),
					clasterSystem.getCenter().getY());
		}
	}

	@Override
	public void paint(Graphics g) {
	}
}
