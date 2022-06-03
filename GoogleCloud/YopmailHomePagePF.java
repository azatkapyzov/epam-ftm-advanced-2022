package GoogleCloud;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class YopmailHomePagePF {
    private static final String HOMEPAGE_URL = "https://yopmail.com/";
    private WebDriver driver;

    @FindBy(xpath = "//input[@class='ycptinput']")
    private WebElement emailAliasInput;

    @FindBy(xpath = "//button[@class='md']")
    private WebElement createEmailButton;

    @FindBy(xpath = "//button[@id='refresh']")
    private WebElement refreshButton;

    @FindBy(xpath = "//iframe[@id='ifinbox']")
    private WebElement inboxIFrame;

    public YopmailHomePagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public YopmailHomePagePF openHomePage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@class='index']")));
        return this;
    }

    public YopmailHomePagePF setEmailAliasAndCreateEmail(String emailAlias){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(emailAliasInput));
        emailAliasInput.sendKeys(emailAlias);
        createEmailButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bname']")));
        return this;
    }

    public String getCreatedEmailAddress(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bname']")));
        WebElement divEmail = driver.findElement(By.xpath("//div[@class='bname']"));
        return divEmail.getText();
    }

    public YopmailHomePagePF refreshInbox() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(refreshButton));
        Boolean IsEmptyInboxTitleDisplayed = driver.findElement(By.xpath("//div[@id='messagectn']")).isDisplayed();
        while (IsEmptyInboxTitleDisplayed){
            Thread.sleep(2500);
            refreshButton.click();
            IsEmptyInboxTitleDisplayed = driver.findElement(By.xpath("//div[@id='messagectn']")).isDisplayed();
        }
        driver.switchTo().defaultContent();
        return this;
    }

    public String getTotalEstimatedCostFromEmail(){
        driver.switchTo().frame(inboxIFrame);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mctn']/div[@class='m'][1]/button")));
        driver.findElement(By.xpath("//div[@class='mctn']/div[@class='m'][1]/button")).click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("ifmail");
        return driver.findElement(By.xpath("//*[@id='mail']/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]")).getText();
    }

}
