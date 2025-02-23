import java.util.*;

public class TRYTRYTRY {
    static Scanner scanner = new Scanner(System.in);

    // Predefined destinations and base prices adrid
    static final Map<String, String> destinations = Map.ofEntries(
            Map.entry("MNL", "Manila"), Map.entry("CEB", "Cebu"), Map.entry("DVO", "Davao"),
            Map.entry("ILO", "Iloilo"), Map.entry("KLO", "Kalibo"), Map.entry("BCD", "Bacolod"),
            Map.entry("TAG", "Bohol"), Map.entry("MPH", "Boracay (Caticlan)"), Map.entry("BXU", "Butuan"),
            Map.entry("CGY", "Cagayan de Oro"), Map.entry("CYP", "Calbayog"), Map.entry("CGM", "Camiguin"),
            Map.entry("CYZ", "Cauayan"), Map.entry("USU", "Coron (Busuanga)"), Map.entry("DPL", "Dipolog"),
            Map.entry("DGT", "Dumaguete"), Map.entry("GES", "General Santos"), Map.entry("LAO", "Laoag"),
            Map.entry("DRP", "Legazpi (Daraga)"), Map.entry("MBT", "Masbate"), Map.entry("WNP", "Naga"),
            Map.entry("OZC", "Ozamiz"), Map.entry("PAG", "Pagadian"), Map.entry("PPS", "Puerto Princesa"),
            Map.entry("RXS", "Roxas"), Map.entry("SJI", "San Jose (Mindoro)"), Map.entry("SFE", "San Vicente (Port Barton)"),
            Map.entry("IAO", "Siargao"), Map.entry("SUG", "Surigao"), Map.entry("TAC", "Tacloban"),
            Map.entry("TWT", "Tawi-Tawi"), Map.entry("TUG", "Tuguegarao"), Map.entry("VRC", "Virac"),
            Map.entry("ZAM", "Zamboanga")
    );

    static final Map<String, List<Map<String, String>>> flightDetails = Map.ofEntries(
            Map.entry("MNL-CEB", List.of(
                    Map.of("Airline", "Philippine Airlines", "Departure", "08:00 AM", "Arrival", "10:00 AM", "Duration", "2h", "Layovers", "0", "Baggage Allowance", "20kg"),
                    Map.of("Airline", "Cebu Pacific", "Departure", "10:30 AM", "Arrival", "12:30 PM", "Duration", "2h", "Layovers", "0", "Baggage Allowance", "15kg")
            )),
            Map.entry("MNL-DVO", List.of(
                    Map.of("Airline", "Philippine Airlines", "Departure", "09:00 AM", "Arrival", "11:30 AM", "Duration", "2h 30m", "Layovers", "0", "Baggage Allowance", "20kg"),
                    Map.of("Airline", "Cebu Pacific", "Departure", "01:00 PM", "Arrival", "03:30 PM", "Duration", "2h 30m", "Layovers", "0", "Baggage Allowance", "15kg")
            ))
    );

    // Seat availability (example: 10 rows, 6 columns)
    static boolean[][] seats = new boolean[10][6];

    // Payment options
    static final List<String> paymentOptions = List.of("Credit/Debit Card", "Digital Wallet", "Bank Transfer");

    // Pre-registered na mga user and admin
    static Map<String, String> users = Map.of(
            "group2", "group2 com246",
            "adrid bacabac", "adrid123"
    );
    static Map<String, Boolean> isAdmin = Map.of(
            "group2", true,
            "adrid bacabac", false
    );

    // Use LinkedHashMap to maintain insertion order
    static final Map<String, Double> addOns = new LinkedHashMap<>(Map.of(
            "In-flight Meal", 0.0,
            "Advance Seat Reservation", 300.0,
            "Travel Insurance", 1000.0,
            "VIP Lounge Access", 1500.0,
            "Auto Check-in", 200.0,
            "Added Baggage", 0.0
    ));

    // Use LinkedHashMap to maintain insertion order
    static final Map<String, Double> baggageOptions = new LinkedHashMap<>(Map.of(
            "Carry on (7kg)", 700.0,
            "1x Baggage (20kg)", 1000.0,
            "1x Baggage (25kg)", 1500.0,
            "1x Baggage (30kg)", 2000.0,
            "1x Baggage (40kg)", 2500.0,
            "1x Baggage (50kg)", 3000.0,
            "1x Baggage (60kg)", 3500.0
    ));

