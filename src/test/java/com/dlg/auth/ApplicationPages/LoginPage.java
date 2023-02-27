package com.dlg.auth.ApplicationPages;

import com.dlg.auth.WebConnector.webconnector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static com.dlg.auth.WebConnector.webconnector.driver;

public class LoginPage {
    String baseUrl = "https://www.tradingview.com/markets/currencies/rates-all/";
    webconnector wc = new webconnector();


    public void goToLoginPage() throws InterruptedException {
        webconnector.driver.get(baseUrl);
        Thread.sleep(300);
        wc.waitForCondition("PageLoad","",60);
    }

    public void loggedIn()  {
        try {
            wc.PerformActionOnElement("OpenUserBtn_TradeViewPage", "Click", "");
            Thread.sleep(300);
            wc.PerformActionOnElement("SignInBtn_TradeViewPage", "Click", "");
            Thread.sleep(300);
            wc.PerformActionOnElement("EmailBtn_LoginPage", "Click", "");
            Thread.sleep(300);
            wc.PerformActionOnElement("UsernameTxtBox_LoginPage", "Type", "tradeviewt35t@gmail.com");
            Thread.sleep(300);
            wc.PerformActionOnElement("PasswordTxtBox_LoginPage", "Type", "Trade!1234");
            Thread.sleep(300);
            wc.PerformActionOnElement("SubmitBtn_LoginPage", "Click", "");
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable not able to login with given user --->");
        }
    }

    public void selectAsiaTab()  {
        try {
            wc.PerformActionOnElement("AsiaTab_TradeViewPage", "Click", "");
            Thread.sleep(300);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to select the tab --->");
        }
    }

    public void searchCurrencyPairAndClickOverview() {
        try {
            wc.PerformActionOnElement("SearchBtn_TradeViewPage", "Click", "");
            Thread.sleep(300);
            wc.PerformActionOnElement("SearchTxtBox_TradeViewPage", "Type", "FX:GBPJPY");
            Thread.sleep(300);
            wc.PerformActionOnElement("SeeOverViewTab_TradeViewPage", "mousehover", "");
            Thread.sleep(300);
            wc.PerformActionOnElement("ClickSeeOverViewBtn_TradeViewPage", "Click", "");
            Thread.sleep(300);
            Assert.assertTrue(true);
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to select the tab --->");
        }
    }


    public void verifyTheCorrectPage() {
        try {
            WebElement pageTitle = driver.findElement(By.xpath("//title[contains(text(),'GBP JPY Chart – Pound to Yen Rate — TradingView')]"));
            wc.PerformActionOnElement("PageTitleTxt_TradeViewPage", "Presence", "");
            Thread.sleep(300);
            String expectedTitle = "GBP JPY Chart – Pound to Yen Rate — TradingView";
            String actualTitle = pageTitle.getText();
            if (actualTitle.contains(expectedTitle)) {
                System.out.println("Test passed. You are on the correct page.");
            } else {
                System.out.println("Test failed. You are not on the correct page.");
            }
        }catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Unable to verify the page --->");
        }
    }
}
