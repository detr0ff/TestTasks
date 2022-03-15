package Forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class BaseForm extends Form {
    protected BaseForm(By locator, String name) {
        super(locator, name);
    }

    public BaseForm() {
        super(By.className("logo__icon"), "Logo");
    }

    protected TimerForm timerForm = new TimerForm();
    protected HelpForm helpForm = new HelpForm();


}
