package ru.netology.web;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import java.time.*;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static java.time.format.DateTimeFormatter.*;

public class CallbackTest {
    String date = LocalDate.now().plusDays(4).format(ofPattern("dd.MM.yyyy"));
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
        Configuration.headless = true;
        open("http://localhost:9999");
        //----- Clear date field -------------------------------
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

    //--------- Empty field tests -------------------------------
    @Test
    public void shouldWarnIfAllFieldsEmpty() {
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfEmptyCityNameField() {
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfEmptyUserNameField() {
        cityField.setValue("Казань");
        dateField.setValue(date);
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfDateNotChanged() {
        cityField.setValue("Казань");
        nameField.setValue("Иван Петров");
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfEmptyTelephoneField() {
        cityField.setValue("Казань");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfEmptyCheckbox() {
        cityField.setValue("Казань");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+79000000000");
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    //------ Bad input -------------------------------
    @Test
    public void shouldWarnIfBadCityName1() {
        cityField.setValue("Тольятти");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadCityName2() {
        cityField.setValue("Kazan");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadDate1() {
        String date = LocalDate.now().plusDays(-2).format(ofPattern("dd.MM.yyyy"));
        cityField.setValue("Москва");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadDate2() {
        String date = LocalDate.now().plusDays(2).format(ofPattern("dd.MM.yyyy"));
        cityField.setValue("Москва");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadUserName() {
        cityField.setValue("Москва");
        dateField.setValue(date);
        nameField.setValue("Ivan Petrov");
        phoneField.setValue("+79000000000");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadTelephone1() {
        cityField.setValue("Москва");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+12345");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadTelephone2() {
        cityField.setValue("Москва");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+123456789012");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadTelephone3() {
        cityField.setValue("Москва");
        dateField.setValue(date);
        nameField.setValue("Иван Петров");
        phoneField.setValue("+abcdefghijk");
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

}

