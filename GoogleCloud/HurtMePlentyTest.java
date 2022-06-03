package GoogleCloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.PageLoadStrategy;

public class HurtMePlentyTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup () {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @Test(description = "Hurt Me Plenty")
    public void calculatePrice() throws InterruptedException {
        String regionValue = "Frankfurt (europe-west3)";
        String commitmentTermValue = "1 Year";
        String machineTypeValue = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
        String localSSDValue = "2x375 GB";
        String totalEstimatedCostManualTest = "1,081.20";

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
                .clickButton("Add to Estimate");

        String regionGetValue = new GoogleCloudPriceCalculatorPF(driver).getRegionFromResults();
        Assert.assertTrue(regionValue.contains(regionGetValue));

        String commitmentTermGetValue = new GoogleCloudPriceCalculatorPF(driver).getCommitmentTermFromResults();
        Assert.assertEquals(commitmentTermValue, commitmentTermGetValue);

        String machineTypeGetValue = new GoogleCloudPriceCalculatorPF(driver).getMachineTypeFromResults();
        Assert.assertTrue((machineTypeValue.contains(machineTypeGetValue)));

        String localSSDGetValue = new GoogleCloudPriceCalculatorPF(driver).getLocalSSDFromResults();
        Assert.assertTrue((localSSDValue.contains(localSSDGetValue)));

        String totalEstimatedCostGetValue = new GoogleCloudPriceCalculatorPF(driver).getTotalEstimatedCostFromResults();
        Assert.assertEquals(totalEstimatedCostGetValue, totalEstimatedCostManualTest);
    }

    @AfterMethod(alwaysRun = true)
    public void browserQuit(){
        driver.quit();
        driver = null;
    }
}
