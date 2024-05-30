package UltimateQA;
import PageFactory.UltimateDashboardPage;
import com.microsoft.playwright.options.LoadState;
import constants.Strings;
import constants.URLS;
import org.testng.annotations.*;
import org.testng.Assert;
import com.microsoft.playwright.*;
import utils.Listeners.TestListener;
import utils.extentreports.ExtentTestManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


@Listeners({TestListener.class})
public class UltimateQATest {
    private Browser browser;
    private Page page;
    private UltimateDashboardPage DashboardPage;






    @BeforeClass
    public void setUp() throws IOException {



        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.setDefaultTimeout(120000);
        DashboardPage = new UltimateDashboardPage(page);

    }


    @Test (priority = 0, enabled = false)
    public void TestBrokenLinksonPage(){
        // Get all links on the page
        List<ElementHandle> links = page.querySelectorAll("a");

        // Iterate through each link and check if it's broken
        for (ElementHandle link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                int statusCode = getResponseStatus(href);
                if (statusCode != 200) {
                    System.out.println("Broken link found: " + href + " (Status Code: " + statusCode + ")");
                }
            }
        }
    }

    @Test (priority = 1)
    public void TestVerifyingDashboardPage() {

        ExtentTestManager.startTest("testVerifyingTitlePage",
                "Verifying the title of the dashboard page");
        DashboardPage.navigateToDashboardPage(URLS.BASE_URL);
        System.out.println(DashboardPage.getDashboardPageTitle());
       Assert.assertEquals(Strings.DashboardTitleString, DashboardPage.getDashboardPageTitle());


    }

    @Test(priority = 2)
    public void TestServicesLink(){

        ExtentTestManager.startTest("testVerifyingServiceLink",
                "Verifying the click on service link");
        // Check if the element is clickable
        boolean isClickable = page.isEnabled("#menu-item-218392");
        System.out.println("Is element clickable: " + isClickable);

        // If element is clickable, click on it
        if (isClickable) {
            page.click("#menu-item-218392");
            System.out.println("Clicked on the element");
            // Wait for the page to be fully loaded
            page.waitForLoadState(LoadState.LOAD);
            System.out.println("Page is fully loaded");
        } else {
            System.out.println("Element is not clickable");
        }

    }

    @Test(priority = 3)
    public void TestAboutLink(){

        ExtentTestManager.startTest("testVerifyingAboutLink",
                "Verifying the click on About link");
        // Check if the element is clickable
        boolean isClickable = page.isEnabled("#menu-item-217940");
        System.out.println("Is element clickable: " + isClickable);

        // If element is clickable, click on it
        if (isClickable) {
            page.click("#menu-item-217940");
            System.out.println("Clicked on the element");
            // Wait for the page to be fully loaded
            page.waitForLoadState(LoadState.LOAD);
            System.out.println("Page is fully loaded");
        } else {
            System.out.println("Element is not clickable");
        }

    }

    @Test(priority = 4)
    public void TestBlogink(){

        ExtentTestManager.startTest("testVerifyingBlogLink",
                "Verifying the click on Blog link");
        // Check if the element is clickable
        boolean isClickable = page.isEnabled("#menu-item-218226");
        System.out.println("Is element clickable: " + isClickable);

        // If element is clickable, click on it
        if (isClickable) {
            page.click("#menu-item-218226");
            System.out.println("Clicked on the element");
            // Wait for the page to be fully loaded
            page.waitForLoadState(LoadState.LOAD);
            System.out.println("Page is fully loaded");
           // page.click("jjj");
        } else {
            System.out.println("Element is not clickable");
        }

    }
    @Test(priority = 5)
    public void TestEducationLink(){

        ExtentTestManager.startTest("testVerifyingEducationLink",
                "Verifying the click on education link");
        // Check if the element is clickable
        boolean isClickable = page.isEnabled("#menu-item-218225");
        System.out.println("Is element clickable: " + isClickable);

        // If element is clickable, click on it
        if (isClickable) {
            page.click("#menu-item-218225");
            System.out.println("Clicked on the element");
        } else {
            System.out.println("Element is not clickable");
        }

    }

    // Method to get HTTP response status code
    private static int getResponseStatus(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            return connection.getResponseCode();
        } catch (Exception e) {
            return -1; // Error occurred while checking status
        }
    }


  //  @AfterClass
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }

    }
}





