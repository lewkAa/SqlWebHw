package ru.netology.data;

import com.github.javafaker.Faker;
import ru.netology.mode.User;

public class DataHelper {
    private static final Faker FAKER = new Faker();

    private DataHelper() {
    }

    public static String genRndLogin() {
        return FAKER.name().username();
    }

    public static String genRndPass() {
        return FAKER.internet().password();
    }

    public static User getTestUser() {
        User validUser = SQLHelper.getUserByLogin("vasya");
        validUser.setPassword("qwerty123");
        return validUser;
    }

    public static String getRandomCode(){
        String code = FAKER.number().digits(6);
        return code;
    }
}