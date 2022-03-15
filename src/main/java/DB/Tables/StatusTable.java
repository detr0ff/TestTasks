package DB.Tables;

import DB.Models.Status;
import DB.SQL;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

import static DB.DataBaseUtils.connection;

public class StatusTable {

    public static long getStatusIDfromStatus(String status){
        try {
            Connection conn = connection();
            QueryRunner run = new QueryRunner();
            ResultSetHandler<Status> listHandler = new BeanHandler<>(Status.class);
            Status st = run.query(conn, SQL.getStatusIDfromStatus, listHandler, status);
            return st.getId();
        } catch (SQLException throwables) {
            throw new RuntimeException("SQL error");
        }
    }
}
