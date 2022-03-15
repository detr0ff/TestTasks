package APIUtils;

import APIUtils.ResponsesClasses.CommentPost.CommentRequest;
import APIUtils.ResponsesClasses.Like.LikeRequest;
import APIUtils.ResponsesClasses.Photo.*;
import APIUtils.ResponsesClasses.WallPost.PostRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

public class VkApiUtils {

    public static String apiUrl = "https://api.vk.com/method/";
    public static String userToken = "0bc51d83e47717949a9f1ed33c87e6ca9e39978efd78addf732d86e2086a77606f83e220c8dc9bd9f5606";
    public static String version = "5.131";

    public static int postInWall(String message){
        StringBuilder url = new StringBuilder(apiUrl);
        url.append("wall.post?message=").append(message).append("&access_token=")
                .append(userToken).append("&v=").append(version);
        String data = RequestUtils.getRequest(url.toString());
        System.out.println(url);
        Gson gson = new Gson();
        PostRequest request = gson.fromJson(data, PostRequest.class);
        return request.getResponse().getPost_id();
    }

    public static int createComment(int postId, String comment){
        StringBuilder url = new StringBuilder(apiUrl);
        url.append("wall.createComment?post_id=").append(postId).append("&message=")
                .append(comment).append("&access_token=").append(userToken).append("&v=").append(version);
        String data = RequestUtils.getRequest(url.toString());
        Gson gson = new Gson();
        CommentRequest request = gson.fromJson(data, CommentRequest.class);
        return request.getResponse().getComment_id();
    }

    public static PhotoUploadServerResponse getWallUploadServer(){
        StringBuilder url = new StringBuilder(apiUrl);
        url.append("photos.getWallUploadServer?").append("&access_token=").append(userToken).append("&v=").append(version);
        String data = RequestUtils.getRequest(url.toString());
        Gson gson = new Gson();
        PhotoUploadServerRequest request = gson.fromJson(data, PhotoUploadServerRequest.class);
        return request.getResponse();
    }

    public static SavePhotoResponse loadPhoto(String path) {
        PhotoUploadServerResponse uploadServer = getWallUploadServer();
        Gson gson = new Gson();
        PhotoResponse request = gson.fromJson(RequestUtils.postRequest(uploadServer.getUpload_url(), path), PhotoResponse.class);
        StringBuilder saveURL = null;
        try {
            saveURL = new StringBuilder(apiUrl);
            saveURL.append("photos.saveWallPhoto?user_id=").append(uploadServer.getUser_id())
                    .append("&server=").append(request.getServer())
                    .append("&photo=").append(URLEncoder.encode(request.getPhoto(), "UTF-8"))
                    .append("&hash=").append(request.getHash())
                    .append("&access_token=").append(userToken).append("&v=").append(version);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SavePhotoRequest savePhotoRequest = gson.fromJson(RequestUtils.getRequest(saveURL.toString()), SavePhotoRequest.class);
        JsonArray array = savePhotoRequest.getResponse();
        Type listType = new TypeToken<List<SavePhotoResponse>>(){}.getType();
        List<SavePhotoResponse> savePhotoResponses = gson.fromJson(array.toString(), listType);
        return savePhotoResponses.get(0);
    }

    public static void editPost(int postID, String message, String filePath){
        SavePhotoResponse photoResponse = loadPhoto(filePath);
        StringBuilder url = new StringBuilder(apiUrl);
        url.append("wall.edit?post_id=").append(postID)
                .append("&message=").append(message)
                .append("&attachments=photo").append(photoResponse.getOwner_id())
                .append("_").append(photoResponse.getId()).append("&access_token=")
                .append(userToken).append("&v=").append(version);
        RequestUtils.getRequest(url.toString());
    }

    public static void likePost(int postID){
        StringBuilder url = new StringBuilder(apiUrl);
        url.append("likes.add?type=post&item_id=").append(postID)
                .append("&access_token=").append(userToken).append("&v=").append(version);
        RequestUtils.getRequest(url.toString());
    }

    public static void deletePost(int postID){
        StringBuilder url = new StringBuilder(apiUrl);
        url.append("wall.delete?post_id=").append(postID)
                .append("&access_token=").append(userToken).append("&v=").append(version);
        RequestUtils.getRequest(url.toString());
    }

    public static int getLikePostList(int userID, int postID){
        StringBuilder url = new StringBuilder(apiUrl);
        url.append("likes.isLiked?type=post&user_id=").append(userID)
                .append("&item_id=").append(postID)
                .append("&access_token=").append(userToken).append("&v=").append(version);
        String data = RequestUtils.getRequest(url.toString());
        Gson gson = new Gson();
        LikeRequest request = gson.fromJson(data, LikeRequest.class);
        return request.getResponse().getLiked();
    }
}
