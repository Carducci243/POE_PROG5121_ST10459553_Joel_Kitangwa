package org.example;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Messages {
    long messageId;
    int numOfMessages;
    int numOfMessagesSent;
    String recipient;
    String message;
    String messageHash;

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


                    Gson gson = new Gson();
                    String json = gson.toJson(this);

                    try (FileWriter writer = new FileWriter("messages.json")) {
                        writer.write(json + "\n");
                        System.out.println(messageSent("store message"));
                        System.out.println("Stored: " + json);
                    } catch (IOException e) {
                        System.out.println("Could not store message.");
                    }
                }
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
