package Forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CommentForm extends Form {

    protected CommentForm(By locator, String name) {
        super(locator, name);
    }

    private final ILabel postLabel = getElementFactory().getLabel(getLocator(), "CommentLabel");
    private final By commentTextLocator = By.className("wall_reply_text");

    public String getCommentText(){
        ITextBox textPost = getElementFactory().findChildElement(postLabel, commentTextLocator, ElementType.TEXTBOX);
        return textPost.getText();
    }

}
