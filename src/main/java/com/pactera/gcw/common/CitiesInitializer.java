package com.pactera.gcw.common;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CitiesInitializer {
    private static final Logger logger = LoggerFactory.getLogger(CitiesInitializer.class);

    public boolean init(Connection conn) throws SQLException {
        try {
            if (validateTableExist(conn, "GCW_CITY")) {
                logger.info("Table GCW_CITY is existing. Need to drop it.");
                //                Statement statement = conn.createStatement();
                //                statement.executeUpdate("Drop table GCW_CITY");
                //                statement.close();
                return true;
            }
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "create table GCW_CITY (code varchar(30) primary key not null, name varchar(50))");
            statement.close();
            logger.info("Table GCW_CITY is created successfully.");
            Statement ins = conn.createStatement();
            ins.executeUpdate("insert into GCW_CITY values ('Sydney', 'Sydney')");
            ins.executeUpdate("insert into GCW_CITY values ('Beijing', 'Beijing')");
            ins.executeUpdate("insert into GCW_CITY values ('Melbourne', 'Melbourne')");
            ins.executeUpdate("insert into GCW_CITY values ('London', 'London')");
            ins.executeUpdate("insert into GCW_CITY values ('Tokyo', 'Tokyo')");
            ins.close();
            logger.info("Five records for GCW_CITY are created.");
        } catch (SQLException e) {
            logger.error("Failed to verify Table GCW_CITY", e);
        } finally {
            conn.close();
        }

        return false;
    }

    public boolean validateTableExist(Connection conn, String tableName) throws SQLException {
        boolean flag = false;
        ResultSet rs = null;
        try {
            DatabaseMetaData meta = conn.getMetaData();
            String type[] = { "TABLE" };
            rs = meta.getTables(null, null, tableName, type);
            flag = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return flag;
    }
}
