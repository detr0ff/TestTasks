package APIUtils.ResponsesClasses.WallPost;

import lombok.Data;

@Data
public class PostResponse {
    int post_id;

    public PostResponse(int post_id) {
        this.post_id = post_id;
    }
}
