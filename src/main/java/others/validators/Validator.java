package others.validators;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @date 11/11/2020
 * @time 12:54:46
 * @author asael
 */
public class Validator {
    
    //Verifica si la cadena es un float
    public static boolean isFloat(String f) {
        try {
            Float.parseFloat(f);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    //Verifica si la cadena es un entero
    public static boolean isInt(String i) {
        try {
            Long.parseLong(i);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Verifica si el formato de la fecha es correcto
    public static boolean isFecha(String f) {
        try {
            LocalDate.parse(f);
            return true;
        } catch (Exception e) {
            try {
                LocalDate.parse(f, DateTimeFormatter.ofPattern("yyyy-M-d"));
                return true;
            } catch (Exception ex) {
                try {
                    LocalDate.parse(f, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    return true;
                } catch (Exception exx) {
                    return false;
                }
            }
        }
    }

    //Verifica si el formato de la hora es correcto
    public static boolean isHora(String h) {
        try {
            LocalTime.parse(h, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (Exception e) {
            try {
                LocalTime.parse(h, DateTimeFormatter.ofPattern("H:mm"));
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public static boolean exists(String nameFile) {
        File file = new File("/tmp/" + nameFile);
        return file.exists();
    }

    //Verifica si el numero es mayor a cero
    public static boolean isMayorACero(String s) {
        return (Float.parseFloat(s) > 0);
    }
}
