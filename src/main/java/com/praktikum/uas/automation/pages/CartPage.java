package com.praktikum.uas.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.praktikum.uas.automation.base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "cartur")
    private WebElement cartMenu;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderBtn;


    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToCart() {
        click(cartMenu);
        // Tunggu hingga tabel keranjang dimuat
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
    }

    public void deleteProduct() {
        // Mencari link Delete yang ada di tabel keranjang
        WebElement deleteLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Delete']")));
        click(deleteLink);

        // Beri jeda agar sistem DemoBlaze memperbarui UI (AJAX)
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public PaymentInfoPage goToPaymentPage() {
        click(placeOrderBtn);
        return new PaymentInfoPage(driver);
    }

    /**
     * FIX: Menggunakan findElements (plural) agar jika kosong tidak melempar Exception */
    public boolean firstItemDisplayed() {
        List<WebElement> items = driver.findElements(By.className("success"));
        return !items.isEmpty() && items.get(0).isDisplayed();
    }

    /**
     * Method tambahan untuk mengecek apakah keranjang kosong tanpa menunggu timeout.
     */
    public boolean isCartEmpty() {
        return driver.findElements(By.xpath("//a[text()='Delete']")).isEmpty();
    }

    public int calculateTableTotalPrice() {
        return 0;
    }

    public int getTotalPriceLabel() {
        return 0;
    }
}