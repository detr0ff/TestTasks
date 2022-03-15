package DB.Models;

import lombok.Data;

@Data
public class BaseModel {

    protected long id;

    public BaseModel() {}

    public BaseModel(long id) {
        this.id = id;
    }

}
