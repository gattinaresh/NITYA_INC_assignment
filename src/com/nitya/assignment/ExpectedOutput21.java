package com.nitya.assignment;

import java.util.HashMap;
import java.util.Map;

public class ExpectedOutput21 {

	public static void main(String[] args) {
		Map<String, Integer> monthFilesCountMap = new HashMap<>();
		monthFilesCountMap.put("Jan", 1);
		monthFilesCountMap.put("Feb", 2);
		monthFilesCountMap.put("Mar", 5);
		monthFilesCountMap.forEach((month, count) -> System.out.println(month + "  " + count));
	}

}
