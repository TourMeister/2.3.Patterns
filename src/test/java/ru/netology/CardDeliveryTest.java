package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataGenerator.*;

public class CardDeliveryTest {

    private final String date = generateDate(3);
    private final String secondDate = generateDate(15);
    private final String expiredDate = generateDate(-15);
    private final String city = generateCity();
    private final String name = generateName();
    private final String phone = generatePhone();

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
    void shouldOrderNewDateCardDelivery() {
        $("[placeholder='Город']").setValue(city);
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(date);

        endOfInsert();

        $(withText(date)).
                shouldBe(visible, Duration.ofSeconds(15));


        $("[placeholder='Город']").setValue(city);
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(secondDate);

        endOfInsert();

        $(withText(secondDate)).
                shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldGetErrorFromDate() {
        $("[placeholder='Город']").setValue("Са");
        $(byText("Санкт-Петербург")).click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a");
        $("[placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue(expiredDate);

        endOfInsert();

        $(withText("Заказ на выбранную дату невозможен")).
                shouldBe(visible, Duration.ofSeconds(15));
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

    @Test
    void shouldTestNewMethods() {
        System.out.println(generateDate(4));  // done
        System.out.println(DataGenerator.generateCity()); // done
        System.out.println(generateName()); // done
        System.out.println(DataGenerator.generatePhone()); // done
    }
}

