package Forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import com.google.gson.Gson;
import org.openqa.selenium.By;

public class UserAvatarForm extends Form {
    protected    UserAvatarForm() {
        super(By.xpath("//div[@id='page_avatar']/a"), "User Avatar Form");
    }

    private final ILabel avatarLabel = getElementFactory().getLabel(getLocator(), "Avatar Label");

    public int getUserID(){
        String data = avatarLabel.getAttribute("data-options");
        Gson gson = new Gson();
        User user = gson.fromJson(data, User.class);
        return user.getOwnerId();
    }
}
