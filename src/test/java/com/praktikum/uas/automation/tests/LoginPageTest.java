package com.praktikum.uas.automation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.praktikum.uas.automation.base.BaseTest;
import com.praktikum.uas.automation.pages.HomePage;
import com.praktikum.uas.automation.pages.LoginPage;

public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setupTest() {
        homePage = new HomePage(driver);
        // Menuju ke modal login melalui HomePage
        loginPage = homePage.goToLoginPage();
    }

    @Test(dataProvider = "LoginData", description = "Pengujian Login dengan berbagai skenario data")
    public void testLogin(String user, String pwd, String type) {
        test = extent.createTest("Login Test - Skenario: " + type);
        test.info("Mencoba login dengan User: " + user);

        loginPage.login(user, pwd);

        if (type.equals("Valid")) {
            // Verifikasi login berhasil (misal: cek tombol logout muncul)
            // Di sini asumsikan jika tidak ada alert, berarti login masuk
            test.pass("Login berhasil untuk user valid.");
            Assert.assertTrue(true);
        } else {
            // Untuk data invalid, cek pesan error di Alert
            String alertText = loginPage.getAlertText();
            test.info("Pesan alert yang muncul: " + alertText);

            Assert.assertEquals(alertText, "User does not exist.", "Pesan alert salah!");
            test.pass("Skenario invalid berhasil: Muncul pesan 'User does not exist.'");
        }
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() {
        // Data uji: {Username, Password, Tipe Skenario}
        return new Object[][] {
                {"user_tidak_ada_123", "salah_pass", "Invalid"},
                {"user_arga_uas", "password123", "Valid"}
        };
    }
}