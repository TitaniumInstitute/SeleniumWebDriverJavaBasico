package com.ti.ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FramesAlerts {
    static WebDriver driver;
    static String demoSite = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt";
    static WebDriverWait wait;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeResult"));
            //driver.switchTo().frame("iframeResult");

            WebElement btnTry = driver.findElement(By.xpath("//button[text()='Try it']"));
            wait.until(ExpectedConditions.elementToBeClickable(btnTry));
            btnTry.click();

            Thread.sleep(1500);

            wait.until(ExpectedConditions.alertIsPresent());
            Alert altWindow = driver.switchTo().alert();
            String alertText = altWindow.getText();
            System.out.println(alertText);
            altWindow.sendKeys("Gilberto S");
            altWindow.accept();

            String demoText = driver.findElement(By.id("demo")).getText();
            System.out.println(demoText.contains("Gilberto") ? demoText : "Prueba Fallida!");

        }catch (NoSuchElementException ne){
            System.err.println("No se encontró el WebElement: "+ ne.getMessage());
        } catch (NoSuchFrameException fe) {
            System.err.println("No se encontró el frame: "+ fe.getMessage());
        }catch (NoAlertPresentException na){
            System.err.println("No se encontró la alerta: "+na.getMessage());
        }catch (TimeoutException te){
            System.err.println("Tiempo de espera excedido: "+ te.getMessage());
        }catch (WebDriverException we) {
            System.err.println(String.format("Problema al iniciar el driver: %s", we.getMessage()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
