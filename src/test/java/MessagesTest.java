import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class MessagesTest {

    public MessagesTest() {
    }

    // Create a sample object for testing
    Messages msg = new Messages(
            1,
            "+27831234567",
            "Hello Chat App"
    );

    @Test
    public void testCheckMessage() {

        boolean result = msg.checkMessage();

        assertTrue(result);
    }

    @Test
    public void testCheckRecipientCell() {

        String expected =
                "Cell phone number successfully captured.";

        String actual = msg.checkRecipientell();

        assertEquals(expected, actual);
    }

    @Test
    public void testCreateMessageHash() {

        String hash = msg.createMessageHash();

        assertNotNull(hash);

        assertTrue(hash.contains(":1:"));
    }

    @Test
    public void testSentMessage() {

        String expected = "Message successfully sent";

        String actual = msg.sentMessage(1);

        assertEquals(expected, actual);
    }

    @Test
    public void testPrintMessage() {

        String result = msg.printMessage();

        assertNotNull(result);

        assertTrue(result.contains("Message ID"));

        assertTrue(result.contains("Recipient"));
    }

    @Test
    public void testReturnTotalMessages() {

        int result = msg.returnTotalMessages(5);

        assertEquals(6, result);
    }

    @Test
    public void testStoreMessage() {

        String result = msg.storeMessage();

        assertNotNull(result);

        assertTrue(result.contains("messageID"));

        assertTrue(result.contains("messageHash"));
    }

    @Test
    public void testGetMessageID() {

        assertNotNull(msg.getMessageID());
    }

    @Test
    public void testGetMessageNumber() {

        assertEquals(1, msg.getMessageNumber());
    }

    @Test
    public void testGetRecipient() {

        assertEquals("+27831234567", msg.getRecipient());
    }

    @Test
    public void testGetMessageText() {

        assertEquals("Hello Chat App", msg.getMessageText());
    }

    @Test
    public void testGetMessageHash() {

        assertNotNull(msg.getMessageHash());
    }
}