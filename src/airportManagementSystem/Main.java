package airportManagementSystem;

import java.util.Scanner;

/**
 * Your task is to implement a flight management system for a local airport
 * called LTU Airport AD Management System. The program is supposed to handle
 * flight scheduling and management of departures and arrivals. methods: 1.
 * Register the scheduled arrival 2. Register the scheduled departure 3.
 * Register the actual arrival of a flight 4. Register the actual departure of a
 * flight 5. Print operations summary q. End program > Enter your option:
 * 
 * @author XIX
 */
public class Main {
	// global variable
	private static Scanner userInputScanner = new Scanner(System.in);

	// non-numerical constants
	public static final String FLIGHT_PLATE = "> Enter flight number: ";
	public static final String AIRPORT_ORIGIN = "> Enter airport of the city: ";
	public static final String SCHEDULED_ARRIVAL = "> Enter scheduled time of arrival: ";
	public static final String SCHEDULED_DEPARTURE = "> Enter destination airport: ";
	public static final String ACTUAL_ARRIVAL = "> Enter flight time for actual arrival: ";
	public static final String ACTURAL_DEPARTURE = "> Enter flight time for actual departing: ";

	// IMPORTANT CONTANTS
	public static final String FLIGHT_EXIST = "Flight number already exists";
	public static final String FLIGHT_NOT_FOUND = "Flight number does not exist";
	public static final String INVALID_TIME_FORMAT = "Invalid time format";
	public static final String INVALID_FLIGHT_FORMAT = "Invalid flight number format";

	// numerical constants
	public static final int MAX_NUMBER = 50;

	public static final int MENU_ITEM_1 = 1;
	public static final int MENU_ITEM_2 = 2;
	public static final int MENU_ITEM_3 = 3;
	public static final int MENU_ITEM_4 = 4;
	public static final int MENU_ITEM_5 = 5;
	public static final int MENU_ITEM_6 = 6;
	public static final int MENU_ITEM_Q = -1;

	public static void main(String[] args) {
		// step 2: local variables

		// arrays for the data storage
		String[][] arrivalFlightPlate = new String[MAX_NUMBER][MAX_NUMBER];
		String[][] departureFlightPlate = new String[MAX_NUMBER][MAX_NUMBER];
		String[][] cityArrival = new String[MAX_NUMBER][MAX_NUMBER];
		String[][] cityDeparture = new String[MAX_NUMBER][MAX_NUMBER];
		int[][] scheduledArrival = new int[MAX_NUMBER][MAX_NUMBER];
		int[][] scheduledDeparture = new int[MAX_NUMBER][MAX_NUMBER];
		int[][] actualArrival = new int[MAX_NUMBER][MAX_NUMBER];
		int[][] actualDeparture = new int[MAX_NUMBER][MAX_NUMBER];
		int[] saveData = new int[MAX_NUMBER];

		boolean programOn = true;

		while (programOn) {
			menu();
			String option = userInputScanner.nextLine();
			switch (option) {
			case "1":
				scheduledArrival(arrivalFlightPlate, cityArrival, scheduledArrival, saveData);
				break;
			case "2":
				scheduledDeparture(departureFlightPlate, cityDeparture, scheduledDeparture, saveData);
				break;
			case "3":
				actualArrival(arrivalFlightPlate, cityArrival, actualArrival, saveData);
				break;
			case "4":
				actualDeparture(departureFlightPlate, cityDeparture, actualDeparture, saveData);
				break;
			case "5":
				summaryArrivalFlights(arrivalFlightPlate, cityArrival, scheduledArrival, actualArrival, saveData);
				summaryDepartureFlights(departureFlightPlate, cityDeparture, scheduledDeparture, actualDeparture,
						saveData);
				operationsSummary(scheduledArrival, scheduledDeparture, actualArrival, actualDeparture, saveData);
				break;
			case "q":
				System.out.println("Exiting program......");
				programOn = false;
				break;
			default:
				System.out.println("Invalid input, choose correctly!");
				break;
			}
		}
	}

