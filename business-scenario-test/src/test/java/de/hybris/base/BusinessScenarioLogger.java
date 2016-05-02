package de.hybris.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

public class BusinessScenarioLogger {

	private static Logger LOG = Logger.getLogger(BusinessScenarioLogger.class);
	private static boolean initializationFlag = false;
	private static String fileName;

	private static void intializeLogger() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();

		RollingFileAppender appender = new RollingFileAppender();
		appender.setName("ConsoleLogger");
		appender.setAppend(true);
		appender.setMaxFileSize("5MB");
		appender.setMaxBackupIndex(1);
		appender.setThreshold(Level.INFO);
		appender.setFile(getFileName() + "_" + dateFormat.format(date) + ".log");
		
		PatternLayout layOut = new PatternLayout();
		layOut.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
		appender.setLayout(layOut);
		appender.activateOptions();
		Logger.getRootLogger().addAppender(appender);
	}

	public static Logger getLogger() {
		if (initializationFlag == false) {
			intializeLogger();
			initializationFlag = true;
			
			return BusinessScenarioLogger.LOG;
		} else {
			return BusinessScenarioLogger.LOG;
		}
	}

	public static void setFileName(String fileName) {
		BusinessScenarioLogger.fileName = fileName;
	}

	public static String getFileName() {
		if (fileName == null || fileName.isEmpty()) {
			fileName = "smartedit";
		}

		return fileName;
	}
}