import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test_Add_Product_to_Cart extends BaseTest {

    HomePage homePage ;
    ProductsPage productsPage ;
    ProductDetailPage productDetailPage ;

    @Test
    @Order(1)
    public void login() throws InterruptedException {
        driver.findElement(By.id("nav-link-accountList")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement email = driver.findElement(By.id("ap_email"));

        email.clear();
        email.sendKeys("your_mail");

        WebElement continueButton=driver.findElement(By.id("continue"));
        continueButton.click();
        Thread.sleep(1000);

        WebElement password=driver.findElement(By.id("ap_password"));
        password.clear();
        password.sendKeys("your_password");
        WebElement login=driver.findElement(By.id("signInSubmit"));
        login.click();

        System.out.println("Successfully signed in!");
    }

    @Test
    @Order(2)

    public void search_a_product() throws InterruptedException {
        System.out.println("Searching is started --->");

        driver.get("https://www.amazon.com.tr/?ref_=nav_ya_signin");
        Thread.sleep(1200);
        homePage = new HomePage(driver);

        //homePage.acceptCookies();
        productsPage = new ProductsPage(driver);
        homePage.searchBox().search("noise cancelling headphones");
        Assertions.assertTrue(productsPage.isOnProductPage() ,
                "Not on products page!");
        productsPage.filterProducts();
        //productsPage.filterSelection();
        driver.findElement(By.id("s-result-sort-select_2")).click();

    }
    @Test
    @Order(4)
    public void select_all_products() throws IOException, InterruptedException {
        productDetailPage = new ProductDetailPage(driver);

        Thread.sleep(8000);

        System.out.println("Program is getting results for writing to console & text.txt file--->");

        List<WebElement> elements = driver.findElements(By.cssSelector(".a-size-base-plus.a-color-base.a-text-normal"));

        File file = new File("test.txt");
        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fwriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fwriter);

        // WebElement element : elements

        for (int i = 0; i < 10; i++){
            System.out.println("---------");

            System.out.println(Integer.toString(i+1) + "- " + elements.get(i).getText());

            bWriter.write( Integer.toString(i+1) + "- " + elements.get(i).getText() + "\n");
        }

        bWriter.close();
        System.out.println("--- Write process is finished ---");
//        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(),
//                "Not on product detail page!");
    }

    @Test
    @Order(5)
    public void select_a_product() throws InterruptedException {
        Thread.sleep(8000);
        //productsPage.selectProduct();
        System.out.println("selection started.");
        Thread.sleep(4000);
        productsPage.getAllProducts().get(1).click();
        Thread.sleep(4000);

        /** Buradaki adımda:: filtreleme işlemini fiyatı düşük olandan yüksek olana sıralatamadım. Interactable element bulunamadı hatası aldım. **/
        /** İlerlediğim adımları tekrar denemek adına comment line olarak bırakıyorum**/

//        //WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[2]/div/div/form/span"));
//        //Select select = new Select(dropdown);
//        driver.findElement(By.xpath("//span[contains(@class,'a-dropdown-container')]")).click();
//        Thread.sleep(5000);
//        WebElement asd = driver.findElement(By.xpath("//*[@id=\"s-result-sort-select_1\"]"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(asd).click().build().perform();
//        //List<WebElement> xx=driver.findElements(By.xpath("//*[contains(@class,'a-dropdown-container')]"));
//        Thread.sleep(3000);
//        //System.out.println(xx.size());
//
////        JavascriptExecutor executor = (JavascriptExecutor) driver;
////        executor.executeScript("arguments[0].click();", xx);
//        //xx.get(0).click();
//        //select.selectByIndex(1);
//
////        WebElement selectLowtoHigh = driver.findElement(By.id("a-autoid-2-announce"));
////        selectLowtoHigh.click();
//        Thread.sleep(4000);
////        WebDriverWait wait = new WebDriverWait(driver, 10);
////        WebElement xx = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"s-result-sort-select_1\"]")));
////        xx.click();
////        //driver.findElement(By.xpath("//*[@id=\"s-result-sort-select_1\"]")).click();
////        Thread.sleep(5000);

        //driver.findElement(By.cssSelector("a-icon a-icon-star-medium a-star-medium-4")).click();

        //driver.findElement(By.id(".s-result-sort-select_1"));

        //Thread.sleep(10000);
        //driver.findElements(By.cssSelector("a-icon a-icon-star-medium a-star-medium-4"));

    }

    @Test
    @Order(6)
    public void add_product_to_cart(){
        System.out.println("Gets first product and adds to cart --->");
        productDetailPage.addToCart();
//        Assertions.assertTrue(homePage.isProductCountUp(),
//                "Product count did not increase!");
    }
    //
    @Test
    @Order(7)
    public void logOut() throws InterruptedException {
        System.out.println("Logout process started, mouse will hover the menu-->");
        WebElement highlightLogOut =driver.findElement(By.id("nav-link-accountList"));
        Actions action = new Actions(driver);
        action.moveToElement(highlightLogOut).perform();

        Thread.sleep(10000);
        WebElement nowLogOut =driver.findElement(By.xpath("//*[@id=\"nav-item-signout\"]/span"));
        nowLogOut.click();
        System.out.println("Logout completed");
    }


}
