package controller.util;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class DateUtil {

	//modele de date utiliser pour la conversion
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	
	// Formateur de date
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    
    // convertit un string en localDate
    public static String format(LocalDate date) 
    {
        if (date == null) 
        {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    //convertit une local date en string
    public static LocalDate parse(String dateString) 
    {
        try 
        {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } 
        catch (DateTimeParseException e) 
        {
            return null;
        }
    }

  
    
    // Verifie que la date est au bon format
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }
    
    
    public static String formater(Date date)
    {
    	if (date == null) 
        {
            return null;
        }
    	
    	Format formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String s = formatter.format(date);
    	
    	return s;
    }
    

}
