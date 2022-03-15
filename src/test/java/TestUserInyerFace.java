import DB.Models.Author;
import DB.Models.Project;
import DB.Models.Session;
import DB.Models.TestModel;
import DB.Tables.*;
import Forms.*;
import Utilities.PasswordGenerator;
import Utilities.RandomRusChar;
import Utilities.RandomUtils;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import testData.DBTestData;
import testData.TestStatus;

import java.sql.Timestamp;

public class TestUserInyerFace {
    public final String picturePath = "src\\main\\resources\\avatar.jpg";
    public final int PasswordCount = 10;
    public final int EmailCount = 10;
    public final int DomainCount = 5;
    public final int NumberOfInterests = 3;
    public final String BeginTime = "00:00:00";
    public String testResult;
    public String methodName;
    public Timestamp startTime;
    public static long sessionID;
    public static long authorID;
    public static long projectID;


    @BeforeClass
    public static void startClass(){
        authorID = AuthorTable.addAuthor(new Author(DBTestData.userName, DBTestData.login, DBTestData.email));
        String session_key = String.valueOf(System.currentTimeMillis());
        Timestamp create_time = new Timestamp(System.currentTimeMillis());
        int numberOfBuild = DBTestData.buildNumber;
        sessionID = SessionTable.addSession(new Session(session_key, create_time, numberOfBuild));
        projectID = ProjectTable.addProject(new Project(DBTestData.projectName));
    }

    @Before
    public void start() {
        startTime = new Timestamp(System.currentTimeMillis());
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
    public void testHideHelpForm() {
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
    }

    @Rule
    public TestRule afterWithFailedInformation = RuleChain.outerRule(new ExternalResource() {
        @Override
        protected void after() {
            Browser browser = AqualityServices.getBrowser();
            String browserName = browser.getBrowserName().name();
            browser.quit();
            Timestamp endTime = new Timestamp(System.currentTimeMillis());
            String env = System.getProperty("user.name");
            String testName = DBTestData.testName1;
            long statusID = StatusTable.getStatusIDfromStatus(testResult);
            TestModel test = new TestModel(testName, statusID, methodName, projectID, sessionID, startTime, endTime, env, browserName, authorID);
            TestTable.updateTest(test);
        }
    }).around(new TestWatcher() {

        @Override
        protected void finished(Description description) {
            methodName = description.getMethodName();
        }

        @Override
        protected void succeeded(Description description) {
            testResult = TestStatus.PASSED.toString();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            testResult = TestStatus.FAILED.toString();;
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            testResult = TestStatus.SKIPPED.toString();;
        }
    });
}


