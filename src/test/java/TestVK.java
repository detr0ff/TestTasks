import APIUtils.*;
import Forms.*;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.awt.image.BufferedImage;

public class TestVK {

    public final int randomWordSize = 10;

    @Before
    public void start(){
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo("https://vk.com/");
        browser.waitForPageToLoad();
    }

    @Test
    public void Test1() {
        LoginForm loginForm = new LoginForm();
        loginForm.enterLogin(TestData.login);
        loginForm.enterPass(TestData.password);
        loginForm.login();
        MenuForm menuForm = new MenuForm();
        menuForm.myPageClick();
        PageForm pageForm = new PageForm();
        int userID = pageForm.userAvatarForm.getUserID();
        String message = RandomUtils.giveRandomWord(randomWordSize);
        int postID = VkApiUtils.postInWall(message);
        PostForm post = pageForm.wallForm.getPost(userID, postID);
        Assert.assertTrue("Post did not appear", post.state().isDisplayed());
        Assert.assertEquals("Messages didn't match", post.getPostText(), message);

        String editMessage = RandomUtils.giveRandomWord(randomWordSize);
        VkApiUtils.editPost(postID, editMessage, TestData.imagePath);
        Assert.assertEquals("Message has not changed", post.getPostText(), editMessage);

        BufferedImage imageVK = ImageUtils.downloadImage(post.getSrcImagePost());
        BufferedImage imageOriginal = ImageUtils.fromFile(TestData.imagePath);
        Assert.assertTrue("Images not equals", ImageUtils.imageEquals(imageVK, imageOriginal));

        String commentMessage = RandomUtils.giveRandomWord(randomWordSize);
        int commentID = VkApiUtils.createComment(postID, commentMessage);
        CommentForm comment = post.getComment(userID, commentID);
        Assert.assertTrue("Comment did not appear", comment.state().isDisplayed());
        Assert.assertEquals("Comment messages didn't match", comment.getCommentText(), commentMessage);

        VkApiUtils.likePost(postID);
        int likeCheck = VkApiUtils.getLikePostList(userID, postID);
        Assert.assertEquals("Like did not appear", likeCheck, 1);

        VkApiUtils.deletePost(postID);
        Assert.assertFalse("Post was not deleted", post.state().isDisplayed());
    }

    @After
    public void close() {
        Browser browser = AqualityServices.getBrowser();
        browser.quit();
    }
}
