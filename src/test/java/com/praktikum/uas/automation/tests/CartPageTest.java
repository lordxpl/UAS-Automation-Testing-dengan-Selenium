package com.praktikum.uas.automation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.praktikum.uas.automation.base.BaseTest;
import com.praktikum.uas.automation.pages.CartPage;
import com.praktikum.uas.automation.pages.HomePage;
import com.praktikum.uas.automation.pages.ProductInfoPage;

public class CartPageTest extends BaseTest {

    private CartPage cartPage;
    private HomePage homePage;
    private ProductInfoPage productInfoPage;

    @BeforeMethod
    public void setupTest() {
        // Inisialisasi semua Page Objects yang dibutuhkan
        cartPage = new CartPage(driver);
        homePage = new HomePage(driver);

        // --- PROSES BELANJA OTOMATIS (Pre-condition) ---
        // Karena browser reset setiap @Test, kita isi keranjang setiap sesi baru dimulai
        test = extent.createTest("Persiapan Data: Menambah Produk ke Keranjang");

        homePage.selectPhoneProduct();
        productInfoPage = new ProductInfoPage(driver);
        productInfoPage.addProductToCart();

        // Ambil alert dan tutup (OK)
        productInfoPage.getAlertText();

        // Navigasi ke halaman keranjang
        cartPage.goToCart();
        test.info("Persiapan selesai: Keranjang sudah terisi produk.");
    }

    @Test(priority = 1, description = "Verifikasi produk tampil di tabel keranjang")
    public void testProductVisibilityInCart() {
        test = extent.createTest("Verifikasi Produk di Keranjang");

        boolean isPresent = cartPage.firstItemDisplayed();
        Assert.assertTrue(isPresent, "Produk tidak muncul di keranjang padahal sudah belanja!");
        test.pass("Produk berhasil terdeteksi di tabel keranjang.");
    }

    @Test(priority = 2, description = "Verifikasi fungsi hapus produk (Delete)")
    public void testDeleteProduct() {
        test = extent.createTest("Hapus Produk dari Keranjang");

        // 1. Pastikan produk ada (hasil dari @BeforeMethod)
        Assert.assertTrue(cartPage.firstItemDisplayed(), "Tidak ada produk yang bisa dihapus!");

        // 2. Klik Delete
        test.info("Mengklik tombol Delete pada produk pertama...");
        cartPage.deleteProduct();

        // 3. Validasi: Seharusnya sekarang keranjang kosong
        boolean isStillPresent = cartPage.firstItemDisplayed();
        Assert.assertFalse(isStillPresent, "Produk masih ada di tabel setelah dihapus!");

        test.pass("Produk berhasil dihapus dan tabel menjadi kosong.");
    }
}