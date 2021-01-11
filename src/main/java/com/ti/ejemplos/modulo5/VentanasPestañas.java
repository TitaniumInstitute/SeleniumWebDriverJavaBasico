package com.ti.ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VentanasPestañas {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    static String userName = "admin";
    static String password = "G3-ySzY%";

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        try{
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);

            // Almacenar el ID de la ventana original
            String originalTab = driver.getWindowHandle();

            // Checar si no exite otra ventana
            System.out.println(driver.getWindowHandles().size() == 1?"Única ventana/tab abierto":"Existe otra ventana/tab abierta");

            login();

            WebElement spnTeacher = driver.findElement(By.xpath("(//span[contains(text(), 'Teach')])[1]"));
            spnTeacher.click();

            WebElement lnkWPSchoolPress = driver.findElement(By.linkText("WPSchoolPress"));
            lnkWPSchoolPress.click();

            //Recorrer hasta encontrar una nueva ID ventana/tab
            for (String windowHandle: driver.getWindowHandles()){
                if(!originalTab.contentEquals(windowHandle)){
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            driver.findElement(By.linkText("Features")).click();
            Thread.sleep(2000);

        }catch (WebDriverException we){
            System.err.println(String.format("Problema al iniciar el driver: %s", we.getMessage()));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            driver.quit();
        }
    }

    static void login(){
        WebElement txtUserName = driver.findElement(By.id("user_login"));
        txtUserName.clear();
        txtUserName.sendKeys(userName);

        WebElement txtPassword = driver.findElement(By.name("pwd"));
        txtPassword.clear();
        txtPassword.sendKeys(password);

        WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log In')]"));
        btnLogin.click();
    }
}
