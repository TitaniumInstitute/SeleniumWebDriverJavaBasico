package com.ti.ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumExceptions {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    static String userName = "admin";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(demoSite);

        try{
            driver.findElement(By.id("user_log")).sendKeys(userName);
            System.out.println("Prueba exitosa!");
        }catch (NoSuchElementException ne){
            System.err.println("Prueba Falló: El elemento, no se encontró: " + ne.getMessage());
        }finally {
            driver.quit();
        }
    }
}
