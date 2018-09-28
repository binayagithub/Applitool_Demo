package com.library;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerInfo
{
			public static final Logger getLogger(String className)
			{
			Logger l4jLogger = Logger.getLogger(className);
	        
	        PropertyConfigurator.configure("log4j.properties");
	       
	        return l4jLogger;
			}
}
