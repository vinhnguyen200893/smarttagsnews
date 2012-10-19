package com.vcc.smarttags.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	public static void loadConfig() throws IOException
	{
	Properties pro=new Properties();
	File f=new File("conf.properties");
	FileInputStream in=new FileInputStream(f);
	pro.load(in);
	SystemInfo.File_StopWords=pro.getProperty("File_StopWords");
	SystemInfo.NEWS_MSSQLSERVER_DB=pro.getProperty("NEWS_MSSQLSERVER_DB");
	SystemInfo.NEWS_MSSQLSERVER_IP=pro.getProperty("NEWS_MSSQLSERVER_IP");
	SystemInfo.NEWS_MSSQLSERVER_PORT=pro.getProperty("NEWS_MSSQLSERVER_PORT");
	SystemInfo.NEWS_MSSQLSERVER_PW=pro.getProperty("NEWS_MSSQLSERVER_PW");
	SystemInfo.NEWS_MSSQLSERVER_UID=pro.getProperty("NEWS_MSSQLSERVER_UID");
	SystemInfo.MYSQL_DB_CONNECTION_URL=pro.getProperty("MYSQL_DB_CONNECTION_URL");
	SystemInfo.MYSQL_DB_PASS=pro.getProperty("MYSQL_DB_PASS");
	SystemInfo.MYSQL_DB_UID=pro.getProperty("MYSQL_DB_UID");
	SystemInfo.NO_OF_DAY_TO_GET_CONTENT=Integer.valueOf(pro.getProperty("NO_OF_DAY_TO_GET_CONTENT"));
	SystemInfo.TIME_SLEEP_WHEN_RERUN_JOB_IN_SECS=Integer.valueOf(pro.getProperty("TIME_SLEEP_WHEN_RERUN_JOB_IN_SECS"));
	}

}
