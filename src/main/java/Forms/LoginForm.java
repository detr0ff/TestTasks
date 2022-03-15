package Forms;

import Utilities.RandomUtils;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

import java.util.List;

public class LoginForm extends BaseForm {

    public LoginForm() {
        super(By.xpath("//input[@placeholder = 'Choose Password']"),
                "LoginForm");
    }

    private final ITextBox password = getElementFactory().getTextBox(getLocator(), "InputPassw");

    private final ITextBox email = getElementFactory().getTextBox(By.xpath("//input[@placeholder = 'Your email']"),
            "InputEmail");

    private final ITextBox domain = getElementFactory().getTextBox(By.xpath("//input[@placeholder = 'Domain']"),
            "InputDomain");

    private final ILabel LabelCheckBox = getElementFactory().getLabel(By.xpath("//span[@class = 'checkbox__box']"),
            "Checkbox");

    private final IButton dropdownButton = getElementFactory().getButton(By.className("dropdown__header"), "dropdownLabel");

    private final ILink NextButton = getElementFactory().getLink(By.className("button--secondary"), "NextButton");

    private final By dropdownListLocator = By.className("dropdown__list-item");

    public final CookieForm cookieForm = new CookieForm();

    public void EnterEmail(String email) {
        this.email.clearAndType(email);
    }

    public void EnterPassword(String passw) {
        password.clearAndType(passw);
    }

    public void EnterDomain(String word) {
        domain.clearAndType(word);
    }

    public void CheckBoxClick() {
        LabelCheckBox.click();
    }

    protected void OpenDropdownList() {
        dropdownButton.click();
    }

    public void ChosseRandomDomain() {
        OpenDropdownList();
        List<ILabel> dropdownList = getElementFactory().findElements(dropdownListLocator, ElementType.LABEL);
        int index = RandomUtils.rndInt(0, dropdownList.size());
        dropdownList.get(index).click();
    }

    public void NextClick() {
        NextButton.clickAndWait();
    }

}









