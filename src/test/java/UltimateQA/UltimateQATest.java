package UltimateQA;
import PageFactory.UltimateDashboardPage;
import constants.Strings;
import constants.URLS;
import org.testng.annotations.*;
import org.testng.Assert;
import com.microsoft.playwright.*;
import utils.Listeners.TestListener;
import utils.extentreports.ExtentTestManager;

import java.io.IOException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


@Listeners({TestListener.class})
public class UltimateQATest {
    private Browser browser;
    private Page page;
    private UltimateDashboardPage DashboardPage;






    @BeforeClass
    public void setUp() throws IOException {



        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
        page.setDefaultTimeout(60000);
        DashboardPage = new UltimateDashboardPage(page);

    }


    @Test
    public void TestVerifyingDashboardPage() {

        ExtentTestManager.startTest("testVerifyingLoginPage",
                "Verifying the title of the login page");
        DashboardPage.navigateToDashboardPage(URLS.BASE_URL);
        System.out.println(DashboardPage.getDashboardPageTitle());
       Assert.assertEquals(Strings.DashboardTitleString, DashboardPage.getDashboardPageTitle());
    }



    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }

    }
}





