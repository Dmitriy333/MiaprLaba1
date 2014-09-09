package by.bsuir.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import by.bsuir.Main.Frame;
import by.bsuir.substances.ClasterSystem;
import by.bsuir.substances.Point;

public class Logic {
	private int numberOfDots = 5000;
	private int numberOfCenters = 7;
	private List<Point> centers = new ArrayList<Point>();
	private List<ClasterSystem> system = new ArrayList<ClasterSystem>();

	public ArrayList<Point> getCenters() {
		return (ArrayList<Point>) centers;
	}

	public ArrayList<ClasterSystem> createSystem() {
		Random random = new Random();
		// устанавливаем точки
		ClasterSystem clasterPoint;
		for (int i = 0; i < numberOfDots; i++) {
			clasterPoint = new ClasterSystem();
			Point point = new Point(random.nextInt(700) + 1,
					random.nextInt(700) + 1);
			// System.out.println(point);
			clasterPoint.setPoint(point);
			system.add(clasterPoint);
		}
		// инициализируем центры
		for (int i = 0; i < numberOfCenters; i++) {
			centers.add(system.get(i).getPoint());
		}
		// устанавливаем соответствие между точкой и цетнром
		for (int i = 0; i < numberOfDots; i++) {
			Point center = centers.get(random.nextInt(numberOfCenters));
			system.get(i).setCenter(new Point(center.getX(), center.getY()));
		}
		return (ArrayList<ClasterSystem>) system;
	}

	public void boundPointsToCenter(List<Point> centers,
			List<ClasterSystem> system) {
		int t1, t2, t3, t4;
		double dist[] = new double[numberOfCenters];
		for (int i = 0; i < numberOfDots; i++) {
			for (int j = 0; j < numberOfCenters; j++) {
				t1 = centers.get(j).getX();
				t3 = system.get(i).getPoint().getX();
				t2 = centers.get(j).getY();
				t4 = system.get(i).getPoint().getY();
				dist[j] = Math.sqrt(Math.abs(Math.pow((t1 - t3), 2)
						+ Math.pow((t2 - t4), 2)));
			}
			int min = 0;
			for (int j = 0; j < numberOfCenters; j++) {
				if (dist[j] < dist[min]) {
					min = j;
				}
			}
			system.get(i).getCenter().setX(centers.get(min).getX());
			system.get(i).getCenter().setY(centers.get(min).getY());
		}
	}

	// нужно получить новые центры //перепресвоить координаты
	public List<Point> resetKernels(Frame frame, List<Point> centers,
			List<ClasterSystem> system) {
		int t1, t2, t3, t4;
		boolean check = true;
		int n = 0;
		while (check) {
			frame.repaint();
			frame.drawPoints(system, centers);
			boundPointsToCenter(centers, system);
			check = false;
			for (Point center : centers) {
				long cmp1 = Integer.MAX_VALUE;
				Point newCenter = new Point(center.getX(), center.getY());
				Point oldCenter = new Point(center.getX(), center.getY());
				for (ClasterSystem clasterSystem : system) {
					if (clasterSystem.getCenter().equals(oldCenter)) {
						long cmp2 = 0;
						for (ClasterSystem clasterSystem2 : system) {
							if (clasterSystem2.getCenter().equals(oldCenter)) {
								t1 = clasterSystem.getPoint().getX();
								t3 = clasterSystem2.getPoint().getX();
								t2 = clasterSystem.getPoint().getY();
								t4 = clasterSystem2.getPoint().getY();
								cmp2 = (long) (cmp2 + (Math.pow((t1 - t3), 2)+ Math.pow((t2 - t4), 2)));
							}
						}
						if (cmp2 < cmp1) {
							cmp1 = cmp2;
							newCenter.setX(clasterSystem.getPoint().getX());
							newCenter.setY(clasterSystem.getPoint().getY());
						}
					}
				}
				center.setX(newCenter.getX());
				center.setY(newCenter.getY());
				if (!center.equals(oldCenter)) {
					check = true;
				}
				for (ClasterSystem clasterSystem : system) {
					if (clasterSystem.getCenter().equals(oldCenter)) {
						clasterSystem.getCenter().setX(newCenter.getX());
						clasterSystem.getCenter().setY(newCenter.getY());
					}
				}
			}
			n++;
		}
		System.out.println("Количество итераций: " + n);
		return centers;
	}

	public List<ClasterSystem> getSystem() {
		return system;
	}

	public void setSystem(List<ClasterSystem> system) {
		this.system = system;
	}


}
