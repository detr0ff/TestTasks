package DB.Models;

import lombok.Data;

@Data
public class Status extends BaseModel {
    private String name;

    public Status(long id, String name) {
        super(id);
        this.name = name;
    }

    public Status(){}
}
