package org.example;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        WelcomeMessage();
        Gson gson=new Gson();
        int option = 0;
        option= scanner.nextInt();
        scanner.nextLine();


        switch (option){
            case 1 ->{

                System.out.println("Welcome to Registration ");
                System.out.println("input Phone Number");
                String phone =scanner.nextLine();
                System.out.println("input username");
                String userName=scanner.nextLine();
                System.out.println("input Password ");
                String password= scanner.nextLine();
                PasswordComplexity(password);
                checkCellPhoneNumber(phone);
                User user=new User(userName,password,phone);
                String jsonUser=gson.toJson(user);
                try (FileWriter writer =new FileWriter("user.json")){
                    writer.write(jsonUser);
                    System.out.println("File created");

                } catch (IOException e) {
                    System.out.println("could not run file");}
                System.out.println(jsonUser);
                System.out.println(user.username);
                System.out.println(user.password);
                System.out.println(user.phoneNumber);


            }

            case 2->{
                System.out.println("Welcome to Log in  ");
                System.out.println("input username");
                String userName=scanner.nextLine();
                System.out.println("input Password ");
                String password= scanner.nextLine();
//                

                try {
                    FileReader reader = new FileReader("user.json");

                    User registeredUser = gson.fromJson(reader, User.class);

                    reader.close();

                   if (password.equals(registeredUser.password) && userName.equals(registeredUser.username)){

                       System.out.println("log in  is successfull");

                   }
                   else {
                       System.out.println("Log in unsuccessful ");
                   }

                } catch (Exception e) {
                    System.out.println("Error reading file");
                }

            }


        } scanner.close();
    }

    static void  WelcomeMessage(){
        System.out.println("Welcome to Home Screen");
        System.out.println(" Press 1 to Register");
        System.out.println(" Press 2 to Log in");
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
            System.out.println("Phone number is" + trimmedPhone);
            isNumberValid=true;
        } else {
            System.out.println("Invalid phone number");
        }
        return isNumberValid;
    }
}

