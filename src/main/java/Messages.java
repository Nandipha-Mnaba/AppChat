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
    // hash
    
    public String createMessagehash(){
        String idPart = messageld.substring(0,2);
        String[]words + messagetext.trim().split("\\s+");
        String firstWord =words.length>0? words[0]:"";
        String lastWord=words.length> 0 ? words[words.length -1]: "";
        this messageHash = (idPart+ ":" + messageNumber =":" +firstWord +lastWord).replaceAll("[^A-Za-z0-9:]","")
                .toUpperCase();
        
        this.messageHash = (idPart + ":" + messageNumber + ":" + stripPunctuation(firstWord) +
                stripPunctuation(lastWord)).ToUpperCase();
        return thismessageHash;
       
    }
    private String stripPunctuation(String word){
        return word.replaceAll("[^A-Za-z0-9:]","");
    }
    //lets the user choose to send or store or delete message 
    public String sent Message(String choice){
        switch (choice.toLowerCase()){
            case "send ":
                    this.flag +"sent";
                    this.messageNumber =++messageCounter
                            createMessageHash();
                            
           return"Message successfully sent."  ;
            case "store":
                this.flag ="stored";
                this.messageNumber=++messageCounter;
                createMessageHash();
                return "Message successfully stored.";
            case "disregard"
                    this.flag ="disregard"
                     return "Press 0 to delete the message."   ;
            default :
            throw new illegalArgumentException("invalid choice : must be send , store , or disregard.") ;
        }
        }
    //helpers 
    
    private String generateMessageld(){
        Random rand = new Random ();
        StringBuilder id = newstringBuilder():
        for (int i =0:i <10;i++){
        id.append(rand.nextInt(10));
    }
        return id.toString();
        
        //getters / setters 
        
        public String getMessageld(){
            return messageld;
            
    }
        public void setMessageld(String messageld){
            this.messageld = messageld;
        }
        public int getMessageNumber(){
            return messageNumber;
        }
        public void setMessageNumber(int messageNumber){
         this.messageNumber = messageNumber;
        }
        public String getRecipient(){
            return recipient;
        }
        public void setRecipient(String recipient){
            this.recipient = recipient;
        }
        public string GetMessageText(){
            return messageText;
        }
        public void setMessageText(string messageText){
            this.messagetext = messageText;
        }
        public String getMessageHash(){
            if(messageHash==null){
                createmessagehash();
            }
            return messageHash;
            }
        public String getFlag(){
            return flag;
        }
        public void setFlag(String flag){
            this.flag = flag;
        }
        //staic counter 
        public static void resetcounter(){
            messageCounter =0;
        }
         public String toString(){
             return"Message ID;"+ messageld
              + "\nMessage Hash: " + getMessageHash()
+ "\nRecipient: " + recipient
+ "\nMessage: " + messageText;
}
} 
       
         
       