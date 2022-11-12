package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import Entities.RegistrationInfo;
import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

/**
 * @author Siumbel
 */

@UtilityClass
public class DataGenerator {

  @UtilityClass
  public static class Registration {

    public static String[] selectCity = {
        "Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик"
        , "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск",
        "Якутск"
        , "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул",
        "Чита"
        , "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток",
        "Ставрополь"
        , "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир",
        "Волгоград"
        , "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров",
        "Кострома"
        , "Курган", "Курск", "Гатчина", "Липецк", "Магадан", "Красногорск", "Мурманск",
        "Нижний Новгород"
        , "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Псков",
        "Ростов-на-Дону"
        , "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов",
        "Тверь"
        , "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск", "Ярославль", "Москва",
        "Санкт-Петербург"
        , "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"
    };

    public static RegistrationInfo generateInfo(String local) {
      Faker faker = new Faker(new Locale(local));
      int n = (int) Math.floor(Math.random() * selectCity.length);
      return new RegistrationInfo(faker.name().firstName() + " " + faker.name().lastName(),
          faker.phoneNumber().phoneNumber(), selectCity[n]
      );
    }
  }

  public String generateDate(int days) {
    return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
  }
}
