package APIUtils.ResponsesClasses.Photo;

import lombok.Data;

@Data
public class PhotoResponse {

    String server;
    String photo;
    String hash;

    public PhotoResponse(String server, String photo, String hash){
        this.server = server;
        this.photo = photo;
        this.hash = hash;
    }
}