	/**
	 * This method is just a guidence for the user When selecting a functionality,
	 * this will help the user which feature to pick
	 */
	public static void menu() {
		System.out.println("---------------------------------");
		System.out.println("# LTU Airport AD Management System");
		System.out.println("---------------------------------");
		System.out.println("1. Register the scheduled arrival");
		System.out.println("2. Register the scheduled departure");
		System.out.println("3. Register the actual arrival of a flight");
		System.out.println("4. Register the actual departure of a flight");
		System.out.println("5. Print operations summary");
		System.out.println("q. End program");
		System.out.print("> Enter your option: ");

	}

	/**
	 * A scheduled arrival is registered by providing flight number, air- port of
	 * origin and its scheduled time of arrival. Validation and requirements for
	 * this method are 1. flight plate A flight number should start with two (2)
	 * capital letters, followed by three (3) digits (for example SK137, HY230,
	 * SQ038, etc.). 2. Scheduled arrival time is provided in format HH:MM (for
	 * example 09:30, 13:00, etc.). 3. Error handling for flight numbers, as well as
	 * scheduled arrival time 4. No duplication It should not be possible to have 2
	 * flights with the same flight number should also not be possible to have an
	 * arriving and a departing flight with the same flight number.
	 * 
	 * @param arrivalFlightPlate A 2D array representing the flight numbers (plates)
	 *                           for the arriving flights.
	 * @param cityArrival        A 2D array representing the cities from which the
	 *                           flights are arriving.
	 * @param scheduledArrival   A 2D array where each element stores the scheduled
	 *                           arrival time for each flight in hours and minutes
	 *                           (using two integers for hours and minutes).
	 * @param saveData           A 1D array where `saveData[0]` contains the number
	 *                           of registered flights, used to track the index for
	 *                           data entry.
	 */
	public static void scheduledArrival(String[][] arrivalFlightPlate, String[][] cityArrival, int[][] scheduledArrival,
			int[] saveData) {
		// step 1: local variables
		String flightNumber = " ";
		String cityOrigin = " ";
		int[] timeArrival;

		// step 2: prompt the user to enter the flight number
		System.out.print(FLIGHT_PLATE);
		flightNumber = userInputScanner.nextLine();

		flightNumber = flightCorrectFormat(flightNumber);
		// Check if the flight already exists
		for (int i = 0; i < saveData[0]; i++) {
			if (arrivalFlightPlate[0][i] != null && arrivalFlightPlate[0][i].equals(flightNumber)) {
				System.out.println(FLIGHT_EXIST);
				return; // exit the loop, because the flight exists
			}
		}

		// step 4: prompt the user to enter the city origin
		System.out.print(AIRPORT_ORIGIN);
		cityOrigin = userInputScanner.nextLine().toLowerCase();

		// Step 5: Prompt for scheduled arrival time using the new method
		timeArrival = validateTimeInput(SCHEDULED_ARRIVAL);

		// Save the entered data
		int saveIndex = saveData[0]++;
		arrivalFlightPlate[0][saveIndex] = flightNumber;
		cityArrival[0][saveIndex] = cityOrigin;
		scheduledArrival[saveIndex][0] = timeArrival[0]; // Hours
		scheduledArrival[saveIndex][1] = timeArrival[1]; // Minutes

		// Confirmation to see the flight is stored
		System.out
				.println("Arrival of flight " + arrivalFlightPlate[0][saveIndex] + " from " + cityArrival[0][saveIndex]
						+ " is scheduled for " + String.format("%02d:%02d", timeArrival[0], timeArrival[1]));
	}
	/**
	 * flight correct format where it controls flight format
	 * @param flightNumber, 2d argument is where the value will be stored
	 * @return flightNumber
	 */
	private static String flightCorrectFormat(String flightNumber) {
		while (!flightNumber.matches("^[A-Z]{2}\\d{3}$")) {
			System.out.println(INVALID_FLIGHT_FORMAT);
			System.out.print(FLIGHT_PLATE);
			flightNumber = userInputScanner.nextLine();
		}
		return flightNumber;
	}

