package com.ti.ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SeleniumImplicitWait {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    static String userName = "admin";
    static String password = "G3-ySzY%";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
            driver.get(demoSite);

            WebElement txtUserName = driver.findElement(By.id("user_login"));
            txtUserName.clear();
            txtUserName.sendKeys(userName);

            WebElement txtPassword = driver.findElement(By.name("pwd"));
            txtPassword.clear();
            txtPassword.sendKeys(password);

            WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log In')]"));
            btnLogin.click();

            WebElement spnTeacher = driver.findElement(By.xpath("(//span[contains(text(), 'Teach')])[1]"));
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
