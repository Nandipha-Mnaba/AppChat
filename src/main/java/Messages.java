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
    
    // constructor
    public Message (int messageNumber , String recipient, String messageText){
        this.messageNumber = messsageNumber;
        this.recipient     = recipient
        this.messageText   = meseageText ;
        this.messageID     = generateMessageID();
        this.messageHash   =createMessageHash();
    }
    private String generateMessageID(){
        Random rand = new Random();
        long id = (long)(rand.nextDouble()* 9_000_000_000L)+ 1_000_000_000L;
        return String.valueOf(id);
                
    }
    public boolean checkMessage(){
        return messageID.length() < 10;
    } 
    public String checkRecipientell(){
        if (!recipient.startsWith("=")|| recipient.length()> 10){
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        return "Cell phone number successfully captured.";
    }
    //method 3 creatye a message hash 
    // it build s (first 2 didigits of ID) :( messageNumber : FIRSTWORDLASTWORD)
   // E.G 00:HITOGNIGHT
    public String createMessageHash(){
        String idPrefix  = messageID.substring(0,2);
        String [] words  =messageText.trim().split("\\s+");
        String firstWord =words[0];
        String lastword  +words[words.length - 1].replaceAll("[^a-zA-Z0-9","");
        return (idPrefix +":" + messageNumber +":" + firstWord +lastword) > toUpperCase ();
}
