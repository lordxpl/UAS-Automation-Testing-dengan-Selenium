package com.praktikum.uas.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.praktikum.uas.automation.base.BasePage;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()='Phones']")
    private WebElement phonesCategory;

    @FindBy(xpath = "//a[text()='Laptops']")
    private WebElement laptopsCategory;

    @FindBy(xpath = "//a[text()='Monitors']")
    private WebElement monitorsCategory;

    @FindBy(xpath = "//a[text()='HTC One M9']")
    private WebElement htcMob;

    @FindBy(xpath = "//a[text()='MacBook air']")
    private WebElement macLappy;

    @FindBy(xpath = "//a[text()='ASUS Full HD']")
    private WebElement asusDesktop;

    @FindBy(id = "nava")
    private WebElement logo;

    @FindBy(id = "signin2")
    private WebElement signUpLink;

    @FindBy(id = "login2")
    private WebElement loginLink;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // --- PERBAIKAN NAMA METHOD AGAR SESUAI DENGAN HOMEPAGETEST ---

    // Sebelumnya getHomePageTitle, diganti agar sesuai dengan HomePageTest
    public String validateHomePageTitle() {
        return driver.getTitle();
    }


    // Sebelumnya isLogoVisible, diganti agar sesuai dengan HomePageTest
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    // Sebelumnya goToSignUp, diganti agar sesuai dengan SignUpPageTest dan me-return objek SignUpPage
    public SignUpPage goToSignUpPage() {
        click(signUpLink); // Menggunakan method click() dari BasePage agar ada Explicit Wait
        return new SignUpPage(driver);
    }

    //  menjadi LoginPage
    public LoginPage goToLoginPage() {
        click(loginLink);
        return new LoginPage(driver);
    }

    // --- METHOD PRODUK ---

    public void selectPhoneProduct() {
        click(phonesCategory);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", htcMob);
        click(htcMob);
    }

    public void selectLaptopProduct() {
        click(laptopsCategory);
        click(macLappy);
    }

    public void selectMonitorProduct() {
        click(monitorsCategory);
        click(asusDesktop);
    }
}