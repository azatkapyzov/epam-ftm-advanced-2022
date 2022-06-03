package GoogleCloud;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class GoogleCloudPriceCalculatorPF {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private WebDriver driver;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//devsite-iframe/iframe")
    private  WebElement outerIFrame;

    @FindBy(id = "myFrame")
    private WebElement innerIFrame;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement machineTypeDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Series']")
    private WebElement seriesDropdown;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement GPUTypeDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUsInput;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD']")
    private WebElement localSSDDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
    private WebElement datacenterLocationDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement committedUsageDropdown;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    public GoogleCloudPriceCalculatorPF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudPriceCalculatorPF setNumberOfInstances(Integer numberOfInstances) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(outerIFrame));
        driver.switchTo().frame(outerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(innerIFrame));
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(numberOfInstancesInput));
        numberOfInstancesInput.sendKeys(numberOfInstances.toString());
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPriceCalculatorPF setSeries(String seriesValue) {
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(seriesDropdown));
        seriesDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='md-select-menu-container md-active md-clickable']")));
        WebElement seriesValueInDropdown = driver.findElement(By.xpath("//md-option[@value='" + seriesValue + "']"));
        seriesValueInDropdown.click();
        driver.switchTo().defaultContent();
        return this;
    }


    public GoogleCloudPriceCalculatorPF setMachineType(String machineType) {
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(machineTypeDropdown));
        machineTypeDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='md-select-menu-container md-active md-clickable']")));
        WebElement machineTypeValue = driver.findElement(By.xpath("//md-option/div[contains(text(), '" + machineType + "')]"));
        machineTypeValue.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPriceCalculatorPF clickCheckBox(String checkBoxAriaLable) {
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        List <WebElement> checkBoxes = driver.findElements(By.xpath("//md-checkbox[@aria-label='" + checkBoxAriaLable + "']"));
        checkBoxes.get(0).click();
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPriceCalculatorPF setGPUType(String GPUType) {
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(GPUTypeDropdown));
        GPUTypeDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='md-select-menu-container md-active md-clickable']")));
        WebElement GPUTypeValue = driver.findElement(By.xpath("//md-option[@value='" + GPUType + "']"));
        GPUTypeValue.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPriceCalculatorPF setNumberOfGPUs(Integer numberOfGPUs) {
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        numberOfGPUsInput.sendKeys(numberOfGPUs.toString());
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPriceCalculatorPF setLocalSSD(String localSSD) {
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(localSSDDropdown));
        localSSDDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='md-select-menu-container md-active md-clickable']")));
        WebElement GPUTypeValue = driver.findElement(By.xpath("//md-option/div[contains(text(), '" + localSSD + "')]"));
        GPUTypeValue.click();
        driver.switchTo().defaultContent();
        return this;
    }
    public GoogleCloudPriceCalculatorPF setDatacenterLocation(String datacenterLocation) {
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(datacenterLocationDropdown));
        datacenterLocationDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-hidden='false']/descendant::md-option/div[contains(text(), '" + datacenterLocation + "')]")));
        WebElement datacenterLocationValue = driver.findElement(By.xpath("//div[@aria-hidden='false']/descendant::md-option/div[contains(text(), '" + datacenterLocation + "')]"));
        datacenterLocationValue.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPriceCalculatorPF setCommittedUsage(String committedUsage) {
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(committedUsageDropdown));
        committedUsageDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='md-select-menu-container md-active md-clickable']")));
        WebElement committedUsageValue = driver.findElement(By.xpath("//div[@aria-hidden='false']/descendant::md-option/div[contains(text(), '" + committedUsage + "')]"));
        committedUsageValue.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPriceCalculatorPF clickButton(String buttonTitle){
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        WebElement button = driver.findElement(By.xpath("//button[contains(text(), '" + buttonTitle + "') and not(@disabled)]"));
        button.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//md-card-content[@class='ng-scope']")));
        driver.switchTo().defaultContent();
        return this;
    }

    public String getRegionFromResults(){
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-content[@id='compute']")));
        WebElement regionElement = driver.findElement(By.xpath("//md-content[@id='compute']/md-list/md-list-item[1]"));
        String result = regionElement.getText().substring(8);
        driver.switchTo().defaultContent();
        return result;
    }

    public String getCommitmentTermFromResults(){
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-content[@id='compute']")));
        WebElement commitmentTermElement = driver.findElement(By.xpath("//md-content[@id='compute']/md-list/md-list-item[3]"));
        String result = commitmentTermElement.getText().substring(17);
        driver.switchTo().defaultContent();
        return result;
    }

    public String getMachineTypeFromResults(){
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-content[@id='compute']")));
        WebElement commitmentTermElement = driver.findElement(By.xpath("//md-content[@id='compute']/md-list/md-list-item[5]/div[1]"));
        String result = commitmentTermElement.getText().substring(15);
        String[] splittedResult = result.split("\n");
        driver.switchTo().defaultContent();
        return splittedResult[0];
    }

    public String getLocalSSDFromResults(){
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-content[@id='compute']")));
        WebElement commitmentTermElement = driver.findElement(By.xpath("//md-content[@id='compute']/md-list/md-list-item[7]/div[1]"));
        String result = commitmentTermElement.getText();
        String[] splittedResult = result.split(" ");
        driver.switchTo().defaultContent();
        return splittedResult[2];
    }

    public String getTotalEstimatedCostFromResults(){
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//md-content[@id='compute']")));
        WebElement totalEstimatedCostElement = driver.findElement(By.xpath("//md-content[@id='compute']/../h2"));
        String result = totalEstimatedCostElement.getText();
        String[] splittedResult = result.split(" ");
        System.out.println(splittedResult[4]);
        driver.switchTo().defaultContent();
        return splittedResult[4];
    }

    public GoogleCloudPriceCalculatorPF setEmailValueInPopUp(String emailAddress){
        driver.switchTo().frame(outerIFrame);
        driver.switchTo().frame(innerIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@name='emailForm']")));
        emailInput.sendKeys(emailAddress);
        driver.switchTo().defaultContent();
        return this;
    }




}
