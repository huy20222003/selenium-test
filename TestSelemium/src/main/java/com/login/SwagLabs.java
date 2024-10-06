package com.login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLabs {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");

            driver.findElement(By.id("login-button")).click();

            Thread.sleep(2000);

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals("https://www.saucedemo.com/inventory.html")) {
                System.out.println("Login successful. Redirected to main page.");
            } else {
                System.out.println("Login failed. Current URL: " + currentUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}