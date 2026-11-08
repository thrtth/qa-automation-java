package tests;

import org.junit.jupiter.api.Test;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @Test
    void successfulFillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Nick");
        $("#lastName").setValue("Testov");
        $("#userEmail").setValue("Testov@test.test");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--022").click();
        $("#subjectsInput").click();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#hobbies-checkbox-3").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/bug.png"));
        $("#currentAddress").setValue("Text text text");
        $("#state input").setValue("Haryana").pressEnter();
        $("#city input").setValue("Panipat").pressEnter();
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }
}
