package System;

import java.util.Scanner;

public class SystemHash {

    public final Hasher creator = new Hasher();
    public final Scanner read = new Scanner(System.in);
    public final Helper helper = new Helper();
    public final Hasher hasher = new Hasher();

    SystemHash(){
        menu();
    }
    public  void menu(){

        while(true){
            System.out.println("\nHASHIER");
            System.out.println("""
                    1) Create a HASH
                    2) Find password with hash
                    Write your option: 
                    """);
            switch (helper.verifyData(read)){
                case -1:
                    System.out.println("\nEnter a numeric value.");
                break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("\nWrite a valid option. ");
            }
        }

    }

    public void createHash(){}
    public void FindPasswordWithHash(){}



}
