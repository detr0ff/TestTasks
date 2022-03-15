import Task5.Classes.Post;
import Task5.Classes.User;
import TestData.TestData;
import Utils.RandomWord;
import Utils.TypeicodeAPIUtils;
import org.junit.Assert;
import org.junit.Test;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TestRestAPI {

    @Test
    public void Test1() {
        List<Post> posts = TypeicodeAPIUtils.getPosts();
        List<Post> sortedPosts = posts.stream().sorted(Comparator.comparingInt(Post::getId)).collect(Collectors.toList());
        Assert.assertEquals("Id не в порядке возрастания", posts, sortedPosts);
    }

    @Test
    public void Test2(){
        Post post = TypeicodeAPIUtils.getPost(TestData.test2PostID);
        Assert.assertEquals(post.getId(), TestData.test2PostID);

        Assert.assertEquals(post.getUserId(), TestData.test2userID);

        Assert.assertFalse("Post is empty", post.getBody().equals("") && post.getTitle().equals(""));
    }

    @Test
    public void Test3(){
        TypeicodeAPIUtils.getPost(TestData.test3PostID);
    }

    @Test
    public void Test4(){
        int userID = TestData.test4UserID;
        String title = RandomWord.generate(TestData.test4TitleSize);
        String body = RandomWord.generate(TestData.test4BodySize);
        Post newPost = new Post(userID, title ,body);
        Post resultPost = TypeicodeAPIUtils.postPost(newPost);
        System.out.println(resultPost.getBody());
    }

    @Test
    public void Test5() {
        List<User> users = TypeicodeAPIUtils.getUsers();
        int checkUserID = TestData.test5UserID;
        User checkUser = users.stream().filter(user -> user.getId() == checkUserID).findFirst().get();
        User testUser = TypeicodeAPIUtils.getUserFromFile(TestData.user5JsonFilePath);
        Assert.assertEquals("Data does'nt match", checkUser, testUser);
    }

    @Test
    public void Test6(){
        User user = TypeicodeAPIUtils.getUser(TestData.test6UserID);
        User testUser = TypeicodeAPIUtils.getUserFromFile(TestData.user5JsonFilePath);
        Assert.assertEquals("Data does'nt match", user, testUser);
    }
}

