package Forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {
    protected HelpForm() {
        super(By.xpath("//h2[@class = 'help-form__title']"), "HelpForm");
    }

    protected IButton SendBottomButton = getElementFactory().getButton(By.xpath("//div[@class = 'help-form']//button[contains(span, 'Send')]"),
            "SendBottom");

    protected void HideHelpForm(){
        SendBottomButton.click();
        state().waitForNotDisplayed();
    }
}
