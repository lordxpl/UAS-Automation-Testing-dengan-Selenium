package com.praktikum.uas.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.praktikum.uas.automation.base.BasePage;

public class SignUpPage extends BasePage {

    // Elemen-elemen pada modal Sign Up menggunakan PageFactory
    @FindBy(id = "sign-username")
    private WebElement sUsername;

    @FindBy(id = "sign-password")
    private WebElement sPassword;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signUpBtn;

    // Constructor: Menghubungkan driver dan inisialisasi elemen menggunakan PageFactory
    public SignUpPage(WebDriver driver) {
        super(driver); // Mengirim driver ke BasePage agar explicit wait bisa digunakan
        PageFactory.initElements(driver, this);
    }

    /**
     * Method ini saya gunakan untuk menjalankan proses pendaftaran user baru.
     * Saya menggunakan method type() dan click() dari BasePage yang sudah
     * memiliki fitur Explicit Wait agar prosesnya lebih stabil.
     */
    public void signUp(String un, String pwd) {
        type(sUsername, un);
        type(sPassword, pwd);

        // Melakukan klik pada tombol pendaftaran
        click(signUpBtn);
    }
}