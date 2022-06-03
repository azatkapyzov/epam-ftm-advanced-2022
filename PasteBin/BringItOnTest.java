package PasteBin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BringItOnTest {
    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup () {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test (description = "I can win")
    public void createNewPasteBringItOn() throws InterruptedException {
        String pasteText = "git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force";
        String pasteformTitle = "how to gain dominance among developers";
        String syntaxLanguage = "Bash";
        new PastebinHomePagePF(driver)
                .openPage()
                .setpostformText(pasteText)
                .setexpirationTime("10 Minutes")
                .setSyntaxHighlighting(syntaxLanguage)
                .setpostformName(pasteformTitle);
        PastebinHomePagePF.clicksubmitButton();

        String pasteformTitleExpected = new PastebinCreatedPagePF(driver).getPasteformTitle();
        Assert.assertEquals(pasteformTitle, pasteformTitleExpected);

        String syntaxLanguageExpected = new PastebinCreatedPagePF(driver).getSyntaxLanguage();
        Assert.assertEquals(syntaxLanguage, syntaxLanguageExpected);

        String pasteTextExpected = new PastebinCreatedPagePF(driver).getPasteFormText();
        Assert.assertEquals(pasteText, pasteTextExpected);
    }

    @AfterMethod (alwaysRun = true)
    public void browserQuit(){
        driver.quit();
        driver = null;
    }
}
