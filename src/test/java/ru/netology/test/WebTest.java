package ru.netology.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class WebTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void cleanDatabase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Успешный логин и верификация")
    void shouldLoginAndVerify() {
        var user = SQLHelper.getUserByLogin("vasya");
        user.setPassword("qwerty123");
        log.info("Получаем id пользователя из БД: " + user.getId());
        log.info("Получаем логин пользователя из БД: " + user.getLogin());
        log.info("Получаем пароль пользователя из БД: " + user.getPassword());
        log.info("Получаем статус пользователя из БД: " + user.getStatus());
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(user);
        var veriCode = SQLHelper.getCodeByUid(String.valueOf(user.getId()));
        log.info("Получаем код верификации из БД: " + veriCode);
        verificationPage.validVerify(veriCode);
    }

    @Test
    @DisplayName("Успешный логин и ошибка верификации")
    void shouldLoginButNotVerify() {
        var user = SQLHelper.getUserByLogin("vasya");
        user.setPassword("qwerty123");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(user);
        verificationPage.inValidVerify();
    }

    @Test
    @DisplayName("Логин с невалидными данными")
    void shouldNotLogin() {
        var user = SQLHelper.getUserByLogin("vasya");
        log.info("Получаем id пользователя из БД: " + user.getId());
        log.info("Получаем логин пользователя из БД: " + user.getLogin());
        log.info("Получаем пароль пользователя из БД: " + user.getPassword());
        log.info("Получаем статус пользователя из БД: " + user.getStatus());
        var loginPage = new LoginPage();
        loginPage.inValidLogin(user);
    }

}
