import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class javawithdsa {
	private static final Scanner scanner = new Scanner(System.in);
	private static final double AVERAGE_SPEED_KMPH = 35.0;
	private static final Map<String, List<Road>> map = new LinkedHashMap<>();

	static class Road {
		String destination;
		double distance;

		Road(String destination, double distance) {
			this.destination = destination;
			this.distance = distance;
		}
	}

	static class RouteState {
		String location;
		double distance;

		RouteState(String location, double distance) {
			this.location = location;
			this.distance = distance;
		}
	}

	public static void main(String[] args) {
		loadSampleMap();

		while (true) {
			printMenu();
			int choice = readInt("Enter your choice: ");

			switch (choice) {
				case 1:
					showLocations();
					break;
				case 2:
					showRoads();
					break;
				case 3:
					findRoute();
					break;
				case 4:
					addLocation();
					break;
				case 5:
					addRoad();
					break;
				case 6:
					System.out.println("Thank you for using Map Navigator.");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void printMenu() {
		System.out.println("\n========== MAP NAVIGATOR / GPS ROUTING SYSTEM ==========");
		System.out.println("1. View all locations");
		System.out.println("2. View all roads");
		System.out.println("3. Find shortest route");
		System.out.println("4. Add a location");
		System.out.println("5. Add a road");
		System.out.println("6. Exit");
	}

	private static void loadSampleMap() {
		addLocationToMap("Home");
		addLocationToMap("School");
		addLocationToMap("City Mall");
		addLocationToMap("Railway Station");
		addLocationToMap("Airport");
		addLocationToMap("Hospital");

		addRoadToMap("Home", "School", 4.0);
		addRoadToMap("Home", "City Mall", 6.0);
		addRoadToMap("School", "Railway Station", 5.0);
		addRoadToMap("School", "Hospital", 7.0);
		addRoadToMap("City Mall", "Railway Station", 3.0);
		addRoadToMap("City Mall", "Hospital", 4.0);
		addRoadToMap("Railway Station", "Airport", 8.0);
		addRoadToMap("Hospital", "Airport", 10.0);
	}

	private static void showLocations() {
		System.out.println("\nAvailable locations:");
		int number = 1;
		for (String location : map.keySet()) {
			System.out.println(number++ + ". " + location);
		}
	}

	private static void showRoads() {
		System.out.println("\nRoad network:");
		for (String source : map.keySet()) {
			for (Road road : map.get(source)) {
				if (source.compareTo(road.destination) < 0) {
					System.out.printf("%s <-> %s : %.1f km%n", source, road.destination, road.distance);
				}
			}
		}
	}

	private static void findRoute() {
		String source = readLocation("Enter starting location: ");
		String destination = readLocation("Enter destination: ");
		Map<String, Double> distances = new HashMap<>();
		Map<String, String> previous = new HashMap<>();
		for (String location : map.keySet()) {
			distances.put(location, Double.POSITIVE_INFINITY);
		}

		PriorityQueue<RouteState> queue = new PriorityQueue<>(Comparator.comparingDouble(state -> state.distance));
		distances.put(source, 0.0);
		queue.offer(new RouteState(source, 0.0));

		while (!queue.isEmpty()) {
			RouteState current = queue.poll();
			if (current.distance > distances.get(current.location)) {
				continue;
			}
			if (current.location.equals(destination)) {
				break;
			}

			for (Road road : map.get(current.location)) {
				double newDistance = current.distance + road.distance;
				if (newDistance < distances.get(road.destination)) {
					distances.put(road.destination, newDistance);
					previous.put(road.destination, current.location);
					queue.offer(new RouteState(road.destination, newDistance));
				}
			}
		}

		if (distances.get(destination).isInfinite()) {
			System.out.println("No route exists between these locations.");
			return;
		}

		List<String> route = new ArrayList<>();
		String current = destination;
		while (current != null) {
			route.add(current);
			current = previous.get(current);
		}
		Collections.reverse(route);
		double distance = distances.get(destination);
		double timeInMinutes = distance / AVERAGE_SPEED_KMPH * 60;

		System.out.println("\nShortest route:");
		System.out.println(String.join(" -> ", route));
		System.out.printf("Total distance: %.1f km%n", distance);
		System.out.printf("Estimated time: %.0f minutes%n", timeInMinutes);
	}

	private static void addLocation() {
		String location = readText("Enter new location name: ");
		if (location.isEmpty()) {
			System.out.println("Location name cannot be empty.");
		} else if (map.containsKey(location)) {
			System.out.println("This location already exists.");
		} else {
			addLocationToMap(location);
			System.out.println("Location added successfully.");
		}
	}

	private static void addRoad() {
		String source = readLocation("Enter first location: ");
		String destination = readLocation("Enter second location: ");
		if (source.equals(destination)) {
			System.out.println("A road needs two different locations.");
			return;
		}

		double distance = readDouble("Enter road distance in km: ");
		if (distance <= 0) {
			System.out.println("Distance must be greater than zero.");
			return;
		}
		addRoadToMap(source, destination, distance);
		System.out.println("Road added successfully.");
	}

	private static String readLocation(String message) {
		while (true) {
			String location = readText(message);
			if (map.containsKey(location)) {
				return location;
			}
			System.out.println("Location not found. Use one of the listed locations.");
		}
	}

	private static String readText(String message) {
		System.out.print(message);
		return scanner.nextLine().trim();
	}

	private static int readInt(String message) {
		while (true) {
			try {
				return Integer.parseInt(readText(message));
			} catch (NumberFormatException exception) {
				System.out.println("Please enter a valid number.");
			}
		}
	}

	private static double readDouble(String message) {
		while (true) {
			try {
				return Double.parseDouble(readText(message));
			} catch (NumberFormatException exception) {
				System.out.println("Please enter a valid distance.");
			}
		}
	}

	private static void addLocationToMap(String location) {
		map.putIfAbsent(location, new ArrayList<>());
	}

	private static void addRoadToMap(String source, String destination, double distance) {
		map.get(source).add(new Road(destination, distance));
		map.get(destination).add(new Road(source, distance));
	}
}
