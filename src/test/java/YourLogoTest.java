import Utils.ExcelUtil;
import Utils.MainBaseMethod;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class YourLogoTest extends MainBaseMethod {
    WebDriver driver;
    YourLogoTestPagePOM yourLogoTestPagePOM = new YourLogoTestPagePOM();
    static final String ExcelFilePath = System.getProperty("user.dir") + "/ExcelFile/ExcelBook_Rufsun.xlsx";
    WebDriverWait wait;

    @BeforeClass
    void setUp() {
        driver = getWebDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @DataProvider(name = "loadFormData")
    public static Object[][] dataLoad() throws Exception {
        return ExcelUtil.ExcelUtils.getTableArray(ExcelFilePath, "Sheet1");
    }

    @Test(dataProvider = "loadFormData")
    void yourLogoRegisterAndLogin(String email, String title, String firstName, String lastName, String password, String address, String city, String zipCode, String phone) throws InterruptedException {
        driver.get(yourLogoTestPagePOM.pageUrl);
        click(yourLogoTestPagePOM.signIn);
        Faker faker = new Faker();
        driver.findElement(yourLogoTestPagePOM.signInEmail).sendKeys(faker.name().username() + "007@gmail.com");
        click(yourLogoTestPagePOM.createAccount);
        wait.until(ExpectedConditions.visibilityOfElementLocated(yourLogoTestPagePOM.firstName));
        Thread.sleep(5000);
        sendKeysToElement(yourLogoTestPagePOM.firstName, firstName);
        sendKeysToElement(yourLogoTestPagePOM.lastName, lastName);
        sendKeysToElement(yourLogoTestPagePOM.password, password);
        int day = faker.number().numberBetween(1, 28);
        selectByValue(yourLogoTestPagePOM.days, String.valueOf(day));
        int month = faker.number().numberBetween(1, 12);
        selectByValue(yourLogoTestPagePOM.month, String.valueOf(month));
        int year = faker.number().numberBetween(1990, 2022);
        selectByValue(yourLogoTestPagePOM.year, String.valueOf(year));
        sendKeysToElement(yourLogoTestPagePOM.address, address);
        sendKeysToElement(yourLogoTestPagePOM.city, city);
        selectByVisibleText(yourLogoTestPagePOM.state, "New York");
        sendKeysToElement(yourLogoTestPagePOM.zipCode, String.valueOf(zipCode));
        sendKeysToElement(yourLogoTestPagePOM.phoneNumber, String.valueOf(phone));
        click(yourLogoTestPagePOM.register);
        click(yourLogoTestPagePOM.logOut);

        driver.get(yourLogoTestPagePOM.pageUrl);
        click(yourLogoTestPagePOM.signIn);
        String username = initializeProperties().getProperty("email");
        driver.findElement(yourLogoTestPagePOM.logInEmail).sendKeys(username);
        String passwords = initializeProperties().getProperty("password");
        driver.findElement(yourLogoTestPagePOM.password).sendKeys(password);
        click(yourLogoTestPagePOM.signInEmailPass);
        click(yourLogoTestPagePOM.women);
        List<WebElement> womenCarts = driver.findElements(yourLogoTestPagePOM.womenProducts);
        WebElement lastWomenCart = womenCarts.get(womenCarts.size() - 1);
        javaScriptExecutorClick(lastWomenCart);
        click(yourLogoTestPagePOM.continueShopping);
        String shoppingCart = driver.findElement(yourLogoTestPagePOM.cart).getText();
        int count = 1;
        Assert.assertTrue(shoppingCart.contains(String.valueOf(count++)));
        click(yourLogoTestPagePOM.tops);
        javaScriptExecutorClick(yourLogoTestPagePOM.sizeSmall);
        wait.until(ExpectedConditions.urlContains("size-s"));
        List<WebElement> topsCarts = driver.findElements(yourLogoTestPagePOM.womenProducts);

        for (int i = 0; i < topsCarts.size(); i++) {
            javaScriptExecutorClick(topsCarts.get(i));
            waitAndClick((yourLogoTestPagePOM.continueShopping), 15);
            String shoppingCart1 = driver.findElement(yourLogoTestPagePOM.cart).getText();
            int count1 = 1;
            Assert.assertTrue(shoppingCart.contains(String.valueOf(count1++)));
        }
        waitAndClick((yourLogoTestPagePOM.cart), 15);
        waitAndClick((yourLogoTestPagePOM.proceedToCheckout), 15);
        click(yourLogoTestPagePOM.addressProceedToCheckout);
        click(yourLogoTestPagePOM.termsAndCondition);
        click(yourLogoTestPagePOM.shippingProceedToCheckout);
        String totalProduct = driver.findElement(yourLogoTestPagePOM.totalProduct).getText();
        totalProduct = totalProduct.replace("$", " ");
        String totalShipping = driver.findElement(yourLogoTestPagePOM.totalShipping).getText();
        totalShipping = totalShipping.replace("$", " ");
        Double addTotal = Double.valueOf(totalProduct) + Double.valueOf(totalShipping);
        String total = driver.findElement(yourLogoTestPagePOM.total).getText();
        Assert.assertTrue(total.contains(String.valueOf(addTotal)));
        click(yourLogoTestPagePOM.bankInfo);
        click(yourLogoTestPagePOM.confirmMyOrder);
    }

    @AfterClass
    void wrapUp() {
        driver.quit();
    }
}
