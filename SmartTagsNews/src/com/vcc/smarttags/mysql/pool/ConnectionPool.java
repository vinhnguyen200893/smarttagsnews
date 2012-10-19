package com.vcc.smarttags.mysql.pool;
import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.apache.log4j.Logger;

import com.vcc.smarttags.config.ConfigUtil;
import com.vcc.smarttags.config.SystemInfo;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Sergii.Zagriichuk
 */
public class ConnectionPool {

    private static DataSource ds;
    static Logger logger = Logger.getLogger("Batch");

    static {
        try {
            ConfigUtil.loadConfig();
        } catch (IOException e) {
            logger.fatal("fatal error when read config:", e);
        }
        DriverAdapterCPDS cpds = new DriverAdapterCPDS();
        try {
            cpds.setDriver("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cpds.setUrl(SystemInfo.MYSQL_DB_CONNECTION_URL);
        cpds.setUser(SystemInfo.MYSQL_DB_UID);
        cpds.setPassword(SystemInfo.MYSQL_DB_PASS);

        SharedPoolDataSource tds = new SharedPoolDataSource();
        tds.setConnectionPoolDataSource(cpds);
        tds.setMaxActive(10);
        tds.setMaxWait(50);

        ds = tds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}