package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    Faker faker = new Faker();
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private SelenideElement errorMsg = $("[data-test-id='error-notification']");

    public VerificationPage() {
        codeField.shouldBe(visible);
        verifyButton.shouldBe(visible);
    }

    public void validVerify( String code) {
        codeField.setValue(code);
        verifyButton.click();

    }

    public void inValidVerify() {
        codeField.setValue(faker.number().digits(6));
        verifyButton.click();
        errorMsg.shouldBe(visible).shouldHave(exactText("Ошибка\n" +
                "Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }

}

