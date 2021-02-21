package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public WebDriver driver;

    public void compareTwoString(String firstElement, String secondElement){
        try{
            if(firstElement.equals(secondElement)){
                System.out.println(firstElement.equals(secondElement));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Assert.fail(secondElement + " and " + secondElement + " are not equal");
        }
    }

    public void compareTwoFloat(Float firstElement, Float secondElement) {
        try{
            if(firstElement.equals(secondElement)){
                System.out.println(firstElement.equals(secondElement));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Assert.fail(firstElement + " and " + secondElement + " are not equal");
        }
    }

    public void quantityItem(Float price, Float quantity, Float total){

        Float productMultiplication = price * quantity;

        try{
            if(productMultiplication.equals(total)){
                System.out.println(productMultiplication.equals(total));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Assert.fail(productMultiplication + " and " + total + " are not equal");
        }
    }

    public void taxCalculation(Float totalProducts, Float totalShipping, Float tax, Float totalPrice) {
        Float priceAfterTaxes = totalProducts + totalShipping + tax;

        try{
            if(priceAfterTaxes.equals(totalPrice)){
                System.out.println(priceAfterTaxes.equals(totalPrice));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Assert.fail(priceAfterTaxes + " and " + totalPrice + " are not equal");
        }
    }

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.startApplication(driver, "Firefox", "http://automationpractice.com/");
    }

    @AfterMethod
    public void tearDown() {
        BrowserFactory.quitBrowser(driver);
    }
}
