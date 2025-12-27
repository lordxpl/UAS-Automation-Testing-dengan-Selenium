package com.praktikum.uas.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.praktikum.uas.automation.base.BasePage;

public class ProductInfoPage extends BasePage {

    // Elemen tombol untuk menambahkan produk ke keranjang
    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartBtn;

    // Inisialisasi driver dan PageFactory untuk elemen di halaman ini
    public ProductInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method ini saya gunakan untuk menambahkan produk yang sedang dilihat ke dalam keranjang.
     * Saya menggunakan method click dari BasePage untuk memastikan elemen sudah siap diklik.
     */
    public void addProductToCart() {
        click(addToCartBtn);
    }

    /**
     * Method tambahan untuk menangani konfirmasi alert setelah klik 'Add to cart'.
     * Saya memanggil fungsi dari BasePage untuk mengambil teks pesan dan menutup alert.
     */
    public String getAlertConfirmation() {
        return getAlertText();
    }
}