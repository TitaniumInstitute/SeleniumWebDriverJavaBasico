package com.ti.ejemplos.modulo4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {
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

        driver.findElement(By.id("user_login")).sendKeys(userName);
        driver.findElement(By.name("pwd")).sendKeys(password);
        driver.findElement(By.cssSelector("#rememberme")).click();
        driver.findElement(By.xpath("//input[contains(@value,'Log In')]")).click();

        actResult = driver.findElement(By.className("wpsp-schoolname")).getText();
        Thread.sleep(2000);

        System.out.println(actResult.equals(expResult)?"Prueba Superada "+actResult:"Prueba Falló");

        driver.quit();
    }
}
