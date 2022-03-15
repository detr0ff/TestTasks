package DB.Tables;

import DB.Models.Session;
import DB.SQL;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;

import static DB.DataBaseUtils.connection;

public class SessionTable {

    public static long addSession(Session session){
        try {
            Connection conn = connection();
            QueryRunner run = new QueryRunner();
            ScalarHandler<BigInteger> scalarHandler = new ScalarHandler<>();
            return run.insert(conn, SQL.addSession, scalarHandler, session.getSession_key(), session.getCreated_time(), session.getBuild_number()).longValue();
        } catch (SQLException throwables) {
            throw new RuntimeException("SQL error");
        }
    }
}
