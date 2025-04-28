package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    Faker faker = new Faker();
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private SelenideElement errorMsg = $("[data-test-id='error-notification']");

    public enum Type {VALID, INVALID}

    public VerificationPage() {
        codeField.shouldBe(visible);
        verifyButton.shouldBe(visible);
    }

    public DbPage Verify(String code, Type type) {
        codeField.setValue(code);
        verifyButton.click();

        if (type == Type.VALID) {
            return new DbPage();
        } else {
            errorMsg.shouldBe(visible)
                    .shouldHave(text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
        }
        return null;
    }
}

