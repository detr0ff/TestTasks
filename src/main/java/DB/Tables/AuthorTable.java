package DB.Tables;

import DB.Models.Author;
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

public class AuthorTable {

    public static long addAuthor(Author author){
        Connection conn = connection();
        QueryRunner run = new QueryRunner();
        try {
            ScalarHandler<BigInteger> scalarHandler = new ScalarHandler<>();
            long id = run.insert(conn, SQL.addAuthor, scalarHandler, author.getName(),
                    author.getLogin(), author.getEmail()).longValue();
            AqualityServices.getLogger().info("Author is created. Return ID this author");
            return id;
        } catch (SQLException throwables) {
            ResultSetHandler<Author> listHandler = new BeanHandler<>(Author.class);
            try {
                AqualityServices.getLogger().info("This author is already created. Return ID this author");
                Author aut = run.query(conn, SQL.getAuthor, listHandler, author.getLogin());
                return aut.getId();
            } catch (SQLException e) {
                throw new RuntimeException("SQL error");
            }
        }
    }
}
