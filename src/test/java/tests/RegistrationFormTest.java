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
        $(".modal-body").$(".table").$(byText("Student Name"))
                .sibling(0).shouldHave(Condition.text("Nick Testov"));
        $(".modal-body").$(".table").$(byText("Student Email"))
                .sibling(0).shouldHave(Condition.text("Testov@test.test"));
        $(".modal-body").$(".table").$(byText("Gender"))
                .sibling(0).shouldHave(Condition.text("Male"));
        $(".modal-body").$(".table").$(byText("Mobile"))
                .sibling(0).shouldHave(Condition.text("1234567890"));
        $(".modal-body").$(".table").$(byText("Date of Birth"))
                .sibling(0).shouldHave(Condition.text("22 October,2000"));
        $(".modal-body").$(".table").$(byText("Subjects"))
                .sibling(0).shouldHave(Condition.text("Arts"));
        $(".modal-body").$(".table").$(byText("Hobbies"))
                .sibling(0).shouldHave(Condition.text("Music"));
        $(".modal-body").$(".table").$(byText("Picture"))
                .sibling(0).shouldHave(Condition.text("Bug.png"));
        $(".modal-body").$(".table").$(byText("Address"))
                .sibling(0).shouldHave(Condition.text("Text text text"));
        $(".modal-body").$(".table").$(byText("State and City"))
                .sibling(0).shouldHave(Condition.text("Haryana Panipat"));
    }
}
