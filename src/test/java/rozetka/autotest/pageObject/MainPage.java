package rozetka.autotest.pageObject;


import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;


public class MainPage {

    private final SelenideElement PhonesTVAndElectronicsLink = $("main-page-sidebar a[href='https://rozetka.com.ua/telefony-tv-i-ehlektronika/c4627949/']");
    private final SelenideElement PhonesTVAndElectronicsTabletLink = $("li[menu_id='3361']");
    private final SelenideElement TelephonesLink = $(".pab-table p a[href='https://rozetka.com.ua/telefony/c4627900/']");
    private final SelenideElement SmartPhonesLink = $("a[href='https://rozetka.com.ua/mobile-phones/c80003/filter/preset=smartfon/']");

    private final SelenideElement HouseholdProductsLink = $("main-page-sidebar a[href='https://rozetka.com.ua/tovary-dlya-doma/c2394287/']");
    private final SelenideElement HouseholdProductsTabletLink = $("li[menu_id='5300']");
    private final SelenideElement HouseholdChemicalsLink = $("li[param='31761'] a.m-cat-l-i-title-link");
    private final SelenideElement ForWashingLink = $("li[param='78335'] a.m-cat-l-i-title-link");
    private final SelenideElement LaundryPowdersLink = $("#title_page a[href='https://rozetka.com.ua/sredstva-dlya-stirki4632103/c4632103/']");



    public MainPage open() {
        Selenide.open("/");
        $(".header__logo").shouldBe();
        return this;
    }

    public MainPage navigateToSmartphones(){

        if (PhonesTVAndElectronicsLink.isDisplayed()) {
            PhonesTVAndElectronicsLink.click();
        } else {
            PhonesTVAndElectronicsTabletLink.hover().click();
        }
        TelephonesLink.click();
        SmartPhonesLink.click();
        return this;
    }

    public MainPage navigateToLaundryPowders(){

        if (HouseholdProductsLink.isDisplayed()) {
            HouseholdProductsLink.click();
        } else {
            HouseholdProductsTabletLink.hover().click();
        }
        HouseholdChemicalsLink.click();
        ForWashingLink.click();
        LaundryPowdersLink.click();
        return this;
    }


}
