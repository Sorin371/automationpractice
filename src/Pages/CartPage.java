package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.List;


public class CartPage {

    WebDriver driver;

    @FindBy(xpath = "//p[@class='product-name']/a")
    WebElement nameItem;

    @FindBy(xpath = "(//span[@class='price']/span[@class='price'])[1]")
    WebElement priceItem;

    @FindBy(xpath = "//td[@class='cart_quantity text-center']/input[@class='cart_quantity_input form-control grey']")
    WebElement quantityItem;

    @FindBy(xpath = "//td[@class='cart_total']/span")
    WebElement totalItem;

    @FindBy(xpath = "//td[@class='cart_total']/span")
    List<WebElement> allTotalItem;

    @FindBy(xpath = "//td[@id= 'total_product']")
    WebElement getTotalProducts;

    @FindBy(xpath = "//td[@id= 'total_shipping']")
    WebElement totalShipping;

    @FindBy(xpath = "//td[@id= 'total_tax']")
    WebElement totalTax;

    @FindBy(xpath = "//span[@id= 'total_price']")
    WebElement totalPrice;

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    WebElement deleteFirstItem;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']")
    WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//button/span[contains(text(), 'Proceed to checkout')]")
    WebElement proceedToCheckoutShippingStep;

    @FindBy(xpath = "//button[@name='processAddress']")
    WebElement proceedToCheckoutAddressStep;

    @FindBy(xpath = "//p[@class='alert alert-warning' and text()='Your shopping cart is empty.']")
    WebElement warningCartIsEmpty;

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='passwd']")
    WebElement passField;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    WebElement signInButton;

    //@FindBy(xpath = "//input[@id='cgv']")
    @FindBy(xpath = "//label[contains(text(), 'I agree to the terms of service and will adhere to them unconditionally')]")
    WebElement checkBoxTermsOfService;

    @FindBy(xpath = "//a[@class='cheque']")
    WebElement payByCheck;

    @FindBy(xpath = "//span[contains(text(), 'I confirm my order')]")
    WebElement confirmMyOrderButton;

    @FindBy(xpath = "//p[contains(text(),'Your order on My Store is complete.') and @class='alert alert-success']")
    WebElement successAlert;

    @FindBy(xpath = "//div[@class='alert alert-danger']/ol/li[contains(text(), 'An email address required.')]")
    WebElement warningEmailIsRequired;

    @FindBy(xpath = "//div[@class='alert alert-danger']/ol/li[contains(text(), 'Password is required.')]")
    WebElement warningPasswordIsRequired;

    @FindBy(xpath = "//div[@class='alert alert-danger']/ol/li[contains(text(), 'Invalid password.')]")
    WebElement warningWrongPassword;

    @FindBy(xpath = "//p[@class='fancybox-error' and contains(text(),'You must agree to the terms of service before continuing.')]")
    WebElement popupWarningTermsOfService;

    public String returnItemName() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {

        }

        String titleItem = nameItem.getAttribute("textContent");
        titleItem = titleItem.replaceAll("\\s", "");
        return titleItem;
    }

    public Float returnItemPrice(){
        String getPrice = priceItem.getAttribute("textContent");
        getPrice = getPrice.replaceAll("\\s", "");
        getPrice = getPrice.replace("$", "");

        return Float.parseFloat(getPrice);
    }

    public void setDeleteFirstItem() {
        deleteFirstItem.click();
    }

    public void setWarningCartIsEmpty() {
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {}


        Assert.assertEquals(true, warningCartIsEmpty.isDisplayed(), warningCartIsEmpty + " is not displayed");
    }

    public void setQuantityItem(String q) {
        //Set quantity to 2
        quantityItem.sendKeys(q);

        totalItem.click();

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {}
    }

    public Float getTotalItem() {
        String getTotal = totalItem.getAttribute("textContent");
        getTotal = getTotal.replaceAll("\\s", "");
        getTotal = getTotal.replace("$", "");

        return Float.parseFloat(getTotal);
    }

    public Float calculateAllItemsToCart() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float t = 0;

        for (WebElement webElement : allTotalItem) {
            String el = webElement.getAttribute("textContent");
            el = el.replaceAll("\\s", "");
            el = el.replace("$", "");
            Float total = Float.parseFloat(el);
            
            t = total + t;
        }

        return Float.valueOf(decimalFormat.format(t));
    }

    public Float getTotalProducts(){
        String getTotal = getTotalProducts.getAttribute("textContent");
        getTotal = getTotal.replaceAll("\\s", "");
        getTotal = getTotal.replace("$", "");

        return Float.parseFloat(getTotal);
    }

    public Float getTotalShipping() {
        String getTotal = totalShipping.getAttribute("textContent");
        getTotal = getTotal.replaceAll("\\s", "");
        getTotal = getTotal.replace("$", "");

        return Float.parseFloat(getTotal);
    }

    public Float getTotalTax() {
        String getTotal = totalTax.getAttribute("textContent");
        getTotal = getTotal.replaceAll("\\s", "");
        getTotal = getTotal.replace("$", "");

        return Float.parseFloat(getTotal);
    }

    public Float getTotalPrice() {
        String getTotal = totalPrice.getAttribute("textContent");
        getTotal = getTotal.replaceAll("\\s", "");
        getTotal = getTotal.replace("$", "");

        return Float.parseFloat(getTotal);
    }

    public void setProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }

    public void setProceedToCheckoutAddressStep(){
        proceedToCheckoutAddressStep.click();
    }

    public void setProceedToCheckoutShippingStep(){
        proceedToCheckoutShippingStep.click();
    }

    public void login(String email, String pass) {
        emailField.sendKeys(email);
        passField.sendKeys(pass);
        signInButton.click();
    }

    public void setCheckBoxTermsOfService() {
        checkBoxTermsOfService.click();
    }

    public void setPayByCheck() {
        payByCheck.click();
    }

    public void setConfirmMyOrderButton(){
        confirmMyOrderButton.click();
    }

    public void setSuccessAlert() {
        Assert.assertEquals(true, successAlert.isDisplayed(), successAlert + " is not displayed");
    }

    public void setWarningEmailIsRequired() {
        Assert.assertEquals(true, warningEmailIsRequired.isDisplayed(), warningEmailIsRequired + " is not displayed");
    }

    public void setWarningPasswordIsRequired() {
        Assert.assertEquals(true, warningPasswordIsRequired.isDisplayed(), warningPasswordIsRequired + " is not displayed");
    }

    public void setWarningWrongPassword() {
        Assert.assertEquals(true, warningWrongPassword.isDisplayed(), warningWrongPassword + " is not displayed");
    }

    public void setPopupWarningTermsOfService(){
        Assert.assertEquals(true, popupWarningTermsOfService.isDisplayed(), popupWarningTermsOfService + " is not displayed");
    }
}
