package Forms;

import lombok.Data;

@Data
public class User {
    int ownerId;

    public User(int ownerId){
        this.ownerId = ownerId;
    }
}
