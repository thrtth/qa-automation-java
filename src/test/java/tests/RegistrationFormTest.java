package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormTest extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");
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
        $(".modal-body").$(byText("Student Name")).parent().shouldHave(text("Nick Testov"));
        $(".modal-body").$(byText("Student Email")).parent().shouldHave(text("Testov@test.test"));
        $(".modal-body").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".modal-body").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".modal-body").$(byText("Date of Birth")).parent().shouldHave(text("22 October,2000"));
        $(".modal-body").$(byText("Subjects")).parent().shouldHave(text("Arts"));
        $(".modal-body").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".modal-body").$(byText("Picture")).parent().shouldHave(text("Bug.png"));
        $(".modal-body").$(byText("Address")).parent().shouldHave(text("Text text text"));
        $(".modal-body").$(byText("State and City")).parent().shouldHave(text("Haryana Panipat"));
    }

    @Test
    void onlyFillRequiredFieldsTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Nick");
        $("#lastName").setValue("Testov");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890").pressEnter();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").$(byText("Student Name")).parent().shouldHave(text("Nick Testov"));
        $(".modal-body").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".modal-body").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
    }

    @Test
    void emptyFormSubmitTest() {
        open("/automation-practice-form");
        $("#submit").click();

        $(".modal-content").shouldNot(Condition.exist);
        $(byText("Thanks for submitting the form")).shouldNotBe(Condition.visible);
    }

    @Test
    void firstNameMissingTest() {
        open("/automation-practice-form");
        $("#lastName").setValue("Testov");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890").pressEnter();

        $(".modal-content").shouldNot(Condition.exist);
        $(byText("Thanks for submitting the form")).shouldNotBe(Condition.visible);
    }

    @Test
    void lastNameMissingTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Nick");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890").pressEnter();

        $(".modal-content").shouldNot(Condition.exist);
        $(byText("Thanks for submitting the form")).shouldNotBe(Condition.visible);
    }

    @Test
    void incorrectMobileNumberTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Nick");
        $("#lastName").setValue("Testov");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567").pressEnter();

        $(".modal-content").shouldNot(Condition.exist);
        $(byText("Thanks for submitting the form")).shouldNotBe(Condition.visible);
    }
}
