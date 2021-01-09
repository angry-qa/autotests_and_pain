package org.random.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import java.util.regex.Pattern;

public class RandomTests {

    static WebDriver driver;

    @Test
    void randomNumbersAreNotRepeatedTest() {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver");

        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://www.random.org");

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("#homepage-generator iframe")));

        driver.findElement(By.id("true-random-integer-generator-button")).click();

        wait.until(ExpectedConditions.textMatches(By.id("true-random-integer-generator-result"), Pattern.compile("^(?!\\s*$).+")));

        String firstRandomNumber = driver.findElement(By.id("true-random-integer-generator-result")).getText();

        Assert.assertTrue(firstRandomNumber != null);

        driver.findElement(By.id("true-random-integer-generator-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='true-random-integer-generator-result']/img")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[@id='true-random-integer-generator-result']/img")));

        String secondRandomNumber = driver.findElement(By.id("true-random-integer-generator-result")).getText();

        Assert.assertNotEquals(firstRandomNumber, secondRandomNumber);
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }
}
