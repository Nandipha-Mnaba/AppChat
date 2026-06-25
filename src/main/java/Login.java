/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lab_services_student
 */
public class Login {
    
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    public static final Pattern CELL_PATTERN =Pattern.complie("^\\+\\d{1,3}\\d{6,9}#$");

    public Login(String firstName, String lastName)
                 {
       
  
        this.firstName = firstName;
        this.lastName = lastName;
    }
    

    // Username must contain "_" and be no more than 5 characters
    public boolean checkUserName(String username) {
        if username == null)return false;
        return username.contains("_")&& username.length()<=5;
    }

    // Password complexity rules
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;

   boolean hasCapital = false;
   boolean hasNumber= false;
   boolean hasSpecial = false;
   

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital= true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Cell phone validation using REGEX (Rubric requirement)
     * Format: +27 followed by 9 digits
     * Source concept: Java regex pattern matching (String.matches)
     */
    public boolean checkCellPhoneNumber(String cell) {
        return cellPhoneNumber != null
                && cellPhoneNumber.matches("^\\+27\\d{9}$");
    }

    // Registration feedback messages
    public String registerUser() {

        if (!checkUserName()) {
            return "Username incorrectly formatted. Must contain '_' and be max 5 characters.";
        }

        if (!checkPasswordComplexity()) {
            return "Password must be 8+ characters with uppercase, number, and special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell number must follow format +27XXXXXXXXX.";
        }

        return "Registration successful.";
    }

    // Authentication check
    public boolean authenticateUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(username)
                && enteredPassword.equals(password);
    }

    public String loginStatus(boolean status) {
        if (status) {
            return "Welcome " + firstName + " " + lastName + ", login successful.";
        } else {
            return "Login failed: incorrect username or password.";
        }
    }
}
    

