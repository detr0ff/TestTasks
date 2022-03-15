package Forms;

import Utilities.PasswordGenerator;
import Utilities.RandomRusChar;
import Utilities.RandomUtils;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUserInyerFace {
    public final String picturePath = "src\\main\\resources\\avatar.jpg";
    public final int PasswordCount = 10;
    public final int EmailCount = 10;
    public final int DomainCount = 5;
    public final int NumberOfInterests = 3;
    public final String BeginTime = "00:00:00";

    @Before
    public void start(){
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(testData.TestUserinyerFaceData.url);
        browser.waitForPageToLoad();
    }

    @Test
    public void testGame() {
        UserInyerfaceForm UserInyerface = new UserInyerfaceForm();
        UserInyerface.clickLink();
        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().waitForDisplayed());

        String email = RandomUtils.giveRandomWord(EmailCount);
        String password = PasswordGenerator.generate(PasswordCount);
        password = password + RandomUtils.giveRandomChar(email) + RandomRusChar.getRandomRusChar();
        String domain = RandomUtils.giveRandomWord(DomainCount);
        loginForm.EnterEmail(email);
        loginForm.EnterPassword(password);
        loginForm.EnterDomain(domain);
        loginForm.ChosseRandomDomain();
        loginForm.CheckBoxClick();
        loginForm.NextClick();
        AvatarAndInterestForm AvInterestForm = new AvatarAndInterestForm();
        Assert.assertTrue("Форма с интересами не открылась", AvInterestForm.state().waitForDisplayed());

        AvInterestForm.unselectAll();
        AvInterestForm.SelectRandomInterested(NumberOfInterests);
        AvInterestForm.loadPicture(picturePath);
        AvInterestForm.NextButtonClick();
        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        Assert.assertTrue("Персональная форма не открылась", personalDetailsForm.state().waitForDisplayed());
    }

    @Test
    public void testHideHelpForm() throws InterruptedException {
        UserInyerfaceForm UserInyerface = new UserInyerfaceForm();
        UserInyerface.clickLink();
        BaseForm baseForm = new BaseForm();
        baseForm.helpForm.HideHelpForm();
        Assert.assertFalse("Форма помощи не исчезла", baseForm.helpForm.state().waitForDisplayed());
    }

    @Test
    public void testAcceptCookies() {
        UserInyerfaceForm UserInyerface = new UserInyerfaceForm();
        UserInyerface.clickLink();
        LoginForm loginForm = new LoginForm();
        loginForm.cookieForm.AcceptCookies();
        Assert.assertFalse("Форма помощи не исчезла", loginForm.cookieForm.state().waitForDisplayed());
    }

    @Test
    public void testTimerForm() {
        UserInyerfaceForm UserInyerface = new UserInyerfaceForm();
        UserInyerface.clickLink();
        BaseForm baseForm = new BaseForm();
        String time = baseForm.timerForm.getTime();
        Assert.assertEquals(time, BeginTime);
    }

    @After
    public void close() {
        Browser browser = AqualityServices.getBrowser();
        browser.quit();
    }
}
