package org.example.pages;

import io.qameta.allure.Step;
import org.example.enums.Color;
import org.example.enums.DYLASelectOptions;
import org.example.enums.Drink;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FormFieldsPage {
    public WebDriver driver;
    public FormFieldsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    @FindBy(id = "name-input")
    private WebElement nameInput;

    @FindBy(css = "input[type ='password']")
    private WebElement passwordInput;

    private static final Map<Drink,By> DRINK_MAP= Map.of(
      Drink.WATER,By.id("drink1"),
      Drink.MILK,By.id("drink2"),
      Drink.COFFEE,By.id("drink3"),
      Drink.WINE,By.id("drink4"),
      Drink.CTRL_ALT_DELIGHT,By.id("drink5")
    );

    private static final Map<Color,By> COLOR_MAP = Map.of(
            Color.RED,By.id("color1"),
            Color.BLUE,By.id("color2"),
            Color.YELLOW,By.id("color3"),
            Color.GREEN,By.id("color4"),
            Color.HASH,By.id("color5")
    );

    @FindBy (id="automation")
    private WebElement doYouLikeAutomationSelect;

    private static final Map<DYLASelectOptions ,By> DYLAOptions = Map.of(
            DYLASelectOptions.DEFAULT,By.cssSelector("#automation option[value='default']"),
            DYLASelectOptions.YES,By.cssSelector("#automation option[value='yes']"),
            DYLASelectOptions.NO,By.cssSelector("#automation option[value='no']"),
            DYLASelectOptions.UNDECIDED,By.cssSelector("#automation option[value='undecided']")
            );

    @FindBy (id="email")
    private WebElement emailInput;

    @FindBy (id="message")
    private WebElement messageTextArea;

    @FindBy (xpath = "//Label[text()='Automation tools']/following-sibling::ul/li")
    private List<WebElement> automationToolsList;

    @FindBy (id="feedbackForm")
    private WebElement feedbackForm;

    @Step("Input name")
    public FormFieldsPage inputName(String userName){
        nameInput.sendKeys(userName);
        return this;
    }

    @Step("Input password")
    public FormFieldsPage inputPassword(String userPassword){
        passwordInput.sendKeys(userPassword);
        return this;
    }

    @Step("Select Drinks")
    public FormFieldsPage selectDrinks(Drink... drinks){
        for(Drink drink:drinks){
            driver.findElement(DRINK_MAP.get(drink)).click();
        }
        return this;
    }

    @Step("Select Color")
    public FormFieldsPage selectColor(Color color){
        driver.findElement(COLOR_MAP.get(color)).click();
        return this;
    }

    @Step("Select automation preferences")
    public FormFieldsPage selectDoYouLikeAutomationSelect(DYLASelectOptions option){
        doYouLikeAutomationSelect.click();
       driver.findElement(DYLAOptions.get(option)).click();
        return this;
    }

    @Step("Input email")
    public FormFieldsPage inputEmail(String email){
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Input message")
    public FormFieldsPage inputMessage(String message){
        messageTextArea.sendKeys(message);
        return this;
    }

    @Step("Calculate automation tools list length")
    public int getATLength(){
      return automationToolsList.size();
    }

    @Step("Determine longest automation tool name")
    public String getATLongestToolName(){
     return automationToolsList.stream().map(WebElement::getText)
             .max(Comparator.comparingInt(String::length))
             .orElse("Empty List");
    }

    @Step("Submit form")
    public FormFieldsPage submitForm(){
        feedbackForm.submit();
        return this;
    }

    @Step("Assert alert message")
    public FormFieldsPage assertAlertMessage(String message){
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.alertIsPresent());
        Assertions.assertEquals(message,alert.getText());
        return this;
    }
}
