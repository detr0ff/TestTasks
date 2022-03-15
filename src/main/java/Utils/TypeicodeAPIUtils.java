package Utils;

import Task5.Classes.Post;
import Task5.Classes.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.util.List;

public class TypeicodeAPIUtils {
    public static String URL = "https://jsonplaceholder.typicode.com/";

    public static List<Post> getPosts(){
        Response response = APIUtils.getRequest(URL+"posts");
        Gson gson = new Gson();
        List<Post> posts = gson.fromJson(response.data, new TypeToken<List<Post>>() {
        }.getType());
        return posts;
    }

    public static List<User> getUsers(){
        Response response = APIUtils.getRequest(URL+"users");
        if (response.code == HttpURLConnection.HTTP_OK){
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            List<User> users = gson.fromJson(response.data, new TypeToken<List<User>>() {
            }.getType());
            return users;
        }
        else {
            throw new RuntimeException("Code error" + response.code);
        }
    }

    public static Post getPost(int id){
        Response response = APIUtils.getRequest(URL+"posts/" + id);
        if (response.code == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Post post = gson.fromJson(response.data, Post.class);
            return post;
        }
        else {
            throw new RuntimeException("Code error" + response.code);
        }
    }

    public static User getUser(int id){
        Response response = APIUtils.getRequest(URL+"users/" + id);
        if (response.code == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            User user = gson.fromJson(response.data, User.class);
            return user;
        }
        else {
            throw new RuntimeException("Code error" + response.code);
        }
    }

    public static Post postPost(Post post){
        Response response = APIUtils.postRequest(URL+"posts/", post);
        if (response.code == HttpURLConnection.HTTP_CREATED) {
            Gson gson = new Gson();
            Post newPost = gson.fromJson(response.data, Post.class);
            return newPost;
        }
        else {
            throw new RuntimeException("Code error" + response.code);
        }
    }

    public static User getUserFromFile(String path){
        Gson gson = new Gson();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(path));
            return gson.fromJson(reader, User.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
