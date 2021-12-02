package ru.netology.data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    String locale = "ru";
    private static String date;
    private static String city;
    private static String name;
    private static String phone;
    private static UserInfo user;

    private DataGenerator() {
    }

    static Faker faker = new Faker();

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        return Faker.instance(Locale.forLanguageTag(locale)).address().city();
    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        // TODO: убрать отчество из faker`a
//        RandomService sex = faker.random(["male, female"])

//        String fullName = faker.name().fullName();

        return Faker.instance(Locale.forLanguageTag(locale)).name().fullName();
    }

    public static String generatePhone(String locale) {
        return Faker.instance(Locale.forLanguageTag(locale)).phoneNumber().phoneNumber();
    }

    public static UserInfo generateUser(String locale) {
        // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
        // generateName(locale), generatePhone(locale)
        name = generateName(locale);
        city = generateCity(locale);
        phone = generatePhone(locale);

        return user;
    }


    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}

