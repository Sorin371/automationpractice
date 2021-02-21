package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;


    public HomePage(WebDriver ldriver) {
        this.driver = ldriver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "(//a[@class='product-name'])[1]")
    WebElement firstItem;

    @FindBy(xpath = "(//a[@class='product-name'])[2]")
    WebElement secondItem;

    @FindBy(xpath = "(//span[@class='price product-price'])[1]")
    WebElement getItemPrice;

    @FindBy(xpath = "//li[contains(@class,'hovered')]//span[text() = 'Add to cart']")
    WebElement addToCartButton;

    @FindBy(xpath = "(//div[@class='product-container'])[1]")
    WebElement productContainer;

    @FindBy(xpath = "//span[contains(text(), 'Proceed to checkout')]")
    WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//span[@title = 'Continue shopping']")
    WebElement continueShoppingButton;


    public String getItemName(){
        String titleItem = firstItem.getAttribute("textContent");
        titleItem = titleItem.replaceAll("\\s", "");
        return titleItem;
    }

    public Float getItemPrice() {
        String getPrice = getItemPrice.getAttribute("textContent");
        getPrice = getPrice.replaceAll("\\s", "");
        getPrice = getPrice.replace("$", "");

        return Float.parseFloat(getPrice);
    }

    public void addFirstItemToCart() {
        Actions action = new Actions(driver);

        //Scroll to element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productContainer);

        //Mouse over element
        action.moveToElement(firstItem).build().perform();

        //Add item to cart
        addToCartButton.click();
    }

    public void addSecondItemToCart() {
        Actions action = new Actions(driver);

        //Scroll to element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", secondItem);

        //Mouse over element
        action.moveToElement(secondItem).build().perform();

        //Add item to cart
        addToCartButton.click();
    }

    public void setProceedToCheckoutButton(){
        /*try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {}*/
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        //Go to the cart page
        proceedToCheckoutButton.click();
    }

    public void setContinueShoppingButton() {
//        try {
//            Thread.sleep(2000);
//        }catch (InterruptedException e) {}

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));

        //Back to home page
        continueShoppingButton.click();
    }
}