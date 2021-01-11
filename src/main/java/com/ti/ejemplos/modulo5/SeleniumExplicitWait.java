package com.ti.ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumExplicitWait {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    static String userName = "admin";
    static String password = "G3-ySzY%";
    static WebDriverWait wait;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);

            wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("user_login"))));
            WebElement txtUserName = driver.findElement(By.id("user_login"));
            txtUserName.clear();
            txtUserName.sendKeys(userName);

            WebElement txtPassword = driver.findElement(By.name("pwd"));
            txtPassword.clear();
            txtPassword.sendKeys(password);

            WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log In')]"));
            btnLogin.click();

            WebElement spnTeacher = new WebDriverWait(driver, Duration.ofSeconds(5)).
                    until(driver -> driver.findElement(By.xpath("(//span[contains(text(), 'Teach')])[1]")));

            spnTeacher.click();

        } catch (NoSuchElementException ne) {
            System.err.println("No se encontró el WebElement: " + ne.getMessage());
        }catch (TimeoutException te){
            System.err.println("Tiempo de espera exedido: "+ te.getMessage());
        }catch (WebDriverException we) {
            System.err.println(String.format("Problema al iniciar el driver: %s", we.getMessage()));
        } finally {
            driver.quit();
        }
    }
}
