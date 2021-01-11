package com.ti.ejemplos.modulo4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

public class RelativeLocators {
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

        //driver.findElement(By.id("user_login")).sendKeys(userName);
        driver.findElement(withTagName("input")
                .above(driver.findElement(By.name("pwd"))))
                .sendKeys(userName);

        //driver.findElement(By.name("pwd")).sendKeys(password);
        driver.findElement(withTagName("input")
                .below(By.id("user_login")))
                .sendKeys(password);

        //driver.findElement(By.cssSelector("#rememberme")).click();
        driver.findElement(withTagName("input")
                .toLeftOf(driver.findElement(By.xpath("//input[contains(@value,'Log In')]"))))
                .click();

        driver.findElement(By.xpath("//input[contains(@value,'Log In')]")).click();

        actResult = driver.findElement(By.className("wpsp-schoolname")).getText();
        Thread.sleep(2000);

        System.out.println(actResult.equals(expResult)?"Prueba Superada "+actResult:"Prueba Falló");

        driver.quit();
    }
}
