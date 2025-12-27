package com.praktikum.uas.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.praktikum.uas.automation.base.BasePage;

public class LoginPage extends BasePage {

    // Elemen-elemen pada modal Login menggunakan PageFactory
    @FindBy(id = "loginusername")
    private WebElement loginUserName;

    @FindBy(id = "loginpassword")
    private WebElement loginPassword;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;

    // Saya menggunakan locator preceding-sibling untuk menargetkan tombol 'Close' di samping 'Log in'
    @FindBy(xpath = "//button[text()='Log in']//preceding-sibling::button")
    private WebElement closeButton;

    // Inisialisasi driver dan elemen halaman
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method untuk menangani alur Login.
     * Di sini saya mengganti Thread.sleep dengan Explicit Wait dari BasePage
     * agar eksekusi lebih stabil dan efisien.
     */
    public void login(String un, String pwd) {
        // Membersihkan field terlebih dahulu untuk memastikan input bersih
        loginUserName.clear();
        type(loginUserName, un);

        loginPassword.clear();
        type(loginPassword, pwd);

        // Klik tombol login menggunakan method click dari BasePage
        click(loginButton);
    }


    // Method buatan saya untuk menutup modal login tanpa melakukan login
    public void clickOnClose() {
        click(closeButton);
    }


}