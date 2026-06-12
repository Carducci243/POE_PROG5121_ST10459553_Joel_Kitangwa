package org.example;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Messages {
    long messageId;
    int numOfMessages;
    int numOfMessagesSent;
    String recipient;
    String message;
    String messageHash;

    static ArrayList<Messages> storedMessages = new ArrayList<>();

    Messages( String recipient , String message){

        this.messageId=generateId();
        checkMessageId(this.messageId);
        this.recipient=recipient;
        this.message=message;
        this.messageHash=createMessageHash(this.messageId, this.numOfMessages, this.message);

    }

    static long generateId(){
        Random random = new Random();
        long id = random.nextLong(1_000_000_000L, 10_000_000_000L);
        System.out.println(id);
        return id;
    }




    static boolean checkMessageId(long id){
        boolean status = true ;
        if (String.valueOf(id).length()>10){
            System.out.println("Message ID is incorrect ");
            status=false;
            System.out.println(status);
    }
        else {
            System.out.println("Message id is correct ");
        }
    return status;
}
    static String createMessageHash(long messageId, int numOfMessages, String message) {

        String firstTwoDigits = String.valueOf(messageId).substring(0, 2);


        String msgCount = String.valueOf(numOfMessages);


        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];


        return (firstTwoDigits + ":" + msgCount + ":" + firstWord + lastWord).toUpperCase();
    }



    static String messageSent(String choice) {
        switch (choice.toLowerCase()) {
            case "send message"      -> { return "Message successfully sent."; }
            case "disregard message" -> { return "Press 0 to delete the message."; }
            case "store message"     -> { return "Message successfully stored."; }
            default                  -> { return "Invalid option."; }
        }
    }
    static void storedMessagesMenu(Scanner scanner) {
        System.out.println("\n=== Stored Messages ===");
        System.out.println("a : Display sender and recipient of all stored messages");
        System.out.println("b : Display longest stored message");
        System.out.println("c : Search by message ID");
        System.out.println("d : Search by recipient");
        System.out.println("e : Delete message by hash");
        System.out.println("f : Full report of all stored messages");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice.toLowerCase()) {

            case "a" -> {
                if (storedMessages.isEmpty()) {
                    System.out.println("No stored messages.");
                } else {
                    for (Messages m : storedMessages) {
                        System.out.println("Recipient: " + m.recipient + " | Message: " + m.message);
                    }
                }
            }

            case "b" -> {
                if (storedMessages.isEmpty()) {
                    System.out.println("No stored messages.");
                } else {
                    Messages longest = storedMessages.get(0);
                    for (Messages m : storedMessages) {
                        if (m.message.length() > longest.message.length()) {
                            longest = m;
                        }
                    }
                    System.out.println("Longest message: " + longest.message);
                }
            }

            case "c" -> {
                System.out.print("Enter message ID: ");
                long searchId = Long.parseLong(scanner.nextLine().trim());
                boolean foundId = false;
                for (Messages m : storedMessages) {
                    if (m.messageId == searchId) {
                        System.out.println("Recipient: " + m.recipient);
                        System.out.println("Message: "   + m.message);
                        foundId = true;
                        break;
                    }
                }
                if (!foundId) System.out.println("Message ID not found.");
            }

            case "d" -> {
                System.out.print("Enter recipient number: ");
                String searchRecipient = scanner.nextLine().trim();
                boolean foundRecipient = false;
                for (Messages m : storedMessages) {
                    if (m.recipient.equals(searchRecipient)) {
                        System.out.println("Message: " + m.message);
                        foundRecipient = true;
                    }
                }
                if (!foundRecipient) System.out.println("No messages found for recipient.");
            }

            case "e" -> {
                System.out.print("Enter message hash: ");
                String searchHash = scanner.nextLine().trim();
                boolean removed = storedMessages.removeIf(m -> m.messageHash.equals(searchHash));
                System.out.println(removed ? "Message deleted." : "Hash not found.");
            }

            case "f" -> {
                if (storedMessages.isEmpty()) {
                    System.out.println("No stored messages.");
                } else {
                    System.out.println("\nFull Report");
                    for (Messages m : storedMessages) {
                        System.out.println("ID: "        + m.messageId);
                        System.out.println("Recipient: " + m.recipient);
                        System.out.println("Message: "   + m.message);
                        System.out.println("Hash: "      + m.messageHash);

                    }
                }
            }

            default -> System.out.println("Invalid option.");
        }
    }
    void MessageOption() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        int numOfMessages = 0;

        while (!choice.equalsIgnoreCase("quit")) {
            System.out.println("\n Quick Message");
            System.out.println("Messages sent: " + this.numOfMessagesSent);
            System.out.println("1 : Send Message");
            System.out.println("2 : Disregard Message");
            System.out.println("3 : Store Message");
            System.out.println("4 : Stored Message Menu");
            System.out.println("Type 'quit' to exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Type your message: ");
                    String message = scanner.nextLine().trim();
                    if (message.length()>250){
                        System.out.println("Invalid Message length");
                        break;
                    }
                    numOfMessages++;
                    this.numOfMessagesSent=numOfMessages;//
                    long messageId = generateId();
                    String hash = createMessageHash(messageId, numOfMessages, message);
                    System.out.println(messageSent("send message"));
                    System.out.println("Message #" + numOfMessages + ": " + message);
                    System.out.println("Hash: " + hash);
                }
                case "2" -> {
                    System.out.println(messageSent("disregard message"));
                    System.out.print("Press 0 to confirm delete: ");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equals("0")) {
                        numOfMessages = Math.max(0, numOfMessages - 1);
                        System.out.println("Message deleted.");
                    }
                }
                case "3" -> {
                    System.out.print("Type your message to store: ");
                    String storedMessage = scanner.nextLine().trim();
                    numOfMessages++;
                    this.numOfMessagesSent = numOfMessages;
                    this.message = storedMessage;
                    this.messageHash = createMessageHash(this.messageId, numOfMessages, storedMessage);

                    // Add to ArrayList
                    Messages stored = new Messages(this.recipient, this.message);
                    stored.messageHash = this.messageHash;
                    stored.numOfMessagesSent = this.numOfMessagesSent;
                    storedMessages.add(stored);


                    Gson gson = new Gson();
                    String json = gson.toJson(this);
                    try (FileWriter writer = new FileWriter("messages.json", true)) {
                        writer.write(json + "\n");
                        System.out.println(messageSent("store message"));
                        System.out.println("Stored: " + json);
                    } catch (IOException e) {
                        System.out.println("Could not store message.");
                    }
                }
                case "4" -> storedMessagesMenu(scanner);
                case "5" -> System.out.println("Total messages sent: " + returnTotalMessages());
                case "quit" -> System.out.println("Goodbye!");
                default     -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    static String checkRecipientCell(String cellphone) {
        if (cellphone.matches("^0\\d{9}$") || cellphone.matches("^\\+27\\d{9}$")) {
            System.out.println("Valid number");
            return "Number is Valid";
        } else {
            System.out.println("Invalid phone number");
            return "Invalid";
        }
    }
    int returnTotalMessages() {
        return this.numOfMessagesSent;
    }
    static String checkMessageLength(String message) {
        if (message.length() > 250) {
            return "Please enter a message of less than 250 characters.";
        } else {
            return "Message sent";
        }
    }

}
