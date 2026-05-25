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

                User.register();
            }

            case 2->{
                User.login();

            }


        }
//        MessageOption();

        scanner.close();
    }

    static void  WelcomeMessage(){
        System.out.println("Welcome to Home Screen");
        System.out.println(" Press 1 to Register");
        System.out.println(" Press 2 to Log in");
    }
//    static void MessageOption(){
//        System.out.println("Welcome to quick Message");
//        System.out.println("1 : Send Messages");
//        System.out.println("2 : Show recently sent (Coming soon)");
//        System.out.println("3 : Quit");
//
//    }

}

