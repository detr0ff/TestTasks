package DB.Models;

import lombok.Data;

@Data
public class Project extends BaseModel {
    String name;

    public Project(long id, String name){
        super(id);
        this.name = name;
    }

    public Project(String name){
        this.name = name;
    }

    public Project(){
    }
}
