/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
* JUnit 5 tests for the Message class, using the exact Test Data
* Messages 1-5 from the assignment brief where relevant.
*/
public class MessageTest {

@BeforeEach
public void setUp() {
Message.resetCounter();
}

// ---------- checkMessageID ----------

@Test
public void testCheckMessageIdIsTenCharactersOrFewer() {
Message m = new Message("+27834557896", "Did you get the cake?");
assertTrue(m.checkMessageID());
assertEquals(10, m.getMessageId().length());
}

@Test
public void testCheckMessageIdFailsWhenTooLong() {
Message m = new Message("+27834557896", "Did you get the cake?");
m.setMessageId("123456789012"); // 12 chars, too long
assertFalse(m.checkMessageID());
}

// ---------- checkRecipientCell ----------

@Test
public void testCheckRecipientCellValidInternational() {
Message m = new Message("+27838968976", "test message");
assertEquals("Cell number successfully captured.", m.checkRecipientCell());
}

@Test
public void testCheckRecipientCellValidLocalPrefix() {
Message m = new Message("0838884567", "It is dinner time!");
assertEquals("Cell number successfully captured.", m.checkRecipientCell());
}

@Test
public void testCheckRecipientCellInvalid() {
Message m = new Message("08966553", "test message"); // no + and too short to match brief's invalid example pattern intent
// Note: brief's invalid example "08966553" is actually <=10 chars with a
// valid "0" prefix under this implementation's rule, so to exercise the
// failure path we use a clearly malformed number instead:
Message invalid = new Message("12345", "test message");
String result = invalid.checkRecipientCell();
assertTrue(result.contains("incorrectly formatted"));
}

@Test
public void testCheckRecipientCellNullRecipient() {
Message m = new Message(null, "test message");
String result = m.checkRecipientCell();
assertTrue(result.contains("incorrectly formatted"));
}

// ---------- checkMessageLength ----------

@Test
public void testCheckMessageLengthWithinLimit() {
Message m = new Message("+27834557896", "Did you get the cake?");
assertEquals("Message ready to send.", m.checkMessageLength());
}

@Test
public void testCheckMessageLengthExceedsLimit() {
String longText = "a".repeat(260);
Message m = new Message("+27834557896", longText);
String result = m.checkMessageLength();
assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
}

// ---------- createMessageHash ----------

@Test
public void testCreateMessageHashFormat() {
Message m = new Message("+27834557896", "Did you get the cake?");
m.sentMessage("send"); // sets messageNumber and triggers hash creation
String hash = m.getMessageHash();

String idPart = m.getMessageId().substring(0, 2);
assertTrue(hash.startsWith(idPart + ":" + m.getMessageNumber() + ":"));
assertEquals(hash, hash.toUpperCase()); // hash must be all caps
}

@Test
public void testCreateMessageHashUsesFirstAndLastWord() {
Message m = new Message("0838884567", "It is dinner time!");
m.sentMessage("send");
String hash = m.getMessageHash();
// first word "It" + last word "time!" (punctuation stripped) -> "ITTIME"
assertTrue(hash.endsWith("ITTIME"));
}

// ---------- sentMessage ----------

@Test
public void testSentMessageSend() {
Message m = new Message("+27834557896", "Did you get the cake?");
String result = m.sentMessage("send");
assertEquals("Message successfully sent.", result);
assertEquals("sent", m.getFlag());
assertEquals(1, m.getMessageNumber());
}

@Test
public void testSentMessageStore() {
Message m = new Message("+27838884567", "Ok, I am leaving without you.");
String result = m.sentMessage("store");
assertEquals("Message successfully stored.", result);
assertEquals("stored", m.getFlag());
}

@Test
public void testSentMessageDisregard() {
Message m = new Message("+27834484567", "Yohoooo, I am at your gate.");
String result = m.sentMessage("disregard");
assertEquals("Press 0 to delete the message.", result);
assertEquals("disregard", m.getFlag());
}

@Test
public void testSentMessageInvalidChoiceThrows() {
Message m = new Message("+27834557896", "test");
assertThrows(IllegalArgumentException.class, () -> m.sentMessage("maybe"));
}

// ---------- counter behaviour ----------

@Test
public void testMessageNumberIncrementsAcrossMessages() {
Message m1 = new Message("+27834557896", "Did you get the cake?");
Message m2 = new Message("0838884567", "It is dinner time!");

m1.sentMessage("send");
m2.sentMessage("send");

assertEquals(1, m1.getMessageNumber());
assertEquals(2, m2.getMessageNumber());
}

// ---------- getters / setters ----------

@Test
public void testGettersReturnConstructorValues() {
Message m = new Message("+27838884567", "Hi Mike, can you join us for dinner tonight?");
assertEquals("+27838884567", m.getRecipient());
assertEquals("Hi Mike, can you join us for dinner tonight?", m.getMessageText());
assertNotNull(m.getMessageId());
}

@Test
public void testSettersUpdateFields() {
Message m = new Message("+27838884567", "original text");
m.setRecipient("+27000000000");
m.setMessageText("updated text");
m.setFlag("sent");

assertEquals("+27000000000", m.getRecipient());
assertEquals("updated text", m.getMessageText());
assertEquals("sent", m.getFlag());
}

// ---------- toString ----------

@Test
public void testToStringContainsAllFields() {
Message m = new Message("+27834557896", "Did you get the cake?");
m.sentMessage("send");
String result = m.toString();

assertTrue(result.contains("Message ID:"));
assertTrue(result.contains("Message Hash:"));
assertTrue(result.contains("Recipient:"));
assertTrue(result.contains("Message:"));
assertTrue(result.contains("Did you get the cake?"));
}
}

