package org.example.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.BaseTest;
import org.example.pages.FormFieldsPage;
import org.junit.jupiter.api.Test;

@Feature("Form Fields")
public class FormFieldsPageTests extends BaseTest {

    private FormFieldsPage formFieldsPage;

    @Override
    protected String getPageUrl(){
        return "https://practice-automation.com/form-fields/";
    }
    @Override
    protected void initPageObjects(){
        formFieldsPage = new FormFieldsPage(driver);
    }

    @Story("Send valid form")
    @Test
    public void sendForm() {
        String longestName = formFieldsPage.getATLongestToolName();
        String toolsCount = String.valueOf(formFieldsPage.getATLength());
        formFieldsPage.inputName("userName")
                .inputPassword("userPassword")
                .selectDrinks(org.example.enums.Drink.MILK, org.example.enums.Drink.COFFEE)
                .selectColor(org.example.enums.Color.YELLOW)
                .selectDoYouLikeAutomationSelect(org.example.enums.DYLASelectOptions.YES)
                .inputEmail("test@form.com")
                .inputMessage(toolsCount +" "+ longestName)
                .submitForm()
                .assertAlertMessage("Message received!");
    }
}
