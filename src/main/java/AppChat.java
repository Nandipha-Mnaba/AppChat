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
        
        //welcome
System.out.println("nwelcome to AppChat.");

 // Number of messages 
 System.out.println("How Many messages do you wish to send ?")









        input.close();
    }
 
  
}