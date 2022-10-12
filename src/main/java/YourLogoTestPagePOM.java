import org.openqa.selenium.By;

public class YourLogoTestPagePOM {
    public String pageUrl = "http://automationpractice.com/";
    public By signIn = new By.ByCssSelector(".header_user_info");
    public By signInEmail = new By.ByCssSelector("#email_create");
    public By createAccount = new By.ByCssSelector("#SubmitCreate");
    public By firstName = new By.ByCssSelector("#customer_firstname");
    public By lastName = new By.ByCssSelector("#customer_lastname");
    public By password = new By.ByCssSelector("#passwd");
    public By days = new By.ByCssSelector("#days");
    public By month = new By.ByCssSelector("#months");
    public By year = new By.ByCssSelector("#years");
    public By address = new By.ByCssSelector("#address1");
    public By city = new By.ByCssSelector("#city");
    public By state = new By.ByCssSelector("#id_state");
    public By zipCode = new By.ByCssSelector("#postcode");
    public By phoneNumber = new By.ByCssSelector("#phone_mobile");
    public By register = new By.ByCssSelector("#submitAccount");
    public By logInEmail = new By.ByCssSelector("#email");
    public By signInEmailPass = new By.ByCssSelector("#SubmitLogin");
    public By women = new By.ByCssSelector("#block_top_menu > ul > li:nth-child(1)");
    public By womenProducts = new By.ByCssSelector(".ajax_block_product .ajax_add_to_cart_button");
    public By cart = new By.ByCssSelector("[title=\"View my shopping cart\"]");
    public By continueShopping = new By.ByCssSelector(".continue");
    public By tops = new By.ByCssSelector(".subcategory-image");
    public By sizeSmall = new By.ByCssSelector("#layered_id_attribute_group_1");
    public By proceedToCheckout= new By.ByCssSelector("[title=\"Proceed to checkout\"]:nth-child(1)");
    public By addressProceedToCheckout= new By.ByCssSelector("[name=\"processAddress\"]");
    public By shippingProceedToCheckout= new By.ByCssSelector("[name=\"processCarrier\"]");
    public By termsAndCondition= new By.ByCssSelector("#uniform-cgv");
    public By totalProduct= new By.ByCssSelector("#total_product");
    public By totalShipping= new By.ByCssSelector("#total_shipping");
    public By total= new By.ByCssSelector("#total_price_container");
    public By bankInfo = new By.ByCssSelector(".bankwire");
    public By  confirmMyOrder = new By.ByCssSelector("#cart_navigation .button-medium");

}
