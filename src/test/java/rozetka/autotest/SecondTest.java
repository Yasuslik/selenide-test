package rozetka.autotest;


import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.autotest.pageObject.CategoryPage;
import rozetka.autotest.pageObject.MainPage;
import rozetka.autotest.support.Custom;
import rozetka.autotest.support.DB;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class SecondTest {

    @BeforeClass
    public static void setUp() {
        Configuration.browser="chrome";
        Configuration.startMaximized=true;
        Configuration.baseUrl = "https://rozetka.com.ua/";

    }

    @Test
    public void userCanLoginByUsername() throws IOException, InterruptedException {
        MainPage mainPage = new MainPage();
        CategoryPage categoryPage = new CategoryPage();

        mainPage.open();
        mainPage.navigateToLaundryPowders();

        categoryPage.getMoreProductsList(5);
        List productsList = categoryPage.getProductNameAndPrice(5);

        List priceRange = Custom.getArrayProductsTest(productsList, true, 100, 300);

        try {
            DB.post(priceRange);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@AfterClass
    public static void logout() {
        closeWebDriver();
    }*/

}
