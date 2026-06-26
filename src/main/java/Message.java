

/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

import java.util.Random;

public class Message {

private static int messageCounter = 0; // auto-incremented "num messages sent" style counter

private String messageId;
private int messageNumber;
private String recipient;
private String messageText;
private String messageHash;
private String flag; // "sent", "stored", "disregard"

public Message(String recipient, String messageText) {
this.recipient = recipient;
this.messageText = messageText;
this.messageId = generateMessageId();
}

// ---------- Validation ----------

/** Ensures the message ID is not more than ten characters. */
public boolean checkMessageID() {
return messageId != null && messageId.length() <= 10;
}

/**
* Ensures the recipient cell number is no more than ten characters
* long and starts with an international code (+) or a local 0-prefix.
*/
public String checkRecipientCell() {
if (recipient == null) {
return "Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
}
boolean validLength = recipient.length() <= 10 || (recipient.startsWith("+") && recipient.length() <= 12);
boolean validPrefix = recipient.startsWith("+") || recipient.startsWith("0");

if (validPrefix && recipient.replace("+", "").length() <= 10) {
return "Cell number successfully captured.";
}
return "Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
}

/** Checks the message length is 250 characters or fewer. */
public String checkMessageLength() {
if (messageText.length() <= 250) {
return "Message ready to send.";
} else {
return "Message exceeds 250 characters by " + (messageText.length() - 250) + "; please reduce the size.";
}
}

// ---------- Hash ----------

/**
* Creates and returns the Message Hash:
* first two digits of ID : message number : first+last word, in caps.
*/
public String createMessageHash() {
String idPart = messageId.substring(0, 2);
String[] words = messageText.trim().split("\\s+");
String firstWord = words.length > 0 ? words[0] : "";
String lastWord = words.length > 0 ? words[words.length - 1] : "";
this.messageHash = (idPart + ":" + messageNumber + ":" + stripPunctuation(firstWord) + stripPunctuation(lastWord)).toUpperCase();
return this.messageHash;
}

private String stripPunctuation(String word) {
return word.replaceAll("[^A-Za-z0-9]", "");
}

/**
* Lets the user choose whether to send, store, or disregard the
* message. Sets the flag and returns the appropriate message.
*/
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
throw new IllegalArgumentException("Invalid choice: must be send, store, or disregard.");
}
}

// ---------- Helpers ----------

private String generateMessageId() {
Random rand = new Random();
StringBuilder id = new StringBuilder();
for (int i = 0; i < 10; i++) {
id.append(rand.nextInt(10));
}
return id.toString();
}

// ---------- Getters / setters ----------

public String getMessageId() {
return messageId;
}

public void setMessageId(String messageId) {
this.messageId = messageId;
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

/** Resets the static counter - useful between test runs. */
public static void resetCounter() {
messageCounter = 0;
}

@Override
public String toString() {
return "Message ID: " + messageId
+ "\nMessage Hash: " + getMessageHash()
+ "\nRecipient: " + recipient
+ "\nMessage: " + messageText;
}
}