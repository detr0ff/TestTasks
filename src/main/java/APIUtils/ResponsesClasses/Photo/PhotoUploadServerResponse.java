package APIUtils.ResponsesClasses.Photo;

import lombok.Data;

@Data
public class PhotoUploadServerResponse {

    String upload_url;
    int user_id;

    public PhotoUploadServerResponse(String upload_url, int user_id) {
        this.upload_url = upload_url;
        this.user_id = user_id;
    }

}
