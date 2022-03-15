package APIUtils.ResponsesClasses.CommentPost;

import lombok.Data;

@Data
public class CommentRequest {
    CommentResponse response;

    public CommentRequest(CommentResponse response){
        this.response = response;
    }
}
