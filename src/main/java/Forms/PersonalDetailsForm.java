package Forms;

import org.openqa.selenium.By;

public class PersonalDetailsForm extends BaseForm {
    protected PersonalDetailsForm() {
        super(By.xpath("//div/h3"), "PersonalDetails");
    }
}
