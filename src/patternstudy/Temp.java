package patternstudy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import patternstudy.temporal.timepoint.DateTime;

public class Temp {
	private static final TimeZone GMT = TimeZone.getTimeZone("Universal");
	public static void main(String[] args) throws InterruptedException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(f.format(cal.getTime()));
		Thread.sleep(1000);
		System.out.println(f.format(cal.getTime()));
		cal = Calendar.getInstance();
		System.out.println(f.format(cal.getTime()));
		System.out.println(cal.getTimeZone());
		System.out.println(GMT);
		//Calendar.getInstance().isLe
		
		System.out.println(f.format((MfDate.PAST.getCalendar().getTime())));
		System.out.println(f.format((DateTime.PAST.getCalendar().getTime())));
		System.out.println(f.format((DateTime.FUTURE.getCalendar().getTime())));
	}
}

class MfDate implements Comparable {
	private SimpleDateFormat myFormatter = (SimpleDateFormat) DateFormat
			.getDateInstance();
	private static MfDate myToday;

	public static final MfDate PAST = new MfDate(new GregorianCalendar(0, 1, 1));
	public static final MfDate FUTURE = new MfDate(new GregorianCalendar(10000,
			1, 1));

	// <codeFragment name = "data">
	private GregorianCalendar myBase;

	public MfDate() {
		this(new GregorianCalendar());
	}

	public MfDate(int year, int month, int day) {
		initialize(new GregorianCalendar(year, month - 1, day));
	}

	private void initialize(GregorianCalendar arg) {
		myBase = trimToDays(arg);
	}

	private GregorianCalendar trimToDays(GregorianCalendar arg) {
		arg.set(Calendar.HOUR_OF_DAY, 0);
		arg.set(Calendar.MINUTE, 0);
		arg.set(Calendar.SECOND, 0);
		arg.set(Calendar.MILLISECOND, 0);
		return arg;
	}

	// </codeFragment>

	public MfDate(Date arg) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(arg);
		initialize(gc);
	}

	public MfDate(GregorianCalendar arg) {
		initialize(arg);
	}

	// <codeFragment name = "addDays">
	public MfDate addDays(int arg) {
		return new MfDate(new GregorianCalendar(getYear(), getMonth(),
				getDayOfMonth() + arg));
	}

	public MfDate minusDays(int arg) {
		return addDays(-arg);
	}

	// </codeFragment>
	// <codeFragment name = "compare">
	public boolean after(MfDate arg) {
		return getTime().after(arg.getTime());
	}

	public boolean before(MfDate arg) {
		return getTime().before(arg.getTime());
	}

	public int compareTo(Object arg) {
		MfDate other = (MfDate) arg;
		return getTime().compareTo(other.getTime());
	}

	public boolean equals(Object arg) {
		if (!(arg instanceof MfDate))
			return false;
		MfDate other = (MfDate) arg;
		return (myBase.equals(other.myBase));
	}

	public Date getTime() {
		return myBase.getTime();
	}

	// </codeFragment>
	public String formattedString() {
		return myFormatter.format(getTime());
	}

	public GregorianCalendar getCalendar() {
		return myBase;
	}

	public int getDayOfMonth() {
		return myBase.get(Calendar.DAY_OF_MONTH);
	}

	public String getMediumString() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return df.format(getTime());
	}

	public int getMonth() {
		return myBase.get(Calendar.MONTH);
	}

	public String getSqlString() {
		myFormatter.applyPattern("#M/d/yyyy#");
		return myFormatter.format(getTime());
	}

	public int getYear() {
		return myBase.get(Calendar.YEAR);
	}

	public int hashCode() {
		return myBase.hashCode();
	}

	public static MfDate past() {
		GregorianCalendar greg = new GregorianCalendar(0, 1, 1);
		return new MfDate(greg);
	}

	public String rawString() {
		return myBase.getTime().toString();
	}

	public static void setToday(int year, int month, int day) {
		setToday(new MfDate(year, month, day));
	}

	public static void setToday(MfDate arg) {
		myToday = arg;
	}

	public static MfDate today() {
		return (myToday == null) ? new MfDate() : myToday;
	}

	public String toString() {
		return formattedString();
	}
}
