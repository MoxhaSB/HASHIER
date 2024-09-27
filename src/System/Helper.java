package System;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Helper {

    Helper(){}
    public final void readArchivesTxt(String hash, Hasher creator) {

        AtomicInteger hasBeenFound = new AtomicInteger();

        if(createDirectory()) {
            Path carpeta = Paths.get("Archives");

            try {
                // Crear un Stream de archivos
                Stream<Path> archivos = Files.list(carpeta);

                // Verificar si la carpeta está vacía
                if (archivos.findAny().isEmpty()) {
                    System.out.println("\nNo txt files found.");
                    System.out.println("Place some files with the .txt extension in this path to search for passwords.");
                    System.out.println("PATH: " + carpeta.toAbsolutePath());
                    openDirectory(carpeta);
                    return;
                }

                // Crear un nuevo Stream para filtrar archivos txt
                archivos = Files.list(carpeta) // Crear un nuevo Stream
                        .filter(archivo -> archivo.toString().endsWith(".txt"));

                archivos.forEach(archivo -> {
                    try {
                        System.out.println("Reading ...  " + archivo.getFileName());

                        // Leer líneas del archivo
                        Stream<String> lineas = Files.lines(archivo, StandardCharsets.UTF_8);
                        // Se saca una línea del archivo leído
                        lineas.forEach(linea -> {

                            hasBeenFound.set(isMatch(linea, hash,creator ));

                        });

                        // Cerrar el stream de líneas
                        lineas.close();

                    } catch (IOException e) {
                        System.out.println("Error leyendo el archivo " + archivo.getFileName());
                        e.printStackTrace();
                    }
                });

                // Cerrar el stream de archivos
                archivos.close();

                if (hasBeenFound.get() == 0) {
                    System.out.println("\n SADLY... The word has not been found.");
                }

            } catch (IOException e) {
                System.out.println("Error al acceder a la carpeta");
                e.printStackTrace();
            }
        }
    }

    public final boolean createDirectory() {
        Path carpeta = Paths.get("Archives");

        // Crear la carpeta si no existe
        if (Files.notExists(carpeta)) {
            try {
                Files.createDirectory(carpeta);
                System.out.println("\nThe directory has been created: " + carpeta.toAbsolutePath());
                System.out.println("Place some files with the .txt extension in the path to search for passwords.");

            } catch (IOException e) {
                System.out.println("The directory could not be created.");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public final void openDirectory(Path carpeta) throws IOException {
        // Abrir la carpeta
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(carpeta.toFile());
        } else {
            System.out.println("Desktop is not supported.");
        }
    }
    public final  int isMatch(String linea, String hash, Hasher creator){

        try {
            //SHA 256
            if(creator.createHash_SHA_256(linea).equals(hash) ){
                System.out.println("\n¡¡ Password has been found !!  with format --> SHA-256");
                System.out.println("|hash to search|: " + hash);
                System.out.println("|Password|: " + linea);
                return 1;
                //SHA 512
            }else if (creator.createHash_SHA_512(linea).equals(hash)){
                System.out.println("\n¡¡ Password has been found !!  with format --> SHA-512");
                System.out.println("|Hash to search|: " + hash);
                System.out.println("|Password|: " + linea);
                return 1;


                //SHA 1
            }else if (creator.createHash_SHA_1(linea).equals(hash)){

                System.out.println("\n¡¡ Password has been found !!  with format --> SHA-1");
                System.out.println("|hash to search|: " + hash);
                System.out.println("|Password match|: " + linea);
                return 1;


                //MD5
            }else if (creator.createHash_MD5(linea).equals(hash)){
                System.out.println("\n¡¡ Password has been found !!  with format --> MD5");
                System.out.println("|hash to search|: " + hash);
                System.out.println("|Password match|: " + linea );
                return 1;


            }


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }
        return 0;
    }
    public final int verifyData(Scanner read){
        int a;
        try{
            a = Integer.parseInt(read.toString());
        }catch (IllegalArgumentException e){
            return -1;
        }
        return a;
    }
}
