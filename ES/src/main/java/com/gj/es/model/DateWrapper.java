package com.gj.es.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;
/*import org.apache.commons.validator.routines.CalendarValidator;
import org.apache.commons.validator.routines.DateValidator;*/
//import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;


/**
 * Acts as a wrapper for a date by breaking it into day,month,year and providing conversion methods
 * @author ganesh jadhav
 */
public class DateWrapper implements Cloneable, Serializable {
  private static final long serialVersionUID = 3023476226777972470L;
  private  static Logger logger = (Logger) LoggerFactory.getLogger(DateWrapper.class);
 // private static final Logger logger = Logger.getLogger(DateWrapper.class);

  private int day;
  private int month;
  private int year;

  private int hour;
  private int minute;
  private int second;

  public DateWrapper() {
	  super();
  }


  public DateWrapper(int day,int month,int year) {
    this.day   = day;
    this.month = month;
    this.year  = year;
  }

  public DateWrapper(Date d) {
    this.setDate(d);
  }

  public DateWrapper(Calendar c) {
    if (c!= null) {
      this.setDate(c.getTime());
    }
  }

  public void setTime(int hour,int minute,int second) {
      this.hour = hour;
      this.minute = minute;
      this.second = second;
  }

  @Override
  public Object clone() { // NOSONAR
    return new DateWrapper(day,month,year);
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }
  public void setDayString(String day) {
    this.day = convertToInt(day);
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }
  public void setMonthString(String month) {
    this.month = convertToInt(month);
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
  public void setYearString(String year) {
    this.year = convertToInt(year);
  }


  public String getDateString() {
    return convertDateToString(getDate(),"dd/MM/yyyy");
  }
  
  public String getDateString(String dateFormat) {
    return convertDateToString(getDate(), dateFormat);
  }
  
  public void setDateString(String s) {
    setDate(convertStringToDate(s,"dd/MM/yyyy"));
  }

  public Date getDate() {
        return getDate(false);
  }

    public Date getDate(boolean notNull) {
      if (this.isValidDate()) {
        final Calendar c = Calendar.getInstance();
        c.set(year,month-1,day,hour,minute,second);
        return c.getTime();
      } else {
        if (notNull) {
            return new Date();
        } else {
            return null;
        }
      }
    }

  public void setDate(Date d) {
    if (d != null) {
      final Calendar c = Calendar.getInstance();
      c.setTime(d);

      day = c.get(Calendar.DAY_OF_MONTH);
      month = c.get(Calendar.MONTH)+1;
      year = c.get(Calendar.YEAR);

    }
  }

    public void setTime(Date d) {
      if (d != null) {
        final Calendar c = Calendar.getInstance();
        c.setTime(d);

        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH)+1;
        year = c.get(Calendar.YEAR);

        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);
        this.second = c.get(Calendar.SECOND);
      }
    }

    public Calendar getCalendar() {
      if (this.isValidDate()) {
        final Calendar c = Calendar.getInstance();
        c.set(year,month-1,day,hour,minute,second);
        return c;
      } else {
        return null;
      }
    }

  public String getDayString() {
    if (day == 0) return "";

    String prefix = "";
    if (day<10) prefix = "0";
    return prefix+Integer.toString(day);
  }

  public String getMonthString() {
    if (month == 0) return "";

    String prefix = "";
    if (month<10) prefix = "0";
    return prefix+Integer.toString(month);
  }

  public String getYearString() {
    if (year == 0) return "";

    return Integer.toString(year);
  }


  public boolean isDateEmpty() {
    return (day==0 && month==0 && year==0);
  }

  public boolean isValidDate(boolean checkForLeapYear) {
    boolean result = true;

    if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1800) {
      result = false;
    } else {

      int daysForMonth = this.DAYS_IN_MONTH[month-1];


      if (checkForLeapYear && month == 2) {
        //leap year check
        if ( ( (year%4 == 0)&&(year%100 != 0) ) || (year%400 == 0) ) {
          daysForMonth = 29;
        } else {
          daysForMonth = 28;
        }
      }

      if (day > daysForMonth) {
        result = false;
      }

    }

    return result;
  }

  // doesn't check the leap year
  public boolean isValidDate() {
    return isValidDate(false);
  }

  public static Date convertStringToDate(String date,String mask) {
    if (date==null || date.length() < 8 || mask==null) return null;

    final SimpleDateFormat df = new SimpleDateFormat(mask);
    Date d = null;

    try {
      d = df.parse(date);
    } catch (ParseException pe) {
     // logger.warn("StringToDate parse exception for date:"+date,pe);
    }

    return d;
  }

  public static Date convertStringToDate(String adate) {
      return convertStringToDate(adate,"dd/MM/yyyy");
  }

  public static String convertDateToString(Date date) {
    return convertDateToString(date,"dd/MM/yyyy");
  }

  public static String convertDateToString(Date date,String mask) {
    if (date==null || mask==null) return null;

    final SimpleDateFormat df = new SimpleDateFormat(mask);
    return df.format(date);
  }


  private static int convertToInt(String s) {
    if (s == null || s.length() == 0) return 0;

    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException nfe) {
     // logger.warn("Failed to convert string to int "+nfe.getMessage());
    }
    return 0;
  }



  private static final int DAYS_IN_MONTH[] = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};



  public static int getTodaysYear() {
    final Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    return c.get(Calendar.YEAR);
  }

  public static int getTodaysDay() {
    final Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    return c.get(Calendar.DAY_OF_MONTH);
  }

  public static int getTodaysMonth() {
    final Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    return c.get(Calendar.MONTH)+1;
  }  
  
  public static Calendar getTodayCalendar() {
	  final Calendar c = Calendar.getInstance();
	  c.setTime(new Date());
	  return c;
  }


