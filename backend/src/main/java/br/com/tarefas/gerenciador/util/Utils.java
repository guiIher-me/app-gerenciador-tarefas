package br.com.tarefas.gerenciador.util;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utils.DATE_PATTERN);
        return date.format(formatter);
    }

    public static LocalDate stringToDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utils.DATE_PATTERN);

        try {
            return LocalDate.parse(stringDate, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException("Invalid date '" + stringDate+"'");
        }
    }   
    
}
