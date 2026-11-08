package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormTest {

    @Test
    void successfulFillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Nick");
        $("#lastName").setValue("Testov");
        $("#userEmail").setValue("Testov@test.test");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--022").click();
        $("#subjectsInput").click();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/bug.png"));
        $("#currentAddress").setValue("Text text text");
        $("#state input").setValue("Haryana").pressEnter();
        $("#city input").setValue("Panipat").pressEnter();
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(
                Condition.text("Nick Testov"),
                Condition.text("Testov@test.test"),
                Condition.text("Male"),
                Condition.text("1234567890"),
                Condition.text("22 October,2000"),
                Condition.text("Arts"),
                Condition.text("Music"),
                Condition.text("Bug.png"),
                Condition.text("Text text text"),
                Condition.text("Haryana Panipat")
        );
    }
}
