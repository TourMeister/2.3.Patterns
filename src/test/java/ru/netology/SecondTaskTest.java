package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.manager.TimeManager;

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

public class SecondTaskTest {
    TimeManager manager = new TimeManager();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @BeforeEach
    void setupTest() {
        open("http://localhost:9999");
    }

    @Test
    void shouldOrderCardDeliveryByText() {
        LocalDate date = LocalDate.now().plusDays(7);
        String dateToInput = formatter.format(date);

        $("[placeholder='Город']").setValue("Мурманск");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(dateToInput);

        manager.endOfInsert();
        manager.replan();

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
        manager.replan();

        String notificationContent = formatter.format(date);

        $(withText(notificationContent)).
                shouldBe(visible, Duration.ofSeconds(15));
    }
}