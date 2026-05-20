/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import java.util.Scanner;
/**
 *
 * @author lab_services_student
 */
public class AppChat {

  public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== Chat Application ===");

        // User registration
        System.out.print("First name: ");
        String firstName = input.nextLine();

        System.out.print("Last name: ");
        String lastName = input.nextLine();

        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        System.out.print("Cell number (+27...): ");
        String cell = input.nextLine();

        Login user = new Login(username, password, cell, firstName, lastName);

        System.out.println("\n" + user.registerUser());

        System.out.println("\n--- Login ---");
        System.out.print("Enter username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password: ");
        String loginPass = input.nextLine();

        boolean status = user.authenticateUser(loginUser, loginPass);

        System.out.println(user.loginStatus(status));

        System.out.println("\nWelcome to AppChat.");

        // Ask how many messages the user wants to send
        System.out.print("How many messages do you wish to send? ");
        int numMessages = Integer.parseInt(input.nextLine().trim());

        // MessageManager
        MessageManager manager = new MessageManager(input);

        // Menu loop
        boolean running = true;
        String sentMessages = "";   // store sent messages
        String storedMessages = ""; // store stored messages

        while (running) {
            System.out.println("\n--- Menu ----");
            System.out.println("1) Send messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    // Loop through each message
                    for (int i = 1; i <= numMessages; i++) {
                        System.out.println("\n--- Message " + i + " ---");
                        System.out.print("Enter recipient number (+27...): ");
                        String recipient = input.nextLine();

                        System.out.print("Enter your message: ");
                        String text = input.nextLine();

                        Messages msg = new Messages(i, recipient, text);

                        System.out.println("\n1) Send message");
                        System.out.println("2) Store message");
                        System.out.print("Choose an option: ");
                        String action = input.nextLine().trim();

                        if (action.equals("1")) {
                            sentMessages += msg.printMessage() + "\n";
                            System.out.println("Message successfully sent.");
                        } else if (action.equals("2")) {
                            storedMessages += msg.storeMessage() + "\n";
                            System.out.println("Message successfully stored.");
                        } else {
                            System.out.println("Invalid choice, message discarded.");
                        }
                    }
                    break;

                case "2":
                    System.out.println("\n--- Sent Messages ---");
                    if (sentMessages.isEmpty()) {
                        System.out.println("No messages sent yet.");
                    } else {
                        System.out.println(sentMessages);
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        input.close();
    } 
}