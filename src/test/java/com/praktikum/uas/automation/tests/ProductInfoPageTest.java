package com.praktikum.uas.automation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.praktikum.uas.automation.base.BaseTest;
import com.praktikum.uas.automation.pages.HomePage;
import com.praktikum.uas.automation.pages.ProductInfoPage;
import com.praktikum.uas.automation.pages.CartPage;

public class ProductInfoPageTest extends BaseTest {

    private ProductInfoPage productInfoPage;
    private HomePage homePage;
    private CartPage cartPage; // Deklarasi variabel global agar bisa dipakai di semua @Test

    @BeforeMethod
    public void setupTest() {
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver); //Inisialisasi cartPage
    }

    @Test(priority = 1, groups = {"ProductSelection"})
    public void testAddPhoneToCart() {
        test = extent.createTest("Tambah Produk HP ke Keranjang");

        homePage.selectPhoneProduct();
        productInfoPage = new ProductInfoPage(driver);

        test.info("Klik tombol Add to Cart");
        productInfoPage.addProductToCart();

        String alertText = productInfoPage.getAlertText();
        test.info("Pesan alert: " + alertText);
        Assert.assertEquals(alertText, "Product added", "Pesan alert tidak sesuai!");

        // Navigasi ke keranjang untuk memastikan item masuk
        cartPage.goToCart();
        test.pass("Produk HP berhasil ditambahkan dan navigasi ke Keranjang sukses");
    }

    @Test(priority = 3, groups = {"ProductSelection"})
    public void testAddLaptopToCart() {
        test = extent.createTest("Tambah Produk Laptop ke Keranjang");
        homePage.selectLaptopProduct();
        productInfoPage = new ProductInfoPage(driver);
        productInfoPage.addProductToCart();

        String alertText = productInfoPage.getAlertText();
        Assert.assertEquals(alertText, "Product added");
        test.pass("Produk Laptop berhasil ditambahkan");
    }

    @Test(priority = 4, groups = {"ProductSelection"})
    public void testAddMonitorToCart() {
        test = extent.createTest("Tambah Produk Monitor ke Keranjang");
        homePage.selectMonitorProduct();
        productInfoPage = new ProductInfoPage(driver);
        productInfoPage.addProductToCart();

        String alertText = productInfoPage.getAlertText();
        Assert.assertEquals(alertText, "Product added");
        test.pass("Produk Monitor berhasil ditambahkan");
    }
}