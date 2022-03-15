package Forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TimerForm extends Form {

    protected TimerForm() {
        super(By.xpath("//div[@class ='timer timer--white timer--center']"), "Timer");
    }
    protected By timerLocator = getLocator();
    protected   ILabel TimerLabel = getElementFactory().getLabel(timerLocator, "TimerLabel");

    public String getTime(){
        return TimerLabel.getText();
    }
}