package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DbPage {

    private SelenideElement dashboard = $("[data-test-id='dashboard']");

    public DbPage(){
        dashboard.shouldBe(visible).shouldHave(text("Личный кабинет"));
    }
}
