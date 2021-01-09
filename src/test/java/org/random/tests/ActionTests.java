package org.random.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionTests {

    @Test
    public void dragAndDropTest(){
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://demo.guru99.com/test/drag_drop.html");

        WebElement bankBlock = driver.findElement(By.cssSelector("#credit2 a"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(bankBlock));

        WebElement accountBlock = driver.findElement(By.cssSelector("#bank li"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(bankBlock, accountBlock)
                .pause(10000)
                .perform();

        WebElement debitTable = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("bal3")));

        Assert.assertTrue(debitTable.isDisplayed());

        driver.quit();
    }
}
