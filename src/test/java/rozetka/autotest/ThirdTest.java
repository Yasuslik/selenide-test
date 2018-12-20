package rozetka.autotest;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import rozetka.autotest.support.Custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    }

    @Test
    public void userCanLoginByUsername() throws IOException {
        boolean sendEmailStatus;

        open("https://rozetka.com.ua/ua/");
        if ($("main-page-sidebar a[href='https://rozetka.com.ua/telefony-tv-i-ehlektronika/c4627949/']").isDisplayed()) {
            $("main-page-sidebar a[href='https://rozetka.com.ua/telefony-tv-i-ehlektronika/c4627949/']").click();
        } else {
            $("li[menu_id='3361']").hover().click();
        }
        $(".pab-table p a[href='https://rozetka.com.ua/telefony/c4627900/']").click();
        $("a[href='https://rozetka.com.ua/mobile-phones/c80003/filter/preset=smartfon/']").click();
        if ($(".exponea-banner .exponea-close").isDisplayed()){
            $(".exponea-banner .exponea-close").click();
        }
        $(".g-i-more-link").shouldBe(visible).click(); // 2
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();// 3
        Object[] mostPopular = $$(By.xpath("//*[contains(@class, 'g-tag-icon-small-popularity')]/../../..//*[contains(@class, 'g-i-tile-i-title')] | //*[contains(@class, 'g-tag-icon-small-popularity')]/../../..//div[@class='g-price-uah']")).texts().toArray();
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();// 4
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();// 5
        $$(".g-i-tile-i-title a").shouldHave(CollectionCondition.size(160));

        Object[] priceRange = $$(By.xpath("//*[contains(@class, 'g-i-tile-i-title')] | //div[@class='g-price-uah']")).texts().toArray();

        String[][] resultMostPopular = Custom.getArrayProducts(mostPopular, false, 0, 0);
        String[][] resultPriseRange = Custom.getArrayProducts(priceRange, true, 3000, 6000);

        Custom.writeToExcel(resultPriseRange, resultMostPopular);
        sendEmailStatus = Custom.sendEmail("workbook.xlsx");

        Assert.assertEquals(true, sendEmailStatus);
    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }
}
