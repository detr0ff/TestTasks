package APIUtils.ResponsesClasses.WallPost;

import lombok.Data;

@Data
public class PostRequest {
    PostResponse response;

    public PostRequest(PostResponse response){
        this.response = response;
    }
}
