package com.krlsedu.seleniun;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TechTalkFormTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\Ferramentas\\chromedriver.exe");
        var options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://127.0.0.1:4200/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void submit() {
        var inputField = driver.findElement(By.id("title"));
        inputField.sendKeys("Selenium");

        var submitButton = driver.findElement(By.cssSelector("button[id='submit']"));
        submitButton.click();

        var finalInputField = driver.findElement(By.cssSelector("input[id='title']"));
        assertEquals("", finalInputField.getAttribute("value"));
    }

    @Test
    public void invalidSubmit() {
        var submitButton = driver.findElement(By.cssSelector("button[id='submit']"));
        submitButton.click();
        var required = driver.findElement(By.id("required-title"));
        assertTrue(required.isDisplayed());
    }

    @Test
    public void reSubmit() {
        var inputField = driver.findElement(By.id("title"));
        inputField.sendKeys("Selenium");

        var submitButton = driver.findElement(By.cssSelector("button[id='submit']"));
        submitButton.click();

        inputField = driver.findElement(By.id("title"));
        inputField.sendKeys("Selenium");

        submitButton.click();
        var exists = driver.findElement(By.id("already-exists-title"));
        assertTrue(exists.isDisplayed());
    }

}
