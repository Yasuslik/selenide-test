package rozetka.autotest.pageObject;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CategoryPage extends MainPage {

    private final SelenideElement moreProductsButton = $(".g-i-more-link");
    private final SelenideElement promoBannerCloseButton = $(".exponea-banner .exponea-close");
    private final ElementsCollection productsNameText = $$(By.xpath("//*[contains(@class, 'g-i-tile-i-title')]"));
    private final ElementsCollection productsNameAndPriceElements = $$(By.xpath("//*[contains(@class, 'g-i-tile-i-title')] | //div[@class='g-price-uah']"));
    private final ElementsCollection getMostPopularProductNameAndPriceElements = $$(By.xpath("//*[contains(@class, 'g-tag-icon-small-popularity')]/../../..//*[contains(@class, 'g-i-tile-i-title')] | //*[contains(@class, 'g-tag-icon-small-popularity')]/../../..//div[@class='g-price-uah']"));
    private final ElementsCollection productsBoxElements = $$(".g-i-tile-i-box");



    public CategoryPage getMoreProducts(){
        moreProductsButton.shouldBe(visible).shouldNotBe(attribute("class", "run-animation")).click();
        return this;
    }

    public CategoryPage closePromoBanner(){
        if (promoBannerCloseButton.isDisplayed()){
            promoBannerCloseButton.click();
        }
        return this;
    }

    public CategoryPage getMoreProductsList(int morePage) {
        this.closePromoBanner();
        for (int i = 0; i<morePage; i++) {
            this.getMoreProducts();
        }
        return this;
    }

    public List getProductsName(int countProductPage){
        int countProducts = countProductPage * 32;
        productsBoxElements.shouldHave(CollectionCondition.size(countProducts));
        return productsNameText.texts();
    }

    public List getProductNameAndPrice(int countProductPage) {
        int countProducts = countProductPage * 32;
        productsBoxElements.shouldHave(CollectionCondition.size(countProducts));
        return productsNameAndPriceElements.texts();
    }

    public List getMostPopularProductNameAndPrice(int countProductPage) {
        int countProducts = countProductPage * 32;
        productsBoxElements.shouldHave(CollectionCondition.size(countProducts));
        return getMostPopularProductNameAndPriceElements.texts();
    }


}
