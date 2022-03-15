package APIUtils.ResponsesClasses.Photo;

import lombok.Data;

@Data
public class SavePhotoResponse {
    int owner_id;
    int id;

    public SavePhotoResponse(int owner_id, int id){
        this.owner_id = owner_id;
        this.id = id;
    }
}
