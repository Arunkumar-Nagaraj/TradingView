package com.dlg.auth.stepdefs;

import com.dlg.auth.ApplicationPages.LoginPage;
import com.dlg.auth.WebConnector.webconnector;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class LoginSteps {
    webconnector wc=new webconnector();
    private final LoginPage loginPage;
    private String scenDesc;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Before
    public void before(Scenario scenario) {
        this.scenDesc = scenario.getName();
//        if (!scenDesc.contains("API")) {
            wc.setUpDriver();
//        }
    }

    @After
    public void after(Scenario scenario) {
           wc.closeDriver(scenario);
    }

    @BeforeStep
    public void beforeStep() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Given("I launch the TradingView site")
    public void iAmAtTheLoginPageOfAnApplication() throws IOException, InvalidFormatException, InterruptedException {
        this.loginPage.goToLoginPage();
    }

    @When("I fill the valid credentials for login into the application")
    public void iFillTheValidCredentialsForLoginIntoTheApplication() throws  InterruptedException {
        this.loginPage.loggedIn();
    }

    @When("I select Asia tab in the application")
    public void selectAsiaTabInTheApplication() throws IOException, InvalidFormatException {
        this.loginPage.selectAsiaTab();
    }

    @Then("I search for the currency pair and click see overview")
    public void iSearchCurrencyPairAndClickOverviewInTheApplication() {
        this.loginPage.searchCurrencyPairAndClickOverview();
    }

    @Then("I verify whether this is correct page")
    public void iValidateProperErrorMessagesAreThrownByApplication() {
        this.loginPage.verifyTheCorrectPage();
    }
}
