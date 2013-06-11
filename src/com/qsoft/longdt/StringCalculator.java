package com.qsoft.longdt;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public static int add(String input) {
		if (input.isEmpty()) {
			return 0;
		} else if (input.length() == 1) {
			return toInt(input);
		} else {
			int sum = 0;
			ArrayList<Integer> listInt = convertString2NumberArray(input);
			for (Integer integer : listInt) {
				sum += integer;
			}
			return sum;
		}
	}

	private static int toInt(String input) {
		return Integer.parseInt(input);
	}

	private static ArrayList<Integer> convertString2NumberArray(String input) {
		String strTmp = input;
		ArrayList<Integer> listInt = new ArrayList<Integer>();
		if (input.contains("//")) {
			strTmp = matchDelimFirstType(input);
		}
		if (strTmp.contains("[") && strTmp.contains("]")) {
			strTmp = matchDelimSecondType(strTmp);
		}

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(strTmp);
		while (m.find()) {
			int number = toInt(m.group());
			if (number < 0) {
				throw new NumberFormatException("negatives not allowed");
			}
			if (number <= 1000)
				listInt.add(number);
		}

		return listInt;
	}

	private static String matchDelimFirstType(String input) {
		Pattern replaceDelimiter = Pattern.compile("//\\[.+?\\]+");
		Matcher m1 = replaceDelimiter.matcher(input);
		while (m1.find()) {
			input = input.replace(m1.group(), ",");
		}
		return input;
	}

	private static String matchDelimSecondType(String input) {
		Pattern replaceDelimiter = Pattern.compile("\\[.+?\\]+");
		Matcher m1 = replaceDelimiter.matcher(input);
		while (m1.find()) {
			input = input.replace(m1.group(), ",");
		}
		return input;
	}
}
