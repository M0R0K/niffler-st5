package guru.qa.niffler.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.jupiter.annotation.GenerateCategory;
import guru.qa.niffler.jupiter.annotation.GenerateSpend;
import guru.qa.niffler.jupiter.extension.CategoryExtension;
import guru.qa.niffler.jupiter.extension.SpendExtension;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.pages.LoginPage;
import guru.qa.niffler.pages.MainPage;
import guru.qa.niffler.pages.WelcomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;


@ExtendWith({CategoryExtension.class, SpendExtension.class})
public class SpendingTest {
    private final WelcomePage welcomePage = new WelcomePage();
    private final LoginPage loginPage = new LoginPage();
    private final MainPage mainPage = new MainPage();


    static {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        Configuration.browserCapabilities = chromeOptions;
    }

    @BeforeEach
    void doLogin() {
        Selenide.open("http://127.0.0.1:3000/main");
        welcomePage.clickLoginButton();
        loginPage.setUsername("test")
                .setPassword("123456")
                .clickSubmitButton();
    }

    @GenerateCategory(category = "Обучение4",
            username = "test")


    @GenerateSpend(
            description = "QA.GURU Advanced 5",
            amount = 65000.00,
            currency = CurrencyValues.RUB
    )
    @Test
    void spendingShouldBeDeletedAfterTableAction(SpendJson spendJson) {
        SelenideElement rowWithSpending = mainPage.findSpendingByDescription(spendJson.description());
        mainPage.selectFirstSpendingCheckbox(rowWithSpending)
                .deleteSpending()
                .checkSpendingsTableEmpty();
    }
}