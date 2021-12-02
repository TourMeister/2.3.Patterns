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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate date1 = LocalDate.now().plusDays(shift);
        date = formatter.format(date1);

        return date;
    }

    public static String generateCity(String locale) {
        return Faker.instance(Locale.forLanguageTag(locale)).address().city();
//        return faker.address().city();
    }

    public static String generateName(String locale) {
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        // TODO: сделать генерацию только имени и фамилии по полу
//        RandomService sex = faker.random(["male, female"])

//        String fullName = faker.name().fullName();

        return Faker.instance(Locale.forLanguageTag(locale)).name().fullName();
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        return Faker.instance(Locale.forLanguageTag(locale)).phoneNumber().phoneNumber();
    }

    public static UserInfo generateUser(String locale) {
        // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
        // generateName(locale), generatePhone(locale)
        name = generateName(locale);
        city = generateCity(locale);

        return user;
    }


    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}

