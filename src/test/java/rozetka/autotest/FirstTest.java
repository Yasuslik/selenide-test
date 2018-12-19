package rozetka.autotest;



import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import rozetka.autotest.support.Custom;

import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class FirstTest {

    @BeforeClass
    public static void setUp() {
        Configuration.browser="chrome";
        Configuration.startMaximized=true;
    }

    @Test
    public void userCanLoginByUsername() {
        boolean deleteFileStatus, writeToFileStatus, sendEmailStatus;

        open("https://rozetka.com.ua/ua/");
        if ($("main-page-sidebar a[href='https://rozetka.com.ua/telefony-tv-i-ehlektronika/c4627949/']").isDisplayed()) {
            $("main-page-sidebar a[href='https://rozetka.com.ua/telefony-tv-i-ehlektronika/c4627949/']").click();
        } else {
            $("li[menu_id='3361']").hover().click();
        }
        $(".pab-table p a[href='https://rozetka.com.ua/telefony/c4627900/']").click();
        $("a[href='https://rozetka.com.ua/mobile-phones/c80003/filter/preset=smartfon/']").click();
        $(".g-i-more-link").shouldBe(visible).click();
        $(".g-i-more-link").shouldNotBe(attribute("class", "run-animation")).click();
        List firstPage = $$(".g-i-tile-i-title a").shouldHave(CollectionCondition.size(96)).texts();

        deleteFileStatus = Custom.deleteFile();
        writeToFileStatus = Custom.writeToFile(firstPage);
        sendEmailStatus = Custom.sendEmail();

        Assert.assertEquals(true, deleteFileStatus);
        Assert.assertEquals(true, writeToFileStatus);
        Assert.assertEquals(true, sendEmailStatus);
    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }

}