package Forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WallForm extends Form {

    public WallForm() {
        super(By.id("page_wall_posts"), "WallForm");
    }

    private final ILabel postsLabel = getElementFactory().getLabel(getLocator(), "PostsLabel");

    public PostForm getPost(int userID, int postID){
        By postLocator = By.id("post"+userID+"_"+postID);
        ILabel postLabel = getElementFactory().findChildElement(postsLabel, postLocator, ElementType.LABEL);
        return new PostForm(postLabel.getLocator(), "Post");
    }
}
