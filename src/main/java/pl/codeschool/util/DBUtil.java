package pl.codeschool.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtil {
    private static DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil.class);

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

    private static DataSource getInstance() {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/heroku_766108dfa8cc9a3");
            } catch (NamingException e) {
                LOGGER.info(e.getMessage());
            }
        }
        return dataSource;
    }

}