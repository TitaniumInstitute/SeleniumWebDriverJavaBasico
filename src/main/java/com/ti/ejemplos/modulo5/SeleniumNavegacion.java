package com.ti.ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumNavegacion {
    static WebDriver driver;
    static String googleSite = "https://www.google.com";
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();

        try {
            driver = new ChromeDriver();
            driver.navigate().to(googleSite);

            System.out.println(String.format("El título de la página es: %s", driver.getTitle()));
            System.out.println(String.format("La url es: %s", driver.getCurrentUrl()));

            wait(2);

            driver.get(demoSite);

            System.out.println("****************************************************************");

            System.out.println(String.format("El título de la página es: %s", driver.getTitle()));
            System.out.println(String.format("La url es: %s", driver.getCurrentUrl()));
            System.out.println(String.format("El código de la página es: %s", driver.getPageSource()));

            wait(2);

            driver.navigate().back();

            wait(2);

            driver.navigate().forward();

            wait(2);

            driver.navigate().refresh();

            wait(2);
        }catch (WebDriverException we){
            System.err.println(String.format("Problema al iniciar el driver: %s", we.getMessage()));
        }finally {
            driver.quit();
        }
    }

    static void wait(int seg){
        try {
            Thread.sleep(seg*1000);
        } catch (InterruptedException e) {
            System.err.println("Falla en el sleeper!");
        }
    }
}
