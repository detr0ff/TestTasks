package Forms;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class PageForm extends Form {

    public PageForm() {
        super(By.className("page_name"), "Page");
    }

    public final WallForm wallForm = new WallForm();
    public final UserAvatarForm userAvatarForm = new UserAvatarForm();
}
