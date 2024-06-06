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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        String ActualValue = page.textContent("#menu-item-218392");
        Assert.assertEquals(Strings.DashboardServiceString, ActualValue);

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

        String ActualValue = page.textContent("#menu-item-217940");
        Assert.assertEquals(Strings.DashboardAboutString, ActualValue);
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

        String ActualValue = page.textContent("#menu-item-218226");
        Assert.assertEquals(Strings.DashboardBlogString, ActualValue);
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

    @Test(priority = 6)
    public void TestDiscoveryLink(){

        ExtentTestManager.startTest("testVerifyingDiscoverSessionLink",
                "Verifying the click on Discovery link");

        String ActualValue = page.textContent("#menu-item-217945");
        Assert.assertEquals(Strings.DashboardDiscoveryString, ActualValue);
        // Check if the element is clickable
        boolean isClickable = page.isEnabled("#menu-item-217945");
        System.out.println("Is element clickable: " + isClickable);

        // If element is clickable, click on it
        if (isClickable) {
            page.click("#menu-item-218225");
            System.out.println("Clicked on the element");
            page.evaluate("window.scrollBy(0, document.body.scrollHeight)");

            try {
                Thread.sleep(3000); // Wait for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Element is not clickable");
        }

    }

    @Test(priority = 7)
    public void TestLogoImage() throws IOException {

        ExtentTestManager.startTest("testVerifyingLogoImage",
                "Verifying the image is available");

        String IMAGE_PATH = "downloaded_image.svg";
        Path imagePath = Paths.get(IMAGE_PATH);
        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
        }
        Locator imgLocator = page.locator("img[data-src='https://ultimateqa.com/wp-content/uploads/2023/11/logo.svg']");



        String imgSrc = imgLocator.getAttribute("src");
        System.out.println("Image src attribute: " + imgSrc);


        // Check if the image is present
        boolean isImagePresent = imgLocator.count() > 0;
        if (isImagePresent) {
            System.out.println("Image is present on the page.");
        } else {
            System.out.println("Image is not present on the page.");
        }

        // Download the image
        downloadImage(imgSrc, "downloaded_image.svg");


    }


    @Test(priority = 8)
    public void TestHeroImage() throws IOException {

        ExtentTestManager.startTest("testVerifyingHeroImage",
                "Verifying the hero image is available");

        page.click("img[data-src='https://ultimateqa.com/wp-content/uploads/2023/11/logo.svg']");
        String IMAGE_PATH = "hero_image.svg";
        Path imagePath = Paths.get(IMAGE_PATH);
        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
        }
        Locator imgLocator = page.locator("img[title='hero-image']");



        String imgSrc = imgLocator.getAttribute("src");
        System.out.println("Image src attribute: " + imgSrc);


        // Check if the image is present
        boolean isImagePresent = imgLocator.count() > 0;
        if (isImagePresent) {
            System.out.println("Image is present on the page.");
        } else {
            System.out.println("Image is not present on the page.");
        }

        // Download the image
        downloadImage(imgSrc, "hero_image.webp");


    }

@Test(priority = 9)
    public void ScrollToMId(){

    ExtentTestManager.startTest("testVerifyScrollToMid",
            "Verifying the browser is");

        scrollToBottom(page);


        page.waitForTimeout(5000);
    }

    @Test(priority = 10)
    public void ScheduleDiscoverySession(){

        ExtentTestManager.startTest("testVerifyScheduleDiscoverySession",
                "Verifying the Schedule of discovery session");

        // Wait for the link to be available in the DOM
        page.waitForSelector(".et_pb_button.et_pb_button_15");

        // Click the link
        page.click(".et_pb_button.et_pb_button_15");

        page.waitForTimeout(20000);
        // Get all open pages (tabs)
        BrowserContext context = browser.contexts().get(0);
        Page secondPage = context.pages().get(1);  // Index 1 for the second tab

        // Switch to the second tab
        browser.contexts().get(0).pages().get(1).bringToFront();
        page.waitForTimeout(5000);

        secondPage.fill("#cu-form-control-0", "Faizan");

        secondPage.fill("#cu-form-control-1", "faizann.maqsood@gmail.com");

        secondPage.fill("#cu-form-control-2", "Automation Engineer");

        secondPage.fill("#cu-form-control-3", "Microfinance bank");

        secondPage.click("[data-test='select__dropdown__toggle']");

        // Wait for the dropdown element to be visible and enabled
        secondPage.waitForSelector(".cu-custom-fields__dropdown-item .cu-custom-fields__dropdown-text");

        // Click on the specific item within the dropdown
        secondPage.click(".cu-custom-fields__dropdown-item .cu-custom-fields__dropdown-text");

        secondPage.fill("#cu-form-control-5", "This is automation testing against ultimate QA website");

        page.waitForTimeout(5000);



      //  Assert.assertEquals(Strings.DashboardServiceString, elementContent);

    }


    public static void scrollToMiddle(Page page) {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight / 2 * 0.9);");
    }

    public static void scrollToBottom(Page page) {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void downloadImage(String urlString, String destinationFile) throws IOException {
        URL url = new URL(urlString);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(destinationFile), StandardCopyOption.REPLACE_EXISTING);
        }
    }


    // Method to get HTTP response status code
    public static int getResponseStatus(String url) {
        final int TIMEOUT_MILLIS = 5000; // 5 seconds
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(TIMEOUT_MILLIS);
            connection.setReadTimeout(TIMEOUT_MILLIS);
            return connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return -1; // Error occurred while checking status
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


  //  @AfterClass
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }

    }
}





