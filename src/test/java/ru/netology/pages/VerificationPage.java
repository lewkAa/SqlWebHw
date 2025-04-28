package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private SelenideElement errorMsg = $("[data-test-id='error-notification']");


    public VerificationPage() {
        codeField.shouldBe(visible);
        verifyButton.shouldBe(visible);
    }

    private void verify(String code) {
        codeField.setValue(code);
        verifyButton.click();
    }

    public DbPage validVerify(String code) {
        verify(code);
        return new DbPage();
    }

    public void invalidVerify(String code) {
        verify(code);
        errorCheck();
    }

    private void errorCheck() {
        errorMsg.shouldBe(visible).shouldHave(text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }
}


