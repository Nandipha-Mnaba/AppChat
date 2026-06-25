/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import java.util.List;
import java.util.Scanner;
/**
 *
 * @author lab_services_student
 */
public class AppChat {

private staic Login login = new Login();
private staic Messagemanager manager = newMessagemanager();
private static Scanner scanner = new Scanner(System.in);

public static void main(String[]args){
    

        Scanner input = new Scanner(System.in);

        System.out.println("=== Chat Application ===");

        // User
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

     System.out.println("\n=== Login ===");
System.out.print("Username: ");
String loginUsername = scanner.nextLine();
System.out.print("Password: ");
String loginPassword = scanner.nextLine();

boolean loggedln = login.loginUser(loginusername, loginPassword);
System.out.println(login.returnLoginStatus(loggedln));

if(!loggedln){
    System.out.println("Cannot proceed without successful login . Exiting.");
    return;
}
       boolean running = true;
       while (running) {
            System.out.println("\n--- Menu ----");
            System.out.println("1) Send messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            

            switch (choice) {
                case "1":
                    sendMessagesFlow();
                    
                    break;
                   
                    case"2":
                        System.out.println("Coming Soon.");
                        break;
                        
                    case "3":
                        running =false;
                        
                        System.out.println("Goodbye");
                        break;
                    default:
                        
                        System.out.println("Invalid option , please choose 1,2or 3");
            }
       }
}
private static void sendMessagesFlow(){
    System.out.print("How many messages would you like to send ?");
    int numMessages;
    
    try{
        numMessages=Integer.parseInt(scanner.nextLine().trim());
    }catch (NumberFormatException e){
        
        System.out.println("invalid number.");
        return;
    }
    for (int i =0;i <numMessages; i++){
        System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
        System.out.print("Recipient (e.g. +27718693002): ");
String recipient = scanner.nextLine();
System.out.print("Message text: ");

String text = scanner.nextLine();

Message message = new Message(recipient, text);

String lengthCheck = message.checkMessageLength();
System.out.println(lengthCheck);

if(!lengthCheck.equals("Messages ready to send .")){
    continue;//skip this message , too long 
}
String cellCheck = message.checkRecipientCell();
System.out.println(cellCheck);
if(!cellCheck.equals("Cell number successfully captured.")){
    continue;//skip invalid number 
}
System.out.print("Choose : send /store /disregard:");
String action = scanner.nextLine().trim().toLowerCase();

try{
    String result =messgae.senrMessage(action);
    System.out.println(result);
    manager.addMessage(message);
    
    if (action.equals("send")){
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



                        
                   
                   
                 
      

                
        
      