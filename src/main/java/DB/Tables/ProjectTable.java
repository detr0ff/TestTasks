package DB.Tables;

import DB.Models.Project;
import DB.SQL;
import aquality.selenium.browser.AqualityServices;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;

import static DB.DataBaseUtils.connection;

public class ProjectTable {

    public static long addProject(Project project) {
        Connection conn = connection();
        QueryRunner run = new QueryRunner();
        try {
            ScalarHandler<BigInteger> scalarHandler = new ScalarHandler<>();
            long id = run.insert(conn, SQL.addProject, scalarHandler, project.getName()).longValue();
            conn.close();
            return id;
        } catch (SQLException throwables) {
            ResultSetHandler<DB.Models.Project> listHandler = new BeanHandler<>(DB.Models.Project.class);
            try {
                AqualityServices.getLogger().info("This project is already created. Return ID this project");
                DB.Models.Project prj = run.query(conn, SQL.getProject, listHandler, project.getName());
                conn.close();
                return prj.getId();
            } catch (SQLException e) {
                throw new RuntimeException("SQL error");
            }
        }
    }
}
