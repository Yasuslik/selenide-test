package rozetka.autotest;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;
import org.openqa.selenium.By;

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
    public void userCanLoginByUsername() {
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
        List mostPopular = $$(By.xpath("//*[contains(@class, 'g-tag-icon-small-popularity')]/../../..//*[contains(@class, 'g-i-tile-i-title')] | //*[contains(@class, 'g-tag-icon-small-popularity')]/../../..//div[@class='g-price-uah']")).texts();
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();// 4
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();// 5
        $$(".g-i-tile-i-title a").shouldHave(CollectionCondition.size(160));
        List priceRange = $$(By.xpath("//*[contains(@class, 'g-tag-icon-small-popularity')]/../../..//*[contains(@class, 'g-i-tile-i-title')] | //*[contains(@class, 'g-tag-icon-small-popularity')]/../../..//div[@class='g-price-uah']")).texts();

        System.out.println(mostPopular);
    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }
}
