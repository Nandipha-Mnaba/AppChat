/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Random;

public class Message {

    private static int messageCounter = 0;

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String flag;

    // Constructor
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
    }

    // Validate recipient
    public String checkRecipientCell() {

        if (recipient == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        boolean validPrefix =
                recipient.startsWith("+") || recipient.startsWith("0");

        if (validPrefix && recipient.replace("+", "").length() <= 10) {
            return "Cell number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }

    // Validate message length
    public String checkMessageLength() {

        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters by "
                    + (messageText.length() - 250)
                    + "; please reduce the size.";
        }
    }

    // Create hash
    public String createMessageHash() {

        String idPart = messageID.substring(0, 2);

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";

        this.messageHash =
                (idPart + ":" + messageNumber + ":"
                        + stripPunctuation(firstWord)
                        + stripPunctuation(lastWord))
                        .toUpperCase();

        return this.messageHash;
    }

    private String stripPunctuation(String word) {
        return word.replaceAll("[^A-Za-z0-9]", "");
    }

    // Send, Store or Disregard
    public String sentMessage(String choice) {

        switch (choice.toLowerCase()) {

            case "send":
                this.flag = "sent";
                this.messageNumber = ++messageCounter;
                createMessageHash();
                return "Message successfully sent.";

            case "store":
                this.flag = "stored";
                this.messageNumber = ++messageCounter;
                createMessageHash();
                return "Message successfully stored.";

            case "disregard":
                this.flag = "disregard";
                return "Press 0 to delete the message.";

            default:
                throw new IllegalArgumentException(
                        "Invalid choice: must be send, store or disregard.");
        }
    }

    // Generate 10-digit ID
    private String generateMessageID() {

        Random rand = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }

        return id.toString();
    }

    // Getters and Setters
    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageHash() {
        if (messageHash == null) {
            createMessageHash();
        }
        return messageHash;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public static void resetCounter() {
        messageCounter = 0;
    }

    @Override
    public String toString() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + getMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }
}