package DB.Models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TestModel extends BaseModel{
    private String name;
    private long status_id;
    private String method_name;
    private long project_id;
    private long session_id;
    private Timestamp start_time;
    private Timestamp end_time;
    private String env;
    private String browser;
    private long author_id;

    public TestModel(long id, String name, long status_id, String method_name, long project_id, long session_id, Timestamp start_time, Timestamp end_time, String env, String browser, long author_id) {
        super(id);
        this.name = name;
        this.status_id = status_id;
        this.method_name = method_name;
        this.project_id = project_id;
        this.session_id = session_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.env = env;
        this.browser = browser;
        this.author_id = author_id;
    }

    public TestModel(String name, long status_id, String method_name, long project_id, long session_id, Timestamp start_time, Timestamp end_time, String env, String browser, long author_id) {
        this.name = name;
        this.status_id = status_id;
        this.method_name = method_name;
        this.project_id = project_id;
        this.session_id = session_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.env = env;
        this.browser = browser;
        this.author_id = author_id;
    }

    public TestModel(){}
}
