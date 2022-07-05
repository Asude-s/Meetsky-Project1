package com.cydeo.step_definitions;

import com.cydeo.pages.LoginPage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Step_Definitions {

    LoginPage loginPage = new LoginPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("login_url"));
    }

    @Then("User sees the Username or email and Password as placeholders")
    public void user_sees_the_username_or_email_and_password_as_placeholders() {
        String expectedUsernamePlaceholder = "Username or email";
        String expectedPasswordPlaceholder = "Password";
        Assert.assertEquals(expectedUsernamePlaceholder, loginPage.usernameInput.getAttribute("placeholder"));
        Assert.assertEquals(expectedPasswordPlaceholder, loginPage.passwordInput.getAttribute("placeholder"));
    }

    @When("User enters {string} and {string} as valid credentials")
    public void user_enters_and_as_valid_credentials(String username, String password) {
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.loginButton.click();
    }

    @Then("User should be able to login and see the dashboard")
    public void user_should_be_able_to_login_and_see_the_dashboard() {
        String expected = "apps/files";

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        wait.until(ExpectedConditions.urlContains(expected));

        String actual = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actual.contains(expected));

        Driver.closeDriver();
    }

    @When("User enters invalid {string} and {string} as username and password")
    public void userEntersInvalidAndAsUsernameAndPassword(String username, String password) {
        if ((username.length() > 15 || username.length() < 2) || (password.length() > 15 || password.length() < 2)) {
            loginPage.usernameInput.sendKeys(username);
            loginPage.passwordInput.sendKeys(password);
        } else {
            loginPage.usernameInput.sendKeys(username);
            loginPage.passwordInput.sendKeys(password);
        }
    }

    @Then("User shouldn't be able to login and should see error message")
    public void user_shouldn_t_be_able_to_login_and_should_see_error_message() {
        loginPage.loginButton.click();
        String expectedWrongCredentialsMessage = "Wrong username or password.";
        String actualWrongCredentialsMessage = Driver.getDriver().findElement(By.xpath("//p[@class='warning wrongPasswordMsg']")).getText();
        Assert.assertEquals(expectedWrongCredentialsMessage, actualWrongCredentialsMessage);
    }

    @When("User does not enter any {string} as username")
    public void user_does_not_enter_any_as_username(String username) {
        loginPage.usernameInput.sendKeys(username);
    }

    @When("User does not enter any {string} as password")
    public void userDoesNotEnterAnyAsPassword(String password) {
        loginPage.passwordInput.sendKeys(password);

    }

    @Then("User shouldn't be able to login and should see required field error message")
    public void user_shouldn_t_be_able_to_login_and_should_see_required_field_error_message() {
        loginPage.loginButton.click();
        String actualRequiredFieldErrorMessage = "";
        WebElement username = new WebDriverWait(Driver.getDriver(), 20)
                .until(ExpectedConditions.elementToBeClickable(By.id("user")));
        actualRequiredFieldErrorMessage = username.getAttribute("validationMessage");

        WebElement password = new WebDriverWait(Driver.getDriver(), 20)
                .until(ExpectedConditions.elementToBeClickable(By.id("password")));
        actualRequiredFieldErrorMessage = password.getAttribute("validationMessage");

        Assert.assertEquals(ConfigurationReader.getProperty("requiredFieldErrorMessage"), actualRequiredFieldErrorMessage);
    }

    @When("User enters {string} as password")
    public void user_enters_as_password(String password) {
        loginPage.passwordInput.sendKeys(password);
    }

    @Then("User should see the password in a form of dots")
    public void user_should_see_the_password_in_a_form_of_dots() {
        Assert.assertEquals("password", loginPage.passwordInput.getAttribute("type"));
    }

    @When("User enters {string} password")
    public void userEntersPassword(String password) {
        loginPage.passwordInput.sendKeys(password);
    }

    @When("User clicks on eye icon")
    public void user_clicks_on_eye_icon() {
        loginPage.visiblePassword.click();
    }

    @Then("User should see the characters of password explicitly")
    public void user_should_see_the_characters_of_password_explicitly() {
        Assert.assertEquals("text", loginPage.passwordInput.getAttribute("type"));
    }

    @When("User clicks on Forgot password? link")
    public void userClicksOnForgotPasswordLink() {
        loginPage.forgotPassword.click();
    }

    @Then("User should see the Reset password button")
    public void userShouldSeeTheResetPasswordButton() {

        wait.until(ExpectedConditions.visibilityOf(Driver.getDriver().findElement(By.id("reset-password-submit"))));
        Assert.assertTrue(Driver.getDriver().findElement(By.id("reset-password-submit")).isDisplayed());
    }


}












