package com.dlg.auth.WebConnector;

import io.cucumber.core.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class webconnector {
    public static WebDriver driver = null;
    public static Properties prop = new Properties();
    public SessionId session = null;
    public String path;

    public webconnector() {
        try {
            prop.load(new FileInputStream("./src/test/config/application.properties"));
            path = new File(".").getCanonicalPath();
            path = path + "/src/test/testdata/Data.xlsx";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return this.getDriver();
    }

    public void setDriver(WebDriver driver) {
        webconnector.driver = driver;
    }

    public void setUpDriver() {
        String browser = prop.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
//                chromeOptions.addArguments("");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }

    public void closeDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            saveScreenshotsForScenario(scenario);
        }
        driver.close();
    }

    private void saveScreenshotsForScenario(final Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    public void waitForPageLoad(int timeout) {
        ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
    }

    public String getSpecificColumnData(String FilePath, String SheetName, String ColumnName) throws InvalidFormatException, IOException {
        FileInputStream fis = new FileInputStream(FilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(SheetName);
        XSSFRow row = sheet.getRow(0);
        int col_num = -1;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getStringCellValue().trim().equals(ColumnName))
                col_num = i;
        }
        row = sheet.getRow(1);
        XSSFCell cell = row.getCell(col_num);
        String value = cell.getStringCellValue();
        fis.close();
        System.out.println("Value of the Excel Cell is - " + value);
        return value;
    }

    public By getElementWithLocator(String WebElement) throws Exception {
        String locatorTypeAndValue = prop.getProperty(WebElement);
        String[] locatorTypeAndValueArray = locatorTypeAndValue.split(",");
        String locatorType = locatorTypeAndValueArray[0].trim();
        String locatorValue = locatorTypeAndValueArray[1].trim();
        switch (locatorType.toUpperCase()) {
            case "ID":
                return By.id(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "TAGNAME":
                return By.tagName(locatorValue);
            case "LINKTEXT":
                return By.linkText(locatorValue);
            case "PARTIALLINKTEXT":
                return By.partialLinkText(locatorValue);
            case "XPATH":
                return By.xpath(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "CLASSNAME":
                return By.className(locatorValue);
            default:
                return null;
        }
    }

    public WebElement FindAnElement(String WebElement) throws Exception {
        return driver.findElement(getElementWithLocator(WebElement));
    }
    public void performMouseHover (String WebElement) throws Exception {
        Actions action = new Actions(driver);
        action.moveToElement(FindAnElement(WebElement)).build().perform();
    }

    public WebElement FindAnDirectElement(String WebElement) throws Exception {
        return driver.findElement(By.xpath(WebElement));
    }

    public void PerformActionOnElement(String WebElement, String Action, String Text) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        switch (Action) {
            case "Click":
                FindAnElement(WebElement).click();
                break;
            case "Type":
                FindAnElement(WebElement).sendKeys(Text);
                break;
            case "mousehover":
                performMouseHover(WebElement);
                break;
            case "Presence":
                waitForCondition("Presence", WebElement, 60);
                break;
            case "ScrollToElement":
                js.executeScript("arguments[0].scrollIntoView();", FindAnElement(WebElement));
                break;
            case "ScrollToEnd":
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                break;
            case "Clear":
                FindAnElement(WebElement).clear();
                break;
            case "WaitForElementDisplay":
                waitForCondition("Presence", WebElement, 60);
                break;
            case "WaitForElementClickable":
                waitForCondition("Clickable", WebElement, 60);
                break;
            case "ElementNotDisplayed":
                waitForCondition("NotPresent", WebElement, 60);
                break;
            default:
                throw new IllegalArgumentException("Action \"" + Action + "\" isn't supported.");
        }
    }

    public void waitForCondition(String TypeOfWait, String WebElement, int Time) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Time, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);
            switch (TypeOfWait) {
                case "PageLoad":
                    wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
                    break;
                case "Clickable":
                    wait.until(ExpectedConditions.elementToBeClickable(FindAnElement(WebElement)));
                    break;
                case "Presence":
                    wait.until(ExpectedConditions.presenceOfElementLocated(getElementWithLocator(WebElement)));
                    break;
                case "Visibility":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(getElementWithLocator(WebElement)));
                    break;
                case "NotPresent":
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(getElementWithLocator(WebElement)));
                    break;
                default:
                    Thread.sleep(Time * 1000);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("wait For Condition \"" + TypeOfWait + "\" isn't supported.");
        }
    }

    public boolean conditionCheck(String TypeOfCondition, String WebElement, int Time) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            boolean elementCondition = false;
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Time, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);
            switch (TypeOfCondition) {
                case "Displayed":
                    elementCondition = FindAnElement(WebElement).isDisplayed();
                    break;
                case "Clickable":
                    FindAnElement(WebElement).isEnabled();
                    break;
                default:
                    Thread.sleep(Time * 1000);
            }
            return elementCondition;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("condition check " + TypeOfCondition + " isn't supported.");
        }
    }

    public void directOperation(String typeOfOperation, String WebElement, String Text) throws Exception {
        try {
            switch (typeOfOperation) {
                case "click":
                    FindAnDirectElement(WebElement).click();
                    break;
                case "type":
                    FindAnDirectElement(WebElement).sendKeys(Text);
                    break;
                case "visible":
                    FindAnDirectElement(WebElement).isDisplayed();
                    break;
                default:
                    Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("directOperation  " + typeOfOperation + " isn't supported.");
        }
    }

    public void scrolltoView(String WebElement) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(WebElement));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void refresh() {
        driver.navigate().refresh();
    }
}
