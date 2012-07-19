package com.s84.smile.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static DateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat timeFormat = new  SimpleDateFormat("HH:mm");

	public static DateFormat getDateFormat() {
		return dateFormat;
	}

	public static DateFormat getTimeFormat() {
		return timeFormat;
	}

	public static Date getDay(int diffMinute) {
		Calendar calendar = Calendar.getInstance();
		//グリニッジ標準時から日本標準時に補正
		calendar.add(Calendar.HOUR, 9);
		//引数に指定した時間分を加算
		calendar.add(Calendar.MINUTE, diffMinute);
		return calendar.getTime();
	}

	public static Date getTime(int diffMinute) {
		Calendar calendar = Calendar.getInstance();
		//グリニッジ標準時から日本標準時に補正
		calendar.add(Calendar.HOUR, 9);
		//引数に指定した時間分を加算
		calendar.add(Calendar.MINUTE, diffMinute);
	
		//5分単位に切り上げ
		calendar.set(Calendar.MINUTE, ceilByFive(calendar.get(Calendar.MINUTE)));

		return calendar.getTime();
	}

	public static Date getTime(Date date, int diffMinute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//引数に指定した時間分を加算
		calendar.add(Calendar.MINUTE, diffMinute);

		return calendar.getTime();
	}

	private static int ceilByFive(int source) {
		//5分単位に切り上げ
		return source + 5 - (source % 5);
	}
}
