package fr.natsystem.javatest.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Configurator {

	private final Properties properties;
	
	private static Configurator instance;
	private static boolean isInited = false;


	public Configurator() {
		properties = new Properties();
		try {
			properties.load(new FileReader(new File("conf.properties")));
		} catch (final IOException e) {
			System.out.println("error while loading the configurator" + e.getMessage());
		}
	}
	public String getProperty(String key) {

		return properties.getProperty(key);

	}
	
	public static Configurator getInstance() {
		if (instance == null) {
			try {
				init();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public static void init() throws FileNotFoundException, IOException {
		if (!isInited) {
			instance = new Configurator();
			isInited = true;
		}
		
	}
}
