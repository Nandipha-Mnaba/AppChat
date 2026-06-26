/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
*/

import java.util.List;
import java.util.Scanner;

public class AppChat {

private static Login login = new Login();
private static MessageManager manager = new MessageManager();
private static Scanner scanner = new Scanner(System.in);

public static void main(String[] args) {
System.out.println("=== Welcome to QuickChat Registration ===");

// ----- Part 1: Register -----
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

String registrationResult = login.registerUser(username, password, cell, firstName, lastName);
System.out.println(registrationResult);

if (!registrationResult.contains("registered successfully")) {
System.out.println("Registration failed. Exiting.");
return;
}

//  Login 
System.out.println("\n=== Login ===");
System.out.print("Username: ");
String loginUsername = scanner.nextLine();
System.out.print("Password: ");
String loginPassword = scanner.nextLine();

boolean loggedIn = login.loginUser(loginUsername, loginPassword);
System.out.println(login.returnLoginStatus(loggedIn));

if (!loggedIn) {
System.out.println("Cannot proceed without successful login. Exiting.");
return;
}

System.out.println("\nWelcome to QuickChat.");
runMenu();
}

/** Runs the numeric menu until the user selects Quit. */
public static void runMenu() {
boolean running = true;
while (running) {
System.out.println("\n1) Send Messages");
System.out.println("2) Show recently sent messages");
System.out.println("3) Quit");
System.out.print("Choose an option: ");
String choice = scanner.nextLine().trim();

switch (choice) {
case "1":
sendMessagesFlow();
break;
case "2":
System.out.println("Coming Soon.");
break;
case "3":
running = false;
System.out.println("Goodbye!");
break;
default:
System.out.println("Invalid option, please choose 1, 2 or 3.");
}
}
}

 
private static void sendMessagesFlow() {
System.out.print("How many messages would you like to send? ");
int numMessages;
try {
numMessages = Integer.parseInt(scanner.nextLine().trim());
} catch (NumberFormatException e) {
System.out.println("Invalid number.");
return;
}

for (int i = 0; i < numMessages; i++) {
System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
System.out.print("Recipient (e.g. +27718693002): ");
String recipient = scanner.nextLine();
System.out.print("Message text: ");
String text = scanner.nextLine();

Message message = new Message(recipient, text);

String lengthCheck = message.checkMessageLength();
System.out.println(lengthCheck);
if (!lengthCheck.equals("Message ready to send.")) {
continue; // skip this message, too long
}

String cellCheck = message.checkRecipientCell();
System.out.println(cellCheck);
if (!cellCheck.equals("Cell number successfully captured.")) {
continue; // skip, invalid recipient number
}

System.out.print("Choose: send / store / disregard: ");
String action = scanner.nextLine().trim().toLowerCase();

try {
String result = message.sentMessage(action);
System.out.println(result);
manager.addMessage(message);

if (action.equals("send")) {
System.out.println("\nMessage Details:");
System.out.println(message.toString());
}
} catch (IllegalArgumentException e) {
System.out.println("Invalid choice, message disregarded by default.");
}
}

System.out.println("\nTotal messages sent so far: " + manager.returnTotalMessages());
System.out.println(manager.displayReport());
}

public static Login getLogin() {
return login;
}

public static MessageManager getManager() {
return manager;
}
}