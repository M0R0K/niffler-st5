package guru.qa.niffler.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement deleteSpendingButton = $(".spendings__bulk-actions button");
    private final ElementsCollection spendingRows = $(".spendings-table tbody").$$("tr");


    public SelenideElement findSpendingByDescription(String description) {
        return spendingRows.find(text(description));
    }

    public MainPage selectFirstSpendingCheckbox(SelenideElement spendingRow) {
        spendingRow.$$("td").first().scrollTo().click();
        return this;
    }

    public MainPage deleteSpending() {
        deleteSpendingButton.click();
        return this;
    }
    public void checkSpendingsTableEmpty() {
        spendingRows.shouldHave(size(0));
    }
}
