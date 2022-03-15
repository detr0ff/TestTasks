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

    protected LoginForm() {
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

    protected final CookieForm cookieForm = new CookieForm();

    protected void EnterEmail(String email) {
        this.email.clearAndType(email);
    }

    protected void EnterPassword(String passw) {
        password.clearAndType(passw);
    }

    protected void EnterDomain(String word) {
        domain.clearAndType(word);
    }

    protected void CheckBoxClick() {
        LabelCheckBox.click();
    }

    protected void OpenDropdownList() {
        dropdownButton.click();
    }

    protected void ChosseRandomDomain() {
        OpenDropdownList();
        List<ILabel> dropdownList = getElementFactory().findElements(dropdownListLocator, ElementType.LABEL);
        int index = RandomUtils.rndInt(0, dropdownList.size());
        dropdownList.get(index).click();
    }

    protected void NextClick() {
        NextButton.clickAndWait();
    }

}









