package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import org.junit.Assert;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class simpleFunctionsOfSite {
    private SelenideElement loginField = $(By.id("Login"));
    private SelenideElement passwordField = $(By.id("Password"));
    private SelenideElement loginButton = $(By.id("loginBtn"));
    private SelenideElement username = $(By.id("username"));
    private SelenideElement searchField = $(By.name("q"));
    private ElementsCollection menu = $$(By.className("menu"));
    private SelenideElement style = $(By.id("style"));

    @Step("User type login = {0} and password = {1}")
    private void typeLoginAndPassword(String login, String password){
        loginField.setValue(login);
        passwordField.setValue(password);
    }

    @Step("User click on login button")
    private void clickOnLoginButton(){
        loginButton.click();
    }

    @Step("User see that he authorized")
    private void isUserAuthorized(){
        username.waitUntil(visible, 5000);
        Assert.assertEquals(username.getText(), "Pyr0Joke");
    }

    @Step("User type on search field {0}")
    public void typeTextOnSearchField(String text){
        searchField.setValue(text).pressEnter();
    }

    public void login(String login, String password){
        typeLoginAndPassword(login, password);
        clickOnLoginButton();
        isUserAuthorized();
    }

    @Step("User click on {0} element of menu")
    public void clickOnElementOfMenu(String text){
        $$(By.linkText(text)).filterBy(attribute("target","")).get(0).click();
        String path = $$(By.linkText(text)).filterBy(attribute("target","")).get(0).getAttribute("href");
        Assert.assertEquals(WebDriverRunner.url(), path);
    }

    @Step("User click on {0} element of menu")
    public void moveToElementOfMenu(String section, String subSection){
        SelenideElement element = $$(By.linkText(section)).filterBy(attribute("target","")).get(0);
        menu.filterBy(attribute("href", element.getAttribute("href"))).get(0).hover();
        String path = $(By.linkText(subSection)).getAttribute("href");
        $(By.linkText(subSection)).click();
        Assert.assertEquals(WebDriverRunner.url(), path);
    }

    @Step("User choice {0} music style")
    public void selectItemFromDropDownMenu(String item){
        style.selectOptionContainingText(item);
        Assert.assertEquals(style.getText(), item);
    }
}
