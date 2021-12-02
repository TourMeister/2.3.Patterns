package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;
import ru.netology.manager.TimeManager;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataGenerator.*;

// TODO Важно: в этот раз вы не должны хардкодить данные прямо в тест!
// TODO Используйте Faker, Lombok, Data-классы (для группировки нужных полей) и утилитный класс-генератор данных* - см. пример в презентации.
//
// Утилитными называют классы, у которых приватный конструктор и статичные методы.
//
// Обратите внимание, что Faker может генерировать не совсем в нужном для вас формате.

public class CardDeliveryTest {
    TimeManager manager = new TimeManager();
    String locale = "ru";

    private String date = generateDate(3);
    private String secondDate = generateDate(15);
    private String city = generateCity();
    private String name = generateName();
    private String phone = generatePhone();

    @BeforeEach
    void setupTest() {
        open("http://localhost:9999");
    }

    @Test
    void shouldOrderCardDelivery() {
         $("[placeholder='Город']").setValue(city);
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(date);

        endOfInsert();

        $(withText(date)).
                shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldOrderNewCardDelivery() {

        $("[placeholder='Город']").setValue("Мурманск");
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(secondDate);

        endOfInsert();

        $(withText(secondDate)).
                shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSelectFromList() {
        $("[placeholder='Город']").setValue("Са");
        $(byText("Санкт-Петербург")).click();
        $("[placeholder='Дата встречи']").click(); // открытие календаря
        LocalDate date = LocalDate.now().plusDays(7);

        calendarSelector(date);
        endOfInsert();

        $(withText(String.valueOf(date))).
                shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestNewMethods() {
        System.out.println(generateDate(4));  // done
        System.out.println(DataGenerator.generateCity()); // done
        System.out.println(generateName()); // done
        System.out.println(DataGenerator.generatePhone()); // done
    }

    private void calendarSelector(LocalDate date) {
        int currentMonth = LocalDate.now().getMonthValue();
        int month = date.getMonthValue();

        if (!Objects.equals(month, currentMonth)) {
            $("[data-step='1']").click();
        }
        String theRightDay = String.valueOf(date.getDayOfMonth());
        $$("[role=gridcell]").find(exactText(theRightDay)).click();
    }

    private void endOfInsert() {
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[class='checkbox__box']").click();
        $(withText("Запланировать")).click();

        /*В случае повторной записи на выбранную дату выполняется следущий код*/
        if ($("[data-test-id='replan-notification']").isDisplayed()) {
            $$("button").find((exactText("Перепланировать"))).click();
        }

        printData();
    }

    private void printData() {
        System.out.println(city);
        System.out.println(date);
        System.out.println(secondDate);
        System.out.println(name);
        System.out.println(phone);
    }
}

