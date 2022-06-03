package PasteBin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ICanWinTest {
    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup () {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test (description = "I can win")
    public void createNewPasteICanWin() throws InterruptedException {
        new PastebinHomePagePF(driver)
                .openPage()
                .setpostformText("Hello from WebDriver")
                .setexpirationTime("10 Minutes")
                .setpostformName("helloweb");
        PastebinHomePagePF.clicksubmitButton();
    }

    @AfterMethod (alwaysRun = true)
    public void browserQuit(){
        driver.quit();
        driver = null;
    }
}
