import java.time.Duration;

import Entities.RegistrationInfo;
import Utils.DataGenerator;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;

/**
 * @author Siumbel
 */
public class DeliveryTest {

  @BeforeEach
  void setup() {
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

    $("//input[@placeholder='Город']").setValue(registrationInfo.getCity());
    $("//input[@type='date']").setValue(firstMeetingDate);
    $("//input[@name='name']").setValue(registrationInfo.getName());
    $("//input[@name='phone']").setValue(registrationInfo.getPhone());
    $("//span[@class='checkbox__box']").click();
    $("//span[@class='button__content']").click();
    $("//div[@data-test-id='success-notification']").shouldHave(
            Condition.text("Встреча успешно забронирована на " + firstMeetingDate),
            Duration.ofSeconds(15)
        )
        .shouldBe(Condition.visible);

    $("//input[@type='date']").setValue(secondMeetingDate);

    $("//div[@data-test-id='success-notification']").shouldHave(
        Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

    $("//button/span[contains(.,'Перепланировать')]").click();

    $("//div[@data-test-id='success-notification']").shouldHave(
            Condition.text("Встреча успешно забронирована на " + secondMeetingDate),
            Duration.ofSeconds(15)
        )
        .shouldBe(Condition.visible);
  }
}
