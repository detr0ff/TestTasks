package DB;

public class SQL {
    public static String addAuthor = "INSERT INTO author (name, login, email) values (?, ?, ?)";
    public static String getAuthor = "SELECT * FROM author WHERE login =?";
    public static String addSession = "INSERT INTO session (session_key, created_time, build_number) values (?, ?, ?)";
    public static String addProject = "INSERT INTO project (name) values (?)";
    public static String getProject = "SELECT * FROM project WHERE name =?";
    public static String getTests = "SELECT * FROM test";
    public static String getTestsByID = "SELECT * FROM test WHERE id = ?";
    public static String addTest = "INSERT INTO test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static String updateTest = "UPDATE test SET status_id = ?, session_id = ?, start_time = ?, end_time = ?, env = ?, browser = ? WHERE name = ? AND method_name = ? AND project_id = ? AND author_id = ?";
    public static String deleteTestById = "DELETE FROM test WHERE id = ?";
    public static String getStatusIDfromStatus = "SELECT * FROM status WHERE name=?";
}
