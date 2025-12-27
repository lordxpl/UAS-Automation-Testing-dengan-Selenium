package com.praktikum.uas.automation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.praktikum.uas.automation.base.BaseTest;
import com.praktikum.uas.automation.pages.CartPage;
import com.praktikum.uas.automation.pages.PaymentInfoPage;
import com.praktikum.uas.automation.pages.HomePage;
import com.praktikum.uas.automation.pages.ProductInfoPage;

public class PaymentInfoPageTest extends BaseTest {

    private PaymentInfoPage paymentInfoPage;
    private CartPage cartPage;
    private HomePage homePage;
    private ProductInfoPage productInfoPage;

    @BeforeMethod
    public void setupTest() {
        cartPage = new CartPage(driver);
        homePage = new HomePage(driver);

        // --- PRE-CONDITION: Pastikan ada barang di Cart agar tombol 'Place Order' muncul ---
        homePage.selectPhoneProduct();
        productInfoPage = new ProductInfoPage(driver);
        productInfoPage.addProductToCart();
        productInfoPage.getAlertText(); // Close alert

        // Masuk ke halaman Cart
        cartPage.goToCart();
    }

    @Test(priority = 1)
    public void verifyPurchaseWithoutInfo() {
        test = extent.createTest("Purchase Tanpa Data - Negative Test");

        // Klik Place Order untuk buka modal payment
        paymentInfoPage = cartPage.goToPaymentPage();
        test.info("Membuka modal pembayaran dan klik Purchase tanpa data");

        paymentInfoPage.clickPurchaseBtn();

        // Ambil alert browser
        String alertText = paymentInfoPage.getAlertText();
        test.info("Pesan alert: " + alertText);

        Assert.assertEquals(alertText, "Please fill out Name and Creditcard.");
        test.pass("Peringatan muncul karena form kosong.");
    }

    @Test(priority = 2)
    public void verifyPurchaseWithInfo() {
        test = extent.createTest("Purchase Dengan Data Lengkap - Positive Test");

        // Buka modal payment
        paymentInfoPage = cartPage.goToPaymentPage();

        test.info("Mengisi detail pembayaran secara dinamis");
        paymentInfoPage.sendDetails(); // Pastikan method ini ada di PaymentInfoPage

        paymentInfoPage.clickPurchaseBtn();

        // Validasi teks sukses di modal
        String successMsg = paymentInfoPage.getSuccessMsg();
        test.info("Validasi pesan: " + successMsg);

        // DemoBlaze biasanya menampilkan teks ini di elemen h2
        Assert.assertTrue(successMsg.contains("Thank you for your purchase"), "Pesan sukses tidak sesuai!");
        test.pass("Transaksi berhasil diselesaikan.");
    }
}