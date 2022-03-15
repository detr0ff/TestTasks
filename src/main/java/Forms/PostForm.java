package Forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class PostForm extends Form {

    protected PostForm(By locator, String name) {
        super(locator, name);
    }

    private final ILabel postLabel = getElementFactory().getLabel(getLocator(), "PostLabel");
    private final By postTextLocator = By.className("wall_post_text");
    private final By imageInPostLocator = By.className("post_info");
    private final By imageSrcLocator = By.xpath("//div[@id='pv_photo']/img");
    private final By imageCloseButton = By.className("pv_close_btn");
    private final By showCommentButton = By.xpath("//div[@class='replies']//a");

    public String getPostText(){
        ITextBox textPost = getElementFactory().findChildElement(postLabel, postTextLocator, ElementType.TEXTBOX);
        return textPost.getText();
    }

    public String getSrcImagePost(){
        ILabel imageInPost = getElementFactory().findChildElement(postLabel, imageInPostLocator, ElementType.LABEL);
        imageInPost.clickAndWait();
        ILabel image = getElementFactory().getLabel(imageSrcLocator, "Image");
        IButton closeButton = getElementFactory().getButton(imageCloseButton, "CloseButton");
        String src = image.getAttribute("src");
        closeButton.clickAndWait();
        return src;
    }

    public CommentForm getComment(int userID, int commentID){
        IButton showComment = getElementFactory().findChildElement(postLabel, showCommentButton, ElementType.BUTTON, ElementState.EXISTS_IN_ANY_STATE);
        By commentLocator = By.id(new StringBuilder("post").append(userID).append("_").append(commentID).toString());
        if(showComment.state().isDisplayed()){
            showComment.clickAndWait();
        }
        CommentForm commentForm = new CommentForm(commentLocator, "comment");
        commentForm.state().waitForDisplayed();
        return commentForm;
    }
}
