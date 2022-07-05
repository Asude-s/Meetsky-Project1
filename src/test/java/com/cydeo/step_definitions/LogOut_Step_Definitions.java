package com.cydeo.step_definitions;

import com.cydeo.pages.LogoutPage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOut_Step_Definitions {

    LogoutPage logoutPage = new LogoutPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);

    @Given("User can login with valid credentials")
    public void user_can_login_with_valid_credentials() {
        Driver.getDriver().get(ConfigurationReader.getProperty("login_url"));
        logoutPage.usernameInput.sendKeys("Employee11");
        logoutPage.passwordInput.sendKeys("Employee123");
        logoutPage.loginButton.click();

        String expected = "apps/files";
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        wait.until(ExpectedConditions.urlContains(expected));
        String actual = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actual.contains(expected));
    }
    @When("User clicks on user profile menu button on top-right corner")
    public void user_clicks_on_user_profile_menu_button_on_top_right_corner() {
        logoutPage.userProfileMenuButton.click();
    }
    @When("User clicks on log out button")
    public void user_clicks_on_log_out_button() {
        logoutPage.userProfileMenuLogoutButton.click();
    }
    @Then("User should be able to logged out successfully and can see the login page")
    public void user_should_be_able_to_logged_out_successfully_and_can_see_the_login_page() {

        String expected = ConfigurationReader.getProperty("login_url");
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().startsWith(expected));
    }

    @Then("User should not be able to go to home page again by clicking step back button")
    public void userShouldNotBeAbleToGoToHomePageAgainByClickingStepBackButton() {
        Driver.getDriver().navigate().back();
        String expected = "login";
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        wait.until(ExpectedConditions.urlContains(expected));
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expected));

    }
}
