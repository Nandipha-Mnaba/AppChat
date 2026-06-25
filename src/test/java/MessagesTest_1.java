import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessagesTest_1 {

    // Sample messages for testing
    Message msg1 = new Message(
            1,
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?"
    );

    Message msg2 = new Message(
            2,
            "08575975889",
            "Hi Keegan, did you receive the payment?"
    );

    Message msgLong = new Message(
            3,
            "+27831234567",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
            + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            + "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
    );

   
    //  Test Message Length
    
    @Test
    public void testMessageLengthSuccess() {
        String result = msg1.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        String result = msgLong.checkMessageLength();
        assertTrue(result.startsWith("Message exceeds 250 characters by"));
    }

    
    //  Test Recipient Number
   
    @Test
    public void testRecipientSuccess() {
        String result = msg1.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientFailure() {
        String result = msg2.checkRecipientCell();
        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
            result
        );
    }

   
    //  Test Message Hash
    
    @Test
    public void testMessageHash() {
        String hash = msg1.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains(":1:"));
    }

    @Test
    public void testMultipleMessageHashes() {
        Message[] messages = {msg1, msg2, msgLong};
        for (Message m : messages) {
            String hash = m.createMessageHash();
            assertNotNull(hash);
            assertTrue(hash.length() > 0);
        }
    }

    
    //  Test Send 
    
    @Test
    public void testSendMessage() {
        String result = msg1.sentMessage(1);
        assertEquals("Message successfully sent", result); // Removed dot
    }

    @Test
    public void testDisregardMessage() {
        String result = msg2.sentMessage(2);
        assertEquals("Press 0 to delet the message ", result); // Matches your Messages class
    }

    @Test
   
public void testStoreMessage() {
    String result = msg1.storeMessage();
    assertTrue(result.contains("\"messageID\""));
    assertTrue(result.contains("\"messageHash\""));
    assertTrue(result.contains("\"recipient\""));
    assertTrue(result.contains("\"message\""));
}

  
    //  Test Getters
    
    @Test
    public void testGetters() {
        assertEquals(1, msg1.getMessageNumber());
        assertEquals("+27718693002", msg1.getRecipient());
        assertEquals("Hi Mike, can you join us for dinner tonight?", msg1.getMessageText());
        assertNotNull(msg1.getMessageHash());
        assertNotNull(msg1.getMessageID());
    }

   
    //  Test Print Message
    
    @Test
    public void testPrintMessage() {
        String result = msg1.printMessage();
        assertNotNull(result);
        assertTrue(result.contains("Message ID"));
        assertTrue(result.contains("Message Hash"));
        assertTrue(result.contains("Recipient"));
        assertTrue(result.contains("Message"));
    }

    // Test Total Messages
   
    @Test
    public void testReturnTotalMessages() {
        int result = msg1.returnTotalMessages(5);
        assertEquals(6, result); // Adds 1
    }
}