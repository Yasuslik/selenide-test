package rozetka.autotest;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import rozetka.autotest.pageObject.CategoryPage;
import rozetka.autotest.pageObject.MainPage;
import rozetka.autotest.support.Custom;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class ThirdTest {

    @BeforeClass
    public static void setUp() {
        Configuration.browser="chrome";
        Configuration.startMaximized=true;
        Configuration.baseUrl = "https://rozetka.com.ua/";
    }

    @Test
    public void userCanLoginByUsername() throws IOException {
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

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }
}
