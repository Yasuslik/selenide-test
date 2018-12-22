package rozetka.autotest;


import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rozetka.autotest.pageObject.CategoryPage;
import rozetka.autotest.pageObject.MainPage;
import rozetka.autotest.support.Custom;
import rozetka.autotest.support.DB;

import java.io.IOException;
import java.util.List;


public class SecondTest {

    @BeforeClass
    public static void setUp() {
        Configuration.browser="chrome";
        Configuration.browserSize = "1980x1080";
        Configuration.baseUrl = "https://rozetka.com.ua/";
    }

    @Test
    public void tryToTest() throws IOException, InterruptedException {
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
}
