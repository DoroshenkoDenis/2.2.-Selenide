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
    String city = "Казань";
    String badCity1 = "Тольятти";
    String badCity2 = "Kazan";

    int dateStep = 4;
    String date = LocalDate.now().plusDays(dateStep).format(ofPattern("dd.MM.yyyy"));
    int badDateStep1 = -2;
    String badDate1 = LocalDate.now().plusDays(badDateStep1).format(ofPattern("dd.MM.yyyy"));
    int badDateStep2 = 2;
    String badDate2 = LocalDate.now().plusDays(badDateStep2).format(ofPattern("dd.MM.yyyy"));

    String user = "Иван Петров";
    String badUser = "Ivan Petrov";

    String tel = "+70000000000";
    String badTel1 = "+12345";
    String badTel2 = "+123456789012";
    String badTel3 = "+abcdefghijk";

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
        dateField.sendKeys(Keys.CONTROL, "a", Keys.DELETE);
    }

    @Test
    public void shouldSent() {
        cityField.setValue(city);
        dateField.setValue(date);
        nameField.setValue(user);
        phoneField.setValue(tel);
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
        nameField.setValue(user);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfEmptyUserNameField() {
        cityField.setValue(city);
        dateField.setValue(date);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfDateNotChanged() {
        cityField.setValue(city);
        nameField.setValue(user);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfEmptyTelephoneField() {
        cityField.setValue(city);
        dateField.setValue(date);
        nameField.setValue(user);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfEmptyCheckbox() {
        cityField.setValue(city);
        dateField.setValue(date);
        nameField.setValue(user);
        phoneField.setValue(tel);
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    //------ Bad input -------------------------------
    @Test
    public void shouldWarnIfBadCityName1() {
        cityField.setValue(badCity1);
        dateField.setValue(date);
        nameField.setValue(user);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadCityName2() {
        cityField.setValue(badCity2);
        dateField.setValue(date);
        nameField.setValue(user);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadDate1() {
        cityField.setValue(city);
        dateField.setValue(badDate1);
        nameField.setValue(user);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadDate2() {
        cityField.setValue(city);
        dateField.setValue(badDate2);
        nameField.setValue(user);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadUserName() {
        cityField.setValue(city);
        dateField.setValue(date);
        nameField.setValue(badUser);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadTelephone1() {
        cityField.setValue(city);
        dateField.setValue(date);
        nameField.setValue(user);
        phoneField.setValue(badTel1);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadTelephone2() {
        cityField.setValue(city);
        dateField.setValue(date);
        nameField.setValue(user);
        phoneField.setValue(badTel2);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

    @Test
    public void shouldWarnIfBadTelephone3() {
        cityField.setValue(city);
        dateField.setValue(date);
        nameField.setValue(user);
        phoneField.setValue(badTel3);
        checkboxField.click();
        buttonSentField.click();
        inputError.shouldBe(exist);
    }

}

