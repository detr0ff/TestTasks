package APIUtils.ResponsesClasses.Photo;

import com.google.gson.JsonArray;
import lombok.Data;

import java.lang.reflect.Array;

@Data
public class SavePhotoRequest {
    JsonArray response;

    public SavePhotoRequest(JsonArray response){
        this.response = response;
    }
}
