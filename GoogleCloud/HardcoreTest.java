package GoogleCloud;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HardcoreTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup () {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @Test(description = "Hardcore")
    public void calculatePriceAndAssertEmail() throws InterruptedException {
        String regionValue = "Frankfurt (europe-west3)";
        String commitmentTermValue = "1 Year";
        String machineTypeValue = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
        String localSSDValue = "2x375 GB";
        String totalEstimatedCostManualTest = "1,081.20";
        String emailAlias = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());

        new GoogleCloudHomePagePF(driver)
                .openHomePage()
                .openNavMenuDropdown("Pricing")
                .openItemInNavMenuDropdown("Pricing calculator");

        new GoogleCloudPriceCalculatorPF(driver)
                .setNumberOfInstances(4)
                .setSeries("n1")
                .setMachineType(machineTypeValue)
                .clickCheckBox("Add GPUs")
                .setGPUType("NVIDIA_TESLA_V100")
                .setNumberOfGPUs(1)
                .setLocalSSD(localSSDValue)
                .setDatacenterLocation(regionValue)
                .setCommittedUsage(commitmentTermValue)
                .clickButton("Add to Estimate")
                .clickButton("Email");

        driver.switchTo().newWindow(WindowType.TAB);

        new YopmailHomePagePF(driver)
                .openHomePage()
                .setEmailAliasAndCreateEmail(emailAlias);

        String emailAddress = new YopmailHomePagePF(driver).getCreatedEmailAddress();

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        new GoogleCloudPriceCalculatorPF(driver)
                .setEmailValueInPopUp(emailAddress)
                .clickButton("Send Email");

        driver.switchTo().window(tabs.get(1));

        new YopmailHomePagePF(driver).refreshInbox();

        String totalEstimatedCostAutoTest = new YopmailHomePagePF(driver).getTotalEstimatedCostFromEmail();
        Assert.assertTrue(totalEstimatedCostAutoTest.contains(totalEstimatedCostManualTest));
    }

    @AfterMethod(alwaysRun = true)
    public void browserQuit(){
        driver.quit();
        driver = null;
    }
}
