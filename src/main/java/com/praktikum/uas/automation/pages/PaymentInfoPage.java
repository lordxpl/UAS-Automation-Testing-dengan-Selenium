package com.praktikum.uas.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.praktikum.uas.automation.base.BasePage;

public class PaymentInfoPage extends BasePage {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseBtn;

    public PaymentInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Saya sesuaikan namanya menjadi sendDetails agar sinkron dengan PaymentInfoPageTest.
     * Saya isi dengan data dummy default untuk memudahkan pengujian UAS.
     */
    public void sendDetails() {
        type(nameField, "Arga UAS Tester");
        type(countryField, "Indonesia");
        type(cityField, "Banjarnegara");
        type(cardField, "1234567890");
        type(monthField, "December");
        type(yearField, "2025");
    }

    // Nama method disesuaikan dengan yang dipanggil di Test
    public void clickPurchaseBtn() {
        click(purchaseBtn);
    }

    /**
     * Nama method disesuaikan menjadi getSuccessMsg agar sinkron dengan PaymentInfoPageTest.
     */
    public String getSuccessMsg() {
        // Mencari elemen h2 yang berisi teks sukses
        WebElement successElement = driver.findElement(By.xpath("//h2[contains(text(),'Thank you')]"));
        return successElement.getText();
    }
}