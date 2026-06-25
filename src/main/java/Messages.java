/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Random;
/**
 *
 * @author lab_services_student
 */
public class Messages {
    private String messageID;
    private int    messageNumber;
    private String recipient ;
    private String messageText;
    private String messageHash;
    private String flag;
    
    
    // constructor
    public Messages (int messageNumber , String recipient, String messageText){
        this.messageNumber = messageNumber;
        this.recipient     = recipient;
        this.messageText   = messageText ;
        
public string checkRecipientCell (){
    if (recipient == null){
        return "Cell phone number is incorrectly formatted or does not contain an international code ;please correct the number and try again .";
                
    }
    boolean vaildLength = recipient.length()<=10||(recipient.startsWith("+"))&&
            recipient.length()<=12);
    boolean validPrefix =recipient.startsWith("+","").length()<=10){
    return "Cell number successfully captured.";
}
    return"Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    
    public String checkMessageLength(){
        if (messagetext.length()<=250){
            return "Message reday to send >";
        }else{
            return"Message exceeds 250 characters by " +(messageText.length()-250)=";please reduce the size"
                    
        }
        }
    
    return
}
}
}
       