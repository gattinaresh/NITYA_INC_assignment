package com.nitya.assignment;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormatSymbols;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nareshbabu.gatti 
 * This Class is to determine monthly files count in a
 * folder
 */
public class MonthFilesCount {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String folderPath = "F:\\PLACEMENTS\\EXP_2.O\\RnD\\test_folder";
			File folderObj = new File(folderPath);
			File[] files = folderObj.listFiles();
			Map<String, Integer> monthFilesCountMap = new HashMap<>();
			for (File fileObj : files) {
				if (fileObj.isFile()) {
					String monthName = getMonthName(Paths.get(fileObj.getAbsolutePath()));
					int filesCount = getFilesCount(monthFilesCountMap, monthName);
					monthFilesCountMap.put(monthName, filesCount);
				}
			}
			monthFilesCountMap.forEach((monthName, filesCount) -> System.out.println(monthName + "  " + filesCount));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get Files count
	 * 
	 * @param monthFilesCountMap
	 * @param monthName
	 * @return
	 */
	private static int getFilesCount(Map<String, Integer> monthFilesCountMap, String monthName) {
		return monthFilesCountMap.containsKey(monthName) ? monthFilesCountMap.get(monthName) + 1 : 1;
	}

	/**
	 * This Method will return MonthName of File
	 * 
	 * @param pathOfFile
	 * @return
	 */
	private static String getMonthName(Path pathOfFile) {
		String monthName = "";
		try {
			BasicFileAttributes attrs = Files.readAttributes(pathOfFile, BasicFileAttributes.class);
			FileTime fileTime = attrs.creationTime();
			int monthValue = fileTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth().getValue();
			monthName = DateFormatSymbols.getInstance().getShortMonths()[monthValue - 1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monthName;
	}
}
