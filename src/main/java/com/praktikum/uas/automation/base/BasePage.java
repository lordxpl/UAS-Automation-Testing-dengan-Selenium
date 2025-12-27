package com.praktikum.uas.automation.base;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    // Variabel statis untuk menampung log dari test yang sedang berjalan
    public static ExtentTest extentLog;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method klik untuk WebElement (PageFactory)
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        if (extentLog != null) {
            extentLog.info("Klik elemen: " + element.toString().split("->")[1]);
        }
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        if (extentLog != null) {
            extentLog.info("Klik locator: " + locator.toString());
        }
    }

    // Method ketik untuk WebElement (PageFactory)
    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
        if (extentLog != null) {
            extentLog.info("Input teks '" + text + "' ke elemen: " + element.toString().split("->")[1]);
        }
    }

    protected void type(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        if (extentLog != null) {
            extentLog.info("Menerima alert: " + text);
        }
        return text;
    }
}