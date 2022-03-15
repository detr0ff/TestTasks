package Forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UserInyerfaceForm extends Form {

    public UserInyerfaceForm() {
        super(new By.ByClassName("start__link"), "link");
    }

    private final ILink link = getElementFactory().getLink(getLocator(), "link", ElementState.DISPLAYED);

    public void clickLink() {
        link.clickAndWait();
    }
}
