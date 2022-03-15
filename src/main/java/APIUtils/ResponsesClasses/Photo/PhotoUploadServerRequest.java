package APIUtils.ResponsesClasses.Photo;

import lombok.Data;

@Data
public class PhotoUploadServerRequest {
    PhotoUploadServerResponse response;

    public PhotoUploadServerRequest(PhotoUploadServerResponse response){
        this.response = response;
    }
}
