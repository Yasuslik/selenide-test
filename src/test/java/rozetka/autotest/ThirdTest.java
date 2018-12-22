package rozetka.autotest;

import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import rozetka.autotest.pageObject.CategoryPage;
import rozetka.autotest.pageObject.MainPage;
import rozetka.autotest.support.Custom;

import java.io.IOException;
import java.util.List;



public class ThirdTest {

    @BeforeClass
    public static void setUp() {
        Configuration.browser="chrome";
        Configuration.browserSize = "1980x1080";
        Configuration.baseUrl = "https://rozetka.com.ua/";
    }

    @Test
    public void tryToTest() throws IOException {
        boolean sendEmailStatus;

        MainPage mainPage = new MainPage();
        CategoryPage categoryPage = new CategoryPage();

        mainPage.open();
        mainPage.navigateToSmartphones();

        categoryPage.getMoreProductsList(2);
        List mostPopularProducts = categoryPage.getMostPopularProductNameAndPrice(3);

        categoryPage.getMoreProductsList(2);
        List productsList = categoryPage.getProductNameAndPrice(5);

        List resultMostPopular = Custom.getArrayProductsTest(mostPopularProducts, false, 0, 0);
        List resultPriseRange = Custom.getArrayProductsTest(productsList, true, 3000, 6000);

        Custom.writeToExcel(resultPriseRange, resultMostPopular);
        sendEmailStatus = Custom.sendEmail("workbook.xlsx");

        Assert.assertEquals(true, sendEmailStatus);
    }
}
