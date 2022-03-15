package Forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookieForm extends Form {
    protected CookieForm() {
        super(By.xpath("//p[@class = 'cookies__message']"), "Cookies");
    }

    protected IButton acceptCookiesButton = getElementFactory().getButton(By.xpath("//div[@class = 'cookies']//button[contains(text(), 'Not')]"),
            "acceptCookiesButton");

    protected void AcceptCookies(){
        acceptCookiesButton.click();
    }
}
