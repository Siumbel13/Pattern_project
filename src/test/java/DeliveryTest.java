import java.time.Duration;

import Entities.RegistrationInfo;
import Utils.DataGenerator;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;

/**
 * @author Siumbel
 */
public class DeliveryTest {

  @BeforeEach
  void setup() {
    Configuration.holdBrowserOpen = true;
    open("http://localhost:9999");
  }

  @Test
  @DisplayName("Should successful plan and replan meeting")
  void shouldSuccessfulPlanAndReplanMeeting() {

    RegistrationInfo registrationInfo = DataGenerator.Registration.generateInfo("ru");
    var daysToAddForFirstMeeting = 4;
    var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
    var daysToAddForSecondMeeting = 7;
    var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

    $("[placeholder='Город']").setValue(registrationInfo.getCity());
    $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);;
    $("[data-test-id='date'] input").setValue(firstMeetingDate);
    $("[name='name']").setValue(registrationInfo.getName());
    $("[name='phone']").setValue(registrationInfo.getPhone());
    $("[class='checkbox__box']").click();
    $("[class='button__content']").click();
    $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
    $x("//div[@data-test-id='success-notification']//div[@class='notification__content']")
        .shouldHave(Condition.text("Встреча успешно забронирована на " + firstMeetingDate), Duration.ofSeconds(15))
        .shouldBe(Condition.visible);


   /*

    $("[type='date']").setValue(secondMeetingDate);

    $("[class='notification__content']").shouldHave(
        Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

    $(withText("Перепланировать")).click();

    $("[data-test-id='success-notification'] input").shouldHave(
            Condition.text("Встреча успешно забронирована на " + secondMeetingDate),
            Duration.ofSeconds(15)
        )
        .shouldBe(visible);

    */
  }
}
