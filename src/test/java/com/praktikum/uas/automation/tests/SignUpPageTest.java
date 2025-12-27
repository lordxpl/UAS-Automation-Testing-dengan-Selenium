package com.praktikum.uas.automation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.praktikum.uas.automation.base.BaseTest;
import com.praktikum.uas.automation.pages.HomePage;
import com.praktikum.uas.automation.pages.SignUpPage;
import java.util.UUID;

public class SignUpPageTest extends BaseTest {

    private SignUpPage signUpPage;
    private HomePage homePage;

    @BeforeMethod
    public void setupTest() {
        // Inisialisasi halaman yang dibutuhkan
        homePage = new HomePage(driver);
        // Saya menavigasi ke modal Sign Up melalui HomePage
        signUpPage = homePage.goToSignUpPage();
    }

    @Test(description = "Skenario pendaftaran user baru dengan data random")
    public void testRegisterUser() {
        // Saya mencatat log awal ke laporan ExtentReports
        test = extent.createTest("Registrasi User Baru");

        // Saya membuat data unik secara otomatis menggunakan UUID agar tidak duplikat
        String randomData = UUID.randomUUID().toString().substring(0, 8);
        String username = "user_" + randomData;
        String password = "Pass_" + randomData;

        test.info("Mendaftarkan user baru: " + username);

        // Eksekusi proses pendaftaran
        signUpPage.signUp(username, password);

        // Saya mengambil teks dari alert konfirmasi menggunakan method di BasePage
        // SignUpPage mewarisi BasePage sehingga bisa mengakses getAlertText()
        String alertText = signUpPage.getAlertText();

        // Validasi pesan sukses
        try {
            Assert.assertEquals(alertText, "Sign up successful.", "Pesan alert tidak sesuai!");
            test.pass("User berhasil dibuat dengan pesan: " + alertText);
        } catch (AssertionError e) {
            test.fail("Gagal membuat user baru. Pesan yang muncul: " + alertText);
            throw e; // Dilempar kembali agar TestNG mencatat kegagalan
        }
    }
}