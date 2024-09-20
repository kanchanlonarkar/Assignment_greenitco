package AssismentGreenitco.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class GreenitcoTestngAssisment {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setBinary("C:\\Users\\kanchan Lonarkar\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(options);

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the login page
        driver.get("https://itassetmanagementsoftware.com/rolepermission/admin/login");
        driver.manage().window().maximize();
    }

    @Test(priority = 1) // First test to run
    public void testInvalidLogin() {
        // Test Case 1: Enter invalid username and click on Continue button
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.sendKeys("Scot@123");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnContinue']")));
        continueButton.click();

        // Validate error message
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='validateUserMsg']")));
        String expectedErrorMessage = "Please enter valid username to continue";
        Assert.assertTrue(errorMessage.getText().contains(expectedErrorMessage), "Error message not as expected");
    }

    @Test(priority = 2) // Second test to run
    public void testForgotPassword() {
        // Click on 'Forgot password?' link
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Forgot password?']")));
        forgotPasswordLink.click();

        // Enter the email address in the reset password field
        WebElement resetPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        resetPasswordField.sendKeys("xyz123@gmail.com");

        // Click the submit button
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnSubmit']")));
        continueButton.click();

        // Validate the confirmation message or error message
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[.='Given email address not have an account with us.']")));
        String expectedConfirmationMessage = "Given email address not have an account with us."; 
        
        Assert.assertTrue(confirmationMessage.getText().contains(expectedConfirmationMessage), "Failed to receive confirmation for password reset");
     
        // Check if i click on I "already know credential" link so it will directly navigate sign in page or not 
        WebElement alreadyKnowCredentialLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='I already know credential']")));
        alreadyKnowCredentialLink.click();
        
      /* // Optionally, navigate back to the login page
        driver.navigate().back();*/
    }

   /* @Test(priority = 3) // Third test to run
    public void testRememberMeFunctionalityWithInvalidCredential() {
        // Locate Remember Me checkbox and click it if while entering invalid username and click on remember me check box and click on continew button it's work or not
    	WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.sendKeys("Kanchan@123");

        WebElement rememberMeCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Remember me']")));
        rememberMeCheckbox.click();
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnContinue")));
        continueButton.click();
        Assert.assertTrue(rememberMeCheckbox.isSelected(), "'Remember Me' checkbox is not selected");
    }*/
    
    @Test(priority = 3) // Third test to run
    public void testRememberMeFunctionalityWithInvalidCredential() {
        // Locate the username field and enter an invalid username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.sendKeys("Kanchan@123");

        // Locate the actual Remember Me checkbox (not the label) and click it
        WebElement rememberMeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='checkbox pad-btm text-left']"))); 
        rememberMeCheckbox.click();
        
        // Check if the checkbox is already selected, if not, click it
       /* if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }*/

        // Click the continue button
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnContinue")));
        continueButton.click();

        // Validate that the Remember Me checkbox is selected
        Assert.assertTrue(rememberMeCheckbox.isSelected(), "'Remember Me' checkbox is not selected");
    }


    /*@AfterTest
    public void teardown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }*/
}
