package APIUtils.ResponsesClasses.Like;

import lombok.Data;

@Data
public class LikeRequest {
    LikeResponse response;

    public LikeRequest(LikeResponse response){
        this.response = response;
    }

}
