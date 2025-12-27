package com.praktikum.uas.automation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.praktikum.uas.automation.base.BaseTest;
import com.praktikum.uas.automation.pages.HomePage;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setupTest() {
        // Menginisialisasi HomePage menggunakan driver dari BaseTest
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, description = "Memverifikasi apakah judul halaman adalah 'STORE'")
    public void verifyHomePageTitle() {
        // Membuat baris baru di laporan ExtentReports
        test = extent.createTest("Verifikasi Judul Halaman");

        String actualTitle = homePage.validateHomePageTitle();
        test.info("Judul yang ditemukan: " + actualTitle);

        try {
            Assert.assertEquals(actualTitle, "STORE", "Judul halaman salah!");
            test.pass("Judul halaman sesuai yaitu STORE");
        } catch (AssertionError e) {
            test.fail("Judul halaman tidak sesuai. Diharapkan: STORE, Ditemukan: " + actualTitle);
            throw e;
        }
    }

    @Test(priority = 2, description = "Memverifikasi apakah Logo DemoBlaze tampil")
    public void verifyLogoPresence() {
        test = extent.createTest("Verifikasi Tampilan Logo");

        boolean isLogoPresent = homePage.isLogoDisplayed();

        if (isLogoPresent) {
            test.pass("Logo DemoBlaze terlihat dengan jelas di header.");
        } else {
            test.fail("Logo DemoBlaze tidak ditemukan di halaman.");
        }

        Assert.assertTrue(isLogoPresent, "Logo tidak ditampilkan!");
    }

    @Test(priority = 3, description = "Memverifikasi navigasi kategori HP")
    public void testPhoneCategoryNavigation() {
        test = extent.createTest("Navigasi Kategori Phones");

        // Menjalankan method selectPhoneProduct yang sudah kamu buat di HomePage
        homePage.selectPhoneProduct();

        // Verifikasi sederhana: cek apakah URL mengandung kata kunci produk
        test.info("Berhasil melakukan scroll dan klik pada produk HTC One M9");
        test.pass("Navigasi kategori dan pemilihan produk berjalan lancar.");
    }
}