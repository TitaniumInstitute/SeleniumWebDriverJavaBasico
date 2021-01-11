package com.ti.ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManipulacionVentana {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        try{
            driver = new ChromeDriver();
            driver.get(demoSite);

            getSizeAndPosition();

            setSizeAndPosition();

            getSizeAndPosition();

            wait(2);

            driver.manage().window().maximize();

            wait(2);

            driver.manage().window().minimize();

            wait(2);

            driver.manage().window().fullscreen();

            wait(2);

        }catch (WebDriverException we){
            System.err.println(String.format("Problema al iniciar el driver: %s", we.getMessage()));
        }finally {
            driver.quit();
        }
    }

    static void getSizeAndPosition(){
        Dimension size = driver.manage().window().getSize();
        System.out.println(String.format("La altura de la ventana es de: %d pixeles", size.getHeight()));
        System.out.println(String.format("El ancho de la ventana es: %d pixeles", size.getWidth()));

        Point position = driver.manage().window().getPosition();

        System.out.println(String.format("La posición en X de la ventana es: %d pixeles", position.getX()));
        System.out.println(String.format("La posición en Y de la ventana es: %d pixeles", position.getY()));
    }

    static void setSizeAndPosition(){
        //Donde 1024 es el ancho y 768 es la altura
        driver.manage().window().setSize(new Dimension(1024, 768));

        //Mover la ventana a la parte superior izquierda del monitor
        driver.manage().window().setPosition(new Point(0,0));
    }

    static void wait(int seg){
        try {
            Thread.sleep(seg*1000);
        } catch (InterruptedException e) {
            System.err.println("Falla en el sleeper!");
        }
    }
}
