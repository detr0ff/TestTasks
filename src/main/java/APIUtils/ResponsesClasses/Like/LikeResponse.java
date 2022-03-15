package APIUtils.ResponsesClasses.Like;

import lombok.Data;

@Data
public class LikeResponse {

    int liked;
    int copied;

    public LikeResponse(int liked, int copied){
        this.liked = liked;
        this.copied = copied;
    }
}
