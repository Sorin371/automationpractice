package Tests;

import Pages.CartPage;
import Pages.HomePage;
import Utils.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

public class CartTest extends BaseTest {

    @Test
    public void addAnItemToCart() {
        HomePage home =  PageFactory.initElements(driver, HomePage.class);
        CartPage cart = PageFactory.initElements(driver, CartPage.class);

        //Get the name and price of the item from the home page
        String itemNameHome = home.getItemName();
        Float itemPriceHome = home.getItemPrice();

        //Add first item to the cart
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        //Get the name and price of the item from the cart page
        String itemNameCart = cart.returnItemName();
        Float itemPriceCart = cart.returnItemPrice();

        //Check if the product has the same name on the Home and Cart pages
        compareTwoString(itemNameHome, itemNameCart);

        //Check if the product has the same price on the Home and Cart pages
        compareTwoFloat(itemPriceHome, itemPriceCart);
    }

    @Test
    public void deleteItemFromCart() {
        HomePage home =  PageFactory.initElements(driver, HomePage.class);
        CartPage cart = PageFactory.initElements(driver, CartPage.class);

        // Add first item to the cart
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        // Delete the first item from the cart
        cart.setDeleteFirstItem();

        // Check if the
        cart.setWarningCartIsEmpty();
    }

    @Test
    public void calculateTotalForOneItem(){
        CartPage cart = PageFactory.initElements(driver, CartPage.class);
        HomePage home =  PageFactory.initElements(driver, HomePage.class);

        // Add first item to the cart and go to Cart page
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        //Set quantity to 2
        cart.setQuantityItem("2");

        //Get price and total
        Float price = cart.returnItemPrice();
        float quantity = 2;
        Float total = cart.getTotalItem();

        System.out.println(price + " " +  quantity + " " + total );

        quantityItem(price, quantity, total);
    }

    @Test
    public void calculateTotalProducts() {
        CartPage cart = PageFactory.initElements(driver, CartPage.class);
        HomePage home = PageFactory.initElements(driver, HomePage.class);

        //Add first item to cart
        home.addFirstItemToCart();

        //Click on the continue shopping button
        home.setContinueShoppingButton();

        //Add second item to cart
        home.addSecondItemToCart();

        //Go to the cart page
        home.setProceedToCheckoutButton();

        //Calculate the total from each product
        Float eachProduct = cart.calculateAllItemsToCart();

        //Get total products from cart page
        Float totalProducts = cart.getTotalProducts();

        //Compare total products from the cart page and the total calculated on each product
        compareTwoFloat(eachProduct, totalProducts);
    }

    @Test
    public void calculateFinalTotal() {
        HomePage home =  PageFactory.initElements(driver, HomePage.class);
        CartPage cart = PageFactory.initElements(driver, CartPage.class);

        //Add first item to the cart
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        //Get Total Products, Total shipping, Tax and Final Total from the cart page
        Float totalProducts = cart.getTotalProducts();
        Float totalShipping = cart.getTotalShipping();
        Float totalTax = cart.getTotalTax();
        Float totalPrice = cart.getTotalPrice();

        //Check the final total after taxes
        taxCalculation(totalProducts, totalShipping, totalTax, totalPrice);
    }

    @Test
    public void sendAnOrder() {
        HomePage home =  PageFactory.initElements(driver, HomePage.class);
        CartPage cart = PageFactory.initElements(driver, CartPage.class);

        //Add first item to the cart
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutButton();

        //Login
        cart.login("aaa@aaa2.com", "test123");

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutAddressStep();

        //Click on checkbox from Terms of service
        cart.setCheckBoxTermsOfService();

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutShippingStep();

        //Select Pay by check
        cart.setPayByCheck();

        //Click on the I confirm my order button
        cart.setConfirmMyOrderButton();

        //Check that the success warning is displayed
        cart.setSuccessAlert();
    }

    @Test
    public void sendOrderWithoutLogin() {
        HomePage home =  PageFactory.initElements(driver, HomePage.class);
        CartPage cart = PageFactory.initElements(driver, CartPage.class);

        //Add first item to the cart
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutButton();

        //Login
        cart.login("", "");

        //Warning An email address required.
        cart.setWarningEmailIsRequired();
    }

    @Test
    public void sendOrderWithoutPassword() {
        HomePage home =  PageFactory.initElements(driver, HomePage.class);
        CartPage cart = PageFactory.initElements(driver, CartPage.class);

        //Add first item to the cart
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutButton();

        //Login
        cart.login("aaa@aaa2.com", "");

        //Warning An email address required.
        cart.setWarningPasswordIsRequired();
    }

    @Test
    public void sendOrderWithWrongPassword() {
        HomePage home =  PageFactory.initElements(driver, HomePage.class);
        CartPage cart = PageFactory.initElements(driver, CartPage.class);

        //Add first item to the cart
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutButton();

        //Login
        cart.login("aaa@aaa2.com", "123");

        //Warning An email address required.
        cart.setWarningWrongPassword();
    }

    @Test
    public void sendAnOrderWithoutCheckboxTermsOfService() {
        HomePage home =  PageFactory.initElements(driver, HomePage.class);
        CartPage cart = PageFactory.initElements(driver, CartPage.class);

        //Add first item to the cart
        home.addFirstItemToCart();

        //Click on the Proceed to checkout button
        home.setProceedToCheckoutButton();

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutButton();

        //Login
        cart.login("aaa@aaa2.com", "test123");

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutAddressStep();

        //Click on the Proceed to checkout button
        cart.setProceedToCheckoutShippingStep();

        //Check popup You must agree to the terms of service before continuing.
        cart.setPopupWarningTermsOfService();
    }
}