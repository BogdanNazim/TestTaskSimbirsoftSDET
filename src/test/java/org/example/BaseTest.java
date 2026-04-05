package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(AllureJunit5.class)
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(getPageUrl());
        initPageObjects();
    }

    @AfterEach
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

    protected String getPageUrl(){
        return null;
    }

    protected void initPageObjects(){
    }
}
