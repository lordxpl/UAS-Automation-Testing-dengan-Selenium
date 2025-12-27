# UAS Automation Testing - DemoBlaze
Nama : Aliffian Arga Putra Pratama
NPM  : 230209033

Project ini adalah untuk memenuhi (UAS) Praktikum Test Automation yang bertujuan untuk mengotomatisasi pengujian fungsional pada website e-commerce DemoBlaze. Framework ini dibangun menggunakan Java, Selenium WebDriver, dan TestNG dengan arsitektur Page Object Model (POM).

## Tech Stack dan Library
* Bahasa Pemrograman: Java 23
* Automation Tool: Selenium WebDriver (v4.27.0)
* Testing Framework: TestNG
* Reporting: ExtentReports (Spark)
* Build Tool: Maven

## Struktur Project
Project ini mengikuti standar arsitektur Page Object Model (POM):
* src/main/java: Berisi BasePage dan Page Objects (Login, Home, Cart, Product, dll).
* src/test/java: Berisi BaseTest dan kumpulan skenario pengujian (Test Cases).
* target/: Berisi hasil build dan file laporan UAS_Report.html.
* testng.xml: Konfigurasi untuk mengatur urutan eksekusi pengujian.

## Skenario Pengujian
Pengujian mencakup alur End-to-End (E2E) pengguna:
1. User Management: Sign Up dan Login.
2. Product Selection: Memilih berbagai kategori produk (Phone, Laptop, Monitor).
3. Cart Management: Menambah produk ke keranjang dan memvalidasi fungsi Delete (AJAX).
4. Checkout Process: Mengisi form pembayaran dan memvalidasi konfirmasi transaksi.

## Hasil Pengujian (Report)
Laporan pengujian dapat ditemukan di:
`target/UAS_Report.html`

Laporan ini mencakup:
* Status Pass/Fail untuk setiap test case.
* Grafik ringkasan eksekusi.
* Log detail setiap aksi (Klik, Type, Alert Handling).
* Total durasi eksekusi (Sekitar 1 Menit 10 Detik).
