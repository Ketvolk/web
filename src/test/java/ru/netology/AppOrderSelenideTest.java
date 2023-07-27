package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

public class AppOrderSelenideTest {

    @Test
    void shouldTestForm () throws InterruptedException {
       open ("http://localhost:9999/");
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

}
