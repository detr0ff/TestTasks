package Forms;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MenuForm extends Form {

    public MenuForm() {
        super(By.id("l_pr"), "Menu Form");
    }

    private final ILink myPageLink = getElementFactory().getLink(By.xpath("//li[@id='l_pr']/a"), "My Page link");

    public void myPageClick(){
        myPageLink.click();
    }
}
