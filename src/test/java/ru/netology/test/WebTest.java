package ru.netology.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
 class WebTest {

    @AfterAll
    static void cleanDatabase() {
        SQLHelper.cleanDatabase();
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Успешный логин и верификация")
    void shouldLoginAndVerify() {
        var user = DataHelper.getTestUser();
        log.info("Получаем id пользователя из БД: " + user.getId());
        log.info("Получаем логин пользователя из БД: " + user.getLogin());
        log.info("Получаем пароль пользователя из БД: " + user.getPassword());
        log.info("Получаем статус пользователя из БД: " + user.getStatus());
        var logPage = new LoginPage();
        var veriPage = logPage.validLogin(user);
        var veriCode = SQLHelper.getCodeByUid(user.getId());
        log.info("Получаем код верификации из БД: " + veriCode);
        veriPage.validVerify(veriCode);
    }

    @Test
    @DisplayName("Успешный логин и ошибка верификации")
    void shouldLoginButNotVerify() {
        var user = DataHelper.getTestUser();
        var logPage = new LoginPage();
        var veriPage = logPage.validLogin(user);
        var veriCode = DataHelper.getRandomCode();
        veriPage.invalidVerify(veriCode);
    }

    @Test
    @DisplayName("Логин с невалидными данными")
    void shouldNotLogin() {
        var user = DataHelper.getBadUser();
        var logPage = new LoginPage();
        logPage.invalidLogin(user);
    }

}
