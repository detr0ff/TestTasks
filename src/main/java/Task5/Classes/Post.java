package Task5.Classes;

import lombok.Data;

@Data
public class Post {
    int userId;
    int id;
    String title;
    String body;

    public Post(int userId, int id, String title, String body){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

}
