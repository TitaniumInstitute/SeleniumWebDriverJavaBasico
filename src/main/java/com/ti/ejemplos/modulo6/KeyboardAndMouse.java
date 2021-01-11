package com.ti.ejemplos.modulo6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class KeyboardAndMouse {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);

            WebElement txtUserName = driver.findElement(By.id("user_login"));
            WebElement btnLogIn = driver.findElement(By.xpath("//input[contains(@value,'Log')]"));

            Actions builider = new Actions(driver);

            Action actionsToLogin = builider
                    .moveToElement(txtUserName)
                    .click()
                    .sendKeys("admin")
                    .sendKeys(Keys.TAB)
                    .keyDown(Keys.SHIFT)
                    .sendKeys("g")
                    .keyUp(Keys.SHIFT)
                    .sendKeys("3-ySz")
                    .keyDown(Keys.SHIFT)
                    .sendKeys("y")
                    .keyUp(Keys.SHIFT)
                    .sendKeys("%")
                    .moveToElement(btnLogIn)
                    .click()
                    .build();

            actionsToLogin.perform();

            driver.findElement(By.className("wpsp-userPic")).click();

            Thread.sleep(4000);

            WebElement lnkSignOut = driver.findElement(By.linkText("Sign out"));
            builider.click().build().perform();

            System.out.println("Prueba es Exitosa!");


        } catch (NoSuchElementException ne) {
            System.err.println("No se encontró el WebElement: " + ne.getMessage());
        } catch (WebDriverException we) {
            System.err.println(String.format("Problema al iniciar el driver: %s", we.getMessage()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
