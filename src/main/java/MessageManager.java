import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> disregardedMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();
    private static final String JSON_FILE_PATH = "storedMessages.json";

    // adds message to the correct array by flag
    public void addMessage(Message message) {
        switch (message.getFlag().toLowerCase()) {
            case "sent":
                sentMessages.add(message);
                break;
            case "stored":
                storedMessages.add(message);
                saveStoredMessageToJson(message);
                break;
            case "disregard":
                disregardedMessages.add(message);
                break;
            default:
                throw new IllegalArgumentException("Unknown flag: " + message.getFlag());
        }
    }

    // JSON stores the messages
    private void saveStoredMessageToJson(Message message) {
        try {
            File file = new File(JSON_FILE_PATH);
            List<String> existingEntries = new ArrayList<>();

            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath())).trim();

                if (content.length() > 2) { // more than just "[]"
                    content = content.substring(1, content.length() - 1).trim();

                    if (!content.isEmpty()) {
                        String[] parts = content.split("\\},\\s*\\{");
                        for (int i = 0; i < parts.length; i++) {
                            String part = parts[i];
                            if (!part.startsWith("{")) {
                                part = "{" + part;
                            }
                            if (!part.endsWith("}")) {
                                part = part + "}";
                            }
                            existingEntries.add(part);
                        }
                    }
                }
            }

            String newEntry = "{"
                    + "\"messageId\":\"" + escapeJson(message.getMessageId()) + "\","
                    + "\"messageHash\":\"" + escapeJson(message.getMessageHash()) + "\","
                    + "\"recipient\":\"" + escapeJson(message.getRecipient()) + "\","
                    + "\"message\":\"" + escapeJson(message.getMessageText()) + "\""
                    + "}";

            existingEntries.add(newEntry);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[" + String.join(",", existingEntries) + "]");
            }

        } catch (IOException e) {
            System.err.println("Failed to write stored message to JSON: " + e.getMessage());
        }
    }

    private String escapeJson(String text) {
        if (text == null) return "";
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    // this reads the messages stored in json file
    public List<Message> readStoredMessagesFromJson() {
        List<Message> result = new ArrayList<>();
        File file = new File(JSON_FILE_PATH);

        if (!file.exists()) return result;

        try {
            String content = new String(Files.readAllBytes(file.toPath())).trim();
            if (content.length() <= 2) return result; // empty array "[]"

            content = content.substring(1, content.length() - 1).trim();
            String[] entries = content.split("\\},\\s*\\{");

            for (String entry : entries) {
                if (!entry.startsWith("{")) entry = "{" + entry;
                if (!entry.endsWith("}")) entry = entry + "}";

                String recipient = extractJsonValue(entry, "recipient");
                String text = extractJsonValue(entry, "message");
                String id = extractJsonValue(entry, "messageId");
                String hash = extractJsonValue(entry, "messageHash");

                Message m = new Message(recipient, text);
                m.setMessageId(id);
                m.setFlag("stored");

                // only if your Message class has this method
                // m.setMessageHash(hash);

                result.add(m);
            }

        } catch (IOException e) {
            System.err.println("Failed to read stored messages from JSON: " + e.getMessage());
        }

        return result;
    }

    private String extractJsonValue(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) return "";
        start += search.length();

        int end = json.indexOf("\"", start);
        if (end == -1) return "";

        return json.substring(start, end)
                .replace("\\\"", "\"")
                .replace("\\\\", "\\");
    }

    // return text of the longest message
    public String findLongestMessage() {
        Message longest = null;
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);

        for (Message m : all) {
            if (longest == null || m.getMessageText().length() > longest.getMessageText().length()) {
                longest = m;
            }
        }

        return longest != null ? longest.getMessageText() : "";
    }

    public String searchByMessageId(String idToFind) {
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);

        for (Message m : all) {
            if (idToFind.equals(m.getMessageId())) {
                return m.getMessageText();
            }
        }

        return "Message ID not found.";
    }

    // returns messages for the given recipient
    public List<String> searchMessagesByRecipient(String recipient) {
        List<String> results = new ArrayList<>();
        List<Message> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);

        for (Message m : all) {
            if (recipient.equals(m.getRecipient())) {
                results.add(m.getMessageText());
            }
        }

        return results;
    }

    // deletes a message from sent, stored, or disregarded
    public String deleteMessageByHash(String hash) {
        List<List<Message>> allArrays = List.of(sentMessages, storedMessages, disregardedMessages);

        for (List<Message> list : allArrays) {
            for (int i = 0; i < list.size(); i++) {
                Message m = list.get(i);
                if (m.getMessageHash().equals(hash)) {
                    String text = m.getMessageText();
                    list.remove(i);
                    return "Message: \"" + text + "\" successfully deleted.";
                }
            }
        }

        return "Message hash not found.";
    }

    public String displayReport() {
        StringBuilder report = new StringBuilder();
        report.append("----- SENT MESSAGE REPORT -----\n");

        for (Message m : sentMessages) {
            report.append("Message Hash: ").append(m.getMessageHash()).append("\n");
            report.append("Recipient: ").append(m.getRecipient()).append("\n");
            report.append("Message: ").append(m.getMessageText()).append("\n");
            report.append("--------------------------------\n");
        }

        return report.toString();
    }

    // returns sent messages while the program is running
    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append(m.toString()).append("\n\n");
        }
        return sb.toString();
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public List<Message> getDisregardedMessages() {
        return disregardedMessages;
    }

    public List<Message> getStoredMessages() {
        return storedMessages;
    }
}

 


