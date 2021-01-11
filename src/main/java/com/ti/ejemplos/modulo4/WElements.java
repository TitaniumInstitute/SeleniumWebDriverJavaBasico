package com.ti.ejemplos.modulo4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WElements {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    static String actResult = "";
    static String expResult = "Titanium School";
    static String userName = "admin";
    static String password = "G3-ySzY%";

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(demoSite);

        WebElement txtUserName = driver.findElement(By.id("user_login"));
        txtUserName.clear();
        txtUserName.sendKeys(userName);

        WebElement txtPassword = driver.findElement(By.name("pwd"));
        txtPassword.clear();
        txtPassword.sendKeys(password);

        WebElement chkRememberMe = driver.findElement(By.cssSelector("#rememberme"));
        chkRememberMe.click();

        WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log In')]"));
        btnLogin.click();

        WebElement lblTitaniumInstitute = driver.findElement(By.className("wpsp-schoolname"));
        actResult =  lblTitaniumInstitute.getText();

        Thread.sleep(2000);

        System.out.println(actResult.equals(expResult)?"Prueba Superada "+actResult:"Prueba Falló");

        driver.quit();
    }
}
