package System;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemHash {

    public final Hasher creator = new Hasher();
    public final Scanner read = new Scanner(System.in);
    public final Helper helper = new Helper();
    public final Hasher hasher = new Hasher();
    public final List<String> txtFiles;
    public final Path carpeta = Paths.get("Archives");

    SystemHash() throws NoSuchAlgorithmException, IOException {
        this.txtFiles = helper.getTxtFiles();
        menu();
    }
    public void menu() throws NoSuchAlgorithmException, IOException {

        System.out.println("\n\n" +
                "░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ ░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓████████▓▒░▒▓███████▓▒░  \n" +
                "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n" +
                "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n" +
                "░▒▓████████▓▒░▒▓████████▓▒░░▒▓██████▓▒░░▒▓████████▓▒░▒▓█▓▒░▒▓██████▓▒░ ░▒▓███████▓▒░  \n" +
                "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n" +
                "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n" +
                "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░ \n" +
                "                                                                                      \n" +
                "                                                                                      \n");
        while(true){

            System.out.println("""
                    \n---------------------------------------------
                    1) Create a HASH
                    2) Find password with hash
                    3) See saved txt files
                    4) Open directory
                    5) Exit
                    Write your option: 
                    """);
            switch (helper.verifyData(read)){
                case -1 -> System.out.println("\nEnter a numeric value. ");
                case 1-> createHash();
                case 2-> findPasswordWithHash();
                case 3-> seeSavedTxtFile();
                case 4-> {System.out.println("Opening... ");helper.openDirectory(carpeta);}
                case 5-> {return;}
                default-> System.out.println("\nWrite a valid option. ");
            }
        }

    }

    public void createHash() throws NoSuchAlgorithmException {
        System.out.println("\nWrite a word to get the HASH: ");
        String word = read.nextLine();
        System.out.println("| SHA-1 | -> " + hasher.createHash_SHA_1(word));
        System.out.println("| SHA-256 | -> " +hasher.createHash_SHA_256(word));
        System.out.println("| SHA-512 | -> " + hasher.createHash_SHA_512(word));
        System.out.println("| MD5 | -> " +hasher.createHash_MD5(word));
    }
    public void findPasswordWithHash(){
        System.out.println("\nWrite the HASH to find the password: ");
        String hash = read.nextLine();

        if(helper.createDirectory()){
            helper.readArchivesTxt(hash,creator);
        }
    }
    public void seeSavedTxtFile(){
        System.out.println("FILES: ");
        for(String name : txtFiles){
            System.out.println(name);
        }
    }




}
