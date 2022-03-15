package DB.Tables;

import DB.Models.TestModel;
import DB.SQL;
import aquality.selenium.browser.AqualityServices;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static DB.DataBaseUtils.connection;

public class TestTable {

    public static List<TestModel> getAllTests() {
        try {
            Connection conn = connection();
            QueryRunner run = new QueryRunner();
            BeanListHandler<TestModel> listHandler = new BeanListHandler<>(TestModel.class);
            return run.query(conn, SQL.getTests, listHandler);
        } catch (SQLException throwables) {
            throw new RuntimeException("SQL error");
        }
    }

    public static TestModel getTestByID(long id) {
        Connection conn = connection();
        QueryRunner run = new QueryRunner();
        ResultSetHandler<TestModel> listHandler = new BeanHandler<>(TestModel.class);
        try {
            TestModel testModel = run.query(conn, SQL.getTestsByID, listHandler, id);
            return testModel;
        } catch (SQLException throwables) {
            throw new RuntimeException("SQL error");
        }
    }

    public static long addTest(TestModel test) {
        try {
            Connection conn = connection();
            QueryRunner run = new QueryRunner();
            ScalarHandler<BigInteger> scalarHandler = new ScalarHandler<>();
            long id = run.insert(conn, SQL.addTest, scalarHandler,
                    test.getName(),
                    test.getStatus_id(),
                    test.getMethod_name(),
                    test.getProject_id(),
                    test.getSession_id(),
                    test.getStart_time(),
                    test.getEnd_time(),
                    test.getEnv(),
                    test.getBrowser(),
                    test.getAuthor_id()).longValue();
            AqualityServices.getLogger().info("Create test in DB. ID="+id);
            conn.close();
            return id;
        } catch (SQLException throwables) {
            throw new RuntimeException("SQL create error");
        }
    }

    public static long updateTest(TestModel test) {
        try {
            Connection conn = connection();
            QueryRunner run = new QueryRunner();
            long id = run.update(conn, SQL.updateTest,
                    test.getStatus_id(),
                    test.getSession_id(),
                    test.getStart_time(),
                    test.getEnd_time(),
                    test.getEnv(),
                    test.getBrowser(),
                    test.getName(),
                    test.getMethod_name(),
                    test.getProject_id(),
                    test.getAuthor_id());
            return id;
        } catch (SQLException throwables) {
            AqualityServices.getLogger().info("Create new test on DB");
            return addTest(test);
        }
    }

    public static long deleteTestByID(long id) {
        Connection conn = connection();
        QueryRunner run = new QueryRunner();
        try {
            return run.update(conn, SQL.deleteTestById, id);
        } catch (SQLException throwables) {
            throw new RuntimeException("SQL delete error");
        }
    }
}
