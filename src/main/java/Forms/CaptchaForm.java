package Forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CaptchaForm extends Form {
    protected CaptchaForm() {
        super(By.className("captcha"), "Captcha");
    }
}
