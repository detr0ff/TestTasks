package APIUtils.ResponsesClasses.CommentPost;

import lombok.Data;

@Data
public class CommentResponse {
    int comment_id;

    public CommentResponse(int comment_id){
        this.comment_id = comment_id;
    }
}
