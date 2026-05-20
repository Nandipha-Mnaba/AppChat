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
    public Messages (int messageNumber , String recipient, String messageText){
        this.messageNumber = messageNumber;
        this.recipient     = recipient;
        this.messageText   = messageText ;
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
        String lastWord  =words[words.length - 1].replaceAll("[^a-zA-Z0-9]","");
        return (idPrefix +":" + messageNumber +":" + firstWord +lastWord).toUpperCase ();
}
 //method 4 sentMessage 
    //sllows the user to send , disregard , or store message 
    
public String sentMessage (int choice ){
    switch(choice){
        case 1 : 
            return "Message successfully sent";
        case 2 :
           return "Press 0 to delet the message ";
        case 3 :
            return "Message successfully stored";
        default: 
            return "Invalid option";
    }
}
//Method 5 print-Message ()
// return this message's details in the required display order:
//message ID message hash , Recipient , Message 
public String printMessage (){
return "Message ID :"   + messageID  + "\n"   +
        "Message Hash:" + messageHash + "\n"  +
        "Recipient: "   + recipient   +"\n "  +
        "Message :"     + messageText +"\n "  +
        "_______";
}

//method 6 -returntotalmessage (int currentTotal )
//Adds 1 to the running total maintained by MessageMnagaer .

public int returnTotalMessages(int currentTotal){
    return currentTotal + 1 ;
}

//method 7 - storeMessage ()[research feature ]
//serialises this message to a JSON- FORMATTED STRING 
public String storeMessage(){
    return "{\n"+
            " \"mesageID\":\""   +messageID       +"\",n" +
            "\"messageNumber\":" +messageNumber  + ",\n" +
            "\"recipient \": \"" + recipient     +"\",\n"+
            "\"message \": \""   + messageText   +"\",\n" +
            "\"messageHash\":\""   +messageHash    +"\"\n" +
                    "}";
}
//Getters
public String getMessageID() { return messageID; }
 public int getMessageNumber (){return messageNumber ;}
 public String getRecipient() {return recipient ;}
 public String getMessageText (){return messageText; }
 public String getMessageHash (){return messageHash; }
 
}