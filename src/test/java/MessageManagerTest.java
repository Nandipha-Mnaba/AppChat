/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

/**
* JUnit 5 tests for MessageManager, using the exact Test Data
* Messages 1-5 from the assignment brief.
*/
public class MessageManagerTest {

private MessageManager manager;
private Message m1, m2, m3, m4, m5;

@BeforeEach
public void setUp() {
Message.resetCounter();
manager = new MessageManager();

// Test Data Message 1
m1 = new Message("+27834557896", "Did you get the cake?");
m1.sentMessage("send");
manager.addMessage(m1);

// Test Data Message 2
m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
m2.sentMessage("store");
manager.addMessage(m2);

// Test Data Message 3
m3 = new Message("+27834484567", "Yohoooo, I am at your gate.");
m3.sentMessage("disregard");
manager.addMessage(m3);

// Test Data Message 4 (Developer entry)
m4 = new Message("0838884567", "It is dinner time!");
m4.sentMessage("send");
manager.addMessage(m4);

// Test Data Message 5
m5 = new Message("+27838884567", "Ok, I am leaving without you.");
m5.sentMessage("store");
manager.addMessage(m5);
}

@AfterEach
public void cleanUp() {
// remove the JSON test artifact created by storing messages
File jsonFile = new File("storedMessages.json");
if (jsonFile.exists()) {
jsonFile.delete();
}
}

/** Sent Messages array correctly populated (Messages 1 and 4 only). */
@Test
public void testSentMessagesArrayPopulatedCorrectly() {
List<Message> sent = manager.getSentMessages();
assertEquals(2, sent.size());

List<String> sentTexts = sent.stream().map(Message::getMessageText).toList();
assertTrue(sentTexts.contains("Did you get the cake?"));
assertTrue(sentTexts.contains("It is dinner time!"));
}

/** Stored Messages array correctly populated (Messages 2 and 5). */
@Test
public void testStoredMessagesArrayPopulatedCorrectly() {
List<Message> stored = manager.getStoredMessages();
assertEquals(2, stored.size());
}

/**
* Stored messages should be written to storedMessages.json and
* readable back into an array, per Part 3's requirement.
*/
@Test
public void testStoredMessagesPersistToJsonAndReadBack() {
List<Message> storedFromJson = manager.readStoredMessagesFromJson();

assertEquals(2, storedFromJson.size());
List<String> texts = storedFromJson.stream().map(Message::getMessageText).toList();
assertTrue(texts.contains("Where are you? You are late! I have asked you to be on time."));
assertTrue(texts.contains("Ok, I am leaving without you."));
}

/** Disregarded Messages array correctly populated (Message 3). */
@Test
public void testDisregardedMessagesArrayPopulatedCorrectly() {
List<Message> disregarded = manager.getDisregardedMessages();
assertEquals(1, disregarded.size());
assertEquals("Yohoooo, I am at your gate.", disregarded.get(0).getMessageText());
}

/** Display the longest message across Messages 1-4. */
@Test
public void testFindLongestMessage() {
String expected = "Where are you? You are late! I have asked you to be on time.";
assertEquals(expected, manager.findLongestMessage());
}

/**
* Search for a message using the brief's Test Data Message 4:
* searching "0838884567" (the developer/recipient number) should
* return "It is dinner time!".
*/
@Test
public void testSearchByMessageId() {
String result = manager.searchByMessageId("0838884567");
assertEquals("It is dinner time!", result);
}

/** Searching with a recipient number that doesn't exist should return the not-found message. */
@Test
public void testSearchByMessageIdNotFound() {
String result = manager.searchByMessageId("+27000000000");
assertEquals("Message ID not found.", result);
}

/** Search all sent/stored messages for recipient "+27838884567" (Messages 2 & 5). */
@Test
public void testSearchMessagesByRecipient() {
List<String> results = manager.searchMessagesByRecipient("+27838884567");
assertEquals(2, results.size());
assertTrue(results.contains("Where are you? You are late! I have asked you to be on time."));
assertTrue(results.contains("Ok, I am leaving without you."));
}

/** Delete a message using its message hash (Test Message 2). */
@Test
public void testDeleteMessageByHash() {
String hash = m2.getMessageHash();
String result = manager.deleteMessageByHash(hash);

assertTrue(result.contains("Message deleted:"));
assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
assertFalse(manager.getStoredMessages().contains(m2));
}

/** Deleting with a hash that doesn't exist should return the not-found message. */
@Test
public void testDeleteMessageByHashNotFound() {
String result = manager.deleteMessageByHash("XX:99:NOTHING");
assertEquals("Message hash not found.", result);
}

/** Display Report shows all sent messages with ID, Recipient, Message, Hash. */
@Test
public void testDisplayReport() {
String report = manager.displayReport();
assertTrue(report.contains("ID:"));
assertTrue(report.contains("Recipient:"));
assertTrue(report.contains("Message:"));
assertTrue(report.contains("Hash:"));
assertTrue(report.contains("Did you get the cake?"));
assertTrue(report.contains("It is dinner time!"));
}

/** printMessages() should list all sent messages with their details. */
@Test
public void testPrintMessages() {
String printed = manager.printMessages();
assertTrue(printed.contains("Did you get the cake?"));
assertTrue(printed.contains("It is dinner time!"));
assertFalse(printed.contains("Where are you?")); // stored, not sent
}

/** Return total number of messages sent (Messages 1 and 4 -> 2 total). */
@Test
public void testReturnTotalMessages() {
assertEquals(2, manager.returnTotalMessages());
}

/** addMessage() should throw for an unrecognized flag. */
@Test
public void testAddMessageThrowsOnInvalidFlag() {
Message bad = new Message("+27000000000", "test");
bad.setFlag("unknown");
assertThrows(IllegalArgumentException.class, () -> manager.addMessage(bad));
}
}