    static void displayBaggageOptions() {
        System.out.println("\nAvailable Added Baggage:");
        System.out.println("--------------------------------------");
        System.out.printf("%-5s %-25s %-10s\n", "No.", "Name", "Price");
        System.out.println("--------------------------------------");
        int count = 1;
        for (Map.Entry<String, Double> entry : baggageOptions.entrySet()) {
            System.out.printf("%-5d %-25s PHP %.2f\n", count++, entry.getKey(), entry.getValue());
        }
        System.out.println("--------------------------------------");
    }

    static final Map<String, Double> mealOptions = new LinkedHashMap<>(Map.of(
            "Burger", 150.0,
            "Fries", 80.0,
            "Soda", 50.0,
            "Chicken Wings", 200.0,
            "Pasta", 180.0,
            "Ice Cream", 70.0
    ));

    static void displayMealOptions() {
        System.out.println("\nAvailable In-flight Meals:");
        System.out.println("----------------------------------");
        System.out.printf("| %-3s | %-15s | %-6s |%n", "No.", "Food Item", "Price");
        System.out.println("----------------------------------");
        int count = 1;
        for (Map.Entry<String, Double> entry : mealOptions.entrySet()) {
            System.out.printf("| %-3d | %-15s | PHP %-4.2f |%n", count++, entry.getKey(), entry.getValue());
        }
        System.out.println("----------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("--- Welcome to Group 2 Airlines ---");
        loginMenu();
    }

    static void loginMenu() {
        System.out.println("1. User Login");
        System.out.println("2. Admin Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                userLogin();
                break;
            case 2:
                adminLogin();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                loginMenu();
        }
    }

    static void userLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            userMenu(username);
        } else {
            System.out.println("Invalid username or password.");
            loginMenu();
        }
    }

    static void adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password) && isAdmin.get(username)) {
            System.out.println("Admin login successful!");
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
            loginMenu();
        }
    }

    static void userMenu(String username) {
        System.out.println("\nUSER MENU");
        System.out.println("1. Book a Flight");
        System.out.println("2. View Bookings");
        System.out.println("3. Logout");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                bookFlight(username);
                break;
            case 2:
                viewBookings(username);
                break;
            case 3:
                loginMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                userMenu(username);
        }
    }

    static void adminMenu() {
        System.out.println("\nADMIN MENU");
        System.out.println("1. Manage Flights");
        System.out.println("2. View All Bookings");
        System.out.println("3. Logout");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                manageFlights();
                break;
            case 2:
                viewAllBookings();
                break;
            case 3:
                loginMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                adminMenu();
        }
    }

    static void bookFlight(String username) {
        displayDestinations();

        System.out.print("Enter FROM destination (code or name): ");
        String from = getCode(scanner.nextLine());
        System.out.print("Enter TO destination (code or name): ");
        String to = getCode(scanner.nextLine());

        String route = from + "-" + to;
        if (!flightDetails.containsKey(route)) {
            System.out.println("No flights available for this route.");
            return;
        }

        System.out.print("Is this a round trip? (yes/no): ");
        boolean isRoundTrip = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Enter departure date (YYYY-MM-DD): ");
        String departureDate = scanner.nextLine();
        String returnDate = "-";
        if (isRoundTrip) {
            System.out.print("Enter return date (YYYY-MM-DD): ");
            returnDate = scanner.nextLine();
        }

        System.out.print("How many adult tickets? ");
        int adults = scanner.nextInt();
        System.out.print("How many child tickets (2-11 yrs old)? ");
        int kids = scanner.nextInt();

        int maxLapInfants = adults;
        int infants = 0;
        do {
            System.out.printf("How many infants (under 2 yrs old)? (Max allowed: %d): ", maxLapInfants + kids);
            infants = scanner.nextInt();
            if (infants > (maxLapInfants + kids)) {
                System.out.println("Exceeded the maximum number of infants. Please try again.");
            }
        } while (infants > (maxLapInfants + kids));

        int lapInfants = 0;
        int seatInfants = 0;

        if (infants > 0) {
            do {
                System.out.printf("How many infants will be on lap? (Max allowed: %d): ", maxLapInfants);
                lapInfants = scanner.nextInt();
                if (lapInfants > maxLapInfants) {
                    System.out.println("One lap infant is allowed per adult. Please try again.");
                }
            } while (lapInfants > maxLapInfants);
            seatInfants = infants - lapInfants;

            for (int i = 0; i < seatInfants; i++) {
                System.out.printf("Is infant %d seated or on lap? (1. Seated / 2. Lap): ", i + 1);
                int infantChoice = scanner.nextInt();
                if (infantChoice == 2) {
                    lapInfants++;
                    seatInfants--;
                }
            }
        }

        double pricePerTicket = getPrice(route, isRoundTrip);
        double lapInfantFee = 900.0 * lapInfants;
        double totalPrice = pricePerTicket * (adults + kids + seatInfants) + lapInfantFee;

        System.out.println("\nAvailable Flights:");
        List<Map<String, String>> flights = flightDetails.get(route);
        for (int i = 0; i < flights.size(); i++) {
            Map<String, String> flight = flights.get(i);
            System.out.printf("%d. %s | Departure: %s | Arrival: %s | Duration: %s | Layovers: %s | Baggage Allowance: %s\n",
                    i + 1, flight.get("Airline"), flight.get("Departure"), flight.get("Arrival"),
                    flight.get("Duration"), flight.get("Layovers"), flight.get("Baggage Allowance"));
        }

        System.out.print("Select a flight (enter number): ");
        int flightChoice = scanner.nextInt();
        scanner.nextLine();

        if (flightChoice < 1 || flightChoice > flights.size()) {
            System.out.println("Invalid flight selection.");
            return;
        }

        System.out.print("How many seniors/PWD are present? ");
        int seniorsPwd = scanner.nextInt();
        scanner.nextLine();
        if (seniorsPwd > 0) {
            double discount = pricePerTicket * 0.2 * seniorsPwd;
            totalPrice -= discount;
            System.out.printf("20%% discount applied for %d seniors/PWD. Discount amount: PHP %.2f\n", seniorsPwd, discount);
        }

        System.out.println("\nAvailable Add-ons:");
        int addOnNumber = 1;
        for (Map.Entry<String, Double> entry : addOns.entrySet()) {
            System.out.printf("%d. %s: PHP %.2f\n", addOnNumber++, entry.getKey(), entry.getValue());
        }
        System.out.print("Do you want to add any add-ons? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Enter add-on number: ");
            int addOnChoice = scanner.nextInt();
            scanner.nextLine();

            if (addOnChoice > 0 && addOnChoice <= addOns.size()) {
                String addOn = (String) addOns.keySet().toArray()[addOnChoice - 1];

                if (addOn.equals("Added Baggage")) {
                    displayBaggageOptions();
                    System.out.print("Enter baggage option number: ");
                    int baggageChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (baggageChoice > 0 && baggageChoice <= baggageOptions.size()) {
                        String selectedBaggage = (String) baggageOptions.keySet().toArray()[baggageChoice - 1];
                        double baggagePrice = baggageOptions.get(selectedBaggage);
                        totalPrice += baggagePrice;
                        System.out.printf("%s added. Total price updated to PHP %.2f\n", selectedBaggage, totalPrice);
                    } else {
                        System.out.println("Invalid baggage option number.");
                    }
                } else if (addOn.equals("In-flight Meal")) {
                    boolean addAnotherMeal = true;
                    while (addAnotherMeal) {
                        displayMealOptions();
                        System.out.print("Enter meal option number: ");
                        int mealChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (mealChoice > 0 && mealChoice <= mealOptions.size()) {
                            String selectedMeal = (String) mealOptions.keySet().toArray()[mealChoice - 1];
                            double mealPrice = mealOptions.get(selectedMeal);
                            totalPrice += mealPrice;
                            System.out.printf("%s added. Total price updated to PHP %.2f\n", selectedMeal, totalPrice);
                        } else {
                            System.out.println("Invalid meal option number.");
                        }

                        System.out.print("Do you want to add another meal? (yes/no): ");
                        String response = scanner.nextLine();
                        if (!response.equalsIgnoreCase("yes")) {
                            addAnotherMeal = false;
                        }
                    }
                } else {
                    totalPrice += addOns.get(addOn);
                    System.out.printf("%s added. Total price updated to PHP %.2f\n", addOn, totalPrice);
                }
            } else {
                System.out.println("Invalid add-on number.");
            }
        }

        System.out.println("\nAvailable Seats:");
        displaySeats();
        for (int i = 1; i <= (adults + kids + seatInfants); i++) {
            System.out.printf("Enter seat row and column for passenger %d (e.g., 1,1): ", i);
            String[] seat = scanner.nextLine().split(",");
            int row = Integer.parseInt(seat[0]) - 1;
            int col = Integer.parseInt(seat[1]) - 1;
            if (row >= 0 && row < 10 && col >= 0 && col < 6 && !seats[row][col]) {
                seats[row][col] = true;
                System.out.println("Seat booked successfully.");
            } else {
                System.out.println("Invalid seat or seat already taken.");
                i--;
            }
        }

        System.out.println("\nSelect Payment Option:");
        for (int i = 0; i < paymentOptions.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, paymentOptions.get(i));
        }
        System.out.print("Enter payment option number: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        if (paymentChoice < 1 || paymentChoice > paymentOptions.size()) {
            System.out.println("Invalid payment option.");
            return;
        }

        String selectedPayment = paymentOptions.get(paymentChoice - 1);
        System.out.printf("Selected payment method: %s\n", selectedPayment);

        List<Map<String, String>> passengers = new ArrayList<>();
        for (int i = 1; i <= (adults + kids + seatInfants + lapInfants); i++) {
            System.out.printf("\nEnter details for passenger %d:\n", i);
            System.out.print("Title (Mr./Ms.): "); String title = scanner.nextLine();
            System.out.print("First name: "); String firstName = scanner.nextLine();
            System.out.print("Last name: "); String lastName = scanner.nextLine();
            System.out.print("Date of birth (YYYY-MM-DD): "); String dob = scanner.nextLine();
            System.out.print("Nationality: "); String nationality = scanner.nextLine();

            passengers.add(Map.of(
                    "Title", title, "FirstName", firstName, "LastName", lastName,
                    "DOB", dob, "Nationality", nationality));
        }

        System.out.println("\nEnter contact information:");
        System.out.print("First name: "); String contactFirstName = scanner.nextLine();
        System.out.print("Last name: "); String contactLastName = scanner.nextLine();
        System.out.print("Mobile number: "); String contactMobile = scanner.nextLine();
        System.out.print("Email: "); String contactEmail = scanner.nextLine();

        System.out.println("\n--- Booking Summary ---");
        System.out.printf("From: %s (%s)\nTo: %s (%s)\nDeparture Date: %s\n",
                from, destinations.get(from), to, destinations.get(to), departureDate);
        Map<String, String> selectedFlight = flights.get(flightChoice - 1);
        System.out.printf("Airline: %s | Departure: %s | Arrival: %s | Duration: %s\n",
                selectedFlight.get("Airline"), selectedFlight.get("Departure"), selectedFlight.get("Arrival"), selectedFlight.get("Duration"));
        System.out.println("Seats Booked:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j]) {
                    System.out.printf("Row %d, Column %d\n", i + 1, j + 1);
                }
            }
        }
        System.out.printf("Payment Method: %s\n", selectedPayment);

        System.out.print("\nConfirm booking? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("\nBooking confirmed! Thank you for using our service.");
        } else {
            System.out.println("\nBooking cancelled.");
        }
    }

    static void displayDestinations() {
        System.out.println("\nAvailable Destinations:");
        System.out.println("--------------------------------------");
        int count = 0;
        for (Map.Entry<String, String> entry : destinations.entrySet()) {
            System.out.printf("%-25s", entry.getValue());
            count++;
            if (count % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n--------------------------------------\n");
    }

    static String getCode(String input) {
        String finalInput = input.trim().toUpperCase();
        for (Map.Entry<String, String> entry : destinations.entrySet()) {
            if (entry.getKey().equals(finalInput) || entry.getValue().equalsIgnoreCase(input.trim())) {
                return entry.getKey();
            }
        }
        return "";
    }

    static double getPrice(String route, boolean isRoundTrip) {
        double price = 1000.0; // Example base price
        return isRoundTrip ? price * 1.8 : price;
    }

    static void displaySeats() {
        System.out.println("Seat Availability:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? "[X] " : String.format("[%d,%d] ", i + 1, j + 1));
            }
            System.out.println();
        }
    }

    static void viewBookings(String username) {
        System.out.println("Viewing bookings for " + username);
    }

    static void viewAllBookings() {
        System.out.println("Viewing all bookings");
    }

    static void manageFlights() {
        System.out.println("Managing flights");
    }
}
