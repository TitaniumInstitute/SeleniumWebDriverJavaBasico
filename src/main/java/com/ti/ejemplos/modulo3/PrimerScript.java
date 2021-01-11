package com.ti.ejemplos.modulo3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrimerScript {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    static String actResult = "";
    static String expResult = "TI Demo Site";

    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C:/Users/Dell/drivers/chromedriver.exe");

        //abrir el navegador
        driver = new ChromeDriver();

        //navegar al demo site
        driver.get(demoSite);

        actResult = driver.getTitle();

        //imprimir el resultado de un operador ternario
        System.out.println(actResult.contains(expResult)?"Prueba Superada "+actResult:"Prueba Falló");

        driver.quit();

    }
}
