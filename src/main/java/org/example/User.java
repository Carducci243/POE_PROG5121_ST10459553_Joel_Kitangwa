package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
    String username;
    String password;
    String phoneNumber;


    User(String name , String password,String phoneNumber){
        this.username=name  ;
        this.phoneNumber=phoneNumber;
        this.password=password;
    }

    public static void  login(){
        Scanner scanner =new Scanner(System.in);
        System.out.println("Welcome to Log in  ");
        System.out.println("Input username");
        String userName=scanner.nextLine();
        System.out.println("Input Password ");
        String password= scanner.nextLine();
        Gson gson=new Gson();
//
        try {
            FileReader reader = new FileReader("user.json");

            User registeredUser = gson.fromJson(reader, User.class);

            reader.close();

            if (password.equals(registeredUser.password) && userName.equals(registeredUser.username)){

                System.out.println("log in  is successfully");

            }
            else {
                System.out.println("Log in unsuccessful ");
            }

        } catch (Exception e) {
            System.out.println("Invalid Login Details");
        }
    }
     public static void register (){
        Scanner scanner=new Scanner(System.in);
        Gson gson=new Gson();
        System.out.println("Welcome to Registration ");
        System.out.println("Input Phone Number");
        String phone =scanner.nextLine();
        System.out.println("Input username");
        String userName=scanner.nextLine();
        System.out.println("Input Password ");
        String password= scanner.nextLine();
        if (checkUserName(userName) && checkCellPhoneNumber(phone) && PasswordComplexity(password)){
            User user=new User(userName,password,phone);
            String jsonUser=gson.toJson(user);
            try (FileWriter writer =new FileWriter("user.json")){
                writer.write(jsonUser);
                System.out.println("Registration is Successful");

            } catch (IOException e) {
                System.out.println("could not run file");}
        }
        scanner.close();

//        System.out.println(jsonUser);
//        System.out.println(user.username);
//        System.out.println(user.password);
//        System.out.println(user.phoneNumber);

        return;
    }
    static boolean PasswordComplexity (String password){

        boolean hasUpperCase=password.matches(".*[A-Z].*");
        boolean hasNumber=password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[^a-zA-Z0-9].*");
        boolean has8Char= password.matches(".{8,}");
        boolean isValid = hasUpperCase && hasNumber && hasSpecialChar && has8Char;
        if (!hasUpperCase) {
            System.out.println("PassWord Invalid Must contain at least one uppercase letter");
        }
        if (!hasNumber) {
            System.out.println("PassWord Invalid Must contain at least one number");
        }
        if (!hasSpecialChar) {
            System.out.println("PassWord Invalid Must contain at least one special character");
        }
        if (!has8Char) {
            System.out.println("PassWord Invalid Must be at least 8 characters long");
        }
        return  isValid;
    }
    static  boolean checkUserName(String username){
        boolean hasUnderscore=username.contains("_");
        boolean haslessCharacters=username.length()>=8;
        boolean isValid = hasUnderscore && haslessCharacters;
        if (!hasUnderscore){
            System.out.println("Username is invalid ooes not contain an Underscore (_)");
        }
        if (!haslessCharacters){
            System.out.println("Username must have at least 5 characters ");
        }
        return isValid;
    }

    static boolean checkCellPhoneNumber(String cellphone){
        boolean isNumberValid=false;
        if (cellphone.matches("^0\\d{9}$")) {
            String trimmedPhone = "+27"+cellphone.substring(1);
            System.out.println("Valid number");
            System.out.println("Phone number is " + trimmedPhone);
            isNumberValid=true;
        } else {
            System.out.println("Invalid phone number");
        }
        return isNumberValid;
    }
}