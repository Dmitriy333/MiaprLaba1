package by.bsuir.Main;

import java.util.ArrayList;



import java.util.List;

import by.bsuir.logic.Logic;
import by.bsuir.substances.ClasterSystem;
import by.bsuir.substances.Point;

public class Main {
	private static Frame frame;
	public static void main(String[] args) {
		frame = new Frame();
		Logic logic = new Logic();
		ArrayList<ClasterSystem> system = logic.createSystem();
		List<Point> centers = logic.getCenters();
		logic.boundPointsToCenter(centers, system);
		frame.drawPoints(system, centers);
		centers = logic.resetKernels(frame, centers, system);
//		frame.drawBoundPointsWithCenters(system);
	}
}