	/**
	 * Validates and retrieves a time in HH:MM format.
	 * 
	 * @param promptMessage The message to display when prompting for the time.
	 * @return An array where index 0 is the hour and index 1 is the minutes.
	 */
	public static int[] validateTimeInput(String timeFlight) {
		int hours = 0, minutes = 0;
		boolean timeValid = false;
		String timeInput;

		// requirement for time
		// is provided in format HH:MM (for example 09:30, 13:00, etc.).
		// to solve this:
		// delimiter for a split
		// enter hour and minute using hasNextInt
		// if-else conditions to check if the user entered valid time like hour and
		// minute
		// otherwise prompt the user to enter valid time format again
		while (!timeValid) {
			System.out.print(timeFlight);
			timeInput = userInputScanner.nextLine();
			Scanner timeScanner = new Scanner(timeInput);
			timeScanner.useDelimiter(":"); // Use colon as delimiter

			if (timeScanner.hasNextInt()) {
				hours = timeScanner.nextInt(); // Get the hour
				if (timeScanner.hasNextInt()) {
					minutes = timeScanner.nextInt(); // Get the minute

					// Validate the hour and minute
					if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
						timeValid = true; // Time is valid
					} else {
						System.out.println(INVALID_TIME_FORMAT);
					}
				} else {
					System.out.println(INVALID_TIME_FORMAT);
				}
			} else {
				System.out.println(INVALID_TIME_FORMAT);
			}
		}

