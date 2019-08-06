package abc.springmvc.javaclasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		LocalDateTime today =  LocalDateTime.now();     //Today
		LocalDateTime tomorrow = today.plusDays(1);
		
		
		System.out.println(tomorrow);
		 

	}

}
