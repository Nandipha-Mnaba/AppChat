import java.util.Scanner;

public class MessageManager {

    private Scanner input;
    private int totalSentCount;

    public MessageManager(Scanner input) {
        this.input = input;
        this.totalSentCount = 0;
    }

    public void handleSendMessages(int numMessages) {
        System.out.println("Sending " + numMessages + " messages...");
    }

    public int getTotalSentCount() {
        return totalSentCount;
    }
}