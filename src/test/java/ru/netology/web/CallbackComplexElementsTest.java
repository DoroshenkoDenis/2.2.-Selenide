package ru.netology.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.format.DateTimeFormatter.ofPattern;

public class CallbackComplexElementsTest {
    SelenideElement cityField = $("[data-test-id=city] .input__control");
    SelenideElement dateField = $("[data-test-id=date] .input__control");
    SelenideElement nameField = $("[data-test-id=name] .input__control");
    SelenideElement phoneField = $("[data-test-id=phone] .input__control");
    SelenideElement checkboxField = $("[data-test-id=agreement] .checkbox__box");
    SelenideElement buttonSentField = $(".button__text");
    SelenideElement notification = $(".notification__title");
    SelenideElement inputError = $((".input_invalid"));

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        Configuration.headless = true;
    }

    @Test
    public void shouldSent() {
        cityField.setValue("сп");
        cityField.$("menu-item").find(withText("Санкт-Петербург")).click();
    }



}

