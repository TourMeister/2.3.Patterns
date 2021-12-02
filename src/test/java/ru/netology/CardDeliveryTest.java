package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.manager.TimeManager;
import ru.netology.data.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

// TODO Важно: в этот раз вы не должны хардкодить данные прямо в тест!
// TODO Используйте Faker, Lombok, Data-классы (для группировки нужных полей) и утилитный класс-генератор данных* - см. пример в презентации.
//
// Утилитными называют классы, у которых приватный конструктор и статичные методы.
//
// Обратите внимание, что Faker может генерировать не совсем в нужном для вас формате.

public class CardDeliveryTest {
    TimeManager manager = new TimeManager();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @BeforeEach
    void setupTest() {
        open("http://localhost:9999");
    }

    @Test
    void shouldOrderCardDeliveryByText() {
        LocalDate date = LocalDate.now().plusDays(3);
        String dateToInput = formatter.format(date);

        $("[placeholder='Город']").setValue("Мурманск");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(dateToInput);

        manager.endOfInsert();

        $(withText(dateToInput)).
                shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldOrderCardDeliveryByText2() {
        LocalDate date = LocalDate.now().plusDays(7);
        String dateToInput = formatter.format(date);

        $("[placeholder='Город']").setValue("Мурманск");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(dateToInput);

        manager.endOfInsert();

        $(withText(dateToInput)).
                shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSelectFromList() {
        $("[placeholder='Город']").setValue("Са");
        $(byText("Санкт-Петербург")).click();
        $("[placeholder='Дата встречи']").click(); // открытие календаря
        LocalDate date = LocalDate.now().plusDays(7);

        manager.calendarSelector(date);
        manager.endOfInsert();

        String notificationContent = formatter.format(date);

        $(withText(notificationContent)).
                shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestNewMethods() {
        System.out.println(DataGenerator.generateDate(4));  // done
        System.out.println(DataGenerator.generateCity("ru")); // done
    }


    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        String firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        String secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе
    }
}

