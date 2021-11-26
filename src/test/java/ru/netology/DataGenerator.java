package ru.netology;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}

    public static class Registration {
        private Registration() {}

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByCardInfo(
                    faker.name().firstName(),
                    faker.phoneNumber(),
                    faker.finance().creditCard(CreditCardType.MASTERCARD),
                    LocalDate.now().plusYears(1)
            );

        }
    }
}
