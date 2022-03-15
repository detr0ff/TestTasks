package DB.Models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Session extends BaseModel{
    private String session_key;
    private Timestamp created_time;
    private int build_number;

    public Session(long id, String session_key, Timestamp created_time, int build_number) {
        super(id);
        this.session_key = session_key;
        this.created_time = created_time;
        this.build_number = build_number;
    }

    public Session(String session_key, Timestamp created_time, int build_number) {
        this.session_key = session_key;
        this.created_time = created_time;
        this.build_number = build_number;
    }

    public Session(){}
}