		return new int[] { hours, minutes }; // Return the validated hour and minute
	}

	/**
	 * A scheduled departure is registered by providing flight number, airport of
	 * destination and its scheduled time of departure. Validation and requirements:
	 * 1. flight plate A flight number should start with two (2) capital letters,
	 * followed by three (3) digits (for example SK137, HY230, SQ038, etc.). 2.
	 * Scheduled arrival time is provided in format HH:MM (for example 09:30, 13:00,
	 * etc.). 3. Error handling for flight numbers, as well as scheduled arrival
	 * time 4. No duplication It should not be possible to have 2 flights with the
	 * same flight number should also not be possible to have an arriving and a
	 * departing flight with the same flight number.
	 * 
	 * @param departureFlightPlate A 2D array where each element represents a flight
	 *                             number for a departing flight.
	 * @param cityDeparture        A 2D array where each element represents the city
	 *                             to which a flight is departing.
	 * @param scheduledDeparture   A 2D array where each element stores the
	 *                             scheduled departure time for each flight in hours
	 *                             and minutes (using two integers for hours and
	 *                             minutes).
	 * @param saveData             A 1D array where `saveData[0]` contains the
	 *                             number of registered flights, used to track the
	 *                             index for data entry.
	 */
	public static void scheduledDeparture(String[][] departureFlightPlate, String[][] cityDeparture,
			int[][] scheduledDeparture, int[] saveData) {
		// step 1: local variables
		String flightNumber = " ";
		String cityDepart = " ";
		int[] timeDeparture;

		// step 2: prompt the user to enter the flight number
		System.out.print(FLIGHT_PLATE);
		flightNumber = userInputScanner.nextLine();

		flightNumber = flightCorrectFormat(flightNumber);

		// It should not be possible to have 2 flights with the same flight number
		for (int i = 0; i < saveData[0]; i++) {
			if (departureFlightPlate[0][i] != null && departureFlightPlate[0][i].equals(flightNumber)) {
				System.out.println(FLIGHT_EXIST);
				return; // exit the looP
			}
		}

		System.out.print(AIRPORT_ORIGIN);
		cityDepart = userInputScanner.nextLine().toLowerCase();

		timeDeparture = validateTimeInput(SCHEDULED_DEPARTURE);

		int indexSave = saveData[0]++;
		departureFlightPlate[0][indexSave] = flightNumber;
		cityDeparture[0][indexSave] = cityDepart;
		scheduledDeparture[indexSave][0] = timeDeparture[0];
		scheduledDeparture[indexSave][1] = timeDeparture[1];

		// confirmation to see if the info is stored
		System.out.println("Arrival of flight " + flightNumber + " from " + cityDeparture[0][indexSave]
				+ " is scheduled for " + String.format("%02d:%02d", timeDeparture[0], timeDeparture[1]));
	}

	/**
	 * Registers the actual arrival time for a flight. The method prompts the user
	 * to enter the flight number and the actual arrival time.
	 * Validation/Requirement: 1. It validates the flight, correct format 2. the
	 * actual arrival time, in HM:MM format 3. if the flight exits, the actual
	 * arrival time is saved and a confirmation message 4. if not display an error
	 * message saying "flight does not exist"
	 * 
	 * @param arrivalFlightPlate A 2D array representing the flight numbers (flight
	 *                           plates) for arriving flights.
	 * @param cityArrival        A 2D array representing the cities from which the
	 *                           flights are arriving.
	 * @param actualArrival      A 2D array representing the actual arrival times,
	 *                           with hours and minutes as integers. A value of -1
	 *                           indicates missing or unavailable data.
	 * @param saveData           A 1D array containing the number of registered
	 *                           arrival flights. `saveData[0]` represents the total
	 *                           number of flights to be processed and displayed in
	 *                           the summary.
	 */
	public static void actualArrival(String[][] arrivalFlightPlate, String[][] cityArrival, int[][] actualArrival,
			int[] saveData) {
		// local variables
		String flightNumber = " ";
		int[] timeArrival;
		boolean flightExist = false;

		// Step 2: Prompt the user to enter the flight number
		System.out.print(FLIGHT_PLATE);
		flightNumber = userInputScanner.nextLine();

		// Find and validate the flight
		for (int i = 0; i < saveData[0]; i++) {
			if (arrivalFlightPlate[0][i] != null && arrivalFlightPlate[0][i].equals(flightNumber)) {
				flightExist = true;

				flightNumber = flightCorrectFormat(flightNumber);

				timeArrival = validateTimeInput(SCHEDULED_ARRIVAL);

				// Save the actual arrival time directly in the array
				actualArrival[i][0] = timeArrival[0];
				actualArrival[i][1] = timeArrival[1];

				// Confirmation message
				System.out.println("Arrival of flight " + flightNumber + " from " + cityArrival[0][i]
						+ " is updated with actual arrival time: "
						+ String.format("%02d:%02d", timeArrival[0], timeArrival[1]));
				return; // Exit after processing the flight

			}
		}

		// If no matching flight is found
		if (!flightExist) {
			System.out.println(FLIGHT_NOT_FOUND);
			return; // exit
		}
	}

	/**
	 * Registers the actual departure time for a flight. The method prompts the user
	 * to enter the flight number and the actual departure time.
	 * Validation/Requirement: 1. It validates the flight, correct format 2. the
	 * actual arrival time, in HM:MM format 3. if the flight exits, the actual
	 * arrival time is saved and a confirmation message 4. if not display an error
	 * message saying "flight does not exist"
	 * 
	 * @param departureflightPlate A 2D array representing the flight plates
	 * @param cityDeparture        A 2D array representing the cities to which the
	 *                             flights are departing.
	 * @param actualDeparture      A 2D array to store the actual departure times
	 *                             for each flight.
	 * @param saveData             An array containing the number of registered
	 *                             flights.
	 */
	public static void actualDeparture(String[][] departureflightPlate, String[][] cityDeparture,
			int[][] actualDeparture, int[] saveData) {
		String flightNumber = " ";
		int[] timeDeparture;
		boolean flightExist = false;

		// step 2: prompt the user to enter the flight number
		System.out.print(FLIGHT_PLATE);
		flightNumber = userInputScanner.nextLine();

		for (int i = 0; i < saveData[0]; i++) {
			if (departureflightPlate[0][i] != null && departureflightPlate[0][i].equals(flightNumber)) {
				flightExist = true;

				flightNumber = flightCorrectFormat(flightNumber);

				timeDeparture = validateTimeInput(SCHEDULED_ARRIVAL);

				// saving the data
				actualDeparture[i][0] = timeDeparture[0];
				actualDeparture[i][1] = timeDeparture[1];

				System.out.println("Flight " + flightNumber + " to " + cityDeparture[0][i] + " has departed at "
						+ String.format("%02d:%02d", timeDeparture[0], timeDeparture[1]));

				return; // exit
			}
		}

		if (!flightExist) {
			System.out.println(FLIGHT_NOT_FOUND);
			return; // end
		}
	}

	/**
	 * Summarizes the flight information for arriving flights. Displays details such
	 * as flight number, origin city, scheduled time of arrival (STA), and actual
	 * time of arrival (ATA). If the actual time of arrival is 00:00, it is
	 * displayed as blank.
	 *
	 * @param arrivalFlightPlate A 2D array holding the flight numbers of arriving
	 *                           flights.
	 * @param cityArrival        A 2D array representing the origin cities of
	 *                           arriving flights.
	 * @param scheduledArrival   A 2D array representing the scheduled arrival times
	 *                           in the format [[HH, MM], ...].
	 * @param actualArrival      A 2D array representing the actual arrival times in
	 *                           the format [[HH, MM], ...]. If no arrival time is
	 *                           available, values are -1.
	 * @param saveData           A 1D array where saveData[0] contains the number of
	 *                           arriving flights to be processed.
	 */
	public static void summaryArrivalFlights(String[][] arrivalFlightPlate, String[][] cityArrival,
			int[][] scheduledArrival, int[][] actualArrival, int[] saveData) {

		// Display Arrival Flight Information
		System.out.println("Arrival:");
		System.out.printf("%-15s %-15s %-15s %-15s%n", "Flight", "From", "STA", "ATA");

		for (int i = 0; i < saveData[0]; i++) {
			// Check if there is valid data to display
			if (arrivalFlightPlate[0][i] != null && cityArrival[0][i] != null && scheduledArrival[i][0] != -1
					&& scheduledArrival[i][1] != -1) {

				String flightArrival = arrivalFlightPlate[0][i];
				String from = cityArrival[0][i];

				String sta = String.format("%02d:%02d", scheduledArrival[i][0], scheduledArrival[i][1]);
				String ata = "-";

				if (actualArrival[i][0] != -1 && actualArrival[i][1] != -1) {
					// If it's 00:00, display as blank instead of 00:00
					if (actualArrival[i][0] == 0 && actualArrival[i][1] == 0) {
						ata = ""; // Leave ATA blank
					} else {
						ata = String.format("%02d:%02d", actualArrival[i][0], actualArrival[i][1]);
					}
				}

				System.out.printf("%-15s %-15s %-15s %-15s%n", flightArrival, from, sta, ata);
			}
		}
	}

	/**
	 * Summarizes the flight information for departing flights. Displays details
	 * such as flight number, destination city, scheduled time of departure (STD),
	 * and actual time of departure (ATD). If the actual time of departure is 00:00,
	 * it is displayed as blank.
	 *
	 * @param departureFlightPlate A 2D array holding the flight numbers of
	 *                             departing flights.
	 * @param cityDeparture        A 2D array representing the destination cities of
	 *                             departing flights.
	 * @param scheduledDeparture   A 2D array representing the scheduled departure
	 *                             times in the format [[HH, MM], ...].
	 * @param actualDeparture      A 2D array representing the actual departure
	 *                             times in the format [[HH, MM], ...]. If no
	 *                             departure time is available, values are -1.
	 * @param saveData             A 1D array where saveData[0] contains the number
	 *                             of departing flights to be processed.
	 */
	public static void summaryDepartureFlights(String[][] departureFlightPlate, String[][] cityDeparture,
			int[][] scheduledDeparture, int[][] actualDeparture, int[] saveData) {

		// Display Departure Flight Information
		System.out.println("\nDeparture:");
		System.out.printf("%-15s %-15s %-15s %-15s%n", "Flight", "To", "STD", "ATD");

		for (int i = 0; i < saveData[0]; i++) {
			// Check if there is valid data to display
			if (departureFlightPlate[0][i] != null && cityDeparture[0][i] != null && scheduledDeparture[i][0] != -1
					&& scheduledDeparture[i][1] != -1) {

				String flightDeparture = departureFlightPlate[0][i];
				String to = cityDeparture[0][i];

				String std = String.format("%02d:%02d", scheduledDeparture[i][0], scheduledDeparture[i][1]);
				String atd = " "; // Initialize ATD as an empty string

				// Check if actual departure time exists
				if (actualDeparture[i][0] != -1 && actualDeparture[i][1] != -1) {
					// If it's 00:00, display as blank instead of 00:00
					if (actualDeparture[i][0] == 0 && actualDeparture[i][1] == 0) {
						atd = ""; // Leave ATD blank
					} else {
						atd = String.format("%02d:%02d", actualDeparture[i][0], actualDeparture[i][1]);
					}
				}

				System.out.printf("%-15s %-15s %-15s %-15s%n", flightDeparture, to, std, atd);
			}
		}
	}

	/**
	 * This method generates an operations summary for both flight arrivals and
	 * departures, calculating the total number of flights and counting how many of
	 * them are delayed based on the scheduled and actual arrival times or scheduled
	 * and actual departure times.
	 * 
	 * Validation/Requirement: 1. Correctly calculates the total number of flights.
	 * 2. Identifies and counts delayed flights accurately.
	 * 
	 * @param scheduledArrival   A 2D array storing the scheduled arrival times for
	 *                           each flight (hours and minutes).
	 * @param scheduledDeparture A 2D array storing the scheduled departure times
	 *                           for each flight (hours and minutes).
	 * @param actualArrival      A 2D array storing the actual arrival times for
	 *                           each flight (hours and minutes).
	 * @param actualDeparture    A 2D array storing the actual departure times for
	 *                           each flight (hours and minutes).
	 * @param saveData           A 1D array where `saveData[0]` contains the total
	 *                           number of registered flights.
	 */
	public static void operationsSummary(int[][] scheduledArrival, int[][] scheduledDeparture, int[][] actualArrival,
			int[][] actualDeparture, int[] saveData) {

		// Step 1: Calculate total number of flights
		int totalFlights = saveData[0];
		int delayedFlights = 0; // Counter for delayed flights

		// Step 2: Loop through all flights to check both arrival and departure delays
		for (int i = 0; i < totalFlights; i++) {
			boolean isDelayed = false; // Flag to track if a flight is delayed

			// Check arrival delay
			if (actualArrival[i][0] > scheduledArrival[i][0] || (actualArrival[i][0] == scheduledArrival[i][0]
					&& actualArrival[i][1] > scheduledArrival[i][1])) {
				isDelayed = true;
			}

			// Check departure delay
			if (actualDeparture[i][0] > scheduledDeparture[i][0] || (actualDeparture[i][0] == scheduledDeparture[i][0]
					&& actualDeparture[i][1] > scheduledDeparture[i][1])) {
				isDelayed = true;
			}

			// If either arrival or departure is delayed, count this flight as delayed
			if (isDelayed) {
				delayedFlights++;
			}
		}

		// Step 3: Print the results
		System.out.println("\nTotal number of flights: " + totalFlights);
		System.out.println("Total number of Delayed Flights: " + delayedFlights);
	}
}
