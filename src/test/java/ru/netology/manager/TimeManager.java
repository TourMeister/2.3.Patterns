package ru.netology.manager;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TimeManager {
//    String[] date;
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public void calendarSelector(LocalDate date) {
        int currentMonth = LocalDate.now().getMonthValue();
        int month = date.getMonthValue();

        if (!Objects.equals(month, currentMonth)) {
            $("[data-step='1']").click();
        }
        String theRightDay = String.valueOf(date.getDayOfMonth());
        $$("[role=gridcell]").find(exactText(theRightDay)).click();
    }

    public void endOfInsert() {
        $("[name='name']").setValue("Пореченков Михаил");
        $("[name='phone']").setValue("+79009009988");
        $("[class='checkbox__box']").click();
        $(withText("Забронировать")).click();
    }

}
