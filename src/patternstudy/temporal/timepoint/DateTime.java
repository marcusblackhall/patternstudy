package patternstudy.temporal.timepoint;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @url http://www.google.com/codesearch/p?hl=en&sa=N&cd=2&ct=rc#yVWdqCbNRSo/projects/agreement/framework/java/src/mf/MfDate.java&q=MfDate
 *
 */
public class DateTime implements Comparable<DateTime> {
	private GregorianCalendar calendar;

	public static final DateTime PAST = new DateTime(1, 1, 1);
	public static final DateTime FUTURE = new DateTime(10000, 1, 1);

	public DateTime(GregorianCalendar calendar) {
		this.calendar = calendar;
	}

	public DateTime(int year, int month, int day) {
		this(new GregorianCalendar(year, month - 1, day));
	}

	public static DateTime now() {
		return new DateTime(new GregorianCalendar());
	}

	public static DateTime today() {
		DateTime now = now();
		return now.setTime(0, 0, 0, 0);
	}

	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public int getMonth() {
		return calendar.get(Calendar.MONTH + 1);
	}

	public int getDay() {
		return calendar.get(Calendar.DATE);
	}

	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}

	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}

	public int getMilliSecond() {
		return calendar.get(Calendar.MILLISECOND);
	}

	@Override
	public int compareTo(DateTime o) {
		return calendar.compareTo(o.calendar);
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public DateTime addYears(int amount) {
		return add(Calendar.YEAR, amount);
	}

	public DateTime addMonths(int amount) {
		return add(Calendar.MONTH, amount);
	}

	public DateTime addDays(int amount) {
		return add(Calendar.DATE, amount);
	}

	public DateTime addHours(int amount) {
		return add(Calendar.HOUR_OF_DAY, amount);
	}

	public DateTime addMinutes(int amount) {
		return add(Calendar.MINUTE, amount);
	}

	public DateTime addSeconds(int amount) {
		return add(Calendar.SECOND, amount);
	}

	private DateTime add(int field, int amount) {
		GregorianCalendar calendar = cloneCalendar(this);
		calendar.add(field, amount);
		return new DateTime(calendar);
	}

	public DateTime setDate(int year, int month, int day) {
		GregorianCalendar calendar = cloneCalendar(this);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, day);
		return new DateTime(calendar);
	}

	public DateTime setTime(int hour, int minute, int second, int millisecond) {
		GregorianCalendar calendar = cloneCalendar(this);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millisecond);
		return new DateTime(calendar);
	}

	private static GregorianCalendar cloneCalendar(DateTime dateTime) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(dateTime.getCalendar().getTime());
		return calendar;
	}
}
