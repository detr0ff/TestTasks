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

    public TimerForm timerForm = new TimerForm();
    public HelpForm helpForm = new HelpForm();


}
