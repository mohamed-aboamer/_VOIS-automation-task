package utilities.dataHelpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.grid.config.ConfigException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop = new Properties();
	private static String FILE_PATH = "./src/main/resources/config.properties";
	private static Logger log=LogManager.getLogger();
	private ConfigReader(){}

	public static void main(String[] args) {
		// SOME TEST CASES
		System.out.println(getStringProperty("RunMode"));
		System.out.println(getBooleanProperty("headless"));
		System.out.println(getIntegerProperty("pageLoadTimeOut"));
		System.out.println(getIntegerProperty("retry"));


	}

	/**
	 *
	 * @param key
	 * @return
	 */

	public static Boolean getBooleanProperty(String key) {
		try {
			InputStream input = new FileInputStream(FILE_PATH);
			prop.load(input);
			Boolean value = Boolean.parseBoolean(prop.getProperty(key));
			return value;

		} catch (Exception exp) {
			log.error(exp.getMessage());
			exp.printStackTrace();
		}
		throw new ConfigException("failed to read boolean property from properties file");
	}

	/**
	 *
	 * @param key
	 * @return
	 */

	public static String getStringProperty(String key) {
		try {
			InputStream input = new FileInputStream(FILE_PATH);
			prop.load(input);
			String value = prop.getProperty(key);
			return value;

		} catch (Exception exp) {
			log.error("failed to read the value from the configuration file");
			exp.printStackTrace();
		}
		throw new ConfigException("failed to read string property from properties file");
	}

	/**
	 *
	 * @param key
	 * @return
	 */

	public static int getIntegerProperty(String key) {

		try {
			InputStream input = new FileInputStream(FILE_PATH);
			prop.load(input);
			int value = Integer.parseInt(prop.getProperty(key));
			return value;
		} catch (Exception exp) {
			log.error(exp.getCause());
			exp.printStackTrace();
		}
		throw new ConfigException("failed to read integer property from properties file");
	}

	/**
	 *
	 * @param property
	 * @param value
	 */

	public static void setProperty(String property, String value,String comment) {
		try {
			OutputStream output = new FileOutputStream(FILE_PATH);
			prop.setProperty(property, value);
			prop.store(output,comment);
		} catch (Exception exp) {
			log.error("failed to set value of the given property");
			exp.printStackTrace();
		}
	}

}