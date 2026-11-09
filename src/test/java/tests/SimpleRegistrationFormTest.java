package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleRegistrationFormTest extends TestBase{

    @Test
    void successfulRequiredFormTest() {
        open("/text-box");
        $("#userName").setValue("Nick Testov");
        $("#userEmail").setValue("nicktestov@test.com");
        $("#submit").click();

        $("#name").shouldHave(text("Nick Testov"));
        $("#email").shouldHave(text("nicktestov@test.com"));
    }

    @Test
    void emptyFormTest() {
        open("/text-box");
        $("#submit").click();

        $("#output #name").shouldNot(Condition.exist);
        $("#output #email").shouldNot(Condition.exist);
        $("#output #currentAddress").shouldNot(Condition.exist);
        $("#output #permanentAddress").shouldNot(Condition.exist);
    }
}
