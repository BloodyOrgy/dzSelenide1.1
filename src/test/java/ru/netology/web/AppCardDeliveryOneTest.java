
package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AppCardDeliveryOneTest {
    private String generateDate(long addDays, String pattern){
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    public void shouldBeSuccessfullyCompleted() {
        Selenide.open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Ульяновск");
        String planningDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Обмайкин Андрей");
        $("[data-test-id='phone'] input").setValue("+79372785728");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content").should(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));
    }
}
