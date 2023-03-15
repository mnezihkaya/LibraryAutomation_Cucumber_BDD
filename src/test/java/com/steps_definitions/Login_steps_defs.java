package com.steps_definitions;

import com.Utils.BrowserUtils;
import com.Utils.LibraryUtils;
import com.testbase.PagesInitializer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_steps_defs extends PagesInitializer {
    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        LibraryUtils.login(username, password);
    }

    @When("user click {string} button")
    public void user_click_button(String btName) {
        BrowserUtils.clickButton(btName);
    }

    @Then("Verify user see the {string} page")
    public void verify_user_see_the_page(String title) {

        Assert.assertTrue(BrowserUtils.isElementDisplayed(dashboardPage.mainLogo));
    }

    @Then("Verify user see the error message")
    public void verify_user_see_the_error_message() {
        Assert.assertTrue(BrowserUtils.isElementDisplayed(loginPage.errorMessage));
    }
}
