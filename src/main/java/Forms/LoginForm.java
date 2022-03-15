package Forms;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginForm extends Form{

    public LoginForm() {
        super(By.id("index_email"), "Login input");
    }

    private final ITextBox loginInput = getElementFactory().getTextBox(getLocator(), "Login input");

    private final ITextBox passwordInput = getElementFactory().getTextBox(By.id("index_pass"), "Password input");

    private final IButton loginButton = getElementFactory().getButton(By.id("index_login_button"), "Login button");

    private final By captchaLocator = By.className("captcha");

    public void enterLogin(String login){
        loginInput.sendKeys(login);
    }



    public void enterPass(String pass){
        passwordInput.sendKeys(pass);
    }

    public void login(){
        loginButton.clickAndWait();
        ILabel captchaLabel = getElementFactory().getLabel(captchaLocator, "Captcha", ElementState.EXISTS_IN_ANY_STATE);
        if (captchaLabel.state().isDisplayed()){
            captchaLabel.state().waitForNotDisplayed();
        }
    }
}


