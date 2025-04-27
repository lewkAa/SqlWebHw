package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import ru.netology.mode.User;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    Faker faker = new Faker();
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");
    private SelenideElement errorMsg = $("[data-test-id='error-notification']");

    public VerificationPage validLogin(User user) {
        loginField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void inValidLogin(User user) {
        loginField.setValue(user.getLogin());
        passwordField.setValue(faker.internet().password(8, 12, true, true, true));
        loginButton.click();
        errorMsg.shouldBe(visible).shouldHave(exactText("Ошибка\n" +
                "Ошибка! Неверно указан логин или пароль"));
    }
}
