package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {

    By shippingOptionLocator = By.id("p_n_free_shipping_eligible-title");
    By filteredLocator = By.id("a-autoid-0-announce");
    By select_a_filterLocator = By.id("s-result-sort-select_2");
    //By productNameLocator = By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']");



    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    public boolean isOnProductPage() {
        return isDisplayed(shippingOptionLocator);
    }
    public void filterProducts() {
        click(filteredLocator);
    }
//    public void filterSelection() {
//        click(select_a_filterLocator);
//    }
    public List<WebElement> getAllProducts(){
        return findAll(By.cssSelector(".a-size-base-plus.a-color-base.a-text-normal"));
    }


}
