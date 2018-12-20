package rozetka.autotest;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
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
    }

    @Test
    public void userCanLoginByUsername() throws IOException, InterruptedException {
        boolean sendEmailStatus;
        String products;

        open("https://rozetka.com.ua/ua/");

        if ($("main-page-sidebar a[href='https://rozetka.com.ua/tovary-dlya-doma/c2394287/']").isDisplayed()) {
            $("main-page-sidebar a[href='https://rozetka.com.ua/tovary-dlya-doma/c2394287/']").click();
        } else {
            $("li[menu_id='5300']").hover().click();

        }

        $("li[param='31761'] a.m-cat-l-i-title-link").click();
        $("li[param='78335'] a.m-cat-l-i-title-link").click();
        $("#title_page a[href='https://rozetka.com.ua/sredstva-dlya-stirki4632103/c4632103/']").click();
        if ($(".exponea-banner .exponea-close").isDisplayed()){
            $(".exponea-banner .exponea-close").click();
        }
        $("#title_page").shouldBe(text("Стиральные средства"));

        $(".g-i-more-link").shouldBe(visible).click(); // 2
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();// 3
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();// 4
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();// 5
        $$(".g-i-tile-i-title a").shouldHave(CollectionCondition.size(160));
        List priceRange = $$(By.xpath("//*[contains(@class, 'g-i-tile-i-title')] | //div[@class='g-price-uah']")).texts();

        for (int i=0; i<priceRange.size(); i++) {
            products = priceRange.get(i).toString();
            products = products.replace(" грн", "");
            products = products.replace(", ", " ");
            priceRange.set(i, products);
        }

        Object[] result = priceRange.toArray();
        String[][] resultMostPopular = Custom.getArrayProducts(result, true, 100, 300);


        try {
            DB.post(resultMostPopular);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }

}
