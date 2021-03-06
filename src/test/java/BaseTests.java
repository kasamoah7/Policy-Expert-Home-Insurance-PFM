import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTests {
    private WebDriver driver;

    By closeCookieButton = By.cssSelector("button[aria-label='Close cookie']");
    By homeInsuranceButton = By.xpath("//a[contains(@href, '/home-insurance')]");
    By getAQuoteButton = By.cssSelector("a[href~='https://insurance.policyexpert.co.uk/home']");
    By selectTitle = By.cssSelector("button[aria-label='Mr']");
    By firstNameInput = By.id("first_name");
    By lastNameInput = By.id("last_name");
    By birthDayInput = By.id("policyholder_date_of_birth.day");
    By birthMonthInput = By.id("policyholder_date_of_birth.month");
    By birthYearInput = By.id("policyholder_date_of_birth.year");
    By selectMaritalStatus = By.cssSelector("button[aria-label='Single']");
    By occupationInput = By.name("occupation");
    By anotherOccupationAnswer = By.cssSelector("button[aria-label='No']");
    By anotherOccupationAnswerYes = By.cssSelector("button[aria-label='Yes']");
    By phoneNumberInput = By.id("primary_phone_number");
    By emailAddressInput = By.id("customer_email");
    By nextButton = By.cssSelector("button.styled__PrimaryButton-sc-qy8nbl-2.Sectionstyle__PrimaryButton-sc-b4fcid-4.lcLfTk.fPpkjj");

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://www.policyexpert.co.uk/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldGetHomeInsuranceQuote() throws InterruptedException {
        startBrowser();
        clickCloseCookieButton();
        clickHomeInsuranceButton();
        clickGetAQuoteButton();
        selectTitle();
        setFirstName("Jim");
        setLastName("Brown");
        setDateOfBirthDay("07");
        setDateOfBirthMonth("04");
        setDateOfBirthYear("1956");
        selectMaritalStatus();
        setOccupation("Doctor");
        anotherOccupationQuestion("No");
        setMainPhoneNumber("07956458235");
        setEmailAddress("vketipisz@qmetric.co.uk");
        clickNextButton();
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue("You have not reached the About you and other residents page", driver.getCurrentUrl().contains("statements-about-you"));

    }

    public void selectTitle() {
        driver.findElement(selectTitle).click();

    }

    public void clickHomeInsuranceButton() {
        driver.findElement(homeInsuranceButton).click();
    }

    public void clickCloseCookieButton(){
        driver.findElement(closeCookieButton).click();
    }

    public void clickGetAQuoteButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(getAQuoteButton);
        js.executeScript("arguments[0].click();", element);
    }

    public void setFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void setDateOfBirthDay(String userDay) {
        driver.findElement(birthDayInput).sendKeys(userDay);
    }

    public void setDateOfBirthMonth(String userMonth) {
        driver.findElement(birthMonthInput).sendKeys(userMonth);
    }

    public void setDateOfBirthYear(String userYear) {
        driver.findElement(birthYearInput).sendKeys(userYear);
    }

    public void selectMaritalStatus() {
        driver.findElement(selectMaritalStatus).click();
    }

    public void setOccupation(String occupation) throws InterruptedException {
        WebElement element = driver.findElement(occupationInput);
        //Slow down the keyboard entry
        for (int i = 0; i < occupation.length(); i++) {
            element.sendKeys(String.valueOf(occupation.charAt(i)));
            TimeUnit.MILLISECONDS.sleep(500);
        }
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);

    }

    public void anotherOccupationQuestion(String answer) {
        if (answer.equals("No")) {
            driver.findElement(anotherOccupationAnswer).click();
        } else {
            driver.findElement(anotherOccupationAnswerYes).click();
        }
    }

    public void setMainPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void setEmailAddress(String emailAddress) {
        driver.findElement(emailAddressInput).sendKeys(emailAddress);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

}
