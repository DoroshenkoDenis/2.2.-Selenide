package ru.netology.web;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.format.DateTimeFormatter.ofPattern;

public class CallbackComplexElementsTest {
    int dateStep = 7;
    LocalDate currentDate = LocalDate.now();
    LocalDate dateOfDelivery = LocalDate.now().plusDays(dateStep);

    String twoLettersForSearch = "сп";
    String city = "Ставрополь";
    String user = "Иван Петров";
    String tel = "+79000000000";

    SelenideElement selectedCity = $$(".menu-item").find(exactText(city));
    SelenideElement cityField = $("[data-test-id=city] .input__control");
    SelenideElement nameField = $("[data-test-id=name] .input__control");
    SelenideElement phoneField = $("[data-test-id=phone] .input__control");
    SelenideElement checkboxField = $("[data-test-id=agreement] .checkbox__box");
    SelenideElement buttonSentField = $(".button__text");
    SelenideElement notification = $(".notification__title");
    SelenideElement calendar = $(".input__icon");
    ElementsCollection dateList = $$("td.calendar__day");
    ElementsCollection cityList = $$(".menu-item");

    @BeforeEach
    void setUp() {
//        Configuration.headless = true;
        open("http://localhost:9999");
    }

    //    Выбор даты на неделю вперёд (начиная от текущей даты) через инструмент календаря
    void setDateStepSevenDays() {
        calendar.click();
        if (currentDate.getMonthValue() != dateOfDelivery.getMonthValue()) {
            $("[data-step='1'").click();
        }
        dateList.find(text(dateOfDelivery.format(ofPattern("d")))).click();
    }

    //    Ввод 2 букв в поле город, после чего выбор нужного города из выпадающего списка
    @Test
    public void shouldSentUseComplexElements() {
        cityField.setValue(twoLettersForSearch);
        selectedCity.click();
        setDateStepSevenDays();
        nameField.setValue(user);
        phoneField.setValue(tel);
        checkboxField.click();
        buttonSentField.click();
        notification.shouldBe(visible, Duration.ofSeconds(15));
    }

    //    (дополнительно) Ввод 2 букв в поле город, после чего проверка списка городов из выпадающего списка
    @Test
    public void shouldFindAllCityByTwoLetters() {
        cityField.setValue("сп");
        cityList.shouldHave(CollectionCondition.containTexts("Санкт-Петербург", "Севастополь", "Симферополь", "Ставрополь"));
        System.out.println(cityList.texts());
    }

}

