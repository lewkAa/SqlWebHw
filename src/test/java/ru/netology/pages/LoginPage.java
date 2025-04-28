package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import ru.netology.mode.User;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class LoginPage {

    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");
    private SelenideElement errorMsg = $("[data-test-id='error-notification']");

    public LoginPage() {
        loginField.shouldBe(visible);
        passwordField.shouldBe(visible);
    }

    private void  login(User user) {
        loginField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        loginButton.click();
    }

    public VerificationPage validLogin(User user){
        login(user);
        return new VerificationPage();
    }

    public void invalidLogin(User user){
        login(user);
        errorCheck();
        }

    private void errorCheck() {
        errorMsg.shouldBe(visible)
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }
}