/*  public boolean isFutureDate() {
    final Calendar now = Calendar.getInstance();

    final Calendar thisDate = Calendar.getInstance();
    thisDate.set(year,month-1,day);

      return compareDates(thisDate,now) == 1;
  }

    public boolean isPastDate() {
      final Calendar now = Calendar.getInstance();

      final Calendar thisDate = Calendar.getInstance();
      thisDate.set(year,month-1,day);
      return compareDates(thisDate,now) == -1;
    }*/

    public boolean isFutureYear() {
      final Calendar now = Calendar.getInstance();
      int nowYear = now.get(Calendar.YEAR);

      return year > nowYear;
    }


    public int getAge() {
        return new DateWrapper().getAge(getCalendar());
    }

    public int getAge(Calendar birth) {     
        if (birth == null) return 0;
        
        final Calendar today = getTodayCalendar();
        
        int factor = 0; //to correctly calculate age when birthday has not yet been celebrated
        
        // Check if birthday has been celebrated this year:
        if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) { // then it's before or during bday month.
        	if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) { // then it's party month!
        		if (today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
                    factor = -1; // birthday not celebrated -- a few days to wait...
        		}
        	} else { // birth month has not yet been hit this year.
        		factor = -1; // birthday not celebrated -- come back in a month or more..
        	}
        }
        
        // Calc the age in years, to the day:
        int years = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + factor;
        
       // if (logger.isDebugEnabled()) logger.debug("getAge: years="+years);   
        
        return years;
    }

   /* 
     * Compare Dates (day, month and year - not time).
     * returing 0, -1 or +1 indicating whether the first date is equal, before or after the second.
     
    public static int compareDates(Calendar cal_1,Calendar cal_2) {
        CalendarValidator val = CalendarValidator.getInstance();
        return val.compareDates(cal_1,cal_2);
    }

    
     * Compare Dates (day, month and year - not time).
     * returing 0, -1 or +1 indicating whether the first date is equal, before or after the second.
     
    public static int compareDates(Date cal_1,Date cal_2) {
        DateValidator val = DateValidator.getInstance();
        return val.compareDates(cal_1,cal_2,null);
    }
*/
    public static Date removeTime(Date date) {
      if(date != null) {

          // Get an instance of the Calendar.
          Calendar calendar = Calendar.getInstance();

          // Make sure the calendar will not perform automatic correction.
          calendar.setLenient(false);
          //calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
          // Set the time of the calendar to the given date.
          calendar.setTime(date);

          // Remove the hours, minutes, seconds and milliseconds.
          calendar.set(Calendar.HOUR_OF_DAY, 0);
          calendar.set(Calendar.MINUTE, 0);
          calendar.set(Calendar.SECOND, 0);
          calendar.set(Calendar.MILLISECOND, 0);

          // Return the date again.
          return calendar.getTime();
      } else {
          return date;
      }
    }


    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
    	if (date == null) return null;

        DateWrapper dw = new DateWrapper(date);

        try {
        	DatatypeFactory factory = DatatypeFactory.newInstance();
        	return factory.newXMLGregorianCalendar( new GregorianCalendar(dw.getYear(), (dw.getMonth() -1), dw.getDay()));
        } catch (DatatypeConfigurationException e) {
			//logger.warn("Failed to convert "+dw.getDateString()+" to XMLGregorianCalendar; reason="+e.getMessage());
			return null;
		}

    }


   public static java.util.Date addMonths(java.util.Date aDate, int number){
        java.util.Calendar aCalendar = java.util.Calendar.getInstance();
        aCalendar.setTime(aDate);
        aCalendar.add(java.util.Calendar.MONTH, number);
        return aCalendar.getTime();
    }

    public static java.util.Date addDays(java.util.Date aDate, int number){
        java.util.Calendar aCalendar = java.util.Calendar.getInstance();
        aCalendar.setTime(aDate);
        aCalendar.add(java.util.Calendar.DATE, number);
        return aCalendar.getTime();
    }
    
    public static java.util.Date addYears(java.util.Date aDate, int number){
        java.util.Calendar aCalendar = java.util.Calendar.getInstance();
        aCalendar.setTime(aDate);
        aCalendar.add(java.util.Calendar.YEAR, number);
        return aCalendar.getTime();
    }
    
	public static java.util.Date getUTCDate(java.util.Date aDate) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.setTime(aDate);
		return c.getTime();
	}

	public static int getXMLGregorianCalendarTimeZone(Date dt) {
		if (dt == null)
			return 0; // UTC
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(dt);
		int offsetInMinutes = (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET)) / (60 * 1000);
		return offsetInMinutes;
	}
	
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(String date, int xmlGregorianCalendarTimeZone) {
		if (date == null)
			return null;
		try {
			DatatypeFactory factory = DatatypeFactory.newInstance();
			XMLGregorianCalendar dt = factory.newXMLGregorianCalendar(date);
			dt.setTimezone(xmlGregorianCalendarTimeZone);
			return dt;
		} catch (DatatypeConfigurationException e) {
			logger.debug("Failed to convert " + date + " to XMLGregorianCalendar; reason=" + e.getMessage());
			return null;
		}

	}
	
	public static Date addTimeStamp(Date dt) {
		Calendar calendar = new GregorianCalendar();
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		java.util.Calendar aCalendar = java.util.Calendar.getInstance();
		aCalendar.setTime(dt);
		aCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		aCalendar.set(Calendar.MINUTE, minute);
		aCalendar.set(Calendar.SECOND, second);
		return aCalendar.getTime();
	}
	
	public static Date addHours(Date dt, int amount) {
		 
		return DateUtils.addHours(dt, amount);
	}
	
	public static Date addMinutes(Date dt, int amount) {
		 
		return DateUtils.addMinutes(dt, amount);
	}
	
	
	public static java.util.Date asDate(XMLGregorianCalendar xmlGC) {
		if (xmlGC == null) {
			return null;
		} else {
			return xmlGC.toGregorianCalendar().getTime();
		}
	}

	public static XMLGregorianCalendar convertToXMLGregorianCalendar(String date) {
		if (date == null)
			return null;
		try {
			DatatypeFactory factory = DatatypeFactory.newInstance();
			XMLGregorianCalendar dt = factory.newXMLGregorianCalendar(date);
			return dt;
		} catch (DatatypeConfigurationException e) {
			logger.debug("Failed to convert " + date + " to XMLGregorianCalendar; reason=" + e.getMessage());
			return null;
		}
	}
	
	public static XMLGregorianCalendar convertToXMLGregorianCalendarWithoutTime(Date date) {
		XMLGregorianCalendar xmlGregorianCalendar = convertToXMLGregorianCalendar(date);
		xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		xmlGregorianCalendar.setHour(DatatypeConstants.FIELD_UNDEFINED);
		xmlGregorianCalendar.setMinute(DatatypeConstants.FIELD_UNDEFINED);
		xmlGregorianCalendar.setSecond(DatatypeConstants.FIELD_UNDEFINED);
		xmlGregorianCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		return xmlGregorianCalendar;
	}
	
	public static java.util.Date removeDays(java.util.Date aDate, int number) {
		return addDays(aDate, number * -1);
	}
	
  /**
  * 
  * @param fromDate
  * @return date with time set as hour=0,min=0,sec=0
  */
 public static Date getStartOfDayDate(Date fromDate) {
     Calendar from = Calendar.getInstance();
     from.setTime(fromDate);

     from.set(Calendar.HOUR_OF_DAY,0); 
     from.set(Calendar.MINUTE,0); 
     from.set(Calendar.SECOND,0);         
     
     return from.getTime();
 }
 
 
 /**
  * 
  * @param toDate
  * @return date with time parameters set to the end of the day values
  */
 public static Date getEndOfDayDate(Date toDate) {                
     Calendar to = Calendar.getInstance();
     to.setTime(toDate);

     to.set(Calendar.HOUR_OF_DAY,23); 
     to.set(Calendar.MINUTE,59); 
     to.set(Calendar.SECOND,59); 
     
     return to.getTime();        
 }
}