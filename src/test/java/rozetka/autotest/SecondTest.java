package rozetka.autotest;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import rozetka.autotest.pageObject.CategoryPage;
import rozetka.autotest.pageObject.MainPage;
import rozetka.autotest.support.Custom;
import rozetka.autotest.support.DB;

import java.beans.Statement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
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

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }

}
