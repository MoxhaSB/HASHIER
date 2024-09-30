package System;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

/**
 * Class SystemHash
 */
public class SystemHash {

    /**
     * the scanner to read inputs
     */
    private final Scanner read = new Scanner(System.in);
    /**
     * class helper to get some methods
     */
    private final Helper helper = new Helper();
    /**
     * the creator of hashes
     */
    private final Hasher hasher = new Hasher();
    /**
     * The list that contains name of the txt files
     */
    private List<String> txtFiles;
    /**
     * the path to the directory
     */
    private final Path carpeta = Paths.get("SuperHashierTxtArchives");

    /**
     * The time reader
     */
    private final TimeReader timeReader = new TimeReader();

    /**
     * Constructor
     * @throws NoSuchAlgorithmException Exception
     * @throws IOException Exception
     */
    SystemHash() throws NoSuchAlgorithmException, IOException {
        this.txtFiles = helper.getTxtFiles();
        menu();
    }

    /**
     * Method that contains the menu
     * @throws NoSuchAlgorithmException exception
     * @throws IOException exception
     */
    public void menu() throws NoSuchAlgorithmException, IOException {

        System.out.println("\n\n" +
                " _   _   ___   _____ _   _ _____ ___________ \n" +
                "| | | | / _ \\ /  ___| | | |_   _|  ___| ___ \\\n" +
                "| |_| |/ /_\\ \\\\ `--.| |_| | | | | |__ | |_/ /\n" +
                "|  _  ||  _  | `--. \\  _  | | | |  __||    / \n" +
                "| | | || | | |/\\__/ / | | |_| |_| |___| |\\ \\ \n" +
                "\\_| |_/\\_| |_/\\____/\\_| |_/\\___/\\____/\\_| \\_|\n" +
                "                 MoxhaSB                               ");
        while(true){

            System.out.println("""
                    \n---------------------------------------------
                    1) Create a HASH
                    2) Find password with hash
                    3) See saved txt files
                    4) Open directory
                    5) Refresh
                    6) Exit
                    Write your option: 
                    """);
            switch (helper.verifyData(read)){
                case -1 -> System.out.println("\nEnter a numeric value. ");
                case 1-> createHash();
                case 2-> findPasswordWithHash();
                case 3-> seeSavedTxtFile();
                case 4-> {System.out.println("Opening... ");helper.openDirectory(carpeta);}
                case 5-> refresh();
                case 6-> {return;}
                default-> System.out.println("\nWrite a valid option. ");
            }
        }

    }

    /**
     * Method that create a hash
     * @throws NoSuchAlgorithmException exception
     */
    public void createHash() throws NoSuchAlgorithmException {
        System.out.println("\nWrite a word to get the HASH: ");
        String word = read.nextLine();
        System.out.println("| SHA-1 | -> " + hasher.createHash_SHA_1(word));
        System.out.println("| SHA-256 | -> " +hasher.createHash_SHA_256(word));
        System.out.println("| SHA-512 | -> " + hasher.createHash_SHA_512(word));
        System.out.println("| MD5 | -> " +hasher.createHash_MD5(word));
    }

    /**
     * Method that find a password from a hash
     */
    public void findPasswordWithHash(){
        System.out.println("\nWrite the HASH to find the password: ");
        String hash = read.nextLine();

        if(helper.createDirectory()){
            Instant start = timeReader.getTime(); //para calcular el tiempo que tarda

            if (helper.readArchivesTxt(hash,hasher)){//find the password

                Instant end = timeReader.getTime(); //timepo final

                timeReader.showDuration(start,end);//muestra el tiempo
            }
        }
    }

    /**
     * Method that save the txt file names
     */
    public void seeSavedTxtFile(){
        if(txtFiles != null) {
            System.out.println("FILES: ");
            for (String name : txtFiles) {
                System.out.println(name);
            }
        }else{
            System.out.println("\nThere is no files.");
        }
    }

    /**
     * Method that refresh the txt file in the system
     */
    public void refresh(){
        this.txtFiles = helper.getTxtFiles();
    }




}
