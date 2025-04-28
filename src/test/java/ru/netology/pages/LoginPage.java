package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import ru.netology.data.DataHelper;
import ru.netology.mode.User;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    Faker faker = new Faker();
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");
    private SelenideElement errorMsg = $("[data-test-id='error-notification']");

    public enum Type { VALID, INVALID }

    public VerificationPage Login(User user, Type type) {
        loginField.setValue(user.getLogin());
        passwordField.setValue(type == Type.VALID ? user.getPassword() : DataHelper.genRndPass());
        loginButton.click();

        if (type == Type.VALID) {
            return new VerificationPage();
        } else {
            errorMsg.shouldBe(visible)
                    .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
            return null;
        }
    }
}
