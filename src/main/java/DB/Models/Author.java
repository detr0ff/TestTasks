package DB.Models;

import lombok.Data;

@Data
public class Author extends BaseModel {
    String name;
    String login;
    String email;

    public Author(long id, String name, String login, String email) {
        super(id);
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public Author(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public Author(String login){
        this.login = login;
    }

    public Author(){}
}
