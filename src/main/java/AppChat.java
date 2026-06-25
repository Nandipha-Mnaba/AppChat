/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import java.util.Scanner;

public class AppChat {

    private static Login login = new Login();
    private static MessageManager manager = new MessageManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=== Chat Application ===");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a username (must contain '_' and be <= 5 chars): ");
        String username = scanner.nextLine();

        System.out.print("Enter a password (8+ chars, capital, number, special char): ");
        String password = scanner.nextLine();

        System.out.print("Enter your SA cell number (e.g. +27838968976): ");
        String cell = scanner.nextLine();

        String registrationResult =
                login.registerUser(username, password, cell, firstName, lastName);

        System.out.println(registrationResult);

        if (!registrationResult.contains("registered successfully")) {
            System.out.println("Registration failed. Exiting.");
            return;
        }

        System.out.println("\n=== Login ===");

        System.out.print("Username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Password: ");
        String loginPassword = scanner.nextLine();

        boolean loggedIn =
                login.loginUser(loginUsername, loginPassword);

        System.out.println(login.returnLoginStatus(loggedIn));

        if (!loggedIn) {
            System.out.println("Cannot proceed without successful login.");
            return;
        }

        boolean running = true;

        while (running) {

            System.out.println("\n--- Menu ---");
            System.out.println("1) Send messages");
            System.out.println("2) Show sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    sendMessagesFlow();
                    break;

                case "2":
                    System.out.println(manager.displayReport());
                    break;

                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void sendMessagesFlow() {

        System.out.print("How many messages would you like to send? ");

        int numMessages;

        try {
            numMessages = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        for (int i = 0; i < numMessages; i++) {

            System.out.println("\n--- Message " + (i + 1) + " ---");

            System.out.print("Recipient: ");
            String recipient = scanner.nextLine();

            System.out.print("Message text: ");
            String text = scanner.nextLine();

            Messages message = new Messages(i + 1, recipient, text);

            System.out.println(message.checkMessageLength());
            System.out.println(message.checkRecipientCell());

            System.out.print("Choose (send/store/disregard): ");
            String action = scanner.nextLine().trim().toLowerCase();

            try {

                String result = message.sentMessage(action);
                System.out.println(result);

                // Only if your MessageManager uses Messages class
                // manager.addMessage(message);

                if (action.equals("send")) {
                    System.out.println(message);
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid option.");
            }
        }
    }

    public static Login getLogin() {
        return login;
    }

    public static MessageManager getManager() {
        return manager;
    }
}