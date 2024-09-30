package System;

import java.time.Duration;
import java.time.Instant;

//class that read the time
public class TimeReader {

    /**
     * Constructor
     */
    TimeReader(){}

    /**
     * the method create a new instant
     * @return an instant
     */
    public Instant getTime(){
        //devuelve el tiempo
        return  Instant.now();
    }

    /**
     * The method shows the time that passed between 2 timestamps
     * @param start firts time
     * @param end second time
     */
    public void showDuration(Instant start, Instant end){
        // Calcular la duración en milisegundos y luego en segundos con tres decimales
        Duration duracion = Duration.between(start, end);
        System.out.println("|*| The word was found in " + (duracion.toMillis() / 1000.0) + " seconds |*|");
    }
}
