package ru.netology.web;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static java.time.format.DateTimeFormatter.*;

public class CallbackTest {
    String date = LocalDate.now().plusDays(6).format(ofPattern("dd.MM.yyyy"));
    SelenideElement cityField = $("[data-test-id=city] .input__control");
    SelenideElement dateField = $("[data-test-id=date] .input__control");
    SelenideElement nameField = $("[data-test-id=name] .input__control");
    SelenideElement phoneField = $("[data-test-id=phone] .input__control");
    SelenideElement checkboxField = $("[data-test-id=agreement] .checkbox__box");
    SelenideElement buttonSentField = $(".button__text");
    SelenideElement notification = $(".notification__title");

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
//        clear date field
        dateField.sendKeys(Keys.CONTROL, "a");
        dateField.sendKeys(Keys.DELETE);
    }

    @Test
    public void shouldSent() {
        cityField.setValue("Казань");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        notification.shouldBe(visible, Duration.ofSeconds(15));
    }
}

