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
 System.out.println("How Many messages do you wish to send ?");
 int numMessages = integer.parseInt(scanner.nextline(). trim());
 
 //----create MessageMnagager 
 MessageManager manager = new Messagemanager (Scanner);
 
 //main Menu loop 
 boolean running = true 
         while (running){
             System.out.println ("\n--- Menu ----");
             System.out.println("1) Send messages ");
             System.out.println("2) show recently sent messages ");
             System.out.println("3)Quit");
             System.out.println ("Choose an option ");
             String choice = scanner.nextLine().trim();
             
             
              switch (choice){
                  case "1":
                      manager.handlSendMessages(numMessages);
                      break ;
                  case "2"  :
                      System.out.println("Coming Soon.");
                      break;
                  case"3":
                      running = false ;
                      System.out.println("Goodbye!");
                      break;
                  default:
                      System.out.println("Invalid option . Please try again ");
              }
         }
         System.out.println("total messages sent:" + manager .getTotalSentCount());
         scanner.close();
  }
}









        input.close();
    }
 
  
}