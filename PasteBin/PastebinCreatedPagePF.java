package PasteBin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class PastebinCreatedPagePF {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='info-top']/h1")
    private static WebElement pasteFormTitle;

    @FindBy(xpath = "//div[@class='top-buttons']/div[@class='left']/a")
    private static WebElement syntaxLanguage;

    public PastebinCreatedPagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPasteformTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(pasteFormTitle));
        return pasteFormTitle.getText();
    }

    public String getSyntaxLanguage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(syntaxLanguage));
        return syntaxLanguage.getText();
    }

    public String getPasteFormText() {
        WebElement divClassSource = driver.findElement(By.xpath("//div[@class='source']"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(divClassSource));
        List<WebElement> listElements = driver.findElements(By.xpath("//div[@class='source']/ol/li"));
        String pasteFormText = "";
        for (int i = 0; i < listElements.size(); i++){
            pasteFormText += listElements.get(i).getText();
            if (i != listElements.size() - 1) {
                pasteFormText += "\n";
            }
        }
        return pasteFormText;
    }
}
