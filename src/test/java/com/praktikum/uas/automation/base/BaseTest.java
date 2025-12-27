package com.praktikum.uas.automation.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.praktikum.uas.automation.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    // Variable public agar bisa diakses oleh semua class Test
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        // Saya menyiapkan folder target untuk menyimpan laporan HTML UAS
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/UAS_Report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Arga");
        extent.setSystemInfo("Environment", "QA - DemoBlaze");
    }

    @BeforeMethod
    public void setUp() {
        // Saya mengambil pilihan browser dari config.properties
        String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();

        // Timeout saya ambil dari config agar dinamis
        int impWait = Integer.parseInt(ConfigReader.getProperty("implicit_wait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(impWait));

        // Membuka URL aplikasi
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        // Menutup browser setelah satu skenario selesai
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        // Menghasilkan file laporan akhir (WAJIB agar file muncul)
        extent.flush();
    }
}