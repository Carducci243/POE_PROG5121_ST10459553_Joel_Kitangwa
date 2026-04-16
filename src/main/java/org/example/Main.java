package org.example;
import java.util.Scanner;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        WelcomeMessage();
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
                User user=new User(userName,password,phone);
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
            }


        } scanner.close();
    }

    static void  WelcomeMessage(){
        System.out.println("Welcome to Home Screen");
        System.out.println(" Press 1 to Register");
        System.out.println(" Press 2 to Log in");
    }
}