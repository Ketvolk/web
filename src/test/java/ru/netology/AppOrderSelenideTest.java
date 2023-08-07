package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class AppOrderSelenideTest {

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999/");
    }


    @Test
    void shouldTestFormSuccess() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Василий Васильков");
        phone.$("[data-test-id=phone] input").setValue("+79100000000");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id=\"order-success\"]").shouldHave(Condition.text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFormNotClickCheckbox() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Василий Васильков");
        phone.$("[data-test-id=phone] input").setValue("+79100000000");
        SelenideElement button = $("[class=\"button__content\"]");
        button.click();
        $("[data-test-id=\"agreement\"]").shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldGetMessageIfNameIsNumber() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("123456");
        phone.$("[data-test-id=phone] input").setValue("+79100000000");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetMessageIfNameIsNull() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        phone.$("[data-test-id=phone] input").setValue("+79100000000");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetMessageIfNameHasSymbols() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Вася%");
        phone.$("[data-test-id=phone] input").setValue("+79100000000");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetMessageIfNameIsNotRussian() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Anna");
        phone.$("[data-test-id=phone] input").setValue("+79100000000");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetMessageIfPhoneIsNull() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        name.$("[data-test-id=name] input").setValue("Васильков Василий");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetMessageIfPhoneHasNotPlus() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Васильков Василий");
        phone.$("[data-test-id=phone] input").setValue("79100000000");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetMessageIfPhoneHasEnglishLetters() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Васильков Василий");
        phone.$("[data-test-id=phone] input").setValue("qwertyuiopas");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetMessageIfPhoneHasRussianLetters() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Васильков Василий");
        phone.$("[data-test-id=phone] input").setValue("йцукенгшщзх");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetMessageIfPhoneIsOneNumber() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Васильков Василий");
        phone.$("[data-test-id=phone] input").setValue("+7");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetMessageIfPhoneIsLong() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Васильков Василий");
        phone.$("[data-test-id=phone] input").setValue("+712345678987");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetMessageIfPhoneIsShorter() {
        SelenideElement name = $("[data-test-id=\"name\"]");
        SelenideElement phone = $("[data-test-id=\"phone\"]");
        name.$("[data-test-id=name] input").setValue("Васильков Василий");
        phone.$("[data-test-id=phone] input").setValue("+7123456789");
        SelenideElement checkbox = $("[data-test-id=\"agreement\"]");
        SelenideElement button = $("[class=\"button__content\"]");
        checkbox.click();
        button.click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}
