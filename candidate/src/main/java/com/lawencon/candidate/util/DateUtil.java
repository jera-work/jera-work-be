package com.lawencon.candidate.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static String dateTimeFormat(LocalDateTime localDateTime) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

		return localDateTime.format(formatter);
	}
	
	public static String dateTimeFormatMonthYear(LocalDateTime localDateTime) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");

		return localDateTime.format(formatter);
	}
	
	public static String dateFormat(LocalDate date) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		return date.format(formatter);
	}

	public static LocalDateTime dateTimeParse(String dateStr) {
//		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
//		final LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
//		final LocalDateTime date = Timestamp.valueOf(dateStr).toLocalDateTime();
		final LocalDateTime date = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
		
		return date;
	}
	
	public static LocalDate dateParse(String dateStr) {
		final LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
		
		return date;
	}
	
	public static LocalDate dateParseCustom(String dateStr) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.S");
		final LocalDate date = LocalDate.parse(dateStr, formatter);

		return date;
	}
	
	public static LocalDateTime dateTimeParseCustom(String dateStr) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.S");
		final LocalDateTime date = LocalDateTime.parse(dateStr, formatter);

		return date;
	}
	
	public static LocalDate educationDateParse(String dateStr) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		final LocalDate date = LocalDate.parse(dateStr, formatter);
		
		return date;
	}

	public static String yearFormat(LocalDateTime date) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

		return date.format(formatter);
	}

}
