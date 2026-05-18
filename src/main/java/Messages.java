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
    private int messageNumber;
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
    
    
}
