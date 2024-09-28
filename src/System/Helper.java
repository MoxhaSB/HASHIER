package System;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helper {

    Helper() {
    }

    public final void readArchivesTxt(String hash, Hasher creator) {
        if (createDirectory()) {
            Path carpeta = Paths.get("Archives");

            try {
                // Crear un Stream de archivos
                try (Stream<Path> archivosStream = Files.list(carpeta)) {

                    // Verificar si la carpeta está vacía
                    if (archivosStream.findAny().isEmpty()) {
                        System.out.println("\nNo txt files found.");
                        System.out.println("Place some files with the .txt extension in this path to search for passwords.");
                        System.out.println("PATH: " + carpeta.toAbsolutePath());
                        openDirectory(carpeta);
                        return;
                    }
                }

                // Crear un nuevo Stream para filtrar archivos txt y convertirlo a una lista
                List<Path> archivos = Files.list(carpeta)
                        .filter(archivo -> archivo.toString().endsWith(".txt"))
                        .collect(Collectors.toList()); // Convertir a List

                for (Path archivo : archivos) {
                    try {
                        System.out.println("\nReading ... -> " + archivo.getFileName());

                        // Leer todas las líneas del archivo y guardarlas en una lista
                        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8); // Cargar líneas en un List

                        // Recorrer las líneas del archivo
                        boolean found = false;

                        for (String linea : lineas) {

                            if (isMatch(linea, hash, creator)) {
                                found = true; // Se encontró una línea que no coincide
                                break; // Rompe el ciclo para este archivo
                            }
                        }

                        if (!found) {
                            System.out.println("|*| The word has not been found :( ");
                        }

                    } catch (IOException e) {
                        System.out.println("Error leyendo el archivo " + archivo.getFileName());
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                System.out.println("Error al acceder a la carpeta");
                e.printStackTrace();
            }
        }
    }

    public final ArrayList<String> getTxtFiles() {

        ArrayList<String> txtFiles = new ArrayList<>();

        if (createDirectory()) {
            Path carpeta = Paths.get("Archives");

            try {
                // Crear un Stream de archivos
                try (Stream<Path> archivosStream = Files.list(carpeta)) {

                    // Verificar si la carpeta está vacía
                    if (archivosStream.findAny().isEmpty()) {
                        System.out.println("\nNo txt files found.");
                        System.out.println("Place some files with the .txt extension in this path to search for passwords.");
                        System.out.println("PATH: " + carpeta.toAbsolutePath());
                        openDirectory(carpeta);
                        return null;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Crear un nuevo Stream para filtrar archivos txt y convertirlo a una lista
                List<Path> archivos = Files.list(carpeta).filter(archivo -> archivo.toString().endsWith(".txt")).collect(Collectors.toList()); // Convertir a List

                for (Path archivo : archivos) {
                    txtFiles.add(archivo.getFileName().toString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return txtFiles;

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

    public final boolean isMatch(String linea, String hash, Hasher creator) {

        try {
            //SHA 256
            if (creator.createHash_SHA_256(linea).equals(hash)) {
                System.out.println("\n¡¡ Password has been found !!  with format --> SHA-256");
                System.out.println("|hash to search|: " + hash);
                System.out.println("|Password|: " + linea);
                return true;
                //SHA 512
            } else if (creator.createHash_SHA_512(linea).equals(hash)) {
                System.out.println("\n¡¡ Password has been found !!  with format --> SHA-512");
                System.out.println("|Hash to search|: " + hash);
                System.out.println("|Password|: " + linea);
                return true;


                //SHA 1
            } else if (creator.createHash_SHA_1(linea).equals(hash)) {

                System.out.println("\n¡¡ Password has been found !!  with format --> SHA-1");
                System.out.println("|hash to search|: " + hash);
                System.out.println("|Password match|: " + linea);
                return true;


                //MD5
            } else if (creator.createHash_MD5(linea).equals(hash)) {
                System.out.println("\n¡¡ Password has been found !!  with format --> MD5");
                System.out.println("|hash to search|: " + hash);
                System.out.println("|Password match|: " + linea);
                return true;

            }


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

        }
        return false;
    }

    public final int verifyData(Scanner read) {
        int a;
        try {
            a = Integer.parseInt(read.nextLine());
        } catch (IllegalArgumentException e) {
            return -1;
        }
        return a;
    }
}
