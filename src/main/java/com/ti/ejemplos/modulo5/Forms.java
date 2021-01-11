package com.ti.ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Forms {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    static String userName = "admin";
    static String password = "G3-ySzY%";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);

            login();

            createNewTeacher();

        } catch (NoSuchElementException ne) {
            System.err.println("No se encontró el WebElement: " + ne.getMessage());
        } catch (TimeoutException te) {
            System.err.println("Tiempo de espera excedido: " + te.getMessage());
        } catch (WebDriverException we) {
            System.err.println(String.format("Problema al iniciar el driver: %s", we.getMessage()));
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    static void createNewTeacher() throws InterruptedException {
        WebElement spnTeacher = new WebDriverWait(driver, Duration.ofSeconds(7)).
                until(driver -> driver.findElement(By.xpath("(//span[contains(text(), 'Teach')])[1]")));
        spnTeacher.click();

        driver.findElement(By.linkText("Create New")).click();

        personalDetails();

        accountInfo();

        Thread.sleep(2000);
    }

    private static void personalDetails() {
        WebElement rdbFemale = new WebDriverWait(driver,Duration.ofSeconds(4))
                .until(driver -> driver.findElement(By.id("Female")));
        rdbFemale.click();

        WebElement txtFirstName = driver.findElement(By.id("firstname"));
        txtFirstName.clear();
        txtFirstName.sendKeys("Loren");

        WebElement txtLastName = driver.findElement(By.id("lastname"));
        txtLastName.clear();
        txtLastName.sendKeys("Swan");

        WebElement txtStreet = driver.findElement(By.name("Address"));
        txtStreet.clear();
        txtStreet.sendKeys("Test Adrress");

        Select drpCountry = new Select(driver.findElement(By.id("Country")));
        drpCountry.selectByVisibleText("Brazil");
    }

    private static void accountInfo() {
        String pass = "T3stPAss.";

        WebElement txtEmail = driver.findElement(By.id("Email"));
        txtEmail.clear();
        txtEmail.sendKeys("test@email.com");

        WebElement txtUserName = driver.findElement(By.id("Username"));
        txtUserName.clear();
        txtUserName.sendKeys("lswan");
        System.out.println(txtEmail.getAttribute("value"));

        WebElement txtPassword = driver.findElement(By.id("Password"));
        txtPassword.clear();
        txtPassword.sendKeys(pass);

        WebElement txtConfirmPass = driver.findElement(By.id("ConfirmPassword"));
        txtConfirmPass.clear();
        txtConfirmPass.sendKeys(pass);

        WebElement txtWorkingHour = driver.findElement(By.id("whours"));
        txtWorkingHour.clear();
        txtWorkingHour.sendKeys("24");
        txtWorkingHour.submit();
    }


}
