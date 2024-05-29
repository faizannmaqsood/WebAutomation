package PageFactory;

import com.microsoft.playwright.*;

public class UltimateDashboardPage {
    private final Page page;




    public UltimateDashboardPage(Page page)
    {
        this.page = page;
    }

    public void navigateToDashboardPage(String url) {
        page.navigate(url);
    }

    public String getDashboardPageTitle(){
        String pageTitle = page.title();
        System.out.println("Page title: " + pageTitle);
        return pageTitle;
    }



}
