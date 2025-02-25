package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleClinicSteps {

    private AppiumDriver driver;

    @Given("the Simple Clinic app is launched")
    public void launchApp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:appActivity", "org.simple.clinic.setup.SetupActivity");
        caps.setCapability("appium:appPackage", "org.simple.clinic.staging");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:deviceName", "sdk_gphone64_x86_64");
        caps.setCapability("appium:platformVersion", "15");
        caps.setCapability("appium:udid", "emulator-5554");
        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("the user proceeds through the intro screens")
    public void proceedThroughIntro() {
        driver.findElement(new By.ByXPath("//android.widget.Button[@resource-id=\"org.simple.clinic.staging:id/nextButton\"]")).click();
        assertEquals("Search & find \nthousands of patients \nwith hypertension", driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.simple.clinic.staging:id/introOneTextView\")")).getText());
        assertEquals("Maintain records of \nblood pressures \n& medicines", driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.simple.clinic.staging:id/introTwoTextView\")")).getText());
        assertEquals("Patients receive\nreminder messages\nto return for visits", driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.simple.clinic.staging:id/introThreeTextView\")")).getText());
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.simple.clinic.staging:id/getStartedButton\")")).click();
    }

    @When("the user agrees to the terms of use")
    public void agreeToTerms() {
        assertEquals("Terms of Use", driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Terms of Use\")")).getText());
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"AGREE AND CONTINUE\")")).click();
    }

    @When("the user selects {string} and {string} as the state")
    public void selectCountryAndState(String country, String state) {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"" + country + "\")")).click();
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" + ".scrollToEnd(10)"));
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"" + state + "\")")).click();
    }

    @When("the user enters a unique phone number")
    public void enterPhoneNumber() {
        String phoneNumber = generatePhoneNumber();
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.simple.clinic.staging:id/phoneNumberEditText\")")).sendKeys(phoneNumber);
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"org.simple.clinic.staging:id/nextButton\")")).click();
    }

    @When("the user enters their full name")
    public void enterFullName() {
        driver.findElement(By.id("org.simple.clinic.staging:id/fullNameEditText")).sendKeys("Test25");
        driver.findElement(By.id("org.simple.clinic.staging:id/nextButton")).click();
    }

    @When("the user sets and confirms a PIN")
    public void setAndConfirmPin() {
        driver.findElement(By.id("org.simple.clinic.staging:id/pinEditText")).sendKeys("5656");
        driver.findElement(By.id("org.simple.clinic.staging:id/confirmPinEditText")).sendKeys("5656");
        driver.findElement(By.id("org.simple.clinic.staging:id/skipButton")).click();
    }

    @When("the user skips facility selection and permissions")
    public void skipFacilityAndPermissions() {
        driver.findElement(By.id("org.simple.clinic.staging:id/facilityAddressTextView")).click();
        driver.findElement(By.id("org.simple.clinic.staging:id/yesButton")).click();
        driver.findElement(By.id("org.simple.clinic.staging:id/skipButton")).click();
        driver.findElement(By.id("com.android.permissioncontroller:id/permission_deny_button")).click();
        driver.findElement(By.id("android:id/button2")).click();
    }

    @Then("the user should see the {string} button")
    public void verifyScanIDButton(String buttonText) {
        String actualScanID = driver.findElement(By.id("org.simple.clinic.staging:id/scanSimpleCardButton")).getText();
        assertEquals(buttonText, actualScanID);
        driver.quit();
    }

    private String generatePhoneNumber() {
        Random random = new Random();
        int num1 = random.nextInt(900) + 100;
        int num2 = random.nextInt(900) + 100;
        int num3 = random.nextInt(9000) + 1000;
        return String.format("%d%d%d", num1, num2, num3);
    }
}