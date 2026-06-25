/
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *

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
    
public static final Pattern CELL_PATTERN =
            Pattern.compile("^\\+\\d{10,12}$");

    public Login(String firstName, String lastName)
                 {
       
  
        this.firstName = firstName;
        this.lastName = lastName;
    }
    

    // Username must contain "_" and be no more than 5 characters
     public boolean checkUserName(String username) {

        if (username == null) {
            return false;
        return username.contains("_")&& username.length()<=5;
    }

    // Password complexity rules
    public boolean checkPasswordComplexity(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }
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
        if (cell == null) return false;
        return cell.startsWith ("+")&& CELL_PATTERN>matcher(cell).matches();
    }
        
    }

    // Registration feedback messages
    public String registerUser(String username, String password , String cell, String firstName ,String lastName){ {

        if (!checkUserName(username)) {
            return "Username incorrectly formatted. Must contain '_' and is no more than five characters in length..";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password "
+ "contains at least eight characters, a capital letter, a number, "
+ "and a special character.";
        }

        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
}
this.username = username;
this.password = password;
this.cellPhoneNumber = cell;
this.firstName = firstName
this.lastName = lastName;

return "Username successfully captured.Password successfully capyured."
        +"Cell phone number successfully added. You have been registered successfully.";
        }
// verfies the username 
public boolean loginUser(string enteredUsername, String enteredPassword){
        return this.username ! =null
        &&this.username.equals(enteredUsername)
                &&this.password.equals(enteredPassword);
    }

 
 

    public String returnloginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + " ," + lastName + ", login successful.";
        } else {
            return "Login failed: incorrect username or password.";
        }
    }
    public String getusername(){
        return username;
    }
        public String getUsername(){
            return username;
            
    }
        
        public String getFirstName(){
            return firstName;
        }
        public void SetFirstname(String firstName){
            this.firstName = firstName;
        }
        public String getCellPhoneNumber(){
            return cellPhoneNumber;
        }



    

