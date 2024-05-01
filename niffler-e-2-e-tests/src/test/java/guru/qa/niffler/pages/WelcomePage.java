package guru.qa.niffler.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage {

    private final SelenideElement welcomeBlock = $(".main__links");
    private final SelenideElement loginButton = welcomeBlock.$(byText("Login"));


    public void clickLoginButton() {
        loginButton.click();
    }



}