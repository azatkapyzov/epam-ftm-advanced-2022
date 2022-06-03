package GoogleCloud;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class GoogleCloudHomePagePF {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='devsite-searchbox']/input[@role='searchbox']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='gs-webResult gs-result']")
    private WebElement resultsDiv;

    @FindBy(xpath = "//*[@id='gc-wrapper']/main/devsite-content")
    private WebElement pageMainContent;

    @FindBy(xpath = "//div[@class='devsite-header-upper-tabs']")
    private WebElement navMenu;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//span[@data-select2-id='1']")
    private WebElement postformSyntaxHighlightingDropdown;

    @FindBy(xpath = "//span[@data-select2-id='3']")
    private WebElement postformExpirationDropdown;

    @FindBy(id = "postform-name")
    private WebElement postformName;

    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement submitButton;

    @FindBy(xpath = "//div[text()='Optional Paste Settings']")
    private static WebElement optionalSettingsTitle;

    public GoogleCloudHomePagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudHomePagePF openHomePage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(searchInput));
        return this;
    }

    public GoogleCloudHomePagePF makeSearchFor(String value) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(value);
        searchInput.sendKeys(Keys.RETURN);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(resultsDiv));
        return this;
    }

    public GoogleCloudHomePagePF openPageFromSearchResult(String resultTitle) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(resultsDiv));

        String targetURL = driver.findElement(By.xpath("//a[text()='" + resultTitle + "']")).getAttribute("href");
        driver.get(targetURL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(pageMainContent));
        return this;
    }

    public GoogleCloudHomePagePF openNavMenuDropdown(String tabTitle) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(navMenu));
        WebElement navMenuButton = driver.findElement(By.xpath("//a[@data-label='Tab: "+tabTitle+"' and @class='gc-analytics-event ']"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(navMenuButton));
        navMenuButton.click();
        return this;
    }

    public GoogleCloudHomePagePF openItemInNavMenuDropdown(String itemTitle) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(navMenu));
        WebElement itemInDropdown = driver.findElement(By.xpath("//a[@track-name='"+ itemTitle.toLowerCase(Locale.ROOT) +"']"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(itemInDropdown));
        itemInDropdown.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(pageMainContent));
        return this;
    }

    public GoogleCloudHomePagePF setNumberOfInstances(Integer numberOfInstances) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html[@ng-app='cloudpricingcalculator']")));
        numberOfInstancesInput.sendKeys(numberOfInstances.toString());
        return this;
    }


    public GoogleCloudHomePagePF setexpirationTime(String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionalSettingsTitle);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(postformExpirationDropdown));
        postformExpirationDropdown.click();
        WebElement expirationValue = driver.findElement(By.xpath("//li[text()='" + value + "']"));
        expirationValue.click();
        return this;
    }

    public GoogleCloudHomePagePF setSyntaxHighlighting(String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionalSettingsTitle);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(postformSyntaxHighlightingDropdown));
        postformSyntaxHighlightingDropdown.click();
        List<WebElement> syntaxValue = driver.findElements(By.xpath("//li[text()='" + value + "']"));
        syntaxValue.get(0).click();
        return this;
    }

    public GoogleCloudHomePagePF setpostformName(String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionalSettingsTitle);
        postformName.sendKeys(value);
        return this;
    }

    public static void clicksubmitButton() {
        submitButton.click();
    }
}
