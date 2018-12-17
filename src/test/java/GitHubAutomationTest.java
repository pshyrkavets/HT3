import org.testng.Assert;
import org.testng.annotations.*;
import com.epam.ta.steps.Steps;

public class GitHubAutomationTest {
    private Steps steps;
    private final String USERNAME = "testautomationuser";
    private final String PASSWORD = "Time4Death!";
    private final String WANTED_REPOSITORY = "EpamRepository";
    private final int REPOSITORY_NAME_POSTFIX_LENGTH = 6;

    @BeforeClass(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.openBrowser();
    }

    @Test
    public void oneCanCreateProject() {
        steps.loginGithub(USERNAME, PASSWORD);
        String repositoryName = steps.generateRandomRepositoryNameWithCharLength(REPOSITORY_NAME_POSTFIX_LENGTH);
        steps.createNewRepository(repositoryName, "auto-generated test repo");
        Assert.assertEquals(steps.getCurrentRepositoryName(), repositoryName);
    }

    @Test
    public void oneCanOpenWantedRepository() {
        steps.openRepository(WANTED_REPOSITORY);
        Assert.assertEquals(steps.obtainUrlOfPage(), "https://github.com/testautomationuser/EpamRepository");
    }

    @Test(description = "Login to Github")
    public void oneCanLoginGithub() {
        steps.loginGithub(USERNAME, PASSWORD);
        Assert.assertEquals(USERNAME.toLowerCase(), steps.getLoggedInUserName());
    }

    @Test(description = "Logout from Github")
    public void oneCanLogoutGithub() {
        steps.logoutGithub();
        Assert.assertNotEquals("", steps.getLoggedInUserName());
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeBrowser();
    }
}
