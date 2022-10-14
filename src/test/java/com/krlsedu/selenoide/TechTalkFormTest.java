package com.krlsedu.selenoide;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TechTalkFormTest {

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://127.0.0.1:4200/");
    }

    @Test
    public void submit() {
        $("[id='title']").sendKeys("Selenium");
        $("button[id='submit']").click();
        $("input[id='title']").shouldHave(attribute("value", ""));
    }

    @Test
    public void invalidSubmit() {
        $("button[id='submit']").click();
        $("[id='required-title']").shouldBe(visible);
    }

    @Test
    public void reSubmit() {
        $("[id='title']").sendKeys("Selenium");
        $("button[id='submit']").click();
        $("[id='title']").sendKeys("Selenium");
        $("button[id='submit']").click();
        $("[id='already-exists-title']").shouldBe(visible);
    }
}
