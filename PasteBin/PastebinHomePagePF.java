package PasteBin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class PastebinHomePagePF {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement postformText;

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

    public PastebinHomePagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePagePF openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
        return this;
    }

    public PastebinHomePagePF setpostformText(String value) {
        postformText.sendKeys(value);
        return this;
    }

    public PastebinHomePagePF setexpirationTime(String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionalSettingsTitle);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(postformExpirationDropdown));
        postformExpirationDropdown.click();
        WebElement expirationValue = driver.findElement(By.xpath("//li[text()='" + value + "']"));
        expirationValue.click();
        return this;
    }

    public PastebinHomePagePF setSyntaxHighlighting(String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionalSettingsTitle);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(postformSyntaxHighlightingDropdown));
        postformSyntaxHighlightingDropdown.click();
        List<WebElement> syntaxValue = driver.findElements(By.xpath("//li[text()='" + value + "']"));
        syntaxValue.get(0).click();
        return this;
    }

    public PastebinHomePagePF setpostformName(String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optionalSettingsTitle);
        postformName.sendKeys(value);
        return this;
    }

    public static void clicksubmitButton() {
        submitButton.click();
    }

}
